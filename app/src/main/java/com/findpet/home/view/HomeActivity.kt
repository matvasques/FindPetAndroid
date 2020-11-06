package com.findpet.home.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.animallist.view.AnimalListActivity
import com.findpet.animalregister.view.AnimalSelectionActivity
import com.findpet.home.model.HomeItem
import com.findpet.home.type.HomeOptionType
import com.findpet.home.view.adapter.HomeRecyclerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_content_layout.view.*

class HomeActivity : AppCompatActivity() {

    private val homeAdapter: HomeRecyclerAdapter by lazy {
        HomeRecyclerAdapter(list) {
            when (it.homeOptionType) {
                HomeOptionType.REGISTER_ANIMAL -> redirectToRegisterAnimal()
                HomeOptionType.HANDLING_ANIMALS -> redirectToAnimalList()

            }
            Toast.makeText(this, "Click in ${it.description}", Toast.LENGTH_SHORT).show()
        }
    }

    private val list: MutableList<HomeItem> by lazy {
        mutableListOf<HomeItem>().apply {
            add(
                HomeItem(
                    R.drawable.icon_register_pet,
                    "Gerenciar PETs",
                    HomeOptionType.HANDLING_ANIMALS
                )
            )
            add(HomeItem(R.drawable.icon_qr_code, "Ler QR Code", HomeOptionType.READ_QR_CODE))
            add(
                HomeItem(
                    R.drawable.icon_handling_pets,
                    "Cadastrar novo PET",
                    HomeOptionType.REGISTER_ANIMAL
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        content.home_content_list.apply {
            adapter = homeAdapter
        }

    }

    private fun redirectToRegisterAnimal() {
        startActivity(Intent(this, AnimalSelectionActivity::class.java))
    }

    private fun redirectToAnimalList() {
        startActivity(AnimalListActivity.getStartIntent(this))
    }
}