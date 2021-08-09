package com.gabrielcastro.listadetarefas.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcastro.listadetarefas.R
import com.gabrielcastro.listadetarefas.adapter.TodoAdapter
import com.gabrielcastro.listadetarefas.databinding.ActivityMainBinding
import com.gabrielcastro.listadetarefas.helper.DBHelper
import com.gabrielcastro.listadetarefas.helper.RecyclerItemClickListener
import com.gabrielcastro.listadetarefas.helper.TodoDAO
import com.gabrielcastro.listadetarefas.model.Todo
import com.google.android.material.snackbar.Snackbar

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
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                applicationContext,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val todoSelected = listTodo[position]

                        val intent = Intent(this@MainActivity, NewTodoActivity::class.java)
                        intent.putExtra("todoSelected", todoSelected)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        val dialog = AlertDialog.Builder(this@MainActivity)
                        dialog.setTitle("Remove Todo")
                        dialog.setMessage("Are you sure you want to remove: ${listTodo[position].name}?")

                        dialog.setPositiveButton("YES") { _: DialogInterface, _: Int ->
                            val todoDAO = TodoDAO(applicationContext)
                            todoDAO.delete(listTodo[position])
                            loadTodos()
                        }
                        dialog.setNegativeButton("NO", null)

                        dialog.create()
                        dialog.show()
                    }

                    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        TODO("Not yet implemented")
                    }
                }
            )
        )

        binding.fab.setOnClickListener {
            val intent = Intent(applicationContext, NewTodoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadTodos() {
        // list
        val todoDAO = TodoDAO(applicationContext)
        listTodo = todoDAO.list()
        // config adapter
        todoAdapter = TodoAdapter(listTodo)
        //config recycler
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayout.VERTICAL
            )
        )
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
            R.id.action_settings -> {
                Snackbar.make(binding.root, "settings", Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}