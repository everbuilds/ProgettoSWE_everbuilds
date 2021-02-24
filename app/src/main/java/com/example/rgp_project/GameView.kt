provapackage com.example.rgp_project

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView

class GameView(context: Context, private val size: Point) : SurfaceView(context), Runnable {

    //Thread
    private val gameThread = Thread(this)

    //Boleano per vedere se gioca
    private var playing = false

    //Gioco è in pausa all'inizio
    private var paused = true

    //Canvas e Paint object
    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()

    //Tempo per ondata
    private var time = 30

    //Punteggio
    private var score = 0

    //Numero ondata
    private var waves = 1

    //Vite
    private var lives = 3

    //Giocatore
    private var player: Player = Player(context, size.x, size.y)

    //Alieni
    private val aliens = ArrayList<Alien>()
    private var numAliens = 0

    //Proiettili player
    private var playerBullet = Bullet(size.y, 1200f, 40f)

    //Proiettili alien
    private val aliensBullets = ArrayList<Bullet>()
    private var nextBullet = 0
    private val maxAliensBullets = 10 + 5*(waves-1)

    private fun buildLevel() {
        //Costruisco ondata
        Alien.numberOfAliens = 0
        numAliens = 0
        for (column in 0..10) {
            for (row in 0..5) {
                aliens.add(Alien(context, row, column, size.x, size.y))
                numAliens++
            }
        }
        //Inizializzo array bullet alien
        for (i in 0 until maxAliensBullets) {
            aliensBullets.add(Bullet(size.y))
        }
    }

    override fun run() {
        var fps: Long = 0
        while (playing) {
            //Ottiene tempo corrente
            val startFrameTime = System.currentTimeMillis()
            //Aggiorna frame
            if (!paused) {
                update(fps)
            }
            //Disegna frame
            draw()
            //Calcola fps rate
            val timeThisFrame = System.currentTimeMillis() - startFrameTime
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame
            }
        }
    }

    private fun update(fps: Long) {
        //Muove player
        player.update(fps)

        //Un alieno ha sbattuto sul bordo
        var bumped = false

        //Player ha perdo
        var lost = false

        //Aggirona Alien visibili
        for (alien in aliens) {
            if (alien.isVisible) {
                alien.update(fps)
                //Alien vuole sparare?
                if (alien.takeAim(player.position.left, player.width, waves)) {
                    //Prova a sparare
                    if (aliensBullets[nextBullet].shoot(alien.position.left + alien.width / 2, alien.position.top, playerBullet.down)) {
                        nextBullet++
                        //Torna all'inizio se si è arrivati alla fine
                        if (nextBullet == maxAliensBullets) {
                            nextBullet = 0
                        }
                    }
                }
                //Se mossa causa scontro bumped viene impostato a true
                if (alien.position.left > size.x - alien.width || alien.position.left < 0) {
                    bumped = true
                }
            }
        }

        //Aggiorna proiettile giocatore
        if (playerBullet.isActive) {
            playerBullet.update(fps)
        }

        //Aggiorna proiettile alieni
        for (bullet in aliensBullets) {
            if (bullet.isActive) {
                bullet.update(fps)
            }
        }

        //Un alieno ha sbattuto?
        if (bumped) {
            //Muovi alieni giù e cambia direzione
            for (alien in aliens) {
                alien.dropDownAndReverse(waves)
                // Have the invaders landed
                if (alien.position.bottom >= size.y && alien.isVisible) {
                    lost = true
                }
            }
        }

        //playerBullet ha raggiunto bordo sopra?
        if (playerBullet.position.bottom < 0) {
            playerBullet.isActive =false
        }

        //alienBullet ha raggiunto bordo sotto?
        for (bullet in aliensBullets) {
            if (bullet.position.top > size.y) {
                bullet.isActive = false
            }
        }
        //playerBullet ha colpito alieno?
        if (playerBullet.isActive) {
            for (alien in aliens) {
                if (alien.isVisible) {
                    if (RectF.intersects(playerBullet.position, alien.position)) {
                        alien.isVisible = false
                        playerBullet.isActive = false
                        Alien.numberOfAliens --
                        score += 10
                        if (Alien.numberOfAliens == 0) {
                            paused = true
                            lives ++
                            aliens.clear()
                            aliensBullets.clear()
                            buildLevel()
                            waves ++
                            time = 30
                            break
                        }
                        //Non controllare altri alieni
                        break
                    }
                }
            }
        }


        // alienBullet ha colpito giocatore
        for (bullet in aliensBullets) {
            if (bullet.isActive) {
                if (RectF.intersects(player.position, bullet.position)) {
                    bullet.isActive = false
                    lives --
                    //Finite le vite?
                    if (lives == 0) {
                        lost = true
                        break
                    }
                }
            }
        }

        if (lost) {
            paused = true
            lives = 3
            score = 0
            waves = 1
            aliens.clear()
            aliensBullets.clear()
            buildLevel()
        }
    }

    private fun draw() {
        //Controllo che la superificie dove disegnare sia valida
        if (holder.surface.isValid) {
            //Blocco la canvas per disegnare
            canvas = holder.lockCanvas()

            //Coloro background
            canvas.drawColor(Color.argb(255, 0, 0, 0))

            //Scelgo colore per disegnare
            paint.color = Color.argb(255, 0, 255, 0)

            //Disegno player
            canvas.drawBitmap(player.bitmap, player.position.left, player.position.top, paint)

            //Disegno alieni
            for (alien in aliens) {
                if (alien.isVisible) {
                    canvas.drawBitmap(Alien.bitmap, alien.position.left, alien.position.top, paint)
                }
            }

            //Disegno bullet player
            if (playerBullet.isActive) {
                canvas.drawRect(playerBullet.position, paint)
            }

            // Disegno bullets alien
            for (bullet in aliensBullets) {
                if (bullet.isActive) {
                    canvas.drawRect(bullet.position, paint)
                }
            }

            // Cambio colore, disegno score/vite/tempo
            paint.color = Color.argb(255, 255, 255, 255)
            paint.textSize = 70f
            canvas.drawText("Score: $score   Lives: $lives Wave: $waves Time: $time", 20f, 75f, paint)

            //Disegno tutto a schermo
            holder.unlockCanvasAndPost(canvas)
        }
    }

    //hut down thread se in pausa.
    fun pause() {
        playing = false
        try {
            gameThread.join()
        } catch (e: InterruptedException) {
            Log.e("Error:", "joining thread")
        }

        val prefs = context.getSharedPreferences(
            "Kotlin Invaders",
            Context.MODE_PRIVATE)

    }

    //Starta thread se starta gioco
    fun resume() {
        playing = true
        buildLevel()
        gameThread.start()
    }

    // La classe SurfaceView implementa onTouchListener quindi bisogna fare override
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        val motionArea = size.y - (size.y / 8)
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE-> {
                paused = false
                if (motionEvent.x > size.x / 2) {
                    player.moving = player.right
                } else {
                    player.moving = player.left
                }
            }

            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                player.moving = player.stopped
            }
        }
        return true
    }

}