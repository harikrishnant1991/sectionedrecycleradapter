package me.harikrishnant.sectionedrecyclersample.fragment

import androidx.lifecycle.ViewModel
import me.harikrishnant.sectionedrecyclersample.adapter.ToDoRecyclerAdapter
import me.harikrishnant.sectionedrecyclersample.model.ModelManager
import me.harikrishnant.sectionedrecyclersample.model.ToDoItem

class ToDoViewModel: ViewModel() {
    private val todoAdapter = ToDoRecyclerAdapter()

    init {
        ModelManager.addToDo(ToDoItem("Active Todo1", "Active ToDo1 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo2", "Active ToDo2 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo3", "Active ToDo3 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo4", "Active ToDo4 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo5", "Active ToDo5 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo6", "Active ToDo6 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo7", "Active ToDo7 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo8", "Active ToDo8 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo9", "Active ToDo9 Description"))
        ModelManager.addToDo(ToDoItem("Active Todo10", "Active ToDo10 Description"))

        ModelManager.addCompletedToDo(ToDoItem("Completed Todo1", "Completed ToDo1 Description"))
        ModelManager.addCompletedToDo(ToDoItem("Completed Todo2", "Completed ToDo2 Description"))
        ModelManager.addCompletedToDo(ToDoItem("Completed Todo3", "Completed ToDo3 Description"))
        ModelManager.addCompletedToDo(ToDoItem("Completed Todo4", "Completed ToDo4 Description"))
        ModelManager.addCompletedToDo(ToDoItem("Completed Todo5", "Completed ToDo5 Description"))
        ModelManager.addCompletedToDo(ToDoItem("Completed Todo6", "Completed ToDo6 Description"))
    }

    fun getAdapter(): ToDoRecyclerAdapter {
        return todoAdapter
    }
}