package com.findpet.UserRegister.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.findpet.R
import com.findpet.extensions.showTextInputLayoutError
import com.findpet.extensions.showToast
import com.findpet.home.view.HomeActivity
import com.findpet.login.UserViewModel
import data.RequestUser
import data.Status
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
            activity_user_registration_name_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_name_til.showTextInputLayoutError()
        }

        if (email.isBlank()) {
            activity_user_registration_email_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_email_til.showTextInputLayoutError()
        }
        if (phone.isBlank()) {
            activity_user_registration_phone_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_phone_til.showTextInputLayoutError()
        }
        if (password.isBlank()) {
            activity_user_registration_password_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_password_til.showTextInputLayoutError()
        }
        if (passwordConfirmation.isBlank()) {
            activity_user_registration_password_confirmation_til.showTextInputLayoutError(
                getString(
                    R.string.field_required_empty_error
                )
            )
            return
        } else {
            activity_user_registration_password_confirmation_til.showTextInputLayoutError()
        }
        if (street.isBlank()) {
            activity_user_registration_address_street_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_address_street_til.showTextInputLayoutError()
        }
        if (number.isBlank()) {
            activity_user_registration_address_number_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_address_number_til.showTextInputLayoutError()
        }
        if (neighborhood.isBlank()) {
            activity_user_registration_address_neighborhood_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_address_neighborhood_til.showTextInputLayoutError()
        }
        if (city.isBlank()) {
            activity_user_registration_address_city_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_address_city_til.showTextInputLayoutError()
        }
        if (state.isBlank()) {
            activity_user_registration_address_state_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_address_state_til.showTextInputLayoutError()
        }
        if (country.isBlank()) {
            activity_user_registration_address_country_til.showTextInputLayoutError(getString(R.string.field_required_empty_error))
            return
        } else {
            activity_user_registration_address_country_til.showTextInputLayoutError()
        }

        userViewModel.onLogin(
            RequestUser(
                name,
                email,
                phone,
                password,
                passwordConfirmation,
                "",
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
            when (it.status) {
                Status.LOADING -> {
                    loader.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    showToast(R.string.user_register_successful)
                    loader.visibility = View.GONE
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                Status.ERROR -> {
                    loader.visibility = View.GONE
                    showToast(getString(R.string.generic_error))
                }
            }
        })
    }
}