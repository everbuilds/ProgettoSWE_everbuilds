package com.example.rgp_project.model

import android.content.Context
import android.graphics.*
import android.util.Log
import com.example.rgp_project.GameView
import java.util.*
import kotlin.math.pow

class Enemy(var player : Player, var left : Float, var top : Float, var width: Float, var height: Float , var scene : GameView) {
    var paint : Paint = Paint();
    private var velocity = 50;
    init {
        paint.color = Color.GREEN
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                shoot();
            }
        },2000,2000)
    }

    fun draw(canvas: Canvas?) {
        canvas?.drawRect(RectF(left, top, left + width, top+height), paint)
    }

    fun update(bullets : List<Bullet>) {
        var center : Float = left + width / 2;
        var dx : Float = 4f
        if(Math.abs(center - player.positionX) < dx ){
            left = player.positionX - width / 2
        } else if (center < player.positionX){
            left += dx
        } else {
            left -= dx
        }

        bullets.forEach{
                el -> run {
                    if (el.y+ Bullet.RADIUS < top+height && el.y- Bullet.RADIUS > top && el.x > left && el.x < left + width ) {
                        el.hit()
                    }
        }

        }
    }

    fun shoot() : Unit{
        scene.bullets.add(Bullet( left + width/2, top + height, Bullet.Direction.DOWN, scene))
    }

}