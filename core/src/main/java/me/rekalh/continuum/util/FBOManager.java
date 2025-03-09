package me.rekalh.continuum.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class FBOManager {

    private final FrameBuffer frameBuffer;
    private final TextureRegion region;

    public FBOManager(int width, int height) {
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        region = new TextureRegion(frameBuffer.getColorBufferTexture());
    }

    public void begin() {
        frameBuffer.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void end() {
        frameBuffer.end();
    }

    public TextureRegion getRegion() {
        return this.region;
    }

    public void dispose() {
        frameBuffer.dispose();
    }
}
