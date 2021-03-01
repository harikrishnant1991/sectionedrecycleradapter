package com.github.sectionedrecyclersample.model

object ModelManager {
    val ACTIVE_KEY = "ACTIVE";
    val COMPLETED_KEY = "COMPLETED";

    private val todoMap = HashMap<String, ArrayList<ToDoItem>>(2)

    init {
        todoMap[ACTIVE_KEY] = ArrayList()
        todoMap[COMPLETED_KEY] = ArrayList()
    }

    fun getActiveTasks(): ArrayList<ToDoItem> {
        return todoMap[ACTIVE_KEY]!!
    }

    fun getCompletedTasks(): ArrayList<ToDoItem> {
        return todoMap[COMPLETED_KEY]!!
    }

    fun addToDo(todo: ToDoItem) {
        todoMap[ACTIVE_KEY]!!.add(todo)
    }

    fun addCompletedToDo(todo: ToDoItem) {
        todoMap[COMPLETED_KEY]!!.add(todo)
    }
}