package com.example.rgp_project.model.factory

import com.example.rgp_project.GameView
import com.example.rgp_project.model.Enemy
/**
 * Represents an Enemy Factory
 * @param scene : the gameview onto which the enemies will be spawned
 */
class EnemyFactory(private val scene : GameView) {
    /**
     * Generates the enemies
     * @return Enemy to the scene
     * @see Player
     */
    fun generate() : Enemy{
        val width = 150F;
        val height = 100F;
        return Enemy(
            scene.player ,
            (scene.getMaxWidth().toFloat() - width) / 2,
            100F,
            width,
            height,
            scene
        )
    }
}

