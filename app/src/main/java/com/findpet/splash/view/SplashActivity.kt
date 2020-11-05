package com.findpet.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.findpet.R
import com.findpet.userregister.view.UserRegisterActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            redirect()
        }, 2000)
    }

    private fun redirect() {
        startActivity(Intent(this, UserRegisterActivity::class.java))
        finish()
    }
}