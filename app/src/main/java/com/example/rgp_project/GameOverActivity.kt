/**
 * Project:  EverBuilds
 * File:  GameOverActivity.kt
 * Author:  Alberto Sinigaglia
 * Created:  2021-02-15
 * Version:  1.0.0
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 * Copyright 2021 EverBuild Group.
 * Licensed under the MIT License.  See License.txt in the project root for license information.
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 *
 */
package com.example.rgp_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
/**
 * Represents the Game Over activity
 */
class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val SCORE = intent.getLongExtra("score", 0)
        findViewById<TextView>(R.id.score).text = SCORE.toString()
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }
}