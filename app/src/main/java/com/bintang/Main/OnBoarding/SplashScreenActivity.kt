package com.bintang.Main.OnBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bintang.Main.Home.MainActivity
import com.bintang.Main.Login.LoginActivity
import com.bintang.Utils.UserPreference
import com.bintang.cinemapp.R

class SplashScreenActivity : AppCompatActivity() {
    lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        userPreference = UserPreference(this)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            if (userPreference.getStatusUser().equals(true)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)
    }
}