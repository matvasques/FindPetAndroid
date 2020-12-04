package com.findpet.animalregister.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.findpet.R
import com.findpet.animallist.view.AnimalViewModel
import com.findpet.extensions.showToast
import com.findpet.home.type.AnimalType
import data.*
import kotlinx.android.synthetic.main.activity_register_animal.*
import org.koin.android.viewmodel.ext.android.viewModel

class AnimalRegisterActivity : AppCompatActivity() {

    private val animalViewModel: AnimalViewModel by viewModel()

    private val selectedOption: AnimalType? by lazy {
        AnimalType.fromValue(intent.getStringExtra(ANIMAL_TYPE_KEY))
    }

    private val animal: ResponseAnimal? by lazy {
        intent.extras?.get(ANIMAL_KEY) as? ResponseAnimal?
    }

    private val isUpdateAnimal: Boolean
        get() = animal != null

    companion object {
        fun getStartIntent(context: Context, animalTypeValue: String) =
            Intent(context, AnimalRegisterActivity::class.java).apply {
                putExtra(ANIMAL_TYPE_KEY, animalTypeValue)
            }

        fun getStartIntent(context: Context, animal: ResponseAnimal) =
            Intent(context, AnimalRegisterActivity::class.java).apply {
                putExtra(ANIMAL_KEY, animal)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register_animal)

        setupView()

        with(animalViewModel) {
            observeAnimal(this)
        }
    }

    private fun setupView() {

        if (isUpdateAnimal) {
            activity_animal_register_register_button.text = getString(R.string.update_animal)

            if (!animal?.imgUrl.isNullOrBlank()) {
                Glide.with(this).load(animal?.imgUrl).into(activity_animal_register_option_selected_icon)
            }
        }

        when (selectedOption) {
            AnimalType.DOG -> {
                activity_animal_register_option_selected_icon.setImageResource(R.drawable.icon_dog)
                activity_animal_register_option_selected_description_label.text =
                    getString(R.string.animal_register_dog_label)
            }
            AnimalType.CAT -> {
                activity_animal_register_option_selected_icon.setImageResource(R.drawable.icon_cat)
                activity_animal_register_option_selected_description_label.text =
                    getString(R.string.animal_register_cat_label)
            }
        }

        activity_animal_register_name_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    activity_animal_register_option_selected_description_label.text =
                        when (selectedOption) {
                            AnimalType.DOG -> {
                                getString(R.string.animal_register_dog_label)
                            }
                            AnimalType.CAT -> {
                                getString(R.string.animal_register_cat_label)
                            }
                            else -> ""
                        }
                } else {
                    activity_animal_register_option_selected_description_label.text = s.toString()
                }
            }
        })

        activity_animal_register_register_button.setOnClickListener {
            if (isUpdateAnimal) {
                animalViewModel.updateAnimal(
                    ResponseAnimal(
                        animal?.id, activity_animal_register_name_edit_text.text.toString(),
                        activity_animal_register_breed_edit_text.text.toString(),
                        activity_user_registration_description_et.text.toString(),
                        "", animal?.userId
                    )
                )
            } else {
                animalViewModel.createAnimal(
                    1,
                    RequestAnimal(
                        activity_animal_register_name_edit_text.text.toString(),
                        activity_animal_register_breed_edit_text.text.toString(),
                        activity_user_registration_description_et.text.toString(),
                        ""
                    )
                )
            }
        }


        animal?.let {
            activity_animal_register_breed_edit_text.setText(it.breeds)
            activity_animal_register_name_edit_text.setText(it.petName)
            activity_user_registration_description_et.setText(it.breeds)
        }
    }


    private fun observeAnimal(viewModel: AnimalViewModel) {
        viewModel.createAnimalLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loader.visibility = View.GONE
                    showToast(getString(R.string.animal_register_successful))
                    setResult(ANIMAL_LIST_REQUEST_CODE)
                    finish()
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

        viewModel.updateAnimalLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loader.visibility = View.GONE
                    showToast(getString(R.string.animal_update_successful))
                    setResult(ANIMAL_LIST_REQUEST_CODE)
                    finish()
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
}