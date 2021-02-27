/**
 * Project:  EverBuilds
 * File:  Lobby.kt
 * Author:  Marco Tesser
 * Created:  2021-02-10
 * Version:  1.0.0
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 * Copyright 2021 EverBuild Group.
 * Licensed under the MIT License.  See License.txt in the project root for license information.
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 *
 */



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
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}