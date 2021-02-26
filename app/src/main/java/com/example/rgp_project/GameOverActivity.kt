package com.example.rgp_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val score = intent.getLongExtra("score", 0)
        findViewById<TextView>(R.id.score).text = score.toString()
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }
}