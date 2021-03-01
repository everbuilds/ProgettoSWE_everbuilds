package com.example.rgp_project.model.factory

import com.example.rgp_project.GameView
import com.example.rgp_project.model.Enemy
import com.example.rgp_project.model.powerup.LifePowerUp
/**
 * Represents a Life PowerUp Factory
 * @param scene : the gameview onto which the life powerups will be spawned
 */
class LifePowerUpFactory(private val scene : GameView) {
    /**
     * Generates the powerups
     * @return LifePowerUp with an health increase
     */
    fun generate() : LifePowerUp{
        return LifePowerUp(
            50,
            Math.random().toFloat() * scene.getMaxWidth(),
            0f
        )
    }
}