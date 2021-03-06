package com.example.rgp_project.panel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.rgp_project.GameLoop
import com.example.rgp_project.GameView

class FPSPanel(var x: Float, var y: Float, var gameLoop : GameLoop, var scene : GameView) : GamePanel{
    override fun draw(canvas: Canvas?) {
        var avg: String = gameLoop.getAverageFPS().toInt().toString();
        val paint : Paint = Paint()
        paint.color = Color.RED
        paint.textSize = 50F;
        canvas?.drawText("FPS: $avg", 20F, scene.getMaxHeight().toFloat()/2, paint)
    }

}