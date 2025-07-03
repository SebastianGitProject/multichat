package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class Register : AppCompatActivity() {
    private lateinit var help : Button
    private lateinit var credits : Button
    //private lateinit var settings : Button
    private lateinit var register : Button
    private lateinit var accedi : TextView
    private lateinit var testo1 : EditText
    private lateinit var testo2 : EditText
    private lateinit var testo3 : EditText
    private lateinit var emailOttenuta: String
    private var db = Database(this, null)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        db.printAllData()
        help = findViewById(R.id.help)
        credits = findViewById(R.id.registerCredits)
        //settings = findViewById(R.id.registerSettings)
        register = findViewById(R.id.registerRegistrati)
        accedi = findViewById(R.id.registerLogin)
        help.setOnClickListener{
            startActivity(Intent(this, Help::class.java))
        }
        /*settings.setOnClickListener{
            startActivity(Intent(this, Settings::class.java))
        }*/
        credits.setOnClickListener{
            startActivity(Intent(this, Credits::class.java))
        }
        accedi.setOnClickListener{
            startActivity(Intent(this, login::class.java))
        }
        register.setOnClickListener{
            testo1 = findViewById(R.id.registerNome)
            var name = testo1.text.toString()
            testo2 = findViewById(R.id.registerEmail)
            var email = testo2.text.toString()
            testo3 = findViewById(R.id.registerPassword)
            var password = testo3.text.toString()
            val name2 =  "'"+ name + "'"
            val email2  =  "'"+ email + "'"
            val password2  =  "'"+ password + "'"
            if(name.isNotEmpty()&&email.isNotEmpty()&&password.isNotEmpty()){
                try{
                    val controllo = db.getEmail(name2, password2)
                    if(controllo == true){
                        Toast.makeText(this, "L'utente è già registrato", Toast.LENGTH_SHORT).show()
                    }else{
                        try{
                            val creazione = db.addName(name, email, password)
                            if(creazione == false){
                                var emailOttenuta1 = db.getEmailValoreString(name2, password2).toString()
                                emailOttenuta = "'" + emailOttenuta1 + "'"
                                creazioneFolder(applicationContext)
                                startActivity(Intent(this, login::class.java))


                            }else{
                                Toast.makeText(this, "Non è possibile create l'account", Toast.LENGTH_SHORT).show()
                            }
                        }catch (e: SQLiteException){ //la query restituisce un sqlite_error se non trova i dati
                            Toast.makeText(this, "Non è possibile create l'account", Toast.LENGTH_SHORT).show()
                            Log.v("files", "${e}")
                        }
                    }
                }catch (e: SQLiteException){ //la query restituisce un sqlite_error se non trova i dati
                    try{
                        val creazione = db.addName(name, email, password)
                        if(creazione == true){
                            startActivity(Intent(this, login::class.java))
                        }
                    }catch (e: SQLiteException){ //la query restituisce un sqlite_error se non trova i dati
                        Toast.makeText(this, "Non è possibile create l'account", Toast.LENGTH_SHORT).show()
                        Log.v("files", "${e}")
                    }
                }





            }else{
                Toast.makeText(this, "Non hai inserito tutte le credenziali richieste", Toast.LENGTH_SHORT).show()
            }
        }
        credits.setOnClickListener{
            startActivity(Intent(this, Credits::class.java))
        }
        /*settings.setOnClickListener{
            startActivity(Intent(this, Settings::class.java))
        }*/
        accedi.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun creazioneFolder(context: Context){
        val data: String = "om namah"
        val path = context.filesDir


        var username = db.getDimenticata(emailOttenuta)
        val folder = File(path, "${username!!.username}")
        folder.mkdirs()

        Log.v("cartella", "Cartella esiste: ${folder.exists()}, percorso: ${folder.path}, nome: ${folder.name}")

        /*val file = File(folder, "test.txt")
        file.appendText("$data")
        Log.v("cartella", "File esiste: ${file.exists()}, percorso: ${file.path}, nome: ${file.name}")*/
    }
}