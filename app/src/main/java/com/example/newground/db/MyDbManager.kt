package com.example.newground.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager (context: Context) {
    val MyDbHelper = MyDbHelper (context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = MyDbHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)

        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)

    }

    fun readDbData()  : ArrayList<String> {
        val dataList = ArrayList<String>()

        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, null)

        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataText = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }

        }

        return dataList
    }

    fun closeDb() {
        MyDbHelper.close()
    }

}

