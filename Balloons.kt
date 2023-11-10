package com.example.project7

class Balloons {
    private val balloons = mutableListOf<Balloon>()

    fun addBalloon(balloon: Balloon) {
        balloons.add(balloon)
    }

    fun popBalloon(x: Float, y: Float): Boolean {
        val iterator = balloons.iterator()
        while (iterator.hasNext()) {
            val balloon = iterator.next()
            if (!balloon.isPopped && isPointInsideBalloon(x, y, balloon)) {
                balloon.isPopped = true
                return true
            }
        }
        return false
    }

    private fun isPointInsideBalloon(x: Float, y: Float, balloon: Balloon): Boolean {
        val dx = x - balloon.x
        val dy = y - balloon.y
        return dx * dx + dy * dy <= balloon.radius * balloon.radius
    }

    fun isGameWon(): Boolean = balloons.all { it.isPopped }

    val size: Int
        get() = balloons.size

    fun getAllBalloons(): List<Balloon> = balloons
}
