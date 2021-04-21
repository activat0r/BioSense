package com.activator.biosense

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import kotlin.time.Duration

class MainActivity : AppCompatActivity(){
    lateinit var empCode_layout :TextInputLayout
    lateinit var empPassword_layout :TextInputLayout

    lateinit var empCode_edittext :EditText
    lateinit var empPassword_edittext :EditText

    lateinit var mainProgressBar: ProgressBar
    lateinit var loginProgressBar: ProgressBar

    lateinit var loginButton:Button

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton)
        empPassword_edittext = findViewById(R.id.LoginEmployeePassword)
        empCode_edittext = findViewById(R.id.LoginEmployeeCode)

        val sharedPref = getSharedPreferences("JsonParsed", MODE_PRIVATE)
        val flag = sharedPref.getBoolean("present",false) //checks if the DB is created or not.


        if(!flag){
            //insert into db
                Log.d(TAG,"Creating db")
            UserDB.getInstance(this).openHelper

        }

        loginButton.setOnClickListener(View.OnClickListener {
            Log.d(TAG,"Logging in")
            val item =  UserDB.getInstance(this).dao().validateUser(empCode_edittext.text.toString(),empPassword_edittext.text.toString())

            if(item!=null){
                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
            }

        })


    }
}