package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class segreto : AppCompatActivity() {

    private val handler = Handler()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segreto)

        var btnsegreto = findViewById<Button>(R.id.btnsegreto)
        var romano = findViewById<TextView>(R.id.romano)
        var mondo = findViewById<TextView>(R.id.mondo)
        var linea = findViewById<View>(R.id.linea)

        handler.postDelayed({
                            romano.visibility = View.VISIBLE
            val animation3 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            romano.startAnimation(animation3)
        }, 4000)

        handler.postDelayed({
            linea.visibility = View.VISIBLE
            mondo.visibility = View.VISIBLE
            val animation3 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            linea.startAnimation(animation3)
            mondo.startAnimation(animation3)
        }, 8000)

        handler.postDelayed({
            btnsegreto.visibility = View.VISIBLE
            val animation3 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            btnsegreto.startAnimation(animation3)
        }, 12000)

        btnsegreto.setOnClickListener {
            val intent = Intent(this, ScrollScreen::class.java)
            startActivity(intent)
        }
    }
}