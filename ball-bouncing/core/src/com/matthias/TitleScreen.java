package com.matthias;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class TitleScreen extends ScreenAdapter {

    BallBouncing game;

    public TitleScreen(BallBouncing ballBouncing) {
        this.game = ballBouncing;
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

        GlyphLayout layout1 = new GlyphLayout(game.font, "Bouncing Ball Game");
        GlyphLayout layout2 = new GlyphLayout(game.font, "Press SPACE to Start");

        game.font.draw(game.batch, layout1, screenCenterX - layout1.width / 2, screenCenterY - layout1.height - 5);
        game.font.draw(game.batch, layout2, screenCenterX - layout2.width / 2, screenCenterY + 5);

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
