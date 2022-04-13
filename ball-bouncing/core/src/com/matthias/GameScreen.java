package com.matthias;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.graphics.Color.PURPLE;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;

public class GameScreen extends ScreenAdapter {

    BallBouncingGame game;
    Circle ball;

    float xVel;
    float yVel;

    int bounces;

    public GameScreen(BallBouncingGame ballBouncing) {
        this.game = ballBouncing;
    }

    @Override
    public void show() {
        bounces = 0;

        xVel = 0f;
        yVel = 0f;

        ball = new Circle(new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f), 65);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(ball.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                    yVel += 600;
                    float multiplier = (ball.x - screenX) / 65;
                    xVel = 600 * multiplier;
                    bounces++;
                }

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        yVel = yVel > -300 ? yVel - 300 * delta : -300;

        ball.setX(ball.x + xVel * delta);
        ball.setY(ball.y + yVel * delta);

        if (ball.x - 65 < 0) {
            xVel *= -1;
            ball.x = 65;
        } else if(ball.x + 65 > Gdx.graphics.getWidth()) {
            xVel *= -1;
            ball.x = Gdx.graphics.getWidth() - 65;
        }

        if(ball.y + 65 < 0) {
            game.setScreen(new GameOverScreen(game, bounces));
        }

        game.shapeRenderer.begin(Filled);
        game.shapeRenderer.setColor(PURPLE);
        game.shapeRenderer.circle(ball.x, ball.y, ball.radius);
        game.shapeRenderer.end();


        game.batch.begin();
        GlyphLayout layout = new GlyphLayout(game.font, "Hello World!");
        float x = ball.x - layout.width / 2;
        float y = ball.y + layout.height / 2;
        game.font.draw(game.batch, layout, x, y);
        game.font.draw(game.batch, "Bounces: " + bounces, 10, Gdx.graphics.getHeight() - 10);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
