package com.gabrielcastro.listadetarefas.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcastro.listadetarefas.R
import com.gabrielcastro.listadetarefas.adapter.TodoAdapter
import com.gabrielcastro.listadetarefas.databinding.ActivityMainBinding
import com.gabrielcastro.listadetarefas.model.Todo

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private var listTodo: ArrayList<Todo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // config recycler
        recyclerView = findViewById(R.id.recyclerTodo)

        binding.fab.setOnClickListener {
            val intent = Intent(applicationContext, NewTodoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadTodos() {
        // list
        listTodo.add(Todo(1, "Todo 1"))
        listTodo.add(Todo(2, "Todo 2"))
        listTodo.add(Todo(3, "Todo 3"))
        // config adapter
        todoAdapter = TodoAdapter(listTodo)
        //config recycler
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayout.VERTICAL))
        recyclerView.adapter = todoAdapter
    }

    override fun onStart() {
        loadTodos()
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}