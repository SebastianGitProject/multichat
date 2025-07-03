package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.mlkit.nl.languageid.LanguageIdentification
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import pl.droidsonroids.gif.AnimationListener
import pl.droidsonroids.gif.GifDrawable
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.security.Permissions
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Locale
import kotlin.random.Random


class ChatbotNuovo : AppCompatActivity() {
    private lateinit var textToSpeech: TextToSpeech
    private var btnSpeak: Button? = null
    private var etSpeak: EditText? = null
    private val client = OkHttpClient()
    // creating variables on below line.
    private lateinit var txtResponse: TextView
    private lateinit var question: EditText
    private lateinit var btnSubmit: Button
    private lateinit var TextConv: LinearLayout
    private lateinit var btnCrediti: Button
    private lateinit var btnAvatar: Button
    private lateinit var conv1: EditText
    private lateinit var conv2: EditText
    private lateinit var scrollview: ScrollView
    private var shortAnimationDuration: Int = 0
    //private lateinit var btnModelli: Button
    private lateinit var gif: GifDrawable
    //private lateinit var btnChangeAvatar: Button
    //private lateinit var queryEdt: TextInputEditText
    private var rispostaUtente: String? = null
    private var rispostagpt: String? = null
    private val multiplePermissionId = 123
    private val multiplePermissionNameList = arrayListOf(
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
        android.Manifest.permission.FOREGROUND_SERVICE,
        android.Manifest.permission.FOREGROUND_SERVICE_CAMERA,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.FOREGROUND_SERVICE_MICROPHONE,
        android.Manifest.permission.ACCESS_NETWORK_STATE,
        android.Manifest.permission.INTERNET
    )
    private val PermissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions
    private val PERMISSION_REQUEST_CODE = 123
    private var fileNames: String? = ""
    private var fileContentss: String? = ""
    private var indexgpt = 0
    private var indexuser = 0
    private var buttonClicked = false
    private val handler = Handler()
    //private var filepath = applicationContext
    val languageCodes = mapOf(
        "it" to "ITALIAN",
        "jp" to "JAPANESE",
        "en" to "ENGLISH",
        "es" to "SPANISH",
        "fr" to "FRENCH",
        "de" to "GERMANY",
        "kr" to "KOREAN",
        "cn" to "CHINESE",
    )
    var languageCodeyyy = ""
    var languageName = "ITALIAN"
    private var startTime: ZonedDateTime? = null
    private var endTime: ZonedDateTime? = null
    private var modelloSelez = 0
    private var db = Database(this, null)
    private lateinit var percorsoDeiFile: String
    private val REQUEST_CODE_SPEECH_INPUT = 100
    private lateinit var mic_btn: at.markushi.ui.CircleButton




    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId", "WrongViewCast", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scrollview = findViewById(R.id.scrollview1)
        question = findViewById(R.id.etQuestion)
        btnSubmit = findViewById(R.id.btnSubmit)
        txtResponse = findViewById(R.id.txtResponse)
        var btnClose = findViewById<at.markushi.ui.CircleButton>(R.id.btnClose)
        TextConv = findViewById(R.id.conversazioni)
        mic_btn = findViewById(R.id.mic)
        //conv1 = findViewById(R.id.conv1)
        //conv2 = findViewById(R.id.conv2)
        //btnCrediti = findViewById(R.id.btn)
        //btnAvatar = findViewById(R.id.avatar)
        var btnModelli = findViewById<at.markushi.ui.CircleButton>(R.id.btnAvatar)
        //gif = findViewById(R.id.)
        var gifImageView = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif)
        val gifDrawable = gifImageView.drawable as GifDrawable

        modelloSelez = db.getAvatarID(GlobalVars.nomeAccount)!!
        Log.v("tempo", "modello: ${modelloSelez}")




        if(modelloSelez == 1){
            percorsoDeiFile = GlobalVars.percorsoModel1Folder
            val modelResourceId = resources.getIdentifier("modelstark1", "drawable", packageName)
            gifImageView.setImageResource(modelResourceId)

            gifDrawable.addAnimationListener(object : AnimationListener {
                override fun onAnimationCompleted(loopNumber: Int) {
                    gifDrawable.stop()
                    Log.v("files", "animazione terminata")

                    // Imposta la variabile su true quando l'animazione è completata
                    //isAnimationCompleted = true
                }
            })
            Log.v("cartelle", "percorsoDeiFile: ${percorsoDeiFile}, GlobalVars: ${GlobalVars.percorsoModel1Folder}")
        }else if(modelloSelez == 2){
            percorsoDeiFile = GlobalVars.percorsoModel2Folder
            val modelResourceId = resources.getIdentifier("modeljoker1", "drawable", packageName)
            gifImageView.setImageResource(modelResourceId)

            gifDrawable.addAnimationListener(object : AnimationListener {
                override fun onAnimationCompleted(loopNumber: Int) {
                    gifDrawable.stop()
                    Log.v("files", "animazione terminata")

                    // Imposta la variabile su true quando l'animazione è completata
                    //isAnimationCompleted = true
                }
            })
        }else if(modelloSelez == 3){
            percorsoDeiFile = GlobalVars.percorsoModel3Folder
            val modelResourceId = resources.getIdentifier("modelgirl1", "drawable", packageName)
            gifImageView.setImageResource(modelResourceId)

            gifDrawable.addAnimationListener(object : AnimationListener {
                override fun onAnimationCompleted(loopNumber: Int) {
                    gifDrawable.stop()
                    Log.v("files", "animazione terminata")

                    // Imposta la variabile su true quando l'animazione è completata
                    //isAnimationCompleted = true
                }
            })
        }else if(modelloSelez == 4){
            percorsoDeiFile = GlobalVars.percorsoModel4Folder
            val modelResourceId = resources.getIdentifier("modelfemina1", "drawable", packageName)
            gifImageView.setImageResource(modelResourceId)

            gifDrawable.addAnimationListener(object : AnimationListener {
                override fun onAnimationCompleted(loopNumber: Int) {
                    gifDrawable.stop()
                    Log.v("files", "animazione terminata")

                    // Imposta la variabile su true quando l'animazione è completata
                    //isAnimationCompleted = true
                }
            })
        }else if(modelloSelez == 5){
            percorsoDeiFile = GlobalVars.percorsoModel5Folder
            val modelResourceId = resources.getIdentifier("modeldaniel1", "drawable", packageName)
            gifImageView.setImageResource(modelResourceId)

            gifDrawable.addAnimationListener(object : AnimationListener {
                override fun onAnimationCompleted(loopNumber: Int) {
                    gifDrawable.stop()
                    Log.v("files", "animazione terminata")

                    // Imposta la variabile su true quando l'animazione è completata
                    //isAnimationCompleted = true
                }
            })
        }else if(modelloSelez == 6){
            percorsoDeiFile = GlobalVars.percorsoModel6Folder
            val modelResourceId = resources.getIdentifier("modelryan1", "drawable", packageName)
            gifImageView.setImageResource(modelResourceId)

            gifDrawable.addAnimationListener(object : AnimationListener {
                override fun onAnimationCompleted(loopNumber: Int) {
                    gifDrawable.stop()
                    Log.v("files", "animazione terminata")

                    // Imposta la variabile su true quando l'animazione è completata
                    //isAnimationCompleted = true
                }
            })
        }else if(modelloSelez == 7){
            percorsoDeiFile = GlobalVars.percorsoModel7Folder
            val modelResourceId = resources.getIdentifier("modelgwenda1", "drawable", packageName)
            gifImageView.setImageResource(modelResourceId)

            gifDrawable.addAnimationListener(object : AnimationListener {
                override fun onAnimationCompleted(loopNumber: Int) {
                    gifDrawable.stop()
                    Log.v("files", "animazione terminata")

                    // Imposta la variabile su true quando l'animazione è completata
                    //isAnimationCompleted = true
                }
            })
        }


        start()
        GlobalVars.btnClicked = false
        GlobalVars.btnNuovo = false
        fileNames = intent.getStringExtra("fileName")
        createAndWriteToFile(applicationContext)
        ReadTxt(applicationContext)











        val permissionsToRequest = arrayOf(
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            android.Manifest.permission.FOREGROUND_SERVICE,
            android.Manifest.permission.FOREGROUND_SERVICE_CAMERA,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.FOREGROUND_SERVICE_MICROPHONE,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.INTERNET
            // Aggiungi altri permessi qui...
        )

        val permissionsDeniedList = mutableListOf<String>()

        for (permission in permissionsToRequest) {
            if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsDeniedList.add(permission)
            }
        }

        if (permissionsDeniedList.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsDeniedList.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }


        //read e write del file txt per conservare le conversazioni
        //
        // Esempio di utilizzo
        val allTextFiles = listaFileTxt(applicationContext)
        for (fileName in allTextFiles) {
            Log.d("contenutoChat","File di testo trovato: $fileName")
        }



        startService(Intent(this, backgroundService::class.java))




        gifDrawable.addAnimationListener {
            if(gifDrawable.isRunning){
                gifDrawable.stop()
            }
        }


        txtResponse.setOnClickListener{
            txtResponse.visibility = View.INVISIBLE
            scrollview.visibility = View.VISIBLE

            TextConv.visibility = View.VISIBLE
            val animation1 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            TextConv.startAnimation(animation1)


            btnClose.visibility = View.VISIBLE
            val animation2 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            btnClose.startAnimation(animation2)






            for (i in 1..TextConv.childCount) {
                val scrollView = TextConv.getChildAt(i - 1) as? ScrollView
                scrollView?.let {
                    // Itera attraverso i TextView all'interno dello ScrollView
                    for (j in 1..it.childCount) {
                        val textView = it.getChildAt(j - 1) as? TextView
                        textView?.visibility = View.VISIBLE
                        val animation2 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                        textView?.startAnimation(animation2)
                    }
                }
            }
        }

        mic_btn.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "it-IT")

            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Parla ora")

            try{
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        textToSpeech = TextToSpeech(applicationContext) { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.US

            }
        }

        btnClose.setOnClickListener {
            btnClose.visibility = View.GONE
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            btnClose.startAnimation(animation)


            TextConv.visibility = View.GONE
            val animation2 = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            TextConv.startAnimation(animation2)

            scrollview.visibility = View.GONE








            for (i in 1..TextConv.childCount) {
                val scrollView = TextConv.getChildAt(i - 1) as? ScrollView
                scrollView?.let {
                    // Itera attraverso i TextView all'interno dello ScrollView
                    for (j in 1..it.childCount) {
                        val textView = it.getChildAt(j - 1) as? TextView
                        textView?.visibility = View.GONE
                        val animation2 = AnimationUtils.loadAnimation(this, R.anim.fade_out)
                        textView?.startAnimation(animation2)
                    }
                }
            }


            txtResponse.visibility = View.VISIBLE
        }


        btnModelli.setOnClickListener {
            var temp1 = db.getTempo(modelloSelez, GlobalVars.nomeAccount)
            var numeroTempo1 = "${temp1?.tempo}"
            var nT1 = numeroTempo1.toInt()
            endTime = ZonedDateTime.now(ZoneId.of("Europe/Rome"))
            if (startTime != null && endTime != null) {
                java.time.Duration.between(startTime, endTime).toMillis()
                val totalTime = java.time.Duration.between(startTime, endTime).toMillis().toInt()
                var totalTimeInt = (totalTime / 1000)
                totalTimeInt = totalTimeInt + nT1
                Log.v("tempo", "Tempo totale: ${totalTimeInt}, inizio: ${startTime}, fine: ${endTime} ")
                db.setTempo(modelloSelez,  GlobalVars.nomeAccount, totalTimeInt)
            }
            val intent = Intent(this, SchermataConversazioni::class.java)
            startActivity(intent)
        }


        btnSubmit.setOnClickListener {

            //txtResponse.typeWrite(this, "ciaooooooooooooooooooooooooooooo", 33L)
            val questions=question.text.toString()
            buttonClicked = true
            rispostaUtente = question.text.toString()
            calcolaLettere(rispostaUtente!!)
            //speakOut()
            if(rispostaUtente == null || rispostaUtente == ""){
                Toast.makeText(this, "Richiesta mancante",Toast.LENGTH_SHORT).show()
            }else {
                fileContentss = "\n\nUser:{" + question.text.toString() + "}finetesto\n\n"
                writeTxt(applicationContext)
                //readTxtNormale(applicationContext)
            }

            //--------------------individua la lingua----------------------
            if(rispostaUtente == null || rispostaUtente == ""){
                Toast.makeText(this, "Richiesta mancante",Toast.LENGTH_SHORT).show()
            }else {
                val text = question.text.toString()
                val languageIdentifier = LanguageIdentification.getClient()
                languageIdentifier.identifyLanguage(text)
                    .addOnSuccessListener { languageCode ->
                        if (languageCode != "und") {
                            Log.v("lingua", "languageCode: ${languageCode}")
                            textToSpeech.language = Locale(languageCode)
                            //textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
                        }
                    }
            }
            //------------------------------------------------------


            if(rispostaUtente == null || rispostaUtente == ""){
                Toast.makeText(this, "Richiesta mancante",Toast.LENGTH_SHORT).show()
            }else{
                getResponse(questions){response ->
                    runOnUiThread{
                        //txtResponse.text=response
                        rispostagpt = response

                        txtResponse.setText("")
                        txtResponse.typeWrite(this, "$response", 33L)
                        textToSpeech.speak(response, TextToSpeech.QUEUE_FLUSH, null, "")
                    }
                }
            }

            if(rispostagpt == null || rispostagpt == ""){
                fileContentss = "\n\nGpt:{" + rispostagpt + "}finetesto\n\n"
                writeTxt(applicationContext)
                readTxtNormale(applicationContext)
            }else{
                fileContentss = "\n\nGpt:{" + rispostagpt + "}finetesto\n\n"
                writeTxt(applicationContext)
                readTxtNormale(applicationContext)
            }

            //mettere tutto l'else all'interno del language identify in else
            if(rispostaUtente == null || rispostaUtente == ""){
                Toast.makeText(this, "Richiesta mancante",Toast.LENGTH_SHORT).show()
            }else{
                for (i in 1..2) {
                    val scrollView = ScrollView(this)
                    val editText = TextView(this)

                    // Imposta l'ID dell'EditText
                    editText.id = resources.getIdentifier("conv$i", "id", packageName)

                    // Imposta le proprietà dell'EditText
                    editText.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    editText.setPadding(60, 60, 60, 60)
                    editText.gravity = Gravity.TOP or Gravity.LEFT
                    editText.textSize = 20f
                    editText.maxLines = 10
                    editText.isVerticalScrollBarEnabled = true
                    editText.setTextColor(ContextCompat.getColor(this, R.color.black))
                    (editText.layoutParams as LinearLayout.LayoutParams).marginStart = 5.dpToPx()
                    (editText.layoutParams as LinearLayout.LayoutParams).marginEnd = 5.dpToPx()
                    (editText.layoutParams as LinearLayout.LayoutParams).topMargin = 25.dpToPx()
                    editText.visibility = View.VISIBLE

                    // Imposta il background alternato
                    if (i % 2 == 0) {
                        editText.setBackgroundResource(R.drawable.bordergpt)
                        if(rispostaUtente == null || rispostaUtente == ""){
                            Log.d("messaggi", "chatgpt - rispostaUtente: $rispostaUtente e risposta chatgpt: $rispostagpt")
                            editText.setText("Chat-gpt: nessuna risposta")
                        }else{
                            Log.d("messaggi", "chatgpt - rispostaUtente: $rispostaUtente e risposta chatgpt: $rispostagpt")
                            editText.setText("ChatGPT: " + rispostagpt)
                            //editText.setText("Chat-gpt: Risposta")
                            //txtResponse.typeWrite(this, "Chtagpt ti ha rispostoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo", 33L)
                            rispostaUtente = null

                        }

                    } else {
                        editText.setBackgroundResource(R.drawable.borderuser)
                        if(rispostaUtente == null || rispostaUtente == ""){
                            editText.setText("Utente: nessuna richiesta")
                        }else{
                            Log.d("messaggi", "utente - rispostaUtente: $rispostaUtente e risposta chatgpt: $rispostagpt")
                            editText.setText("Utente: " + rispostaUtente)
                        }

                    }


                    // Aggiungi EditText allo ScrollView e ScrollView al container
                    scrollView.addView(editText)
                    TextConv.addView(scrollView)
                }
            }
            question.setText("")
            val randomModelNumber = Random.nextInt(3, 6)
            if(modelloSelez == 1){
                val modelResourceId = resources.getIdentifier("modellostark$randomModelNumber", "drawable", packageName)
                gifImageView.setImageResource(modelResourceId)
            }else if(modelloSelez == 2) {
                val modelResourceId = resources.getIdentifier(
                    "modellojoker$randomModelNumber",
                    "drawable",
                    packageName
                )
                gifImageView.setImageResource(modelResourceId)
            }else if(modelloSelez == 3) {
                val modelResourceId = resources.getIdentifier(
                    "modellogirl$randomModelNumber",
                    "drawable",
                    packageName
                )
                gifImageView.setImageResource(modelResourceId)
            }
            else if(modelloSelez == 4) {
                val modelResourceId = resources.getIdentifier(
                    "modellofemina$randomModelNumber",
                    "drawable",
                    packageName
                )
                gifImageView.setImageResource(modelResourceId)
            }else if(modelloSelez == 5) {
                val modelResourceId = resources.getIdentifier(
                    "modellodaniel$randomModelNumber",
                    "drawable",
                    packageName
                )
                gifImageView.setImageResource(modelResourceId)
            }else if(modelloSelez == 6) {
                val modelResourceId = resources.getIdentifier(
                    "modelloryan$randomModelNumber",
                    "drawable",
                    packageName
                )
                gifImageView.setImageResource(modelResourceId)
            }else if(modelloSelez == 7) {
                val modelResourceId = resources.getIdentifier(
                    "modellogwenda$randomModelNumber",
                    "drawable",
                    packageName
                )
                gifImageView.setImageResource(modelResourceId)
            }
            Log.v("files", "animazione eseguita")


        }



        handler.postDelayed({
            if (!buttonClicked) {
                if(modelloSelez == 1){
                    val modelResourceId = resources.getIdentifier("modelstark2", "drawable", packageName)
                    gifImageView.setImageResource(modelResourceId)
                    gifDrawable.reset()
                    Log.v("files", "bottone non premuto")
                }else if(modelloSelez == 2){
                    val modelResourceId = resources.getIdentifier("modeljoker2", "drawable", packageName)
                    gifImageView.setImageResource(modelResourceId)
                    gifDrawable.reset()
                    Log.v("files", "bottone non premuto")
                }else if(modelloSelez == 3){
                    val modelResourceId = resources.getIdentifier("modelgirl2", "drawable", packageName)
                    gifImageView.setImageResource(modelResourceId)
                    gifDrawable.reset()
                    Log.v("files", "bottone non premuto")
                }else if(modelloSelez == 4){
                    val modelResourceId = resources.getIdentifier("modelfemina2", "drawable", packageName)
                    gifImageView.setImageResource(modelResourceId)
                    gifDrawable.reset()
                    Log.v("files", "bottone non premuto")
                }else if(modelloSelez == 5){
                    val modelResourceId = resources.getIdentifier("modeldaniel2", "drawable", packageName)
                    gifImageView.setImageResource(modelResourceId)
                    gifDrawable.reset()
                    Log.v("files", "bottone non premuto")
                }else if(modelloSelez == 6){
                    val modelResourceId = resources.getIdentifier("modelryan2", "drawable", packageName)
                    gifImageView.setImageResource(modelResourceId)
                    gifDrawable.reset()
                    Log.v("files", "bottone non premuto")
                }else if(modelloSelez == 7){
                    val modelResourceId = resources.getIdentifier("modelgwenda2", "drawable", packageName)
                    gifImageView.setImageResource(modelResourceId)
                    gifDrawable.reset()
                    Log.v("files", "bottone non premuto")
                }
            }
        }, 15000)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && null != data) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                question.setText(result!![0])
            }else{

            }
        }
    }

    override fun onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun start() {
        startTime = ZonedDateTime.now(ZoneId.of("Europe/Rome"))
    }




    fun calcolaLettere(testo: String){
        var temp1 = db.getTempo(modelloSelez, GlobalVars.nomeAccount)
        var numeroMes1 = "${temp1?.nummes}"
        var nM1 = numeroMes1.toInt()
        var count = testo.count()
        count = count + nM1
        db.setNumMes(modelloSelez, GlobalVars.nomeAccount, count )

    }


    fun getResponse(question:String, callback: (String) -> Unit){
        var api = "sk-proj-lsvImsVxTfd3nx085gHqT3BlbkFJ5iv97WM7xGvdIqmR8KET"
        var url = "https://api.openai.com/v1/completions"
        var requestBody="""
            {
            "model": "gpt-3.5-turbo-instruct",
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            //.header("User-Agent", "OkHttp Headers.java")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $api")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "API failed",e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body=response.body?.string()
                if (body != null) {
                    Log.v("data",body)
                }
                else{
                    Log.v("data", "Empty")
                }
            }
        })
    }
    fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
        this@typeWrite.text = ""
        lifecycleOwner.lifecycleScope.launch {
            repeat(text.length) {
                delay(intervalMs)
                this@typeWrite.text = text.take(it + 1)
            }
        }
    }



    private fun Int.dpToPx(): Int {
        val scale = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }




    fun createAndWriteToFile(context: Context) {
        val fileName = fileNames//"testProva.txt"
        val fileContents = fileContentss

        try {

            // Crea un oggetto File per il file specificato
            val file = File(percorsoDeiFile, fileName)

            if (file.exists()) {

                FileOutputStream(file, true).use { stream ->
                    if (fileContents != null) {

                        stream.write(fileContents.toByteArray())

                    }
                }
                Log.d("CHAT", "Contenuto aggiunto al file: ${file.name}")
                // Il file esiste già, non sovrascrivere il contenuto
                Log.d("CHAT","Il file $fileName esiste già. Non verrà sovrascritto.")
            } else {

                // Scrivi il contenuto nel file
                FileOutputStream(file).use { stream ->
                    if (fileContents != null) {

                        stream.write(fileContents.toByteArray())

                    }
                }
                Log.d("CHAT","File creato: ${file.name}")
            }


            // Leggi il contenuto del file
            val readContent = file.readText()
            Log.d("CHAT", "Contenuto del file: $readContent")
            Log.d("CHAT", "Directory del file: " + percorsoDeiFile)

            //println("Contenuto del file: $readContent")
        } catch (e: IOException) {
            Log.d("CHAT","Si è verificato un errore durante la creazione o la lettura del file. ${e.printStackTrace()}")
            e.printStackTrace()
        }

    }

    fun ReadTxt(context: Context) {
        TextConv = findViewById(R.id.conversazioni)
        val filePath = percorsoDeiFile // Sostituisci con il percorso del tuo file

        val file = File(filePath, fileNames)
        val readContent = file.readText()
        val lineCount = file.readLines().size
        val userTexts = mutableListOf<String>()
        val gptTexts = mutableListOf<String>()


        //--------------------------------presa testi dell'utente-----------------------------

        val userRegex = Regex("""User:\{.*?\}finetesto""")
        val gptRegex = Regex("""Gpt:\{.*?\}finetesto""")
        val text = readContent

        val userMatches = mutableListOf<String>()
        val gptMatches = mutableListOf<String>()

        val userMatchesResult = userRegex.findAll(text)
        for (match in userMatchesResult) {
            val extractedText = match.value.substringAfter("{").substringBefore("}").trim()
            userMatches.add(extractedText)
        }

        val gptMatchesResult = gptRegex.findAll(text)
        for (match in gptMatchesResult) {
            val extractedText = match.value.substringAfter("{").substringBefore("}").trim()
            gptMatches.add(extractedText)
        }

        //----------------------------------------------------------------------------
        var matchestot = gptMatches.size
        matchestot = matchestot * 2

        for (i in 1..matchestot) {

            val scrollView = ScrollView(this)
            val editText = TextView(this)

            // Imposta l'ID dell'EditText
            editText.id = resources.getIdentifier("conv$i", "id", packageName)

            // Imposta le proprietà dell'EditText
            editText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            editText.setPadding(60, 60, 60, 60)
            editText.gravity = Gravity.TOP or Gravity.LEFT
            editText.textSize = 20f
            editText.maxLines = 10
            editText.isVerticalScrollBarEnabled = true
            editText.setTextColor(ContextCompat.getColor(this, R.color.black))
            (editText.layoutParams as LinearLayout.LayoutParams).marginStart = 5.dpToPx()
            (editText.layoutParams as LinearLayout.LayoutParams).marginEnd = 5.dpToPx()
            (editText.layoutParams as LinearLayout.LayoutParams).topMargin = 25.dpToPx()
            editText.visibility = View.VISIBLE

            // Imposta il background alternato
            if (i % 2 == 0) {
                editText.setBackgroundResource(R.drawable.bordergpt)
                if (indexgpt < gptMatches.size){
                    Log.d("contenutoChat", "\nGpt Matches: ${gptMatches[indexgpt]}")
                    Log.d("contenutoChat", "indexgpt:" + i)
                    editText.setText("ChatGPT: ${gptMatches[indexgpt]}")
                    indexgpt++
                }
            } else {
                editText.setBackgroundResource(R.drawable.borderuser)
                if (indexuser < userMatches.size){
                    Log.d("contenutoChat", "\nUser Matches: ${userMatches[indexuser]}")
                    Log.d("contenutoChat", "indexuser:" + i)
                    editText.setText("Utente: ${userMatches[indexuser]}")
                    indexuser++
                }


            }


            // Aggiungi EditText allo ScrollView e ScrollView al container
            scrollView.addView(editText)
            TextConv.addView(scrollView)
        }
    }

    fun writeTxt(context: Context){
        val filePath = percorsoDeiFile

        val file = File(filePath, fileNames)
        FileWriter(file, true).use { writer ->
            writer.append(fileContentss)
        }
    }

    fun readTxtNormale(context: Context){
        val filePath = percorsoDeiFile

        val file = File(filePath, fileNames)
        val readContent = file.readText()
        //Log.d("contenutoChat", "" + readContent)

        val userRegex = Regex("""User:\{.*?\}finetesto""")
        val gptRegex = Regex("""Gpt:\{.*?\}finetesto""")
        val text = readContent

        val userMatches = mutableListOf<String>()
        val gptMatches = mutableListOf<String>()

        val userMatchesResult = userRegex.findAll(text)
        for (match in userMatchesResult) {
            val extractedText = match.value.substringAfter("{").substringBefore("}").trim()
            userMatches.add(extractedText)
        }

        val gptMatchesResult = gptRegex.findAll(text)
        for (match in gptMatchesResult) {
            val extractedText = match.value.substringAfter("{").substringBefore("}").trim()
            gptMatches.add(extractedText)
        }

        Log.d("contenutoChat","User Matches:")
        userMatches.forEach { Log.d("contenutoChat", "" + it )}

        Log.d("contenutoChat","\nGpt Matches:")
        gptMatches.forEach { Log.d("contenutoChat", "" + it ) }
    }


    fun listaFileTxt(context: Context): List<String> {
        val percorsoFolder = percorsoDeiFile
        val Folder = File(percorsoFolder)
        val filesDir = Folder
        //val filesDir = context.filesDir
        val textFiles = mutableListOf<String>()

        // Ottieni l'elenco di tutti i file nella directory dell'app
        val fileList = filesDir.listFiles()
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






    private fun checkMultiplePermission(): Boolean {
        val listPermissionNeeded = arrayListOf<String>()
        for (permission in multiplePermissionNameList) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermissionNeeded.add(permission)
                Log.d("permessi", "Permessi accettati: " + listPermissionNeeded.joinToString(" "))
            }
        }
        if (listPermissionNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionNeeded.toTypedArray(),
                multiplePermissionId
            )

            return false
        }
        return true
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Permissions", "${permissions[i]}: Accettato")
                } else {
                    Log.d("Permissions", "${permissions[i]}: Negato")
                }
            }
        }
    }

}