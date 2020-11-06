package com.findpet.animallist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.animallist.view.adapter.AnimalListRecyclerAdapter
import com.findpet.animallist.view.model.AnimalItem
import kotlinx.android.synthetic.main.activity_animal_list.*

class AnimalListActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, AnimalListActivity::class.java)
    }


    private val recyclerAdapter: AnimalListRecyclerAdapter by lazy {
        AnimalListRecyclerAdapter(list, {
            Toast.makeText(this, "Click in ${it.name}", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(this, "Click in QR Code for ${it.name}", Toast.LENGTH_SHORT).show()
        })
    }

    private val list: MutableList<AnimalItem> by lazy {
        mutableListOf<AnimalItem>().apply {
            add(
                AnimalItem(
                    name = "Nome do animal",
                    breed = "Raça do animal",
                    description = "Descrição do animal"
                )
            )
            add(
                AnimalItem(
                    name = "Nome do animal",
                    breed = "Raça do animal",
                    description = "Descrição do animal"
                )
            )
            add(
                AnimalItem(
                    name = "Nome do animal",
                    breed = "Raça do animal",
                    description = "Descrição do animal"
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animal_list)

        activity_animal_list_recycler_view.apply {
            adapter = recyclerAdapter
        }
    }
}