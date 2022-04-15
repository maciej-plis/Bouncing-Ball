package com.matthias

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class BallBouncingGame : Game() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    lateinit var shapeRenderer: ShapeRenderer

    override fun create() {
        initializeModules()
        setScreen(GameTitleScreen(this))
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
        shapeRenderer.dispose()
    }

    private fun initializeModules() {
        batch = SpriteBatch()
        font = BitmapFont()
        shapeRenderer = ShapeRenderer()
    }
}