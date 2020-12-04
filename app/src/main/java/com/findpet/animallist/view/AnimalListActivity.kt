package com.findpet.animallist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.findpet.R
import com.findpet.animallist.view.adapter.AnimalListRecyclerAdapter
import com.findpet.animalregister.view.AnimalRegisterActivity
import com.findpet.extensions.showToast
import data.ResponseAnimal
import data.Status
import kotlinx.android.synthetic.main.activity_animal_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class AnimalListActivity : AppCompatActivity() {

    private val animalViewModel: AnimalViewModel by viewModel()

    companion object {
        fun getStartIntent(context: Context) = Intent(context, AnimalListActivity::class.java)
    }

    private val list: MutableList<ResponseAnimal?> by lazy {
        mutableListOf<ResponseAnimal?>().apply {
            add(
                ResponseAnimal(
                    id = 4,
                    petName = "Nome do animal",
                    breeds = "Raça do animal",
                    description = "Descrição do animal"
                )
            )
            add(
                ResponseAnimal(
                    id = 5,
                    petName = "Nome do animal",
                    breeds = "Raça do animal",
                    description = "Descrição do animal"
                )
            )
            add(
                ResponseAnimal(
                    id = 1,
                    petName = "Nome do animal",
                    breeds = "Raça do animal",
                    description = "Descrição do animal"
                )
            )
        }
    }

    private val recyclerAdapter: AnimalListRecyclerAdapter by lazy {
        AnimalListRecyclerAdapter(mutableListOf(), {
            redirectToAnimalEdition(it)
        }, {
            QRCodeDialogFragment(this, it.id).show()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animal_list)

        with(animalViewModel) {
            observeAnimal(this)
        }

        /*recyclerAdapter.data = list*/
            activity_animal_list_recycler_view.apply {
            adapter = recyclerAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        animalViewModel.getAnimals()
    }

    private fun observeAnimal(animalViewModel: AnimalViewModel) {
        animalViewModel.getAnimalsLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    activity_animal_empty_state_text.visibility = View.GONE
                    loader.visibility = View.GONE
                    it.data?.let {
                        recyclerAdapter.run {
                            data = it.toMutableList()
                            notifyDataSetChanged()
                        }
                    }
                }
                Status.ERROR -> {
                    activity_animal_empty_state_text.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                    showToast(R.string.generic_error)

                }
                Status.LOADING -> {
                    activity_animal_empty_state_text.visibility = View.GONE
                    loader.visibility = View.VISIBLE
                }

                Status.EMPTY -> {
                    showToast(getString(R.string.no_animals_found))
                    activity_animal_empty_state_text.visibility = View.VISIBLE
                    loader.visibility = View.GONE
                }
            }
        })

    }

    private fun redirectToAnimalEdition(animal: ResponseAnimal) {
        startActivity(Intent(AnimalRegisterActivity.getStartIntent(this, animal)))
    }
}