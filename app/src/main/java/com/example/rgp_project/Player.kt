package com.example.rgp_project

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF

class Player(context: Context, private val screenX: Int, private val screenY: Int) {

    //Giocatore rappresentato da un bitmap
    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ship)

    //grandezza navicella
    val width = screenX / 8f
    private val height = width
    //private val height = screenY / 20f

    //Tiene traccia della posizione
    //val position = RectF(screenX / 2f, screenY-height, screenX/2 + width, screenY.toFloat())
    val position = RectF(screenX / 2f-width/2f, screenY / 1.5f, screenX / 2f + width/2f, screenY-screenY/1.5f)

    //Velocità navicella
    private val speed  = 450f

    val stopped = 0
    val left = 1
    val right = 2


    //Direzione della navicella, inizializzata con stopped
    var moving = stopped

    init{
        //Adatta bitmap a risoluzione
        bitmap = Bitmap.createScaledBitmap(bitmap, width.toInt() , height.toInt() , false)
    }

    fun update(fps: Long) {
        if (moving == left && position.left > 0) {
            position.left -= speed / fps
        }
        else if (moving == right && position.left < screenX - width) {
            position.left += speed / fps
        }
        position.right = position.left + width
    }

}