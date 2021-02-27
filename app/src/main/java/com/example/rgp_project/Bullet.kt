/**
 * Project:  EverBuilds
 * File:  Bullet.kt
 * Author:  Giovanni Michieletto
 * Created:  2021-02-17
 * Version:  1.0.0
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 * Copyright 2021 EverBuild Group.
 * Licensed under the MIT License.  See License.txt in the project root for license information.
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 *
 */

package com.example.rgp_project

import android.graphics.RectF

class Bullet(screenY: Int, private val speed: Float = 350f, heightModifier: Float = 20f) {

    val position = RectF()

    // Bullet Direction
    val up = 0
    val down = 1
    private var heading = -1

    private val width = 2
    private var height = screenY / heightModifier

    var isActive = false

    fun shoot(startX: Float, startY: Float, direction: Int): Boolean {
        if (!isActive) {
            position.left = startX
            position.top = startY
            position.right = position.left + width
            position.bottom = position.top + height
            heading = direction
            isActive = true
            return true
        }
        // Bullet already active
        return false
    }

    fun update(fps: Long) {
        if (heading == up) {
            position.top -= speed / fps
        } else {
            position.top += speed / fps
        }
        // Updates bottom position
        position.bottom = position.top + height
    }
}