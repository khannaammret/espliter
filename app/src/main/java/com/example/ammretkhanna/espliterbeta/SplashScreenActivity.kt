package com.example.ammretkhanna.espliterbeta

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        start.setOnClickListener {
            val loginPageActivity = Intent(this@SplashScreenActivity,LoginActivity::class.java)
            startActivity(loginPageActivity)
        }
    }
}
