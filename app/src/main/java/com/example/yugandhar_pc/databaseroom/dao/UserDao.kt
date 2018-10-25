package com.example.yugandhar_pc.databaseroom.dao

import android.arch.persistence.room.*
import com.example.yugandhar_pc.databaseroom.entitiy.UserEntity

@Dao
interface UserDao {
    //    Select User
    @Query("select * from User")
    fun getAll(): List<UserEntity>

    //Insert
    @Insert()
    fun insertUser(userEntity: UserEntity): Long

    //Delete
    @Delete()
    fun deleteUser(userEntity: UserEntity)

    //Where Clause
    @Query("select * from User where id=:id")
    fun getId(id: Int): UserEntity

    //Update
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRow(userEntity: UserEntity):Int

}