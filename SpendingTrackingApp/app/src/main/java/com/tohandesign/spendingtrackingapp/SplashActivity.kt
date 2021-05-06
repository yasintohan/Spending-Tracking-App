package com.tohandesign.spendingtrackingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.tohandesign.spendingtrackingapp.Network.NetworkConnection
import com.tohandesign.spendingtrackingapp.Retrofit.CurrencyApi

class SplashActivity : AppCompatActivity() {

    val PREFS_FILENAME = "com.tohandesign.spendingtrackingapp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val bgImg : ImageView = findViewById(R.id.imageView)
        val appText : TextView = findViewById(R.id.textView)
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.slide)

        bgImg.startAnimation(slideAnim)
        appText.startAnimation(slideAnim)

        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

        Handler().postDelayed({
            if(prefences.getBoolean("IS_FIRST", true)) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }

        }, 3000)

    }
}