package com.findpet.animallist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.findpet.R
import data.ResponseAnimal
import kotlinx.android.synthetic.main.animal_list_item.view.*

class AnimalListRecyclerAdapter(
    var data: MutableList<ResponseAnimal?>,
    private val itemClickedAction: (item: ResponseAnimal) -> Unit,
    private val seeQrCodeClickedAction: (item: ResponseAnimal) -> Unit
) :
    RecyclerView.Adapter<AnimalListRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ResponseAnimal?) {
            view.apply {
                item?.let { animal ->
                    animal_item_name.text = animal.petName
                    animal_item_breed.text = animal.breeds
                    animal_item_description.text = animal.description

                    if (!animal.imgUrl.isNullOrBlank()){
                        Glide.with(context).load(animal.imgUrl).into(animal_item_image_view)
                    }

                    animal_item_name_see_qr_code_image_button.setOnClickListener {
                        seeQrCodeClickedAction(animal)
                    }

                    setOnClickListener {
                        itemClickedAction(animal)
                    }
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