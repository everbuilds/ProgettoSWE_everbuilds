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

    private lateinit var mainLayout:ViewGroup
    private lateinit var image:ImageView

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        mainLayout = findViewById(R.id.main)
        image = findViewById(R.id.image)
        var listener = View.OnTouchListener(function = {mainLayout, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                mainLayout.y = motionEvent.rawY - mainLayout.height/2
                mainLayout.x = motionEvent.rawX - mainLayout.width/2
            }

            true

        })
        image.setOnTouchListener(listener)
    }
}
