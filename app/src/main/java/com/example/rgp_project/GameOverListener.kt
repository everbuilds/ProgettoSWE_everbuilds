/**
 * Project:  EverBuilds
 * File:  GameOverListener.kt
 * Author:  Samuele Sartor
 * Created:  2021-02-15
 * Version:  1.0.0
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 * Copyright 2021 EverBuild Group.
 * Licensed under the MIT License.  See License.txt in the project root for license information.
 * ––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
 *
 */

package com.example.rgp_project

abstract class GameOverListener {
     abstract fun gameOver( score: Long);
}