package com.findpet.home.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.home.model.HomeItem
import com.findpet.home.view.adapter.HomeRecyclerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_content_layout.view.*

class HomeActivity : AppCompatActivity() {

    private val homeAdapter : HomeRecyclerAdapter by lazy {
        HomeRecyclerAdapter(list) {
            Toast.makeText(this, "Click in ${it.description}", Toast.LENGTH_SHORT).show()
        }
    }

    private val list : MutableList<HomeItem> by lazy {
        mutableListOf<HomeItem>().apply {
            add (HomeItem("Gerenciar PETs"))
            add (HomeItem("Ler QR Code"))
            add (HomeItem("Cadastrar novo PET"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        content.home_content_list.apply {
            adapter = homeAdapter
        }

    }
}