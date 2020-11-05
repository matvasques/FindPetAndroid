package com.findpet.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.findpet.R
import com.findpet.home.model.HomeItem
import kotlinx.android.synthetic.main.home_item_view.view.*

class HomeRecyclerAdapter(
    private val data: MutableList<HomeItem>,
    private val itemClickedAction: (item: HomeItem) -> Unit
) :
    RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: HomeItem) {
            view.apply {
                text.text = item.description
                setOnClickListener {
                    itemClickedAction(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])
}