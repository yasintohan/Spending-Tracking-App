package com.tohandesign.spendingtrackingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val bgImg : ImageView = findViewById(R.id.imageView)
        val appText : TextView = findViewById(R.id.textView)
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.slide)

        bgImg.startAnimation(slideAnim)
        appText.startAnimation(slideAnim)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 3000)

    }
}