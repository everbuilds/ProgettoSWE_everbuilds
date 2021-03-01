/**
 * Project:  EverBuilds
 * File:  Chart.kt
 * Author:  Giovanni Michieletto
 * Created:  2021-02-21
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
/**
 * Represents a Chart
 */
class Chart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
    }
}