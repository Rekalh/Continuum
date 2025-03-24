package me.rekalh.continuum.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

/*
    FBOManager is used for the custom ImGui window rendering. Basically, we draw everything to a FrameBuffer object and
    everything get turned into a texture (Texture region) that we can use to render. The specified width and height are
    the dimensions of the FrameBuffer, and subsequently they determine the size of the image that gets drawn. (If rendering
    on an ImGui window, they should be the size of the window).
 */

public class FBOManager {

    private FrameBuffer frameBuffer;
    private TextureRegion region;

    public FBOManager(int width, int height) {
        create(width, height);
    }

    private void create(int width, int height) {
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

    public void updateSize(int width, int height) {
        dispose();
        create(width, height);
    }

    public TextureRegion getRegion() {
        return this.region;
    }

    public void dispose() {
        frameBuffer.dispose();
    }
}
