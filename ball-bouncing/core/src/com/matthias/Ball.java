package com.matthias;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

@Getter
public class Ball {

    private static final float BOUNCE_Y_ACCELERATION = 600f;
    private static final float BOUNCE_X_MAX_ACCELERATION = 600f;

    private static final float FALL_ACCELERATION = -350;
    private static final float FALL_VEL_CAP = -500f;

    private final Circle circle;
    private final Color color;
    private final GlyphLayout ballText;

    private float xVel;
    private float yVel;
    private int bounces = 0;

    public Ball(Vector2 pos, float radius, Color color, GlyphLayout ballText) {
        this.circle = new Circle(pos, radius);
        this.color = color;
        this.ballText = ballText;
    }

    public void update(float leftWall, float rightWall, float delta) {
        accelerateFall(delta);
        circle.x += xVel * delta;
        circle.y += yVel * delta;

        bounceLeftWall(leftWall);
        bounceRightWall(rightWall);
    }

    public boolean isInside(Vector2 point) {
        return circle.contains(point);
    }

    public void bounceUp(Vector2 point) {
        if (!isInside(point)) {
            return;
        }

        yVel += BOUNCE_Y_ACCELERATION;
        xVel = BOUNCE_X_MAX_ACCELERATION * (circle.x - point.x) / circle.radius;
        bounces++;
    }

    public boolean isBelowCamera(int cameraFloor) {
        return circle.y + circle.radius < cameraFloor;
    }

    public float getX() {
        return circle.x;
    }

    public float getY() {
        return circle.y;
    }

    public float getRadius() {
        return circle.radius;
    }

    private void accelerateFall(float delta) {
        if (yVel <= FALL_VEL_CAP) {
            yVel = FALL_VEL_CAP;
        } else {
            yVel += FALL_ACCELERATION * delta;
        }
    }

    private void bounceLeftWall(float leftWall) {
        if (circle.x - circle.radius >= leftWall) {
            return;
        }

        xVel *= -1;
        circle.x = leftWall + circle.radius;
        bounces++;

    }

    private void bounceRightWall(float rightWall) {
        if (circle.x + circle.radius <= rightWall) {
            return;
        }

        xVel *= -1;
        circle.x = rightWall - circle.radius;
        bounces++;
    }
}
