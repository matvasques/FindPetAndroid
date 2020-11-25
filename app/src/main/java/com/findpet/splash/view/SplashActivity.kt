package com.findpet.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.home.view.HomeActivity
import com.findpet.login.LoginActivity
import com.findpet.login.UserViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            redirect()
        }, 2000)
    }

    private fun redirect() {
        startActivity(
            Intent(
                this,
                if (userViewModel.getUser() != null) HomeActivity::class.java else LoginActivity::class.java
            )
        )
        finish()
    }
}