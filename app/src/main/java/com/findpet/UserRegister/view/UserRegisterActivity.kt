package com.findpet.UserRegister.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.findpet.R
import com.findpet.home.view.HomeActivity
import com.findpet.login.UserViewModel
import data.RequestUser
import kotlinx.android.synthetic.main.activity_user_register.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserRegisterActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_register)

        with(userViewModel) {
            observeUser(this)
        }

        activity_user_registration_register.setOnClickListener {
            validateFields(
                activity_user_registration_name_et.text.toString(),
                activity_user_registration_email_et.text.toString(),
                activity_user_registration_phone_et.text.toString(),
                activity_user_registration_password_et.text.toString(),
                activity_user_registration_password_confirmation_et.text.toString(),
                activity_user_registration_address_street_et.text.toString(),
                activity_user_registration_address_number_et.text.toString(),
                activity_user_registration_address_neighborhood_et.text.toString(),
                activity_user_registration_address_city_et.text.toString(),
                activity_user_registration_address_state_et.text.toString(),
                activity_user_registration_address_country_et.text.toString()
            )
        }
    }

    private fun validateFields(
        name: String,
        email: String,
        phone: String,
        password: String,
        passwordConfirmation: String,
        street: String,
        number: String,
        neighborhood: String,
        city: String,
        state: String,
        country: String
    ) {
        if (name.isBlank()) {
            return
        }

        if (email.isBlank()) {
            return
        }
        if (phone.isBlank()) {
            return
        }
        if (password.isBlank()) {
            return
        }
        if (passwordConfirmation.isBlank()) {
            return
        }
        if (street.isBlank()) {
            return
        }
        if (number.isBlank()) {
            return
        }
        if (neighborhood.isBlank()) {
            return
        }
        if (city.isBlank()) {
            return
        }
        if (state.isBlank()) {
            return
        }
        if (country.isBlank()) {
            return
        }

        userViewModel.onLogin(
            RequestUser(
                name,
                email,
                phone,
                password,
                passwordConfirmation,
                number.toInt(),
                "",
                "",
                neighborhood,
                city,
                state,
                country
            )
        )
    }

    private fun observeUser(viewModel: UserViewModel) {
        viewModel.liveData.observe(this, Observer {
            Toast.makeText(this, it.first, Toast.LENGTH_SHORT).show()

            if (it.second) {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        })
    }
}