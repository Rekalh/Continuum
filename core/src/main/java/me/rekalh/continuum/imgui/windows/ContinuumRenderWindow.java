package me.rekalh.continuum.imgui.windows;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import imgui.ImGui;
import me.rekalh.continuum.imgui.PropertiesWindow;
import me.rekalh.continuum.imgui.RenderWindow;
import me.rekalh.continuum.imgui.windows.project1d.PropertiesLinearRectWindow;

/*
    This is where the magic happens.
*/

public class ContinuumRenderWindow extends RenderWindow {

    private final PropertiesWindow properties;
    private final ShaderProgram shader;

    public ContinuumRenderWindow(int width, int height, PropertiesWindow properties, int... flags) {
        super(width, height, "Render - " + properties.getProjectName(), flags);
        this.properties = properties;
        this.shader = properties.getShader();
    }

    SpriteBatch batch = new SpriteBatch();
    @Override
    public void render() {
        manager.begin();

        ScreenUtils.clear(0.15f, 0.15f, 0.15f, 1f);
        batch.setShader(shader);

        switch (properties.getGeometry()) {
            case LINEAR, RECTANGLE -> {
                PropertiesLinearRectWindow linearProperties = (PropertiesLinearRectWindow)properties;

                float[] pColor1 = linearProperties.getPColor1();
                float[] pColor2 = linearProperties.getPColor2();
                float[] pColor3 = linearProperties.getPColor3();
                float pWidth = linearProperties.getPWidth();
                float pHeight = linearProperties.getPHeight();

                shader.setUniformf("u_color1", pColor1[0], pColor1[1], pColor1[2]);
                shader.setUniformf("u_color2", pColor2[0], pColor2[1], pColor2[2]);
                shader.setUniformf("u_color3", pColor3[0], pColor3[1], pColor3[2]);
                shader.setUniformf("u_size", pWidth, pHeight);
            }
        }

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

    @Override
    public void onResize() {
        manager.updateSize(width, height);
    }
}
