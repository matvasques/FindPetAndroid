package com.findpet.animallist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.findpet.R
import com.findpet.animallist.view.model.AnimalItem
import kotlinx.android.synthetic.main.animal_list_item.view.*

class AnimalListRecyclerAdapter(
    private val data: MutableList<AnimalItem>,
    private val itemClickedAction: (item: AnimalItem) -> Unit,
    private val seeQrCodeClickedAction: (item: AnimalItem) -> Unit
) :
    RecyclerView.Adapter<AnimalListRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: AnimalItem) {
            view.apply {
                animal_item_name.text = item.name
                animal_item_breed.text = item.breed
                animal_item_description.text = item.description

                animal_item_name_see_qr_code_image_button.setOnClickListener {
                    seeQrCodeClickedAction(item)
                }

                setOnClickListener {
                    itemClickedAction(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.animal_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])
}