package com.github.sectionedrecyclersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.harikrishnant1991.sectionedrv.SectionedRecyclerAdapter
import com.github.sectionedrecyclersample.R
import com.github.sectionedrecyclersample.databinding.HeaderTodoTypeBinding
import com.github.sectionedrecyclersample.databinding.ItemActiveTodoBinding
import com.github.sectionedrecyclersample.databinding.ItemCompletedTodoBinding
import com.github.sectionedrecyclersample.model.ModelManager
import com.github.sectionedrecyclersample.model.ToDoItem
import java.lang.RuntimeException

class ToDoRecyclerAdapter: SectionedRecyclerAdapter<ToDoRecyclerAdapter.HeaderViewHolder, RecyclerView.ViewHolder>() {

    private val HEADER_VIEW_TYPE = 10
    private val ACTIVE_TODO_VIEW_TYPE = 20
    private val COMPLETED_TODO_VIEW_TYPE = 30

    override fun getSectionCount(): Int {
        return 2
    }

    override fun getChildCount(section: Int): Int {
        when (section) {
            0 -> return ModelManager.getActiveTasks().size
            1 -> return ModelManager.getCompletedTasks().size
        }
        throw RuntimeException("Invalid section")
    }

    override fun getHeaderViewType(section: Int): Int {
        return HEADER_VIEW_TYPE
    }

    override fun getChildViewType(section: Int, index: Int): Int {
        when (section) {
            0 -> return ACTIVE_TODO_VIEW_TYPE
            1 -> return COMPLETED_TODO_VIEW_TYPE
        }
        throw RuntimeException("Invalid section")
    }

    override fun onBindHeader(holder: HeaderViewHolder, section: Int) {
        when (section) {
            0 -> (holder as? HeaderViewHolder)?.bind("TO DO LIST")
            1 -> (holder as? HeaderViewHolder)?.bind("COMPLETED")
        }
    }

    override fun onBindChild(holder: RecyclerView.ViewHolder, section: Int, index: Int) {
        when (section) {
            0 -> (holder as? ActiveToDoViewHolder)?.bind(ModelManager.getActiveTasks()[index])
            1 -> (holder as? CompletedToDoViewHolder)?.bind(ModelManager.getCompletedTasks()[index])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            HEADER_VIEW_TYPE -> {
                val binding: HeaderTodoTypeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.header_todo_type, parent, false)
                return HeaderViewHolder(binding)
            }
            ACTIVE_TODO_VIEW_TYPE -> {
                val binding: ItemActiveTodoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_active_todo, parent, false)
                return ActiveToDoViewHolder(binding)
            }
            COMPLETED_TODO_VIEW_TYPE -> {
                val binding: ItemCompletedTodoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_completed_todo, parent, false)
                return CompletedToDoViewHolder(binding)
            }
        }
        throw RuntimeException("Invalid view type")
    }

    class HeaderViewHolder(private val binding: HeaderTodoTypeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) {
            binding.title = title
        }
    }

    class ActiveToDoViewHolder(private val binding: ItemActiveTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: ToDoItem) {
            binding.title = todo.title
            binding.description = todo.description
        }
    }

    class CompletedToDoViewHolder(private val binding: ItemCompletedTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: ToDoItem) {
            binding.title = todo.title
            binding.description = todo.description
        }
    }
}