package me.rekalh.continuum.imgui.windows.project1d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import imgui.ImGui;
import me.rekalh.continuum.imgui.RenderWindow;
import me.rekalh.continuum.util.ShaderManager;

public class Linear1DRenderWindow extends RenderWindow {

    private final PropertiesLinear1DWindow properties;
    private ShaderProgram shader;

    private final int[] renderSize = new int[2];

    private long startTime;

    public Linear1DRenderWindow(int width, int height, PropertiesLinear1DWindow properties, int... flags) {
        super(width, height, "Render - " + properties.getProjectName(), flags);
        this.properties = properties;
        initializeShader();

        renderSize[0] = Gdx.graphics.getWidth();
        renderSize[1] = Gdx.graphics.getHeight();

        startTime = TimeUtils.millis();
    }

    public Linear1DRenderWindow(PropertiesLinear1DWindow properties, int... flags) {
        super("Render - " + properties.getProjectName(), flags);
        this.properties = properties;
        initializeShader();

        renderSize[0] = Gdx.graphics.getWidth();
        renderSize[1] = Gdx.graphics.getHeight();
    }

    SpriteBatch batch = new SpriteBatch();
    @Override
    public void render() {
        float pWidth = properties.getPWidth();
        float pHeight = properties.getPHeight();
        float[] pColor1 = properties.getPColor1();
        float[] pColor2 = properties.getPColor2();

        manager.begin();
        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1f);
        batch.setShader(shader);

        float x1 = (renderSize[0] - pWidth) / 2f;
        float y1 = (renderSize[1] - pHeight) / 2f;
        float x2 = (renderSize[0] + pWidth) / 2f;
        float y2 = (renderSize[1] + pHeight) / 2f;

        assert shader != null;
        shader.setUniformf("u_color1", pColor1[0], pColor1[1], pColor1[2]);
        shader.setUniformf("u_color2", pColor2[0], pColor2[1], pColor2[2]);
        shader.setUniformf("u_region", x1, y1, x2, y2);

        batch.begin();
        batch.draw(manager.getRegion(), -1, -1, renderSize[0], renderSize[1]);

        batch.end();
        batch.setShader(null);

        manager.end();

        TextureRegion region = manager.getRegion();
        ImGui.image(region.getTexture().getTextureObjectHandle(), this.width - 17, this.height - 58);

        if (ImGui.button("Close project")) {
            close();
            properties.close();
        }
    }

    private void initializeShader() {
        shader = ShaderManager.loadShader("shader1DLinear.vert", "shader1DLinear.frag");
    }

    @Override
    public void onResize() {
        System.out.println("Resized window!");
    }
}
