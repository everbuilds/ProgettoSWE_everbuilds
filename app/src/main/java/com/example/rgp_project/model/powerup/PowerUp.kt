package com.example.rgp_project.model.powerup

import android.graphics.Canvas
import com.example.rgp_project.model.Player

interface PowerUp {
    companion object {
        public val RADIUS: Float = 20f;
    }
    fun apply(player : Player)
    fun getX() : Float?
    fun getY() : Float?
    fun isUsed() : Boolean
    fun update() : Unit
    fun draw(canvas: Canvas?) : Unit
}