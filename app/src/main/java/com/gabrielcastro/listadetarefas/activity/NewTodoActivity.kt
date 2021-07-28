package com.gabrielcastro.listadetarefas.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gabrielcastro.listadetarefas.R

class NewTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)
    }
}