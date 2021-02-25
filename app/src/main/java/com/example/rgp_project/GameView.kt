
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
    private var MAX_WIDTH : Int = 0;
    private var MAX_HEIGHT : Int = 0;
    private var enemies : CopyOnWriteArrayList<Enemy> = CopyOnWriteArrayList();
    var player : Player
    val bullets = CopyOnWriteArrayList<Bullet>()

    private var surfaceHolder = holder;
    private var gameLoop: GameLoop = GameLoop(this, surfaceHolder);
    //Thread
    private val gameThread = GameLoop(this, surfaceHolder)
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
        super.draw(canvas)
        drawFPS(canvas)
        drawUPS(canvas)
        player.draw(canvas)
        enemies.forEach {
                el -> el.draw(canvas)
        }
        bullets.forEach {
                el -> el.draw(canvas)
        }
    }

    fun update() {
        player.update(bullets)
        enemies.forEach{ el ->
            el.update(bullets)
        }
        bullets.forEach {
            el -> el.update()
        }
        Log.i("n. bullets" , bullets.size.toString())
        bullets.removeIf{
            el -> el.toRemove()
        }
    }

    fun addEnemy( enemy : Enemy ){
        enemies.add(enemy)
    }






    private fun drawUPS(canvas: Canvas?){
        var avg: String = gameLoop.getAverageUPS().toString();
        val paint : Paint = Paint()
        paint.color = Color.RED
        paint.textSize = 100F;
        canvas?.drawText("UPS: $avg", 20F, 100F, paint)
    }
    private fun drawFPS(canvas: Canvas?){
        var avg: String = gameLoop.getAverageFPS().toString();
        val paint : Paint = Paint()
        paint.color = Color.RED
        paint.textSize = 100F;
        canvas?.drawText("FPS: $avg", 20F, 200F, paint)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()
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

}