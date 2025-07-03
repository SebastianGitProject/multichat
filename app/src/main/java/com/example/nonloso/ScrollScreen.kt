package com.example.nonloso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView


class ScrollScreen : AppCompatActivity() {

    private var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_screen)

        val mRelativeLayout = findViewById<RelativeLayout>(R.id.relative_layout_1)
        val mButtonRight = findViewById<at.markushi.ui.CircleButton>(R.id.button_2)
        val mTextView1 = findViewById<TextView>(R.id.text_view_1)
        val mTextView2 = findViewById<TextView>(R.id.text_view_2)
        val mTextView3 = findViewById<TextView>(R.id.text_view_3)
        val mTextView4 = findViewById<TextView>(R.id.text_view_4)
        var pallina1 = findViewById<ImageView>(R.id.pal1)
        var pallina2 = findViewById<ImageView>(R.id.pal2)
        var pallina3 = findViewById<ImageView>(R.id.pal3)
        var pallina4 = findViewById<ImageView>(R.id.pal4)
        val start = findViewById<Button>(R.id.startbtn)
        val btn = findViewById<TextView>(R.id.btn)

        pallina1.setBackgroundResource(R.drawable.pallinaattiva)
        mButtonRight.setOnClickListener {

            if(i == 0){
                pallina4.setBackgroundResource(R.drawable.pallinadisattivata)
                pallina1.setBackgroundResource(R.drawable.pallinaattiva)
                mTextView4.visibility = View.INVISIBLE
                val mSlideRight = Slide()
                mSlideRight.slideEdge = Gravity.END
                TransitionManager.beginDelayedTransition(mRelativeLayout, mSlideRight)

                mTextView1.visibility = View.VISIBLE
                i++
            }else if(i == 1){
                pallina1.setBackgroundResource(R.drawable.pallinadisattivata)
                pallina2.setBackgroundResource(R.drawable.pallinaattiva)
                mTextView1.visibility = View.INVISIBLE
                val mSlideRight = Slide()
                mSlideRight.slideEdge = Gravity.END
                TransitionManager.beginDelayedTransition(mRelativeLayout, mSlideRight)

                mTextView2.visibility = View.VISIBLE
                i++
            }else if(i == 2){
                pallina2.setBackgroundResource(R.drawable.pallinadisattivata)
                pallina3.setBackgroundResource(R.drawable.pallinaattiva)
                mTextView2.visibility = View.INVISIBLE
                val mSlideRight = Slide()
                mSlideRight.slideEdge = Gravity.END
                TransitionManager.beginDelayedTransition(mRelativeLayout, mSlideRight)

                mTextView3.visibility = View.VISIBLE
                i++
            }
            else if(i == 3){
                pallina3.setBackgroundResource(R.drawable.pallinadisattivata)
                pallina4.setBackgroundResource(R.drawable.pallinaattiva)
                mTextView3.visibility = View.INVISIBLE
                val mSlideRight = Slide()
                mSlideRight.slideEdge = Gravity.END
                TransitionManager.beginDelayedTransition(mRelativeLayout, mSlideRight)

                mTextView4.visibility = View.VISIBLE
                start.visibility = View.VISIBLE
                i=0
            }

            start.setOnClickListener {
                val intent = Intent(this, login::class.java)
                startActivity(intent)
            }

            btn.setOnClickListener{
                val intent = Intent(this, segreto::class.java)
                startActivity(intent)
            }



        }


    }


}