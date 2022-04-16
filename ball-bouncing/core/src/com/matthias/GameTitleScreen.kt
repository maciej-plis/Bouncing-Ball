package com.matthias

import com.badlogic.gdx.*
import com.badlogic.gdx.Input.Keys.SPACE
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils

class GameTitleScreen(private val game: BouncingBallGame) : ScreenAdapter() {

    private val gameTitleText: GlyphLayout = GlyphLayout(game.font, "Bouncing Ball Game\nPress SPACE to Start", Color.WHITE, 0f, Align.center, false)
    private val titleScreenInputProcessor: InputProcessor = object : InputAdapter() {
        override fun keyDown(keycode: Int): Boolean {
            if (keycode == SPACE) {
                game.screen = GameScreen(game)
            }
            return true
        }
    }

    override fun show() {
        Gdx.input.inputProcessor = titleScreenInputProcessor
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(Color.BLACK)
        game.batch.begin()
        drawTitleText()
        game.batch.end()
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    private fun drawTitleText() {
        game.font.draw(game.batch, gameTitleText, Gdx.graphics.screenCenterX, Gdx.graphics.screenCenterY + gameTitleText.height / 2)
    }
}