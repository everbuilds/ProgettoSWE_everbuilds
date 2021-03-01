/**
 * Project:  EverBuilds
 * File:  Alien.kt
 * Author:  Samuele Sartor
 * Created:  2021-02-18
 * Version:  1.0.0
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 * Copyright 2021 EverBuild Group.
 * Licensed under the MIT License.  See License.txt in the project root for license information.
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 *
 */
package com.example.rgp_project

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import java.util.*
/**
 * Represents an Alien
 * @param screenX : position on the x axis
 * @param screenY : position on the y axis
 * @param row : the row onto which the alien will be placed
 * @param column : the column onto which the alien will be placed
 * @param scene : the context onto which the player will be drawn
 */
class Alien(context: Context, row: Int, column: Int, screenX: Int, screenY: Int) {

    //Alien's size
    var width = screenX / 35f
    private var height = screenY / 35f
    private val padding = screenX / 45

    var position = RectF(column * (width + padding), 100 + row * (width + padding / 4), column * (width + padding) + width, 100 + row * (width + padding / 4) + height)

    //Alien's speed
    private var speed = 40f

    private val left = 1
    private val right = 2

    //Movement direction
    private var shipMoving = right

    var isVisible = true

    companion object {
        lateinit var bitmap: Bitmap
        var numberOfAliens = 0
    }

    init {
        //Initializes bitmap
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.alien)
        bitmap = Bitmap.createScaledBitmap(bitmap, (width.toInt()), (height.toInt()), false)
        numberOfAliens ++
    }



    /**
     * Performs an update operation of the position of the ship.
     * @param fps : the fps of the current game 
     */
    fun update(fps: Long) {
        if (shipMoving == left) {
            position.left -= speed / fps
        }
        if (shipMoving == right) {
            position.left += speed / fps
        }
        position.right = position.left + width
    }

    /**
     * Handles the ship movement in certain cirmcumstances
     * @param waveNumber : the current waveNumber of the game
     */
    fun dropDownAndReverse(waveNumber: Int) {
        shipMoving = if (shipMoving == left) {
            right
        } else {
            left
        }
        position.top += height
        position.bottom += height

        //Speed increases as waves progress
        speed *=  (1.1f + (waveNumber.toFloat() / 20))
    }

    /**
     * Handles the player's aiming towards enemies
     * @param playerShipX : the player's ship position.
     * @param playerShipLength : the player's ship length.
     * @param waves : the current waveNumber of the game.
     */
    fun takeAim(playerShipX: Float, playerShipLength: Float, waves: Int): Boolean {

        val generator = Random()
        var randomNumber: Int

        //If near player consideres shooting
        if (playerShipX + playerShipLength > position.left && playerShipX + playerShipLength < position.left + width ||
                playerShipX > position.left && playerShipX < position.left + width) {
            //More aliens there are the less they shoot and viceversa
            randomNumber = generator.nextInt((100 * numberOfAliens) / waves)
            if (randomNumber == 0) {
                return true
            }

        }

        // If player shoots randomly and not to the nearby player
        randomNumber = generator.nextInt(150 * numberOfAliens)
        return randomNumber == 0
    }
}
