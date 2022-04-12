package com.matthias;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen extends ScreenAdapter {

    BallBouncing game;
    int score;

    public GameOverScreen(BallBouncing ballBouncing, int score) {
        this.game = ballBouncing;
        this.score = score;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        float screenCenterX = Gdx.graphics.getWidth() / 2f;
        float screenCenterY = Gdx.graphics.getHeight() / 2f;

        game.batch.begin();

        GlyphLayout layout1 = new GlyphLayout(game.font, "GAME OVER");
        GlyphLayout layout2 = new GlyphLayout(game.font, "YOUR SCORE: " + score);
        GlyphLayout layout3 = new GlyphLayout(game.font, "Press SPACE to play again");

        game.font.draw(game.batch, layout1, screenCenterX - layout1.width / 2, screenCenterY + layout1.height + layout2.height + 20);
        game.font.draw(game.batch, layout2, screenCenterX - layout2.width / 2, screenCenterY + layout2.height+ 10);
        game.font.draw(game.batch, layout3, screenCenterX - layout3.width / 2, screenCenterY);

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
