package com.example.todo
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.object_database.view.*
class Adapter(var data: List<ObjectData>) : RecyclerView.Adapter<Adapter.viewHolder>() {
    class viewHolder(Object: View) : RecyclerView.ViewHolder(Object) {
        var title = Object.title
        var desc = Object.desc
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var Data = LayoutInflater.from(parent.context).inflate(R.layout.object_database, parent, false)
        return viewHolder(Data)
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        if (data[position].title.length <= 15)
            holder.title.text = data[position].title
        if (data[position].desc.length <= 70)
            holder.desc.text = data[position].desc
        if (data[position].title.length > 15)
            holder.title.text = data[position].title.substring(0,15) + "..."
        if (data[position].desc.length > 70)
            holder.desc.text = data[position].desc.substring(0,70) + "..."
        else {
            holder.itemView.setOnClickListener{
                val transition= Intent(holder.itemView.context,DeleteObject::class.java)
                transition.putExtra("id",position)
                holder.itemView.context.startActivity(transition)
            }
        }
    }
}