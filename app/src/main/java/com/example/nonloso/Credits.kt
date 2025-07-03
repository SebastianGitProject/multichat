package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Credits : AppCompatActivity() {
    private lateinit var bottone: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        bottone = findViewById(R.id.btnCreditsIndietro)
        bottone.setOnClickListener {
            //startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}