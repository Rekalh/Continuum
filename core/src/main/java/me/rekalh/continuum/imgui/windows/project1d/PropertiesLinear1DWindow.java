package me.rekalh.continuum.imgui.windows.project1d;

import imgui.ImGui;
import imgui.type.ImString;
import me.rekalh.continuum.imgui.ImGuiWindow;

/*
    In this window you specify the parameters for the project. These parameters include:
        - Width and height of the geometry
        - 2 Color accents
        - More to come in the future (provided the project doesn't get abandoned)
 */

public class PropertiesLinear1DWindow extends ImGuiWindow {

    private final float[] pWidth = {100};
    private final float[] pHeight = {100};
    private final float[] pColor1 = {1f, 0f, 0f};
    private final float[] pColor2 = {0f, 0f, 1f};
    private final float[] pColor3 = {0f, 1f, 0f};

    private final String projectName;

    public PropertiesLinear1DWindow(int width, int height, String title, ImString projectName, int... flags) {
        super(width, height, title, flags);

        this.projectName = projectName.get();
    }

    public PropertiesLinear1DWindow(String title, ImString projectName, int... flags) {
        super(title, flags);

        this.projectName = projectName.get();
    }

    @Override
    public void render() {
        ImGui.text("Parameters");
        ImGui.dragFloat("Width", pWidth);
        ImGui.dragFloat("Height", pHeight);
        ImGui.colorEdit3("Select 'high' color", pColor1);
        ImGui.colorEdit3("Select 'low' color", pColor2);
        ImGui.colorEdit3("Select 'neutral' color", pColor3);
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

    public String getProjectName() {
        return this.projectName;
    }
}
