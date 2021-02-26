package com.example.rgp_project.model

import android.content.Context
import android.graphics.*
import android.util.Log
import com.example.rgp_project.GameView

class Bullet(var x : Float, var y : Float, var direction : Direction, var scene : GameView) {
    enum class Direction {
        UP, DOWN
    }
    public var damage = 300;
    private var isHit : Boolean = false;
    var paint : Paint = Paint();
    private val DY : Float = 10.0f
    companion object {
        var RADIUS: Float = 10.0f
        var id : Int = 0;
    }
    var _id = id;
    init {
        id++;
        paint.color = Color.YELLOW
    }
    fun draw(canvas: Canvas?) {
        canvas?.drawCircle(x, y , RADIUS , paint)
    }

    fun update() {
        y += when (direction) {
            Direction.UP -> -DY
            Direction.DOWN -> DY
        }
    }
    fun hit() : Unit{
        isHit = true
    }
    fun isHit() : Boolean{
        return isHit
    }
    fun toRemove() : Boolean{
        return isHit || this.y < 0 || this.y > scene.getMaxHeight()
    }

}