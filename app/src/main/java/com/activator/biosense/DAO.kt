package com.activator.biosense

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: ArrayList<UserDetails>)

    @Query("SELECT * FROM UserDetails WHERE Empcodes=:empLogin AND Password=:password LIMIT 1")
    fun validateUser(empLogin: String, password: String): UserDetails?

}