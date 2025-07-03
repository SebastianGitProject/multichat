package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nonloso.MainActivity
import com.example.nonloso.R

class Home : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var but= findViewById<Button>(R.id.chat);
        but.setOnClickListener {
            if(GlobalVars.btnSceltaPremuto == false){
                Toast.makeText(this, "DEVI SELEZIONARE PRIMA UN AVATAR!", Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, SchermataConversazioni::class.java);
                startActivity(intent);
            }
        }

        var but2= findViewById<Button>(R.id.impostazioni);
        but2.setOnClickListener {
            val intent = Intent(this, Settings::class.java);
            startActivity(intent);
        }

        var but3= findViewById<Button>(R.id.scelta);
        but3.setOnClickListener {
            GlobalVars.btnSceltaPremuto = true
            val intent = Intent(this, scelta::class.java);
            startActivity(intent);
        }


    }


}