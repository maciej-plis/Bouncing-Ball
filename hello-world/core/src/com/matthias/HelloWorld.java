package com.matthias;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import static com.badlogic.gdx.graphics.Color.PURPLE;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;

public class HelloWorld extends ApplicationAdapter {

	SpriteBatch batch;
	BitmapFont font;
	ShapeRenderer shapeRenderer;

	float screenCenterX;
	float screenCenterY;

	float ballX;
	float ballY;

	float xVel = 120;
	float yVel = 60;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();

		screenCenterX = Gdx.graphics.getWidth() / 2f;
		screenCenterY = Gdx.graphics.getHeight() / 2f;
		ballX = screenCenterX;
		ballY = screenCenterY;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		ballX += xVel * Gdx.graphics.getDeltaTime();
		ballY += yVel * Gdx.graphics.getDeltaTime();

		if (ballX - 65 < 0) {
			ballX = 65;
			xVel = randomVel();
		} else if (ballX + 65 > Gdx.graphics.getWidth()) {
			ballX = Gdx.graphics.getWidth() - 65;
			xVel = -randomVel();
		}

		if (ballY - 65 < 0) {
			ballY = 65;
			yVel = randomVel();
		} else if (ballY + 65 > Gdx.graphics.getHeight()) {
			ballY = Gdx.graphics.getHeight() - 65;
			yVel = -randomVel();
		}

		shapeRenderer.begin(Filled);
		shapeRenderer.setColor(PURPLE);
		shapeRenderer.circle(ballX, ballY, 65);
		shapeRenderer.end();

		batch.begin();
		GlyphLayout layout = new GlyphLayout(font, "Hello World!");
		float x = ballX - layout.width / 2;
		float y = ballY + layout.height / 2;
		font.draw(batch, layout, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		shapeRenderer.dispose();
	}

	private int randomVel() {
		return new Random().nextInt(150) + 120;
	}
}
