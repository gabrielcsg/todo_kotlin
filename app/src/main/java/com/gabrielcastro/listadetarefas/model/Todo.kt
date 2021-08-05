package com.gabrielcastro.listadetarefas.model

import java.io.Serializable

class Todo : Serializable {
    lateinit var name: String
    var id: Long = 0

    constructor() {

    }

    constructor(id: Long, name: String) : this() {
        this.id = id
        this.name = name
    }
}