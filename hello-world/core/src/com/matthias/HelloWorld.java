package com.matthias;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import static com.badlogic.gdx.Input.Keys.*;
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

	float xVel = 240;
	float yVel = 240;

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
		movableBall();
	}


	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		shapeRenderer.dispose();
	}

	private void movableBall() {

		if(Gdx.input.isTouched()) {
			ballX = Gdx.input.getX();
			ballY = Gdx.graphics.getHeight() - Gdx.input.getY();
		}

		if(Gdx.input.isKeyPressed(W)) {
			ballY += xVel * Gdx.graphics.getDeltaTime();
		} else if(Gdx.input.isKeyPressed(S)) {
			ballY -= xVel * Gdx.graphics.getDeltaTime();
		}

		if (Gdx.input.isKeyPressed(A)) {
			ballX -= yVel * Gdx.graphics.getDeltaTime();
		} else if(Gdx.input.isKeyPressed(D)) {
			ballX += yVel * Gdx.graphics.getDeltaTime();
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

	private void inputEvents() {
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyTyped(char character) {
				Gdx.app.log("tag", "Key typed");
				return true;
			}

			@Override
			public boolean keyDown(int keycode) {
				Gdx.app.log("tag", "Key down");
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				Gdx.app.log("tag", "Key up");
				return true;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				Gdx.app.log("tag", "Touch up");
				return true;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				Gdx.app.log("tag", "Touch down");
				return true;			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				Gdx.app.log("tag", "Touch dragged");
				return true;			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				Gdx.app.log("tag", "Mouse moved");
				return true;			}

			@Override
			public boolean scrolled(float amountX, float amountY) {
				Gdx.app.log("tag", "Scrolled");
				return true;			}
		});
	}

	private void bouncingBall() {
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

	private int randomVel() {
		return new Random().nextInt(150) + 120;
	}
}
