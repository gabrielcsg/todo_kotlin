package com.gabrielcastro.listadetarefas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielcastro.listadetarefas.R
import com.gabrielcastro.listadetarefas.model.Todo

class TodoAdapter(var listTodo: List<Todo>): RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tarefa: TextView = itemView.findViewById(R.id.textTodo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemList = LayoutInflater.from(parent.context).inflate(R.layout.adapter_todo_list, parent, false)
        return MyViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todo = listTodo[position]
        holder.tarefa.text = todo.name
    }

    override fun getItemCount(): Int {
        return this.listTodo.size
    }
}