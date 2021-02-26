package com.example.rgp_project.panel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.rgp_project.GameLoop
import com.example.rgp_project.GameView

class UPSPanel(var x: Float, var y: Float, var gameLoop : GameLoop, var scene : GameView) : GamePanel{
    override fun draw(canvas: Canvas?) {
        var avg: String = gameLoop.getAverageUPS().toInt().toString();
        val paint : Paint = Paint()
        paint.color = Color.RED
        paint.textSize = 50F;
        canvas?.drawText("UPS: $avg", x, y, paint)
    }

}