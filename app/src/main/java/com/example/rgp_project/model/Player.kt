package com.example.rgp_project.model

import android.content.Context
import android.graphics.*
import android.util.Log
import com.example.rgp_project.GameView
import com.example.rgp_project.model.component.HealthBar
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.sqrt

class Player(var positionX : Float, var positionY : Float, var radius : Float, var scene : GameView) : HasHealth {
    val paint : Paint = Paint();
    companion object{
        val MAX_HEALTH : Float = 1000f
        val SHOT_EVERY_UPDATES : Int = 100;
    }
    var curUpdatesFromShot = 0;

    var health : Float = MAX_HEALTH
    lateinit var healthBar : HealthBar ;
    init {
        paint.color = Color.RED
        var hbColor : Paint = Paint()
        hbColor.setColor(Color.CYAN)
        healthBar = HealthBar(
                RectF(20f, scene.getMaxHeight().toFloat() - 50, scene.getMaxWidth().toFloat()-20, scene.getMaxHeight().toFloat() - 10),
                hbColor,
                this
        );
    }
    fun draw(canvas: Canvas?) {
        canvas?.drawCircle(positionX, positionY, radius, paint)
        healthBar.draw(canvas)
    }

    fun update( bullets: List<Bullet>) {
        curUpdatesFromShot++
        if(curUpdatesFromShot >= SHOT_EVERY_UPDATES){
            curUpdatesFromShot = 0;
            shoot();
        }

        bullets.forEach{
            el -> run {
                val dist = sqrt((
                            (el.x - positionX).pow(2) +
                                    (el.y - positionY).pow(2)).toDouble())
                if (dist < Bullet.RADIUS + radius) {
                    el.hit()
                    if(el.damage.toFloat() > this.health){
                        this.health = 0f;
                    }else {
                        this.health -= el.damage.toFloat()
                    }
                }
            }
        }
        healthBar.update()
    }

    fun setPosition(x: Float, y: Float) {
        if(x > radius && x < scene.getMaxWidth() - radius){
            positionX = x
        } else if (x < radius) {
            positionX = radius;
        } else {
            positionX = scene.getMaxWidth() - radius
        }
        positionY = y;
    }
    fun shoot() : Unit{
        scene.bullets.add(Bullet( positionX , positionY - radius - Bullet.RADIUS - 2, Bullet.Direction.UP, scene))
    }

    override fun getCurrentHealth(): Float {
        return health
    }

    override fun getMaxHealth(): Float {
        return MAX_HEALTH
    }

}