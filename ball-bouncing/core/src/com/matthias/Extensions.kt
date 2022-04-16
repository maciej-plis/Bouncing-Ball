package com.matthias

import com.badlogic.gdx.Graphics

val Graphics.screenCenterX: Float
    get() = this.width / 2f

val Graphics.screenCenterY: Float
    get() = this.height / 2f