package `in`.pabreja.coding.android.todolist.adaptor

import `in`.pabreja.coding.android.todolist.R
import `in`.pabreja.coding.android.todolist.data.ToDo
import `in`.pabreja.coding.android.todolist.view.ToDoViewHolder
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_todo.view.*

class ToDoAdaptor(
    private val todos: MutableList<ToDo>
): RecyclerView.Adapter<ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_todo,
                parent,
                false
            )
        )
    }

    public fun addToDo(todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    public fun deleteDoneToDos(){
        todos.removeAll{
            todo -> todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDoItem = todos[position]
        holder.itemView.apply {
            tvToDoTitle.text = curToDoItem.title
            cbDone.isChecked = curToDoItem.isChecked
            toggleStrikeThrough(tvToDoTitle,cbDone.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvToDoTitle,isChecked)
                curToDoItem.isChecked = !curToDoItem.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }

}