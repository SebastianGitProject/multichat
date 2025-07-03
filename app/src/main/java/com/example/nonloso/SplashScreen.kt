package com.example.nonloso

import android.R.attr.logo
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*


@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var testo: TextView
    private lateinit var bar: ProgressBar
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        testo  = findViewById(R.id.testo)
        /*val imageViewObjectAnimator = ObjectAnimator.ofFloat(
            testo,
            "rotation", 0f, 360f
        )
        imageViewObjectAnimator.setDuration(1000) // miliseconds

        runBlocking {
            delay(500)
        }

        imageViewObjectAnimator.start()*/






        bar = findViewById(R.id.bar)
        image = findViewById(R.id.SplashScreenImage)

        /*val anim = ValueAnimator.ofFloat(1f, 1.5f)
        anim.setDuration(1000)
        anim.addUpdateListener { animation ->
            image.rotation(animation.animatedValue as Float)
            testo.setScaleX(animation.animatedValue as Float)
            image.setScaleY(animation.animatedValue as Float)
            testo.setScaleY(animation.animatedValue as Float)
        }
        anim.repeatCount = 1
        anim.repeatMode = ValueAnimator.REVERSE
        anim.start()*/
        Thread.sleep(1000)
        bar.startAnimation(
        AnimationUtils.loadAnimation(applicationContext, R.anim.rotation))


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ScrollScreen::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}