package com.example.yugandhar_pc.databaseroom.entitiy

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

//Table Name 
@Entity(tableName = "User")
data class UserEntity(@ColumnInfo(name = "id") @PrimaryKey var id: Int
                      , @ColumnInfo(name = "first_name") var firstName: String
                      , @ColumnInfo(name = "last_name") var lastName: String
                      , @ColumnInfo(name = "age") var age: Int
                      ,@ColumnInfo(name="check")var check:String) {
    //    Secoundry Constructor
    constructor() : this(0, "", "", 0,"")

}