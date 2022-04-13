package com.matthias;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.utils.Align.center;
import static java.lang.String.format;

public class GameOverScreen extends ScreenAdapter {

    private final BallBouncingGame game;
    private final GlyphLayout gameOverText;

    private final InputProcessor gameOverScreenInputProcessor = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.SPACE) {
                game.setScreen(new GameScreen(game));
            }
            return true;
        }
    };

    public GameOverScreen(BallBouncingGame game, int score) {
        this.game = game;
        gameOverText = new GlyphLayout(this.game.font, format("Game Over%nYour Score: %d%nPress SPACE to Play Again", score), WHITE, 0, center, false);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameOverScreenInputProcessor);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BLACK);

        game.batch.begin();
        drawGameOverText();
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void drawGameOverText() {
        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;
        game.font.draw(game.batch, gameOverText, screenCenterX, screenCenterY + gameOverText.height / 2);
    }
}
