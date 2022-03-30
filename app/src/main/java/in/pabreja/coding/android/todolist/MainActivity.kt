package `in`.pabreja.coding.android.todolist

import `in`.pabreja.coding.android.todolist.adaptor.ToDoAdaptor
import `in`.pabreja.coding.android.todolist.data.ToDo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var toDoAdaptor: ToDoAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoAdaptor = ToDoAdaptor(mutableListOf())
        rvToDoItems.adapter = toDoAdaptor
        rvToDoItems.layoutManager = LinearLayoutManager(this)
        btnToDoAddItem.setOnClickListener{
            val toDoTitle = etToDoTextItems.text.toString()
            if(toDoTitle.isNotBlank()){
                val toDo =  ToDo(toDoTitle)
                toDoAdaptor.addToDo(toDo)
            }
            etToDoTextItems.text.clear()
        }
        btnToDoDeleteDone.setOnClickListener {
            toDoAdaptor.deleteDoneToDos()
        }
    }
}