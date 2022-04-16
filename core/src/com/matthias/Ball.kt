package com.matthias

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2

class Ball(pos: Vector2?, radius: Float, color: Color, ballText: GlyphLayout) {

    companion object {
        private const val BOUNCE_Y_ACCELERATION = 600f
        private const val BOUNCE_X_MAX_ACCELERATION = 600f
        private const val FALL_ACCELERATION = -350f
        private const val FALL_VEL_CAP = -500f
    }

    private val circle: Circle

    val color: Color
    val ballText: GlyphLayout

    var xVel: Float = 0f
    var yVel: Float = 0f
    var bounces: Int = 0

    init {
        this.circle = Circle(pos, radius)
        this.color = color
        this.ballText = ballText
    }

    fun update(leftWall: Float, rightWall: Float, delta: Float) {
        accelerateFall(delta)
        circle.x += xVel * delta
        circle.y += yVel * delta
        bounceLeftWall(leftWall)
        bounceRightWall(rightWall)
    }

    fun bounceUp(point: Vector2) {
        if (!isInside(point)) {
            return
        }
        yVel += BOUNCE_Y_ACCELERATION
        xVel = BOUNCE_X_MAX_ACCELERATION * (circle.x - point.x) / circle.radius
        bounces++
    }

    fun isBelowCamera(cameraFloor: Int): Boolean {
        return circle.y + circle.radius < cameraFloor
    }

    private fun accelerateFall(delta: Float) {
        if (yVel <= FALL_VEL_CAP) {
            yVel = FALL_VEL_CAP
        } else {
            yVel += FALL_ACCELERATION * delta
        }
    }

    private fun isInside(point: Vector2?): Boolean {
        return circle.contains(point)
    }

    private fun bounceLeftWall(leftWall: Float) {
        if (circle.x - circle.radius >= leftWall) {
            return
        }
        xVel *= -1f
        circle.x = leftWall + circle.radius
        bounces++
    }

    private fun bounceRightWall(rightWall: Float) {
        if (circle.x + circle.radius <= rightWall) {
            return
        }
        xVel *= -1f
        circle.x = rightWall - circle.radius
        bounces++
    }

    val x: Float
        get() = circle.x

    val y: Float
        get() = circle.y

    val radius: Float
        get() = circle.radius
}