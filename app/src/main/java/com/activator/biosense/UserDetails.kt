package com.activator.biosense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class UserDetails (
    @ColumnInfo(name = "Empcodes") val Empcodes: String?,
    @ColumnInfo(name = "Region") val Region: String?,
    @ColumnInfo(name = "Employee_Name") val Employee_Name: String?,
    @ColumnInfo(name = "Territory") val Territory: String?,
    @ColumnInfo(name = "Desgn") val Desgn: String?,
    @ColumnInfo(name = "employee_AM") val employee_AM: String?,
    @ColumnInfo(name = "Am_code") val Am_code: String?,
    @ColumnInfo(name = "AM_Territory") val AM_Territory: String?,
    @ColumnInfo(name = "employee_RBM") val employee_RBM: String?,
    @ColumnInfo(name = "RBM_code") val RBM_code: String?,
    @ColumnInfo(name = "RBM_Territory") val RBM_Territory: String?,
    @ColumnInfo(name = "employee_zbm") val employee_zbm: String?,
    @ColumnInfo(name = "ZBM_code") val ZBM_code: String?,
    @ColumnInfo(name = "ZBM_Territory") val ZBM_Territory: String?,
    @ColumnInfo(name = "employee_SM") val employee_SM: String?,
    @ColumnInfo(name = "SM_code") val SM_code: String?,
    @ColumnInfo(name = "SM_Territory") val SM_Territory: String?,
    @ColumnInfo(name = "Password") val Password: String?,
        )
{
    @PrimaryKey(autoGenerate = true) var user_id: Int =0

}