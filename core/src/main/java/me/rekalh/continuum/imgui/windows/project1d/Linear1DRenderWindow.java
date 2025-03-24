package me.rekalh.continuum.imgui.windows.project1d;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import imgui.ImGui;
import me.rekalh.continuum.imgui.RenderWindow;
import me.rekalh.continuum.util.ShaderManager;

/*
    This is where the magic happens.
 */

public class Linear1DRenderWindow extends RenderWindow {

    private final PropertiesLinear1DWindow properties;
    private ShaderProgram shader;


    public Linear1DRenderWindow(int width, int height, PropertiesLinear1DWindow properties, int... flags) {
        super(width, height, "Render - " + properties.getProjectName(), flags);
        this.properties = properties;
        initializeShader();
    }

    public Linear1DRenderWindow(PropertiesLinear1DWindow properties, int... flags) {
        super("Render - " + properties.getProjectName(), flags);
        this.properties = properties;
        initializeShader();
    }

    SpriteBatch batch = new SpriteBatch();
    @Override
    public void render() {
        float pWidth = properties.getPWidth();
        float pHeight = properties.getPHeight();
        float[] pColor1 = properties.getPColor1();
        float[] pColor2 = properties.getPColor2();
        float[] pColor3 = properties.getPColor3();

        manager.begin();

        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1f);
        batch.setShader(shader);

        shader.setUniformf("u_color1", pColor1[0], pColor1[1], pColor1[2]);
        shader.setUniformf("u_color2", pColor2[0], pColor2[1], pColor2[2]);
        shader.setUniformf("u_color3", pColor3[0], pColor3[1], pColor3[2]);
        shader.setUniformf("u_size", pWidth, pHeight);
        shader.setUniformf("u_resolution", width, height);

        batch.begin();
        batch.draw(manager.getRegion(), -1, -1, width, height);
        batch.end();
        batch.setShader(null);

        manager.end();

        TextureRegion region = manager.getRegion();
        ImGui.image(region.getTexture().getTextureObjectHandle(), width - 17, height - 58);

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
        manager.updateSize(width, height);
    }
}
