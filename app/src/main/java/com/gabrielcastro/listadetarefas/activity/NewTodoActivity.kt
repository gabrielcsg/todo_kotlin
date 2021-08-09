package com.gabrielcastro.listadetarefas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.gabrielcastro.listadetarefas.R
import com.gabrielcastro.listadetarefas.helper.TodoDAO
import com.gabrielcastro.listadetarefas.model.Todo
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class NewTodoActivity : AppCompatActivity() {
    private lateinit var editTodo: TextInputEditText
    private var currentTodo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)

        editTodo = findViewById(R.id.text_input_new_todo)

        currentTodo = intent.getSerializableExtra("todoSelected") as Todo?

        editTodo.setText(currentTodo?.name)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_secondary, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save -> {
                if (currentTodo != null) updateTodo()
                else saveTodo()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateTodo() {
        if (editTodo.text.toString().isNotEmpty()) {
            currentTodo!!.name = editTodo.text.toString()
            val todoDAO = TodoDAO(applicationContext)
            todoDAO.update(currentTodo!!)
            finish()
        }
        Toast.makeText(applicationContext, "Insert a task", Toast.LENGTH_SHORT).show()
    }

    private fun saveTodo() {
        if (editTodo.text.toString().isNotEmpty()) {
            val todo = Todo()
            todo.name = editTodo.text.toString()
            val todoDAO = TodoDAO(applicationContext)
            todoDAO.save(todo)
            finish()
        }
        Toast.makeText(applicationContext, "Insert a task", Toast.LENGTH_SHORT).show()
    }
}