package com.example.rgp_project

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.Button
import android.widget.ImageView

class Game : AppCompatActivity() {

    private var mVelocityTracker: VelocityTracker? = null
    var Xposition = 25
    var Yposition = 600
    //var right = 50
    //var bottom = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels

        val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)

        var shapeDrawable: ShapeDrawable
        shapeDrawable = ShapeDrawable(RectShape())
        shapeDrawable.setBounds( Xposition-25, Yposition-25 ,Xposition+25,Yposition+25)
        shapeDrawable.getPaint().setColor(Color.parseColor("#009944"))
        shapeDrawable.draw(canvas)


        val imageV = findViewById<ImageView>(R.id.imageV)
        imageV.background = BitmapDrawable(getResources(), bitmap)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val x : Float = event.x
        val y : Float = event.y
        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                /*var dx = x-Xposition
                var dy = y-Yposition*/
                Xposition = x.toInt()
                Yposition = y.toInt()
                //println(Xposition)
                //println(Yposition)
            }
            MotionEvent.ACTION_UP -> {
                var view : View =  
            }
        }
        return true
    }
}