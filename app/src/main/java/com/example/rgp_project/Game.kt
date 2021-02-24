package com.example.rgp_project

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import androidx.appcompat.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.MotionEvent.INVALID_POINTER_ID
import android.widget.Button
import androidx.core.view.MotionEventCompat
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

class Game: AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        //var xDelta:Int = 0
        //var yDelta:Int = 0
        var mainLayout:ViewGroup = findViewById(R.id.main)
        var image:ImageView = findViewById(R.id.image)
        var listener = View.OnTouchListener(function = {mainLayout, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                mainLayout.y = motionEvent.rawY - mainLayout.height/2
                mainLayout.x = motionEvent.rawX - mainLayout.width/2
            }

            true

        })
        image.setOnTouchListener(listener)
        /*image.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(view:View, event:MotionEvent):Boolean {
                val x = event.getRawX() as Int
                val y = event.getRawY() as Int
                when (event.getAction() and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_DOWN -> {
                        val lParams = view.getLayoutParams() as RelativeLayout.LayoutParams
                        xDelta = x - lParams.leftMargin
                        yDelta = y - lParams.topMargin
                    }
                    MotionEvent.ACTION_UP -> Toast.makeText(this@Game, "I'm here!", Toast.LENGTH_SHORT)
                        .show()
                    MotionEvent.ACTION_MOVE -> {
                        val layoutParams = view
                            .getLayoutParams() as RelativeLayout.LayoutParams
                        layoutParams.leftMargin = x - xDelta
                        layoutParams.topMargin = y - yDelta
                        layoutParams.rightMargin = 0
                        layoutParams.bottomMargin = 0
                        view.setLayoutParams(layoutParams)
                    }
                }
                //mainLayout?.invalidate()
                return true
            }
        })*/
    }
    /*private fun onTouchListener():OnTouchListener {
        return object: OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(view:View, event:MotionEvent):Boolean {
                val x = event.getRawX() as Int
                val y = event.getRawY() as Int
                when (event.getAction() and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_DOWN -> {
                        val lParams = view.getLayoutParams() as RelativeLayout.LayoutParams
                        xDelta = x - lParams.leftMargin
                        yDelta = y - lParams.topMargin
                    }
                    MotionEvent.ACTION_UP -> Toast.makeText(this@Game, "I'm here!", Toast.LENGTH_SHORT)
                            .show()
                    MotionEvent.ACTION_MOVE -> {
                        val layoutParams = view
                            .getLayoutParams() as RelativeLayout.LayoutParams
                        layoutParams.leftMargin = x - xDelta
                        layoutParams.topMargin = y - yDelta
                        layoutParams.rightMargin = 0
                        layoutParams.bottomMargin = 0
                        view.setLayoutParams(layoutParams)
                    }
                }
                mainLayout?.invalidate()
                return true
            }
        }
    }*/
}
