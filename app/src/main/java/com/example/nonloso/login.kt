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
import com.example.nonloso.Credits
import com.example.nonloso.R
import java.io.File

class login : AppCompatActivity() {
    private lateinit var help : Button
    private lateinit var credits : Button
    private lateinit var login : Button
    //private lateinit var settings : Button
    private lateinit var register : TextView
    private lateinit var testo1 : EditText
    private lateinit var testo2 : EditText
    private lateinit var recupero: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.logincentrale)
        val db = Database(this, null)
        db.logTableNames()
        db.printAllData()
        db.printAllDataModello()
        db.printAllDataConvers()
        db.printAllDataInfoConvers()
        var nomeCartelley = getFolderNames(applicationContext)
        for(cartelle in nomeCartelley){
            Log.v("cartelle", "Cartella: ${nomeCartelley}")
        }
        help = findViewById(R.id.help)
        credits = findViewById(R.id.credits)
        //settings = findViewById(R.id.LoginSettings)
        login = findViewById(R.id.login)
        register = findViewById(R.id.loginRegistrati)
        recupero = findViewById(R.id.loginRecupero)
        help.setOnClickListener{
            startActivity(Intent(this, Help::class.java))

        }
        register.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
        }
        credits.setOnClickListener{
            startActivity(Intent(this, Credits::class.java))
        }
        recupero.setOnClickListener {
            startActivity(Intent(this, Dimenticata::class.java))
        }

        login.setOnClickListener{
            testo1 = findViewById(R.id.loginName)
            var nome = testo1.text.toString()
            var name = "'"+ testo1.text.toString()+ "'"  //gli oggetti text in sqlite vengono riconosciuti come stringhe solo se hanno le ''
            testo2 = findViewById(R.id.loginPassword)
            var password = "'"+ testo2.text.toString()+ "'"

            if(name.isNotEmpty() && password.isNotEmpty()){

                try{
                    val email = db.getEmail(name, password)
                    Log.v("files", "boolean: ${email}")
                    if(email == true){
                        GlobalVars.nomeAccount = nome
                        var nomeCartelle = getFolderNames(applicationContext)
                        for(cartelle in nomeCartelle){
                            Log.v("cartelle", "Cartella: ${nomeCartelle}")
                        }

                        val selectedFolder = nomeCartelle.find { it == nome }
                        val percorso = selectedFolder?.let {
                            val folder = File(applicationContext.filesDir, it)
                            folder.absolutePath
                        }
                        if(percorso != null){
                            Log.v("cartelle", "Cartella trovata con nome: ${nome} e percorso: ${percorso}")
                            GlobalVars.percorsoFolder = percorso
                            val folderDaControllare = File(percorso)

                            if (folderDaControllare.isDirectory) {
                                //-----------------controllo subCartelle------------------------
                                val subFolders = folderDaControllare.listFiles { file -> file.isDirectory }
                                val model1Folder = subFolders?.find { folder -> folder.name == "Stark-Ironman" }
                                val model2Folder = subFolders?.find { folder -> folder.name == "Joker" }
                                val model3Folder = subFolders?.find { folder -> folder.name == "Jessica" }
                                val model4Folder = subFolders?.find { folder -> folder.name == "Aurora" }
                                val model5Folder = subFolders?.find { folder -> folder.name == "Daniel" }
                                val model6Folder = subFolders?.find { folder -> folder.name == "Ryan" }
                                val model7Folder = subFolders?.find { folder -> folder.name == "Gwenda" }

                                //-----------------Cartella Stark-Ironman-----------------------
                                if (model1Folder != null) {
                                    Log.v("cartelle", "La cartella del modello Stark-Ironman esiste già: ${model1Folder.path}")
                                    GlobalVars.percorsoModel1Folder = model1Folder.path
                                } else {
                                    Log.v("cartelle", "La cartella del modello Stark-Ironman non esiste")
                                    val newModel1Folder = File(folderDaControllare, "Stark-Ironman")
                                    if (newModel1Folder.mkdir()) {

                                        Log.v("cartelle", "La cartella del modello Stark-Ironman è stata creata")
                                        //Log.v("cartelle", "path model1 appena creata: ${newModel1Folder}")
                                        GlobalVars.percorsoModel1Folder = newModel1Folder.path
                                    } else {
                                        Log.v("cartelle", "Impossibile creare la cartella Stark-Ironman")
                                    }
                                }
                                //---------------------------------------------------------------
                                //-----------------Cartella Joker-----------------------
                                if (model2Folder != null) {
                                    Log.v("cartelle", "La cartella del modello Joker esiste già: ${model2Folder.path}")
                                    GlobalVars.percorsoModel2Folder = model2Folder.path
                                } else {
                                    Log.v("cartelle", "La cartella del modello Joker non esiste")
                                    val newModel2Folder = File(folderDaControllare, "Joker")
                                    if (newModel2Folder.mkdir()) {

                                        Log.v("cartelle", "La cartella del modello Joker è stata creata")
                                        GlobalVars.percorsoModel2Folder = newModel2Folder.path
                                    } else {
                                        Log.v("cartelle", "Impossibile creare la cartella Joker")
                                    }
                                }
                                //---------------------------------------------------------------
                                //-----------------Cartella Jessica-----------------------
                                if (model3Folder != null) {
                                    Log.v("cartelle", "La cartella del modello Jessica esiste già: ${model3Folder.path}")
                                    GlobalVars.percorsoModel3Folder = model3Folder.path
                                } else {
                                    Log.v("cartelle", "La cartella del modello Jessica non esiste")
                                    val newModel3Folder = File(folderDaControllare, "Jessica")
                                    if (newModel3Folder.mkdir()) {

                                        Log.v("cartelle", "La cartella del modello Jessica è stata creata")
                                        GlobalVars.percorsoModel3Folder = newModel3Folder.path
                                    } else {
                                        Log.v("cartelle", "Impossibile creare la cartella Jessica")
                                    }
                                }
                                //---------------------------------------------------------------
                                //-----------------Cartella Aurora-----------------------
                                if (model4Folder != null) {
                                    Log.v("cartelle", "La cartella del modello Aurora esiste già: ${model4Folder.path}")
                                    GlobalVars.percorsoModel4Folder = model4Folder.path
                                } else {
                                    Log.v("cartelle", "La cartella del modello Aurora non esiste")
                                    val newModel4Folder = File(folderDaControllare, "Aurora")
                                    if (newModel4Folder.mkdir()) {

                                        Log.v("cartelle", "La cartella del modello Aurora è stata creata")
                                        GlobalVars.percorsoModel4Folder = newModel4Folder.path
                                    } else {
                                        Log.v("cartelle", "Impossibile creare la cartella Aurora")
                                    }
                                }
                                //---------------------------------------------------------------
                                //-----------------Cartella Daniel-----------------------
                                if (model5Folder != null) {
                                    Log.v("cartelle", "La cartella del modello Daniel esiste già: ${model5Folder.path}")
                                    GlobalVars.percorsoModel5Folder = model5Folder.path
                                } else {
                                    Log.v("cartelle", "La cartella del modello Daniel non esiste")
                                    val newModel5Folder = File(folderDaControllare, "Daniel")
                                    if (newModel5Folder.mkdir()) {

                                        Log.v("cartelle", "La cartella del modello Daniel è stata creata")
                                        GlobalVars.percorsoModel5Folder = newModel5Folder.path
                                    } else {
                                        Log.v("cartelle", "Impossibile creare la cartella Daniel")
                                    }
                                }
                                //---------------------------------------------------------------
                                //-----------------Cartella Ryan-----------------------
                                if (model6Folder != null) {
                                    Log.v("cartelle", "La cartella del modello Ryan esiste già: ${model6Folder.path}")
                                    GlobalVars.percorsoModel6Folder = model6Folder.path
                                } else {
                                    Log.v("cartelle", "La cartella del modello Ryan non esiste")
                                    val newModel6Folder = File(folderDaControllare, "Ryan")
                                    if (newModel6Folder.mkdir()) {

                                        Log.v("cartelle", "La cartella del modello Ryan è stata creata")
                                        GlobalVars.percorsoModel6Folder = newModel6Folder.path
                                    } else {
                                        Log.v("cartelle", "Impossibile creare la cartella Ryan")
                                    }
                                }
                                //---------------------------------------------------------------
                                //-----------------Cartella Gwenda-----------------------
                                if (model7Folder != null) {
                                    Log.v("cartelle", "La cartella del modello Gwenda esiste già: ${model7Folder.path}")
                                    GlobalVars.percorsoModel7Folder = model7Folder.path
                                } else {
                                    Log.v("cartelle", "La cartella del modello Gwenda non esiste")
                                    val newModel7Folder = File(folderDaControllare, "Gwenda")
                                    if (newModel7Folder.mkdir()) {

                                        Log.v("cartelle", "La cartella del modello Gwenda è stata creata")
                                        GlobalVars.percorsoModel7Folder = newModel7Folder.path
                                    } else {
                                        Log.v("cartelle", "Impossibile creare la cartella Gwenda")
                                    }
                                }
                                //---------------------------------------------------------------
                                //------------------------------------------------------------------
                            } else {
                                println("La cartella selezionata non è valida.")
                            }
                        }else{
                            Log.v("cartelle", "Cartella non trovata con il nome: ${nome}")
                        }
                        startActivity(Intent(this, Home::class.java))
                    }else{
                        Toast.makeText(this, "Utente non trovato", Toast.LENGTH_SHORT).show()
                    }

                }catch (e: SQLiteException){ //la query restituisce un sqlite_error se non trova i dati
                    Toast.makeText(this, "Utente non trovato", Toast.LENGTH_SHORT).show()
                    Log.v("files", "${e}")
                }
            }else{
                Toast.makeText(this, "Non hai inserito tutte le credenziali richieste", Toast.LENGTH_SHORT).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getFolderNames(context: Context): MutableList<String> {
        val filesDir = File(context.filesDir.absolutePath)
        val folderNames = mutableListOf<String>()

        if (filesDir.isDirectory) {
            val folders = filesDir.listFiles { file -> file.isDirectory }
            folders?.forEach { folder ->
                folderNames.add(folder.name)
            }
        }

        return folderNames
    }
}