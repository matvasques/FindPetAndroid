package com.findpet.userregister.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.home.view.HomeActivity
import kotlinx.android.synthetic.main.activity_user_register.*

class UserRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_register)

        activity_user_registration_register.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}