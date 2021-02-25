package com.example.rgp_project

import android.app.Activity
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        //Inizializza gameView e la imposta come view
        gameView = GameView(this)
        setContentView(gameView)
        val width = 150F;
        val height = 100F;
        gameView?.addEnemy(
            Enemy(
                gameView!!.player ,
                (gameView!!.getMaxWidth().toFloat() - width) / 2,
                20F,
                    width.toFloat(),
                    height,
                    gameView!!
                )
        )
    }

}