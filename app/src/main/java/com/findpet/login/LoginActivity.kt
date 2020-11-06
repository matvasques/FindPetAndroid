package com.findpet.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.userregister.view.UserRegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        activity_login_register_button.setOnClickListener {
            redirectToRegister()
        }

    }

    private fun redirectToRegister() {
        startActivity(Intent(this, UserRegisterActivity::class.java))
    }
}