package com.matthias

import com.badlogic.gdx.*
import com.badlogic.gdx.Input.Keys.SPACE
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils

class GameOverScreen(private val game: BouncingBallGame, score: Int) : ScreenAdapter() {

    private val gameOverText: GlyphLayout = GlyphLayout(game.font, String.format("Game Over%nYour Score: %d%nPress SPACE to Play Again", score), Color.WHITE, 0f, Align.center, false)
    private val gameOverScreenInputProcessor: InputProcessor = object : InputAdapter() {
        override fun keyDown(keycode: Int): Boolean {
            if (keycode == SPACE) {
                game.screen = GameScreen(game)
            }
            return true
        }
    }

    override fun show() {
        Gdx.input.inputProcessor = gameOverScreenInputProcessor
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        game.batch.begin()
        drawGameOverText()
        game.batch.end()
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    private fun drawGameOverText() {
        game.font.draw(game.batch, gameOverText, Gdx.graphics.screenCenterX, Gdx.graphics.screenCenterY + gameOverText.height / 2)
    }
}