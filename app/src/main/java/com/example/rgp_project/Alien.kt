package com.example.rgp_project

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import java.util.*

class Alien(context: Context, row: Int, column: Int, screenX: Int, screenY: Int) {

    //Grandezza alieni
    var width = screenX / 35f
    private var height = screenY / 35f
    private val padding = screenX / 45

    var position = RectF(column * (width + padding), 100 + row * (width + padding / 4), column * (width + padding) + width, 100 + row * (width + padding / 4) + height)

    //Velocità alieni
    private var speed = 40f

    private val left = 1
    private val right = 2

    //Direzione movimento
    private var shipMoving = right

    var isVisible = true

    companion object {
        lateinit var bitmap: Bitmap
        var numberOfAliens = 0
    }

    init {
        //Inizializza bitmap
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.alien)
        bitmap = Bitmap.createScaledBitmap(bitmap, (width.toInt()), (height.toInt()), false)
        numberOfAliens ++
    }

    fun update(fps: Long) {
        if (shipMoving == left) {
            position.left -= speed / fps
        }
        if (shipMoving == right) {
            position.left += speed / fps
        }
        position.right = position.left + width
    }

    fun dropDownAndReverse(waveNumber: Int) {
        shipMoving = if (shipMoving == left) {
            right
        } else {
            left
        }
        position.top += height
        position.bottom += height

        //Velocità aumenta con le ondate
        speed *=  (1.1f + (waveNumber.toFloat() / 20))
    }

    fun takeAim(playerShipX: Float, playerShipLength: Float, waves: Int): Boolean {

        val generator = Random()
        var randomNumber: Int

        //Se player vicino considera di sparare
        if (playerShipX + playerShipLength > position.left && playerShipX + playerShipLength < position.left + width || playerShipX > position.left && playerShipX < position.left + width) {
            //Più alieni ci sono meno sparano e viceversa
            randomNumber = generator.nextInt((100 * numberOfAliens) / waves)
            if (randomNumber == 0) {
                return true
            }

        }

        // Se spara a caso e non a giocatore vicino
        randomNumber = generator.nextInt(150 * numberOfAliens)
        return randomNumber == 0
    }
}
