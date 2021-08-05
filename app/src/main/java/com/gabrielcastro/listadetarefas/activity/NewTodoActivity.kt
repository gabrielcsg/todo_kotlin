package com.gabrielcastro.listadetarefas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.gabrielcastro.listadetarefas.R
import com.google.android.material.snackbar.Snackbar

class NewTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_secondary, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save -> {
                Snackbar.make(
                    this.findViewById(R.id.contentNewTodo),
                    "O item foi salvo!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}