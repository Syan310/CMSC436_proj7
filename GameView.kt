package com.example.project7

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.util.logging.Handler

class GameView(context: Context, private val balloons: Balloons) : View(context) {
    private val paint = Paint()
    private var attempts = 0
    private val maxAttempts = balloons.size + 3

    init {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (balloon in balloons.getAllBalloons()) {
            if (!balloon.isPopped) {
                paint.color = 0xFF6A1B9A.toInt() // Purple color
                canvas.drawCircle(balloon.x, balloon.y, balloon.radius, paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y
            val isPopped = balloons.popBalloon(x, y)
            attempts++

            if (isPopped) {
                invalidate() // Redraw the view to show updated state
                if (balloons.isGameWon()) {
                    Toast.makeText(context, "YOU WON", Toast.LENGTH_SHORT).show()
                }
            }

            if (attempts > maxAttempts) {
                Toast.makeText(context, "Game Over: Max attempts reached", Toast.LENGTH_SHORT).show()
                // Terminate the app after a short delay to show the toast
                android.os.Handler(Looper.getMainLooper()).postDelayed({
                    (context as Activity).finish()
                }, 2000)
            }
        }
        return super.onTouchEvent(event)
    }
}
