package com.greycom.gadsleaderboard.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.greycom.gadsleaderboard.R
import com.jaeger.library.StatusBarUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusBarUtil.setTransparent(this)
        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java));
            finish()
        }, 2000.toLong())

    }
}