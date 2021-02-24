package com.example.rgp_project

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class Game : AppCompatActivity() {

    //gameView sarà la view e gestirà la logica del gioco
    private var gameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Essendo windowManager.defaultDisplay deprecated nelle API dal 30 bisogna mettere entrambi i casi essendo retrocompatibile fino alla 26, manca da fare perchè non mi veniva
        //Prendo un Display object per accedere allo schermo e un Point con le misure
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        //Inizializza gameView e la imposta come view
        gameView = GameView(this,size)
        setContentView(gameView)
    }

    // Viene eseguito quando inizia il gioco
    override fun onResume() {
        super.onResume()
        gameView?.resume()
    }

    // Viene eseguito quando chiude il gioco
    override fun onPause() {
        super.onPause()
        gameView?.pause()
    }
}