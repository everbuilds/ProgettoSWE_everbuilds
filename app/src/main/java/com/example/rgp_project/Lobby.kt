package com.example.rgp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Lobby : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)

        val buttongame = findViewById<Button>(R.id.button4)
        buttongame.setOnClickListener{
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
        }
    }
}