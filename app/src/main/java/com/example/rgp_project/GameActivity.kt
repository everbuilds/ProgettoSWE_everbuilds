/**
 * Project:  EverBuilds
 * File:  GameActivity.kt
 * Author:  Marco Tesser
 * Created:  2021-02-05
 * Version:  1.0.0
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 * Copyright 2021 EverBuild Group.
 * Licensed under the MIT License.  See License.txt in the project root for license information.
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 *
 */
package com.example.rgp_project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.rgp_project.model.Enemy


class GameActivity : Activity() {

    //gameView will be the mainview and it will manage the game's logic
    private var gameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val METRICS = DisplayMetrics()
        val WINDOW_MANAGER = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        WINDOW_MANAGER.defaultDisplay.getMetrics(METRICS)


        gameView = GameView(this, METRICS.widthPixels.toFloat(), METRICS.heightPixels.toFloat())
        gameView!!.setGameOverListener (object: GameOverListener() {
            override fun gameOver(score : Long) {
                callGameOverActivity(score )
            }
        })

        setContentView(gameView)

    }

    private fun callGameOverActivity(score : Long) {
        val INTENT : Intent = Intent(this, GameOverActivity::class.java);
        INTENT.putExtra("score", score)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        gameView?.pause();
    }

    override fun onBackPressed() {
        // super.onBackPressed()
    }

}