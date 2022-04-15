package com.matthias;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.graphics.Color.*;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.utils.Align.center;

public class GameScreen extends ScreenAdapter {

    private final BallBouncingGame game;
    private final Ball ball;

    private final InputProcessor gameScreenInputProcessor = new InputAdapter() {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector2 clickedPoint = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
            ball.bounceUp(clickedPoint);
            return true;
        }
    };

    public GameScreen(BallBouncingGame game) {
        this.game = game;
        this.ball = new Ball(
            new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f),
            65,
            PURPLE,
            new GlyphLayout(this.game.font, "Hello World!", WHITE, 0, center, false)
        );
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameScreenInputProcessor);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BLACK);

        ball.update(0, Gdx.graphics.getWidth(), delta);

        if (ball.isBelowCamera(0)) {
            game.setScreen(new GameOverScreen(game, ball.getBounces()));
        }

        // Draw ball
        game.shapeRenderer.begin(Filled);
        game.shapeRenderer.setColor(ball.getColor());
        game.shapeRenderer.circle(ball.getX(), ball.getY(), ball.getRadius());
        game.shapeRenderer.end();

        // Draw bounce counter
        game.batch.begin();
        game.font.draw(game.batch, ball.getBallText(), ball.getX(), ball.getY());
        game.font.draw(game.batch, "Bounces: " + ball.getBounces(), 10, Gdx.graphics.getHeight() - 10);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
