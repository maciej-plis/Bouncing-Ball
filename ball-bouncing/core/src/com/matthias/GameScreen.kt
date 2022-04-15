package com.matthias

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils

class GameScreen(private val game: BallBouncingGame) : ScreenAdapter() {

    private val ball: Ball = Ball(
            Vector2(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f),
            65f,
            Color.PURPLE,
            GlyphLayout(game.font, "Hello World!", Color.WHITE, 0f, Align.center, false)
    )
    private val gameScreenInputProcessor: InputProcessor = object : InputAdapter() {
        override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
            val clickedPoint = Vector2(screenX.toFloat(), (Gdx.graphics.height - screenY).toFloat())
            ball.bounceUp(clickedPoint)
            return true
        }
    }

    override fun show() {
        Gdx.input.inputProcessor = gameScreenInputProcessor
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)

        ball.update(0f, Gdx.graphics.width.toFloat(), delta)
        if (ball.isBelowCamera(0)) {
            game.screen = GameOverScreen(game, ball.bounces)
        }

        // Draw ball
        game.shapeRenderer.begin(ShapeType.Filled)
        game.shapeRenderer.color = ball.color
        game.shapeRenderer.circle(ball.x, ball.y, ball.radius)
        game.shapeRenderer.end()

        // Draw bounce counter
        game.batch.begin()
        game.font.draw(game.batch, ball.ballText, ball.x, ball.y)
        game.font.draw(game.batch, "Bounces: " + ball.bounces, 10f, (Gdx.graphics.height - 10).toFloat())
        game.batch.end()
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }
}