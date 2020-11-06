package com.findpet.animalregister.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.home.type.AnimalType
import kotlinx.android.synthetic.main.activity_register_animal.*

private const val ANIMAL_TYPE_KEY = "ANIMAL_TYPE"

class AnimalRegisterActivity : AppCompatActivity() {

    private val selectedOption: AnimalType? by lazy {
        AnimalType.fromValue(intent.getStringExtra(ANIMAL_TYPE_KEY))
    }

    companion object {
        fun getStartIntent(context: Context, animalTypeValue: String) =
            Intent(context, AnimalRegisterActivity::class.java).apply {
                putExtra(ANIMAL_TYPE_KEY, animalTypeValue)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register_animal)

        setupView()
    }

    private fun setupView() {
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
    }
}