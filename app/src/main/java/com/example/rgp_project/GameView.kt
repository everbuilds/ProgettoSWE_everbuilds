
package com.example.rgp_project


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import com.example.rgp_project.model.Bullet
import com.example.rgp_project.model.Enemy
import com.example.rgp_project.model.Player
import java.util.concurrent.CopyOnWriteArrayList


class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val gameOverListeners : ArrayList<GameOverListener> = ArrayList()
    private val MAX_WIDTH : Int;
    private val MAX_HEIGHT : Int;
    private var enemies : CopyOnWriteArrayList<Enemy> = CopyOnWriteArrayList();
    var player : Player
    val bullets = CopyOnWriteArrayList<Bullet>()
    val startTime = System.currentTimeMillis();
    private var surfaceHolder = holder;
    private var gameLoop: GameLoop = GameLoop(this, surfaceHolder);
    init {
        val metrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(metrics)
        MAX_WIDTH = metrics.widthPixels
        MAX_HEIGHT = metrics.heightPixels
        player = Player(metrics.widthPixels.toFloat() / 2, metrics.heightPixels.toFloat() / 5 * 4, 40F, this)
        surfaceHolder.addCallback(this);
        focusable = View.FOCUSABLE;
    }

    override fun draw(canvas: Canvas?) {
        if (player.health <= 0) {
            return;
        }
        super.draw(canvas)
        drawFPS(canvas)
        drawUPS(canvas)
        player.draw(canvas)
        enemies.forEach { el -> el.draw(canvas)
        }
        bullets.forEach { el -> el.draw(canvas)
        }
    }

    fun update() {
        if (player.health <= 0) {
            gameOverListeners.forEach{
                el -> el.gameOver(System.currentTimeMillis() - startTime)
            }
            return;
        }
        enemies.removeIf{
            el -> el.health <= 0
        }
        if(enemies.size == 0){
            val width = 150F;
            val height = 100F;
            this.addEnemy(
                    Enemy(
                            player ,
                            (getMaxWidth().toFloat() - width) / 2,
                            100F,
                            width,
                            height,
                            this
                    )
            )
        }
        player.update(bullets)
        enemies.forEach{ el ->
            el.update(bullets)
        }
        bullets.forEach { el -> el.update()
        }
        bullets.removeIf{ el -> el.toRemove()
        }
    }

    fun addEnemy(enemy: Enemy){
        enemies.add(enemy)
    }






    private fun drawUPS(canvas: Canvas?){
        var avg: String = gameLoop.getAverageUPS().toInt().toString();
        val paint : Paint = Paint()
        paint.color = Color.RED
        paint.textSize = 50F;
        canvas?.drawText("UPS: $avg", 20F, MAX_HEIGHT.toFloat()/2, paint)
    }
    private fun drawFPS(canvas: Canvas?){
        var avg: String = gameLoop.getAverageFPS().toInt().toString();
        val paint : Paint = Paint()
        paint.color = Color.RED
        paint.textSize = 50F;
        canvas?.drawText("FPS: ${avg.format(2)}", 20F, MAX_HEIGHT.toFloat()/2 + 100, paint)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (gameLoop.state == Thread.State.TERMINATED) {
            val surfaceHolder = getHolder()
            surfaceHolder.addCallback(this)
            gameLoop = GameLoop(this, surfaceHolder)
        }

        gameLoop.startLoop()
    }
    public fun pause() : Unit{
        gameLoop.stopLoop();
    }
    public fun stop() : Unit{
        gameLoop.killLoop();
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // TODO("Not yet implemented")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when(event?.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                player.setPosition(event.x, player.positionY)
                true
            }
            else -> true;
        }
    }

    public fun getMaxHeight() : Int{
        return MAX_HEIGHT
    }
    public fun getMaxWidth() : Int{
        return MAX_WIDTH
    }
    fun setGameOverListener(listener : GameOverListener){
        gameOverListeners.add(listener)
    }
}