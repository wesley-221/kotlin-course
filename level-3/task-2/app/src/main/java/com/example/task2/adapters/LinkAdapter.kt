package com.example.task2.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.R
import com.example.task2.models.Link
import kotlinx.android.synthetic.main.link.view.*

class LinkAdapter(private val links: List<Link>, private val onClickListener: (View, Link) -> Unit): RecyclerView.Adapter<LinkAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.link, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(links[position])

        holder.itemView.setOnClickListener{
            onClickListener.invoke(it, links[position])
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(link: Link) {
            itemView.tvHeader.text = link.header
            itemView.tvLink.text = link.link

//            itemView.setBackgroundColor(Color.parseColor(R.color.colorPrimary.toString()))
            itemView.tvHeader.setTextColor(Color.WHITE)
            itemView.tvLink.setTextColor(Color.WHITE)
        }
    }
}