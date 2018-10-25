package com.example.yugandhar_pc.databaseroom

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.yugandhar_pc.databaseroom.R.id.bt_insert
import com.example.yugandhar_pc.databaseroom.database.UserDataBase
import com.example.yugandhar_pc.databaseroom.entitiy.UserEntity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.simpleName
    private lateinit var userDb: UserDataBase
    private lateinit var userEntity: UserEntity
    private lateinit var doAsyncCheck: doAsync
    private lateinit var doAsyncSelectCheck: doAsyncSelect
    private lateinit var doAsyncSelectwhere: doAsyncSelectWhere
    private lateinit var doAsyncupdateRow: doAsyncUpdate
    private var id: Int = 0
    private var userentity: UserEntity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDb = UserDataBase.getInstance(this)!!


//        without findview by id
        bt_insert.setOnClickListener {
            Log.e(TAG, ": check");
            userEntity = UserEntity((Math.random() * 49 + 1).toInt(), "H", "O", 1,"")
            doAsyncCheck = doAsync()
            doAsyncCheck.execute()
        }
        bt_update.setOnClickListener {
            doAsyncSelectCheck = doAsyncSelect()
            doAsyncSelect().execute()
        }
        bt_select_where.setOnClickListener {
            doAsyncSelectwhere = doAsyncSelectWhere()
            doAsyncSelectwhere.execute()
        }
        bt_update_row.setOnClickListener {
            doAsyncupdateRow = doAsyncUpdate()
            doAsyncupdateRow.execute()
        }


    }

    //insert
    inner class doAsync() : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            var rowId = userDb.userDao().insertUser(userEntity)
            Log.e(TAG, "Row id $rowId")
            return null
        }
    }

    //    Select rows
    inner class doAsyncSelect() : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {

            var rowId = userDb.userDao().getAll()
            Log.e(TAG, "Row id ${rowId.size}\n${rowId.get(1).firstName}")
            id = rowId.get(2).id
            return null
        }
    }

    //    Where Clause
    inner class doAsyncSelectWhere() : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {

            var rowId = userDb.userDao().getId(id)
            Log.e(TAG, "Row id where ${rowId.id}\n${rowId}")
            return null
        }
    }

    //    Update the row
    inner class doAsyncUpdate() : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {

            var rowId = userDb.userDao().getId(id)
            rowId.firstName = "Juno"
            var noRowsEffected = userDb.userDao().updateRow(rowId)
            Log.d(TAG, "No of rows update ${noRowsEffected}");
            var getAll = userDb.userDao().getAll()
            for (i in getAll) {
                Log.d(TAG, "Check for updated row ${i.firstName}");
            }

            return null
        }
    }
}
