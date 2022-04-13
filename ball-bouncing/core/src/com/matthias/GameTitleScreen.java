package com.matthias;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.utils.Align.center;

public class GameTitleScreen extends ScreenAdapter {

    private final BallBouncingGame game;
    private final GlyphLayout gameTitleText;

    private final InputProcessor titleScreenInputProcessor = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            if (keycode == SPACE) {
                game.setScreen(new GameScreen(game));
            }
            return true;
        }
    };

    public GameTitleScreen(BallBouncingGame game) {
        this.game = game;
        gameTitleText = new GlyphLayout(this.game.font, "Bouncing Ball Game\nPress SPACE to Start", WHITE, 0, center, false);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(titleScreenInputProcessor);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BLACK);

        game.batch.begin();
        drawTitleText();
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void drawTitleText() {
        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;
        game.font.draw(game.batch, gameTitleText, screenCenterX, screenCenterY + gameTitleText.height / 2);
    }
}
