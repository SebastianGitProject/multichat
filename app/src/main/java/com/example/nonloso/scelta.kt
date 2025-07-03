package com.example.nonloso

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.example.nonloso.R
import pl.droidsonroids.gif.AnimationListener
import pl.droidsonroids.gif.GifDrawable

class scelta : AppCompatActivity() {

    private lateinit var main11: LinearLayout
    private lateinit var main22: LinearLayout
    private lateinit var btnindietro: Button
    private lateinit var btnindietro2: Button
    private lateinit var btnavanti: Button
    private lateinit var avatar1: ImageView
    private lateinit var avatar2: ImageView
    private lateinit var avatar3: ImageView
    private lateinit var avatar4: ImageView
    private lateinit var avatar5: ImageView
    private lateinit var avatar6: ImageView
    private lateinit var avatar7: ImageView
    private lateinit var card1: LinearLayout
    private lateinit var card2: LinearLayout
    private lateinit var card3: LinearLayout
    private lateinit var card4: LinearLayout
    private lateinit var card5: LinearLayout
    private lateinit var card6: LinearLayout
    private lateinit var card7: LinearLayout
    private lateinit var back1: Button
    private lateinit var back2: Button
    private lateinit var back3: Button
    private lateinit var back4: Button
    private lateinit var back5: Button
    private lateinit var back6: Button
    private lateinit var back7: Button
    private lateinit var testo1: TextView
    private lateinit var testo2: TextView
    private lateinit var testo3: TextView
    private lateinit var testo4: TextView
    private lateinit var testo5: TextView
    private lateinit var testo6: TextView
    private lateinit var testo7: TextView
    private lateinit var selezionato1: Button
    private lateinit var selezionato2: Button
    private lateinit var selezionato3: Button
    private lateinit var selezionato4: Button
    private lateinit var selezionato5: Button
    private lateinit var selezionato6: Button
    private lateinit var selezionato7: Button
    private lateinit var gif: GifDrawable
    private lateinit var gif2: GifDrawable
    private lateinit var gif3: GifDrawable
    private lateinit var gif4: GifDrawable
    private lateinit var gif5: GifDrawable
    private lateinit var gif6: GifDrawable
    private lateinit var gif7: GifDrawable
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scelta2)
        var nome = GlobalVars.nomeAccount
        var db = Database(this, null)

        if(db.getRighe(nome)){
            var temp1 = db.getTempo(1, nome)
            var temp2 = db.getTempo(2, nome)
            var temp3 = db.getTempo(3, nome)
            var temp4 = db.getTempo(4, nome)
            var temp5 = db.getTempo(5, nome)
            var temp6 = db.getTempo(6, nome)
            var temp7 = db.getTempo(7, nome)


            //------- prendere il tempo dalla tabella model---------
            var numeroTempo1 = "${temp1?.tempo}"
            var nT1 = numeroTempo1.toInt() //converte da stringa a int
            var numeroTempo2 = "${temp2?.tempo}"
            var nT2 = numeroTempo2.toInt()
            var numeroTempo3 = "${temp3?.tempo}"
            var nT3 = numeroTempo3.toInt()
            var numeroTempo4 = "${temp4?.tempo}"
            var nT4 = numeroTempo4.toInt()
            var numeroTempo5 = "${temp5?.tempo}"
            var nT5 = numeroTempo5.toInt()
            var numeroTempo6 = "${temp6?.tempo}"
            var nT6 = numeroTempo6.toInt()
            var numeroTempo7 = "${temp7?.tempo}"
            var nT7 = numeroTempo7.toInt()
            //-------------------------------------------------------


            //------- prendere il numMessaggi dalla tabella model----------
            var numeroMes1 = "${temp1?.nummes}"
            var nM1 = numeroMes1.toInt()  //converte da stringa a int
            var numeroMes2 = "${temp2?.nummes}"
            var nM2 = numeroMes2.toInt()
            var numeroMes3 = "${temp3?.nummes}"
            var nM3 = numeroMes3.toInt()
            var numeroMes4 = "${temp4?.nummes}"
            var nM4 = numeroMes4.toInt()
            var numeroMes5 = "${temp5?.nummes}"
            var nM5 = numeroMes5.toInt()
            var numeroMes6 = "${temp6?.nummes}"
            var nM6 = numeroMes6.toInt()
            var numeroMes7 = "${temp7?.nummes}"
            var nM7 = numeroMes7.toInt()
            //-------------------------------------------------------------

            db.addModel(nome,nT1,nM1,"Stark-Ironman", 1)
            db.addModel(nome,nT2,nM2,"Joker", 2)
            db.addModel(nome,nT3,nM3,"Jessica", 3)
            db.addModel(nome,nT4,nM4,"Aurora", 4)
            db.addModel(nome,nT5,nM5,"Daniel", 5)
            db.addModel(nome,nT6,nM6,"Ryan", 6)
            db.addModel(nome,nT7,nM7,"Gwenda", 7)
        }else{
            db.addModel(nome,0,0,"Stark-Ironman", 1)
            db.addModel(nome,0,0,"Joker", 2)
            db.addModel(nome,0,0,"Jessica", 3)
            db.addModel(nome,0,0,"Aurora", 4)
            db.addModel(nome,0,0,"Daniel", 5)
            db.addModel(nome,0,0,"Ryan", 6)
            db.addModel(nome,0,0,"Gwenda", 7)
        }
        btnavanti = findViewById(R.id.avanti)
        btnindietro = findViewById(R.id.indietrobtn)
        btnindietro2 = findViewById(R.id.indietrobtn2)
        main11 = findViewById(R.id.main11)
        main22 = findViewById(R.id.main22)
        avatar1 = findViewById(R.id.ava1)
        avatar2 = findViewById(R.id.ava2)
        avatar3 = findViewById(R.id.ava3)
        avatar4 = findViewById(R.id.ava4)
        avatar5 = findViewById(R.id.ava5)
        avatar6 = findViewById(R.id.ava6)
        avatar7 = findViewById(R.id.ava7)
        card1 = findViewById(R.id.card1)
        card2 = findViewById(R.id.card2)
        card3 = findViewById(R.id.card3)
        card4 = findViewById(R.id.card4)
        card5 = findViewById(R.id.card5)
        card6 = findViewById(R.id.card6)
        card7 = findViewById(R.id.card7)
        back1 = findViewById(R.id.back1)
        back2 = findViewById(R.id.back2)
        back3 = findViewById(R.id.back3)
        back4 = findViewById(R.id.back4)
        back5 = findViewById(R.id.back5)
        back6 = findViewById(R.id.back6)
        back7 = findViewById(R.id.back7)
        testo1 = findViewById(R.id.testos1)
        testo2 = findViewById(R.id.testos2)
        testo3 = findViewById(R.id.testos3)
        testo4 = findViewById(R.id.testos4)
        testo5 = findViewById(R.id.testos5)
        testo6 = findViewById(R.id.testos6)
        testo7 = findViewById(R.id.testos7)
        selezionato1 = findViewById(R.id.seleziona1)
        selezionato2 = findViewById(R.id.seleziona2)
        selezionato3 = findViewById(R.id.seleziona3)
        selezionato4 = findViewById(R.id.seleziona4)
        selezionato5 = findViewById(R.id.seleziona5)
        selezionato6 = findViewById(R.id.seleziona6)
        selezionato7 = findViewById(R.id.seleziona7)
        var gifImageView1 = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif11)
        val gifDrawable1 = gifImageView1.drawable as GifDrawable
        var gifImageView2 = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif22)
        val gifDrawable2 = gifImageView2.drawable as GifDrawable
        var gifImageView3 = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif33)
        val gifDrawable3 = gifImageView3.drawable as GifDrawable
        var gifImageView4 = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif44)
        val gifDrawable4 = gifImageView4.drawable as GifDrawable
        var gifImageView5 = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif55)
        val gifDrawable5 = gifImageView5.drawable as GifDrawable
        var gifImageView6 = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif66)
        val gifDrawable6 = gifImageView6.drawable as GifDrawable
        var gifImageView7 = findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gif77)
        val gifDrawable7 = gifImageView7.drawable as GifDrawable


        gifDrawable1.addAnimationListener(object : AnimationListener { //listener per l'animazione della gif(eseguita solo 1 volta)
            override fun onAnimationCompleted(loopNumber: Int) {
                gifDrawable1.stop()
            }
        })
        gifDrawable2.addAnimationListener(object : AnimationListener {
            override fun onAnimationCompleted(loopNumber: Int) {
                gifDrawable2.stop()
            }
        })
        gifDrawable3.addAnimationListener(object : AnimationListener {
            override fun onAnimationCompleted(loopNumber: Int) {
                gifDrawable3.stop()
            }
        })
        gifDrawable4.addAnimationListener(object : AnimationListener {
            override fun onAnimationCompleted(loopNumber: Int) {
                gifDrawable4.stop()
            }
        })
        gifDrawable5.addAnimationListener(object : AnimationListener {
            override fun onAnimationCompleted(loopNumber: Int) {
                gifDrawable5.stop()
            }
        })
        gifDrawable6.addAnimationListener(object : AnimationListener {
            override fun onAnimationCompleted(loopNumber: Int) {
                gifDrawable6.stop()
            }
        })
        gifDrawable7.addAnimationListener(object : AnimationListener {
            override fun onAnimationCompleted(loopNumber: Int) {
                gifDrawable7.stop()
            }
        })

        btnindietro.setOnClickListener {
            finish()
        }
        btnindietro2.setOnClickListener {
            main22.visibility = View.INVISIBLE
            main11.visibility = View.VISIBLE
        }
        btnavanti.setOnClickListener {
            main11.visibility = View.INVISIBLE
            main22.visibility = View.VISIBLE
        }

        avatar1.setOnClickListener{
            card1.visibility = View.VISIBLE
            var testo = db.getModelloInfo(nome,1)
            if (testo != null) {
                Log.v("modelli", "Modello1 -> Tempo: ${testo.tempo}, NumMessaggi: ${testo.nummes}, NomeModello: ${testo.nomemod}")
                testo1.setText("Caratteristiche del modello: \nTempo utilizzo: ${testo.tempo}S,\n Numero Messaggi: ${testo.nummes},\n Nome Modello: ${testo.nomemod}")
            }else{
                testo1.setText("Nessuna caratteristica del modello trovata...")
            }
        }
        back1.setOnClickListener{
            card1.visibility = View.INVISIBLE
        }

        avatar2.setOnClickListener{
            card2.visibility = View.VISIBLE
            var testo = db.getModelloInfo(nome,2)
            if (testo != null) {
                Log.v("modelli", "Modello2 -> Tempo: ${testo.tempo}, NumMessaggi: ${testo.nummes}, NomeModello: ${testo.nomemod}")
                testo2.setText("Caratteristiche del modello: \nTempo utilizzo: ${testo.tempo}S,\n Numero Messaggi: ${testo.nummes},\n Nome Modello: ${testo.nomemod}")
            }else{
                testo2.setText("Nessuna caratteristica del modello trovata...")
            }
        }
        back2.setOnClickListener{
            card2.visibility = View.INVISIBLE
        }

        avatar3.setOnClickListener{
            card3.visibility = View.VISIBLE
            var testo = db.getModelloInfo(nome,3)
            if (testo != null) {
                Log.v("modelli", "Modello3 -> Tempo: ${testo.tempo}, NumMessaggi: ${testo.nummes}, NomeModello: ${testo.nomemod}")
                testo3.setText("Caratteristiche del modello: \nTempo utilizzo: ${testo.tempo}S,\n Numero Messaggi: ${testo.nummes},\n Nome Modello: ${testo.nomemod}")
            }else{
                testo3.setText("Nessuna caratteristica del modello trovata...")
            }
        }
        back3.setOnClickListener{
            card3.visibility = View.INVISIBLE
        }

        avatar4.setOnClickListener{
            card4.visibility = View.VISIBLE
            var testo = db.getModelloInfo(nome,4)
            if (testo != null) {
                Log.v("modelli", "Modello4 -> Tempo: ${testo.tempo}, NumMessaggi: ${testo.nummes}, NomeModello: ${testo.nomemod}")
                testo4.setText("Caratteristiche del modello: \nTempo utilizzo: ${testo.tempo}S,\n Numero Messaggi: ${testo.nummes},\n Nome Modello: ${testo.nomemod}")
            }else{
                testo4.setText("Nessuna caratteristica del modello trovata...")
            }
        }
        back4.setOnClickListener{
            card4.visibility = View.INVISIBLE
        }

        avatar5.setOnClickListener{
            card5.visibility = View.VISIBLE
            var testo = db.getModelloInfo(nome,5)
            if (testo != null) {
                Log.v("modelli", "Modello5 -> Tempo: ${testo.tempo}, NumMessaggi: ${testo.nummes}, NomeModello: ${testo.nomemod}")
                testo5.setText("Caratteristiche del modello: \nTempo utilizzo: ${testo.tempo}S,\n Numero Messaggi: ${testo.nummes},\n Nome Modello: ${testo.nomemod}")
            }else{
                testo5.setText("Nessuna caratteristica del modello trovata...")
            }
        }
        back5.setOnClickListener{
            card5.visibility = View.INVISIBLE
        }

        avatar6.setOnClickListener{
            card6.visibility = View.VISIBLE
            var testo = db.getModelloInfo(nome,6)
            if (testo != null) {
                Log.v("modelli", "Modello6 -> Tempo: ${testo.tempo}, NumMessaggi: ${testo.nummes}, NomeModello: ${testo.nomemod}")
                testo6.setText("Caratteristiche del modello: \nTempo utilizzo: ${testo.tempo}S,\n Numero Messaggi: ${testo.nummes},\n Nome Modello: ${testo.nomemod}")
            }else{
                testo6.setText("Nessuna caratteristica del modello trovata...")
            }
        }
        back6.setOnClickListener{
            card6.visibility = View.INVISIBLE
        }

        avatar7.setOnClickListener{
            card7.visibility = View.VISIBLE
            var testo = db.getModelloInfo(nome,7)
            if (testo != null) {
                Log.v("modelli", "Modello7 -> Tempo: ${testo.tempo}, NumMessaggi: ${testo.nummes}, NomeModello: ${testo.nomemod}")
                testo7.setText("Caratteristiche del modello: \nTempo utilizzo: ${testo.tempo}S,\n Numero Messaggi: ${testo.nummes},\n Nome Modello: ${testo.nomemod}")
            }else{
                testo7.setText("Nessuna caratteristica del modello trovata...")
            }
        }
        back7.setOnClickListener{
            card7.visibility = View.INVISIBLE
        }

        selezionato1.setOnClickListener {
            Toast.makeText(this, "MODELLO SELEZIONATO", Toast.LENGTH_SHORT).show()
            db.aggiornaSelezionato(1,1,nome)
            GlobalVars.selezionato = 1

            if(db.getRigheConv(GlobalVars.nomeAccount, GlobalVars.selezionato)){
                val tempY = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
                var numeroFile1 = "${tempY?.numfile}"
                var nF1 = numeroFile1.toInt()


                db.addConvers(GlobalVars.nomeAccount, nF1, GlobalVars.selezionato)
            }else{
                db.addConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
            }

            db.updateAvatar(1,nome)
            db.printAllDataModello()
            db.printAllData()
        }
        selezionato2.setOnClickListener {
            Toast.makeText(this, "MODELLO SELEZIONATO", Toast.LENGTH_SHORT).show()
            db.aggiornaSelezionato(2,2,nome)
            GlobalVars.selezionato = 2

            if(db.getRigheConv(GlobalVars.nomeAccount, GlobalVars.selezionato)){
                val tempY = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
                var numeroFile1 = "${tempY?.numfile}"
                var nF1 = numeroFile1.toInt()


                db.addConvers(GlobalVars.nomeAccount, nF1, GlobalVars.selezionato)
            }else{
                db.addConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
            }

            db.updateAvatar(2,nome)
            db.printAllDataModello()
            db.printAllData()
        }
        selezionato3.setOnClickListener {
            Toast.makeText(this, "MODELLO SELEZIONATO", Toast.LENGTH_SHORT).show()
            db.aggiornaSelezionato(3,3,nome)
            GlobalVars.selezionato = 3

            if(db.getRigheConv(GlobalVars.nomeAccount, GlobalVars.selezionato)){
                val tempY = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
                var numeroFile1 = "${tempY?.numfile}"
                var nF1 = numeroFile1.toInt()


                db.addConvers(GlobalVars.nomeAccount, nF1, GlobalVars.selezionato)
            }else{
                db.addConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
            }

            db.updateAvatar(3,nome)
            db.printAllDataModello()
            db.printAllData()
        }
        selezionato4.setOnClickListener {
            Toast.makeText(this, "MODELLO SELEZIONATO", Toast.LENGTH_SHORT).show()
            db.aggiornaSelezionato(4,4,nome)
            GlobalVars.selezionato = 4

            if(db.getRigheConv(GlobalVars.nomeAccount, GlobalVars.selezionato)){
                val tempY = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
                var numeroFile1 = "${tempY?.numfile}"
                var nF1 = numeroFile1.toInt()


                db.addConvers(GlobalVars.nomeAccount, nF1, GlobalVars.selezionato)
            }else{
                db.addConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
            }

            db.updateAvatar(4,nome)
            db.printAllDataModello()
            db.printAllData()
        }
        selezionato5.setOnClickListener {
            Toast.makeText(this, "MODELLO SELEZIONATO", Toast.LENGTH_SHORT).show()
            db.aggiornaSelezionato(5,5,nome)
            GlobalVars.selezionato = 5

            if(db.getRigheConv(GlobalVars.nomeAccount, GlobalVars.selezionato)){
                val tempY = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
                var numeroFile1 = "${tempY?.numfile}"
                var nF1 = numeroFile1.toInt()


                db.addConvers(GlobalVars.nomeAccount, nF1, GlobalVars.selezionato)
            }else{
                db.addConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
            }

            db.updateAvatar(5,nome)
            db.printAllDataModello()
            db.printAllData()
        }

        selezionato6.setOnClickListener {
            Toast.makeText(this, "MODELLO SELEZIONATO", Toast.LENGTH_SHORT).show()
            db.aggiornaSelezionato(6,6,nome)
            GlobalVars.selezionato = 6

            if(db.getRigheConv(GlobalVars.nomeAccount, GlobalVars.selezionato)){
                val tempY = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
                var numeroFile1 = "${tempY?.numfile}"
                var nF1 = numeroFile1.toInt()


                db.addConvers(GlobalVars.nomeAccount, nF1, GlobalVars.selezionato)
            }else{
                db.addConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
            }

            db.updateAvatar(6,nome)
            db.printAllDataModello()
            db.printAllData()
        }

        selezionato7.setOnClickListener {
            Toast.makeText(this, "MODELLO SELEZIONATO", Toast.LENGTH_SHORT).show()
            db.aggiornaSelezionato(7,7,nome)
            GlobalVars.selezionato = 7

            if(db.getRigheConv(GlobalVars.nomeAccount, GlobalVars.selezionato)){
                val tempY = db.getNumFile(GlobalVars.nomeAccount, GlobalVars.selezionato)
                var numeroFile1 = "${tempY?.numfile}"
                var nF1 = numeroFile1.toInt()


                db.addConvers(GlobalVars.nomeAccount, nF1, GlobalVars.selezionato)
            }else{
                db.addConvers(GlobalVars.nomeAccount, 0, GlobalVars.selezionato)
            }

            db.updateAvatar(7,nome)
            db.printAllDataModello()
            db.printAllData()
        }
    }



}

