/**
* Project:  EverBuilds
* File:  <NameFile>
* Author:  <Name Surname>
* Created:  <YYYY>-<MM>-<DD>
* Version:  <versionCode>
* ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
* Copyright 2021 EverBuild Group.
* Licensed under the MIT License.  See License.txt in the project root for license information.
* ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
*
*/

/**
 * Project:  EverBuilds
 * File:  MainActivity.kt
 * Author:  Marco Tesser
 * Created:  2021-02-01
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
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BUTTON_SETTING = findViewById<Button>(R.id.button)
        BUTTON_SETTING.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        val BUTTON_SP = findViewById<Button>(R.id.button2)
        BUTTON_SP.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        val BUTTON_CHART = findViewById<ImageButton>(R.id.imageButton5)
        BUTTON_CHART.setOnClickListener {
            val intent = Intent(this, Chart::class.java)
            startActivity(intent)
        }
        val BUTTON_MP = findViewById<Button>(R.id.button3)
        BUTTON_MP.setOnClickListener {
            val intent = Intent(this, Lobby::class.java)
            startActivity(intent)
        }

    }
}