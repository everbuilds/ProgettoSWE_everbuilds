package com.example.rgp_project.model

import android.content.Context
import android.graphics.*
import android.util.Log
import com.example.rgp_project.GameView
import com.example.rgp_project.model.component.HealthBar
import java.util.*
import kotlin.math.pow

class Enemy(var player : Player, var left : Float, var top : Float, var width: Float, var height: Float , var scene : GameView) : HasHealth{
    var paint : Paint = Paint();
    private var velocity = 50;
    companion object{
        val MAX_HEALTH : Float = 1000f
        val SHOT_EVERY_UPDATES : Int = 100;
    }
    var curUpdatesFromShot = 0;
    var health : Float = MAX_HEALTH
    var healthBar : HealthBar;
    init {
        paint.color = Color.GREEN

        var hbColor : Paint = Paint()
        hbColor.setColor(Color.GRAY)
        healthBar = HealthBar(
                RectF(20f, 10f, scene.getMaxWidth().toFloat()-20, 50f),
                hbColor,
                this
        );
    }

    fun draw(canvas: Canvas?) {
        canvas?.drawRect(RectF(left, top, left + width, top+height), paint)
        healthBar.draw(canvas)
    }

    fun update(bullets : List<Bullet>) {

        curUpdatesFromShot++
        if(curUpdatesFromShot >= SHOT_EVERY_UPDATES){
            curUpdatesFromShot = 0;
            shoot();
        }


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

    fun shoot() : Unit{
        scene.bullets.add(Bullet( left + width/2, top + height, Bullet.Direction.DOWN, scene))
    }

    override fun getCurrentHealth(): Float {
        return health
    }

    override fun getMaxHealth(): Float {
        return MAX_HEALTH
    }

}