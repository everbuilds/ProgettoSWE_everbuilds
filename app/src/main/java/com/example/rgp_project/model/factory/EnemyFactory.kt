package com.example.rgp_project.model.factory

import com.example.rgp_project.GameView
import com.example.rgp_project.model.Enemy

class EnemyFactory(private val scene : GameView) {

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

