package com.gabrielcastro.listadetarefas.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DBHelper(context: Context?) : SQLiteOpenHelper(context, NAME_DB, null, VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        // first install
        val sql = "CREATE TABLE IF NOT EXISTS $TABLE_TODO (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)"
        try {
            sqLiteDatabase.execSQL(sql)
            Log.i("INFO DB", "Create table success!")
        } catch (e: Exception) {
            Log.i("INFO DB", "Create table error. ${e.message}")
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int) {
        // update install
    }

    companion object {
        var VERSION = 1 // increment with new version db code
        var NAME_DB = "DB_TODO"
        var TABLE_TODO = "todos"
    }
}