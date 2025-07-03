package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Dimenticata : AppCompatActivity() {
    private lateinit var recuperoBtn: Button
    private lateinit var indietro: Button
    private lateinit var credenziali: TextView
    private lateinit var email: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dimenticata)
        var db = Database(this, null)
        recuperoBtn = findViewById(R.id.recupera)
        indietro = findViewById(R.id.indietroo)
        credenziali = findViewById(R.id.testo)
        email = findViewById(R.id.email)
        recuperoBtn.setOnClickListener {
            var email =
                "'" + email.text.toString() + "'"  //gli oggetti text in sqlite vengono riconosciuti come stringhe solo se hanno le ''

            if (email.isNotEmpty()) {
                try {
                    val email = db.getDimenticata(email)
                    if (email != null) {
                        Log.v("files", "Username: ${email.username}, Password: ${email.password}")
                        credenziali.setText("Nome: ${email.username},\n Password: ${email.password}")
                    }else{
                        credenziali.setText("Nessuna email trovata...")
                    }

                } catch (e: SQLiteException) { //la query restituisce un sqlite_error se non trova i dati
                    Toast.makeText(this, "Email non trovata", Toast.LENGTH_SHORT).show()
                    Log.v("files", "${e}")
                }
            }

            indietro.setOnClickListener {
                finish()
            }
        }
    }
}
