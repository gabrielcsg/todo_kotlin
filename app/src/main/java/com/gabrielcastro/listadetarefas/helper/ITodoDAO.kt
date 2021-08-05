package com.gabrielcastro.listadetarefas.helper

import com.gabrielcastro.listadetarefas.model.Todo

interface ITodoDAO {
    fun save(todo: Todo): Boolean
    fun update(todo: Todo): Boolean
    fun delete(todo: Todo): Boolean
    fun list(): ArrayList<Todo>
}