package me.rekalh.continuum.imgui.windows.project1d;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import imgui.ImGui;
import imgui.type.ImString;
import me.rekalh.continuum.imgui.windows.CreateProjectWindow;
import me.rekalh.continuum.imgui.windows.PropertiesWindow;
import me.rekalh.continuum.util.ShaderManager;

public class PropertiesLinearRectWindow extends PropertiesWindow {

    private final float[] pWidth = {100};
    private final float[] pHeight = {100};
    private final float[] pColor1 = {1f, 0f, 0f};
    private final float[] pColor2 = {0f, 0f, 1f};
    private final float[] pColor3 = {0f, 1f, 0f};

    public PropertiesLinearRectWindow(int width, int height, String title, ImString projectName, CreateProjectWindow.Geometry geometry, int... flags) {
        super(width, height, title, projectName, geometry, flags);
    }

    public PropertiesLinearRectWindow(String title, ImString projectName, CreateProjectWindow.Geometry geometry, int... flags) {
        super(title, projectName, geometry, flags);
    }

    @Override
    public void render() {
        ImGui.text("Parameters");
        ImGui.sliderFloat("Width", pWidth, 0, 500);
        ImGui.sliderFloat("Height", pHeight, 0, 500);
        ImGui.colorEdit3("Select 'high' color", pColor1);
        ImGui.colorEdit3("Select 'low' color", pColor2);
        ImGui.colorEdit3("Select 'neutral' color", pColor3);
    }

    protected ShaderProgram initializeShader() {
        return ShaderManager.loadShader("shader1DLinear.vert", "shader1DLinear.frag");
    }

    public float getPWidth() {
        return this.pWidth[0];
    }

    public float getPHeight() {
        return this.pHeight[0];
    }

    public float[] getPColor1() {
        return this.pColor1;
    }

    public float[] getPColor2() {
        return this.pColor2;
    }

    public float[] getPColor3() {
        return this.pColor3;
    }
}
