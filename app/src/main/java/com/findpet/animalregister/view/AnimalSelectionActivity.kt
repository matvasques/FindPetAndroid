package com.findpet.animalregister.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.home.type.AnimalType
import kotlinx.android.synthetic.main.activity_animal_selection.*

class AnimalSelectionActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animal_selection)

        activity_animal_selection_register_later_button.setOnClickListener {
            finish()
        }

        dog_options.setOnClickListener(this)

        cat_options.setOnClickListener(this)
    }

    private fun redirectToAnimalRegister(animalType: AnimalType) {
        startActivity(AnimalRegisterActivity.getStartIntent(this, animalType.value))
    }

    override fun onClick(v: View?) {
        redirectToAnimalRegister(
            when (v?.id) {
                R.id.dog_options -> AnimalType.DOG
                R.id.cat_options -> AnimalType.CAT
                else -> AnimalType.INVALID
            }
        )
    }
}