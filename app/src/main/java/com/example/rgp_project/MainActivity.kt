package com.example.rgp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonsetting = findViewById<Button>(R.id.button)
        buttonsetting.setOnClickListener{
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        val buttonsp = findViewById<Button>(R.id.button2)
        buttonsp.setOnClickListener{
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
        }
        val buttonchart = findViewById<ImageButton>(R.id.imageButton5)
        buttonchart.setOnClickListener{
            val intent = Intent(this, Chart::class.java)
            startActivity(intent)
        }
        val buttonmp = findViewById<Button>(R.id.button3)
        buttonmp.setOnClickListener{
            val intent = Intent(this, Lobby::class.java)
            startActivity(intent)
        }

    }
}