package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class SchermataConversazioni : AppCompatActivity() /*, View.OnClickListener*/{

    private lateinit var buttonNuovo: ImageView
    private lateinit var indietrobtn: ImageView
    private lateinit var TextConv: LinearLayout
    private var indexModifica = 0
    private var indexBtn = 0
    private var i = 1
    private var index = 0
    private val createdTime = mutableListOf<String>()
    private var btnClicked = false
    private var btnNuovo = false
    private var percorsoDeiFile = ""

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schermata_conversazioni)

        Log.v("segreto", "ciaoooooooo, sono in onCreate")

        buttonNuovo = findViewById(R.id.btnNuovo)
        TextConv = findViewById(R.id.TextConv)
        indietrobtn = findViewById(R.id.indietroyu)

        val db = Database(this, null)
        var idmodel = db.getAvatarID(GlobalVars.nomeAccount)
        if(idmodel == 1){
            percorsoDeiFile = GlobalVars.percorsoModel1Folder
        }else if(idmodel == 2){
            percorsoDeiFile = GlobalVars.percorsoModel2Folder
        }else if(idmodel == 3){
            percorsoDeiFile = GlobalVars.percorsoModel3Folder
        }else if(idmodel == 4){
            percorsoDeiFile = GlobalVars.percorsoModel4Folder
        }else if(idmodel == 5){
            percorsoDeiFile = GlobalVars.percorsoModel5Folder
        }else if(idmodel == 6){
            percorsoDeiFile = GlobalVars.percorsoModel6Folder
        }else if(idmodel == 7){
            percorsoDeiFile = GlobalVars.percorsoModel7Folder
        }

        //----------------POTERE DIVINO-------------------
        //delete(applicationContext, percorsoDeiFile)
        //db.deleteTable4()
        //db.aggiornaConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
        //------------------------------------------------

        val numeroFile = getFileCountInFilesDir(applicationContext, percorsoDeiFile)


        indietrobtn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        Log.v("files", "numero files: ${numeroFile}")
        var txt = listaFileTxt(applicationContext, percorsoDeiFile)
        for(texty in txt){
            Log.v("files", "Nome File: ${texty}")
        }
        if (numeroFile == 0) {
            val scrollView = ScrollView(this)
            val editText = TextView(this)

            // Imposta le proprietà dell'EditText
            editText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            editText.setPadding(10, 70, 10, 70)
            editText.gravity = Gravity.CENTER
            editText.text = "Nessuna conversazione trovata"
            editText.setTextColor(1)
            editText.textSize = 17f
            editText.maxLines = 2
            editText.isVerticalScrollBarEnabled = true
            editText.setTextColor(ContextCompat.getColor(this, R.color.black))
            (editText.layoutParams as LinearLayout.LayoutParams).marginStart = 70.dpToPx()
            (editText.layoutParams as LinearLayout.LayoutParams).marginEnd = 70.dpToPx()
            (editText.layoutParams as LinearLayout.LayoutParams).topMargin = 150.dpToPx()
            editText.visibility = View.VISIBLE

            // Aggiungi EditText allo ScrollView e ScrollView al container
            scrollView.addView(editText)
            TextConv.addView(scrollView)
        } else {

            /*val allTextFiles = listaFileTxt(applicationContext, percorsoDeiFile)

            for (fileName in allTextFiles) {
                Log.v("files", "File di testo trovato: $fileName")
            }

            val mutableTextFiles: MutableList<String> = allTextFiles.toMutableList()


            val timestamps = getLastModifiedTimestampsForTxtFiles(applicationContext, percorsoDeiFile)


            val limite = 5*/

            var numInfo = db.getInfoConv(GlobalVars.nomeAccount, GlobalVars.selezionato)

            for (i in 0 until (numInfo?.size!!)) {
                    val scrollView = ScrollView(this)
                    val editText = Button(this)




                    editText.id = i



                    // Imposta le proprietà dell'EditText
                    editText.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    editText.setPadding(10, 70, 10, 70)
                    editText.gravity = Gravity.CENTER
                    editText.textSize = 17f
                    editText.maxLines = 2
                    editText.isVerticalScrollBarEnabled = true
                    editText.setTextColor(ContextCompat.getColor(this, R.color.black))
                    (editText.layoutParams as LinearLayout.LayoutParams).marginStart = 70.dpToPx()
                    (editText.layoutParams as LinearLayout.LayoutParams).marginEnd = 70.dpToPx()
                    (editText.layoutParams as LinearLayout.LayoutParams).topMargin = 25.dpToPx()
                    editText.visibility = View.VISIBLE

                    // Imposta il background alternato
                    editText.setBackgroundResource(R.drawable.borderstorageconv2)


                Log.v("files", "fileeeee: ${numInfo?.get(i)}")


                    if (indexModifica < numInfo?.size!! /*&& (GlobalVars.btnClicked != true && GlobalVars.btnNuovo != true)*/) {
                       // val string = timestamps.values.elementAtOrNull(indexModifica)
                        //editText.setText(timestamps.values.elementAtOrNull(indexModifica))
                        editText.setText(numInfo?.get(i))
                        indexModifica++
                        Log.v("files", "nome impostato nei file!")
                    }

                    scrollView.addView(editText)
                    TextConv.addView(scrollView)



            }


            val rootView = findViewById<View>(android.R.id.content)
            setOnClickListeners(rootView)


            for (i in 0..TextConv.childCount) {
                index = i
                val buttonId = resources.getIdentifier("btn$i", "id", packageName)
                val button = findViewById<Button>(buttonId)

                val TextFile = listaFileTxt(applicationContext, percorsoDeiFile)

            }


        }



        buttonNuovo.setOnClickListener {
            btnNuovo = true
            GlobalVars.btnNuovo = true
            val allTextFiles = listaFileTxt(applicationContext, percorsoDeiFile)


            val numeriNomiFile = allTextFiles.mapNotNull { it.substringAfter("file").substringBefore(".txt").toIntOrNull() }

            val numeroMassimo = numeriNomiFile.maxOrNull() ?: 0
            val intervalloNumeri = 1..numeroMassimo
            val numeroDaVerificare = i

            val numeroPresente = numeroDaVerificare <= numeroMassimo
            val numeriNomiFileStringa = numeriNomiFile.joinToString(", ")

            val numeroFile = getFileCountInFilesDir(applicationContext, percorsoDeiFile)



            Log.v("files", "i: ${i}, numero massimo: ${numeroMassimo}")
            var startTime = ZonedDateTime.now(ZoneId.of("Europe/Rome"))
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val formattedTime = startTime.format(formatter)
            db.addInfoConv(GlobalVars.nomeAccount, GlobalVars.selezionato, formattedTime)
            var data = db.getInfoConv(GlobalVars.nomeAccount, GlobalVars.selezionato)  //forse ottiene solo 1 dato e non molteplici
            val intent = Intent(this, ChatbotNuovo::class.java)
            var num = data?.size
            Log.v("pippos", "num prima: ${num}")
            //num = num?.plus(1)
            Log.v("pippos", "num dopo: ${num}")
            intent.putExtra("fileName", "file${num}.txt")
            var nfile = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
            var numDinFile = nfile?.numfile
            numDinFile = numDinFile?.plus(1)
            if (numDinFile != null) {
                db.aggiornaConvers(GlobalVars.nomeAccount, numDinFile ,GlobalVars.selezionato)
            }else{
                Log.v("error", "Impossibile incrementare numfile della terza tabella: NumDinFile:${numDinFile}, num: ${num}")
            }
            startActivity(intent)
            /*
            if(numeroFile == 1 && !numeroPresente){

                Log.v("files", "i dopo1: ${i}")
                val intent = Intent(this, ChatbotNuovo::class.java)
                intent.putExtra("fileName", "file$i.txt")
                startActivity(intent)

            }else{
                if (numeroPresente) {
                    i = numeroMassimo
                    i++
                    Log.v("files", "i dopo2: ${i}")
                    val intent = Intent(this, ChatbotNuovo::class.java)
                    intent.putExtra("fileName", "file$i.txt")
                    startActivity(intent)
                    Log.v("files", "numeroPresente")
                } else {
                    Log.v("files", "i dopo3: ${i}")
                    val intent = Intent(this, ChatbotNuovo::class.java)
                    intent.putExtra("fileName", "file$i.txt")
                    startActivity(intent)
                    Log.v("files", "numero non Presenteeeeeeeeeeeeeeeeeeeeeeeeeeeeee")

                }
            }*/

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onResume(){
        super.onResume()
        Log.v("segreto", "ciaoooooooo, sono in onResume")

    }


    override fun onDestroy(){
        super.onDestroy()
        Log.v("segreto", "ciaoooooooo, sono stato eliminato tramite onDestroy")
    }

    override fun onStop(){
        super.onStop()
        Log.v("segreto", "ciaoooooooo, sono in modalità onStop")
    }


    private fun setOnClickListeners(view: View) {
        btnClicked = true
        GlobalVars.btnClicked = true
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                if (child is Button) {
                    child.setOnClickListener {
                        val allTextFiles = listaFileTxt(applicationContext, percorsoDeiFile)
                        var buttonId = child.id
                        Log.v("files", "ID bottone clickato: ${buttonId}, file: file${buttonId}")
                        val intent = Intent(this, MainActivity::class.java)
                        buttonId = buttonId + 1
                        intent.putExtra("fileName", "file${buttonId}.txt")
                        startActivity(intent)
                    }
                } else if (child is ViewGroup) {
                    setOnClickListeners(child)
                }
            }
        }
    }


    fun delete(context: Context, percorsoCartella: String){
        val percorsoFolder = percorsoCartella
        val Folder = File(percorsoFolder)

            val textFiles = mutableListOf<String>()
            val fileList = Folder.listFiles()//context.filesDir.listFiles()
            if (fileList != null) {
                for (file in fileList) {
                    if (file.isFile && file.name.endsWith(".txt")) {
                        file.delete()
                    }
                }
            }
        //}




    }

    fun listaFileTxt(context: Context, percorsoCartella: String): List<String> {
        val percorsoFolder = percorsoCartella
        val Folder = File(percorsoFolder)

            val textFiles = mutableListOf<String>()

            // Ottieni l'elenco di tutti i file nella directory dell'app
            val fileList = Folder.listFiles()
            if (fileList != null) {
                for (file in fileList) {
                    if (file.isFile && file.name.endsWith(".txt")) {
                        // Aggiungi il nome del file di testo all'elenco
                        textFiles.add(file.name)
                    }
                }
            }
            return textFiles


    }

    fun getFileCountInFilesDir(context: Context, percorsoCartella: String): Int {
        val percorsoFolder = percorsoCartella
        val Folder = File(percorsoFolder)


        val files = Folder.listFiles()

        return files?.size ?: 0
    }




    fun getLastModifiedTimestampsForTxtFiles(context: Context, percorsoCartella: String): Map<String, String> {
        val percorsoFolder = percorsoCartella
        val Folder = File(percorsoFolder)
        val txtFiles = Folder.listFiles { _, name -> name.endsWith(".txt") }
        val timestampsMap = mutableMapOf<String, String>()

        val romeDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val romeTimeZone = TimeZone.getTimeZone("Europe/Rome")
        romeDateFormat.timeZone = romeTimeZone

        txtFiles?.forEach { file ->
            val lastModified = file.lastModified()
            val formattedDate = romeDateFormat.format(Date(lastModified))
            timestampsMap[file.name] = formattedDate
        }

        val sortedTimestamps = timestampsMap.entries.sortedBy { it.value }
        val sortedMap = sortedTimestamps.associate { it.key to it.value }

        return sortedMap
    }

    fun setModifiedTimeFile(context: Context) {

        val numeroFile = getFileCountInFilesDir(applicationContext, percorsoDeiFile)
            for (i in 1..numeroFile) {
                val fileName = "file$i.txt"
                val file = File(fileName)

                // Imposta la data di modifica del file
                if (file.exists()) {
                    val modifiedTime = createdTime.getOrNull(i - 1)
                    if (modifiedTime != null) {
                        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ITALY)
                        val date = sdf.parse(modifiedTime)
                        file.setLastModified(date.time)
                        Log.v("files","File $fileName modificato con successo alla data: $modifiedTime")
                    } else {
                        Log.v("files","Errore: Nessuna data corrispondente per il file $fileName")
                    }
                } else {
                    Log.v("files","Errore: Il file $fileName non esiste.")
                }
            }
    }



    private fun Int.dpToPx(): Int {
        val scale = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt() //i dp hanno un'acuratezza maggiore dei px
    }
}