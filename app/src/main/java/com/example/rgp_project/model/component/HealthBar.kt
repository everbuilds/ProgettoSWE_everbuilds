package com.example.rgp_project.model.component

import android.content.Context
import android.graphics.*
import android.util.Log
import com.example.rgp_project.GameView
import com.example.rgp_project.model.HasHealth
import com.example.rgp_project.model.Player;

import java.util.*
import kotlin.math.pow

class HealthBar(private val initialLife: RectF, var paint: Paint, var element : HasHealth) {
    private var life = RectF(initialLife);

    fun draw(canvas: Canvas?) {
        canvas?.drawRect(life, paint)
    }

    fun update() {
        val curHealth = element.getCurrentHealth()
        val maxHealth = curHealth.coerceAtLeast(element.getMaxHealth())
        life.right = initialLife.left + (initialLife.right - initialLife.left) * (curHealth / maxHealth)
    }


}