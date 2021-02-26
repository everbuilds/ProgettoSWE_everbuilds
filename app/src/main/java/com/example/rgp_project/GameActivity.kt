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

    //gameView sarà la view e gestirà la logica del gioco
    private var gameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val metrics = DisplayMetrics()
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(metrics)


        gameView = GameView(this, metrics.widthPixels.toFloat(), metrics.heightPixels.toFloat())
        gameView!!.setGameOverListener (object: GameOverListener() {
            override fun gameOver(score : Long) {
                callGameOverActivity(score )
            }
        })

        setContentView(gameView)

    }

    private fun callGameOverActivity(score : Long) {
        val intent : Intent = Intent(this, GameOverActivity::class.java);
        intent.putExtra("score", score)
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