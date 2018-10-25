package com.example.yugandhar_pc.databaseroom.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.example.yugandhar_pc.databaseroom.dao.UserDao
import com.example.yugandhar_pc.databaseroom.entitiy.UserEntity
import java.util.*

//Created Data base
//while adding table or column also we have to upgrade the database version 
@Database(entities = arrayOf(UserEntity::class), version = 3, exportSchema = true)
abstract class UserDataBase : RoomDatabase(){
    abstract fun userDao(): UserDao

    //Like Static Block in java
    companion object {
        private var INSTANCE: UserDataBase? = null
        fun getInstance(context: Context): UserDataBase? {
            if (INSTANCE == null) {
                synchronized(UserDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, UserDataBase::class.java, "user_db") .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
        
    }
//               var migration=OMigration(2,3){}



    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}