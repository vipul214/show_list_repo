package com.vipul.showtodo.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vipul.showtodo.R
import com.vipul.showtodo.models.data.ToDoListData

class ToDoListAdapter(toDoList : ArrayList<ToDoListData>?) :
    RecyclerView.Adapter<ToDoListAdapter.ListViewHolder>(){

    var toDoList : ArrayList<ToDoListData>? = null

    init {
        this.toDoList = toDoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_to_do_list, parent, false) as View
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return toDoList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if(toDoList!=null && position<itemCount) {
            //toDoList!!.get(position).title = "abv"
            holder.title.text = toDoList!!.get(position).title
            holder.status.text = if(toDoList!!.get(position).status){
                "Completed"
            }else{
                "Pending"
            }
        }
    }

    class ListViewHolder(view: View): RecyclerView.ViewHolder(view){
        var itemView = view
        var title : TextView = view.findViewById<TextView>(R.id.tv_title)
        var status : TextView = view.findViewById<TextView>(R.id.tv_status)
    }

}