package com.example.rgp_project

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameLoop(var game: GameView, var surfaceHolder: SurfaceHolder) : Thread(){

    private val MAX_UPS: Double = 60.0
    private val UPS_PERIOD: Double = 1000 / MAX_UPS
    var isRunning = false;
    var averageUPS : Double = 0.0;
    var averageFPS : Double = 0.0;
    @JvmName("getAverageUPS1")
    fun getAverageUPS(): Double {
        return averageUPS;
    }

    @JvmName("getAverageFPS1")
    fun getAverageFPS(): Double {
        return averageFPS;
    }

    fun startLoop() {
        isRunning = true;
        start();
    }
    fun stopLoop() {
        isRunning = false
        try {
            join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    override fun run() {
        super.run()
        var canvas : Canvas? = null;
        var frameCount : Int = 0
        var updateCount : Int = 0
        var startTime: Long = System.currentTimeMillis()
        var elapsedTime: Long = 0
        var sleepTime: Long = 0
        while(isRunning){
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    game.update();
                    updateCount++
                    game.draw(canvas)
                }
            } catch (ex: IllegalArgumentException){
                ex.printStackTrace()
            } finally {
                try {
                    if(canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                        frameCount++
                    }
                }catch (ex: Exception){
                    ex.printStackTrace()
                }
            }



            // evitare di superare max UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (updateCount * UPS_PERIOD).toLong() - elapsedTime
            if(sleepTime > 0){
                try{
                    sleep(sleepTime)
                } catch (ex: InterruptedException){
                    ex.printStackTrace()
                }
            }

            //saltare frame per mantere UPS stabili
            while(sleepTime < 0 && updateCount < MAX_UPS-1){
                game.update()
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (updateCount * UPS_PERIOD).toLong() - elapsedTime
            }



            if(elapsedTime > 1000){
                averageUPS = updateCount.toDouble() / (1E-3 * elapsedTime).toDouble()
                averageFPS = frameCount.toDouble() / (1E-3 * elapsedTime).toDouble()
                frameCount = 0;
                updateCount = 0;
                startTime = System.currentTimeMillis()
            }
        }
    }

    fun killLoop() {
        this.interrupt()
    }
}