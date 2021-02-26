package com.example.rgp_project.model.factory

import com.example.rgp_project.GameView
import com.example.rgp_project.model.Enemy
import com.example.rgp_project.model.powerup.LifePowerUp

class LifePowerUpFactory(private val scene : GameView) {

    fun generate() : LifePowerUp{
        return LifePowerUp(
            50,
            Math.random().toFloat() * scene.getMaxWidth(),
            0f
        )
    }
}