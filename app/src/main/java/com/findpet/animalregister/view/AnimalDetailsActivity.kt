package com.findpet.animalregister.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.findpet.R
import com.findpet.animallist.view.AnimalViewModel
import com.findpet.extensions.showToast
import com.findpet.login.UserViewModel
import data.ANIMAL_KEY
import data.ResponseAnimal
import data.ResponseUser
import data.Status
import kotlinx.android.synthetic.main.activity_animal_details.*
import org.koin.android.viewmodel.ext.android.viewModel


class AnimalDetailsActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, animal: ResponseAnimal) =
            Intent(context, AnimalDetailsActivity::class.java).apply {
                putExtra(ANIMAL_KEY, animal)
            }
    }

    private val animal: ResponseAnimal? by lazy {
        intent.extras?.get(ANIMAL_KEY) as? ResponseAnimal?
    }

    private val userViewModel: UserViewModel by viewModel()
    private val animalViewModel: AnimalViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animal_details)

        with(userViewModel) {
            observeUser(this)
        }

        with(animalViewModel) {
            observeAnimal(this)
        }

        setupView()

        animal?.userId?.let {
            userViewModel.getUserById(it)
        }
    }

    private fun setupView() {

        animal_data_name.text = animal?.petName?.let {
            getString(R.string.animal_name_formatter, it)
        } ?: getString(R.string.not_found)

        animal_data_breed.text = animal?.petName?.let {
            getString(R.string.animal_breed_formatter, it)
        } ?: getString(R.string.not_found)

        animal_data_description.text = animal?.petName?.let {
            getString(R.string.animal_description_formatter, it)
        } ?: getString(R.string.not_found)
    }

    private fun showUserData(user: ResponseUser) {
        owner_data_name.text = user.name?.let {
            getString(R.string.animal_name_formatter, it)
        } ?: getString(R.string.not_found)


        owner_data_address.text = if (user.neighborhood != null && user.city != null) {
            getString(R.string.user_address_formatter, user.neighborhood, user.city)
        } else {
            getString(R.string.not_found)
        }

        owner_data_contact.text = user.phoneNumber?.let {
            getString(R.string.user_contact_formatter, it)
        } ?: getString(R.string.not_found)
    }

    private fun observeUser(userViewModel: UserViewModel) {
        userViewModel.getUserLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loader.visibility = View.GONE
                    it.data?.let { user ->
                        showUserData(user)
                    }
                }
                Status.ERROR -> {
                    loader.visibility = View.GONE
                    showToast(R.string.generic_error)
                }
                Status.LOADING -> {
                    loader.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun observeAnimal(animalViewModel: AnimalViewModel) {

    }
}