package com.activator.biosense


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

@Database(entities = [(UserDetails::class)], version = 1, exportSchema = true)
abstract class UserDB : RoomDatabase() {
    abstract fun dao(): DAO
    companion object {
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getInstance(context: Context): UserDB {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = createInstance(context)
                }
                return INSTANCE!!
            }
        }

        private fun createInstance(context: Context):UserDB {
            Log.d("UserDB","Creating DB ")

            return Room.databaseBuilder(context.applicationContext, UserDB::class.java, "DataBase.db")
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable {
                            Log.d("UserDB","Starting thread")
                            prepopulateDb(context, getInstance(context))
                        }).start()
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }


        private fun prepopulateDb(context: Context, db: UserDB) {
            val employeeList = loadJsonArray(context)
            val list = ArrayList<UserDetails>()
            Log.d("UserDB","Pre populating DB ")


            if (employeeList != null) {
                Log.d("UserDB","inserting ${employeeList.length()} records")

                for (i:Int in 0 until  employeeList.length()){
                    val item = employeeList.getJSONObject(i)
                    list.add(UserDetails(item.getString("Empcodes"),item.getString("Region"),
                    item.getString("Employee_Name"),item.getString("Territory"),
                    item.getString("Desgn"),item.getString("employee_AM"),item.getString("Am_code"),
                    item.getString("AM_Territory"),item.getString("employee_RBM"),item.getString("RBM_code"),item.getString("RBM_Territory"),
                        item.getString("employee_zbm"),item.getString("ZBM_code"),item.getString("ZBM_Territory"),
                        item.getString("employee_SM"),item.getString("SM_code"),item.getString("SM_Territory"),item.getString("Password")))
                }
                db.dao().insertAll(list)
            }
            else{
                Log.e("UserDB","Insert failed")
            }
        }

        private fun loadJsonArray(context: Context): JSONArray? {
            val builder = StringBuilder()
            val input = context.resources.openRawResource(R.raw.employee);
            val reader = BufferedReader(InputStreamReader(input))
            Log.d("UserDB","Loading JSON Array")
            var line: String
            try {
                line = reader.readLine()

                while (line != null) {
                    builder.append(line);
                }
                val json = JSONObject(builder.toString());
                return json.getJSONArray("emp_details");

            } catch (exception: Exception) {
                Log.e("UserDB","Exceptions")
                exception.printStackTrace();
            }

            return null;

        }

    }

}