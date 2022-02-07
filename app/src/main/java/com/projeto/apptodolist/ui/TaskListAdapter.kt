package com.projeto.apptodolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projeto.apptodolist.R
import com.projeto.apptodolist.databinding.ItemTaskBinding
import com.projeto.apptodolist.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallBack()) {

    var listenerEdit : (Task) -> Unit = {}
    var listenerDeletar : (Task) -> Unit = {}

    inner class TaskViewHolder(private val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(item: Task){
                binding.tvTitle.text = item.title
                binding.tvData.text = "${item.date}:${item.hour}"
                binding.iconOptions.setOnClickListener {
                    showPopUP(item)
                }
            }
        private fun showPopUP(item: Task){
            val ivMore = binding.iconOptions
            val popupMenu = PopupMenu(ivMore.context, ivMore)
            popupMenu.menuInflater.inflate(R.menu.popupmenu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_edit -> listenerEdit(item)
                    R.id.action_deletar -> listenerDeletar(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind((getItem(position)))
    }
}


class  DiffCallBack : DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

}