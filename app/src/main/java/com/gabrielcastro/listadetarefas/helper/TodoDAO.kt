package com.gabrielcastro.listadetarefas.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.gabrielcastro.listadetarefas.model.Todo
import java.lang.Exception

class TodoDAO(context: Context) : ITodoDAO {
    private var write: SQLiteDatabase
    private var read: SQLiteDatabase
    private var tag: String = "TODO DAO"

    init {
        val dbHelper = DBHelper(context)
        write = dbHelper.writableDatabase
        read = dbHelper.readableDatabase
    }

    override fun save(todo: Todo): Boolean {
        try {
            val cv = ContentValues()
            cv.put("name", todo.name)
            write.insert(DBHelper.TABLE_TODO, null, cv)
            Log.d(tag, "Todo save successfully")
        } catch (e: Exception) {
            Log.d(tag, "Error on save todo")
            return false
        }
        return true
    }

    override fun update(todo: Todo): Boolean {
        try {
            val cv = ContentValues()
            cv.put("id", todo.id)
            cv.put("name", todo.name)
            write.update(DBHelper.TABLE_TODO, cv, "id=${todo.id}", null)
            Log.d(tag, "Todo updated successfully")
        } catch (e: Exception) {
            Log.d(tag, "Error on update todo")
            return false
        }
        return true
    }

    override fun delete(todo: Todo): Boolean {
        try {
            write.delete(DBHelper.TABLE_TODO, "id=${todo.id}", null)
            Log.d(tag, "Todo removed successfully")
        } catch (e: Exception) {
            Log.d(tag, "Error on remove todo")
            return false
        }
        return true
    }

    override fun list(): ArrayList<Todo> {
        val list: ArrayList<Todo> = ArrayList()

        val sql = "SELECT * FROM ${DBHelper.TABLE_TODO} ;"
        val cursor = read.rawQuery(sql, null)

        while (cursor.moveToNext()) {
            val todo = Todo()
            todo.id = cursor.getLong(cursor.getColumnIndex("id"))
            todo.name = cursor.getString(cursor.getColumnIndex("name"))

            list.add(todo)
        }
        return list
    }
}