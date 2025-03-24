package me.rekalh.continuum.imgui;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import imgui.type.ImString;
import me.rekalh.continuum.imgui.windows.CreateProjectWindow;

/*
    In this window you specify the parameters for the project. These parameters include:
        - The geometry
        - Width and height of the geometry
        - 3 Color accents
        - The shader that gets used
        - The name
        - More to come in the future (provided the project doesn't get abandoned)
 */

public abstract class PropertiesWindow extends ImGuiWindow {

    protected final String projectName;
    protected final CreateProjectWindow.Geometry geometry;
    protected final ShaderProgram shader;

    public PropertiesWindow(int width, int height, String title, ImString projectName, CreateProjectWindow.Geometry geometry, int... flags) {
        super(width, height, title, flags);
        this.projectName = projectName.get();
        this.geometry = geometry;
        this.shader = this.initializeShader();
    }

    public PropertiesWindow(String title, ImString projectName, CreateProjectWindow.Geometry geometry, int... flags) {
        super(title, flags);
        this.projectName = projectName.get();
        this.geometry = geometry;
        this.shader = this.initializeShader();
    }

    protected abstract ShaderProgram initializeShader();

    public String getProjectName() {
        return this.projectName;
    }

    public CreateProjectWindow.Geometry getGeometry() {
        return geometry;
    }

    public ShaderProgram getShader() {
        return shader;
    }
}
