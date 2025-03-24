package me.rekalh.continuum.imgui.windows;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImInt;
import imgui.type.ImString;
import me.rekalh.continuum.Main;
import me.rekalh.continuum.imgui.ImGuiWindow;
import me.rekalh.continuum.imgui.windows.project1d.PropertiesLinearRectWindow;

/*
    /!\ Absolute mess. Please proceed with caution. /!\

    This is the Create Project window. You can specify the project type, and it automagically sets everything up for you.
    The implementation is really awful, and it should be reworked in the future.

    Won't bother commenting much.
 */

public class CreateProjectWindow extends ImGuiWindow {

    private Geometry geometry;
    private final ImString projectName = new ImString(128);

    public CreateProjectWindow(int width, int height, String title, int... flags) {
        super(width, height, title, flags);
        this.geometry = Geometry.NONE;
    }

    public CreateProjectWindow(String title, int... flags) {
        super(title, flags);
        this.geometry = Geometry.NONE;
    }

    @Override
    public void render() {
        this.selectGeometry();

        if (this.geometry != Geometry.NONE) {
            this.enterName();
        }
    }

    ImInt shapeIndexSelected = new ImInt(0);
    final String[] shapeOptions = new String[] {"Select Geometry", "Linear (1D)", "Rectangular (2D)", "Disk (2D)"};

    private void selectGeometry() {
        if (ImGui.combo("Geometry", shapeIndexSelected, shapeOptions)) {
            this.geometry = Geometry.values()[shapeIndexSelected.get()];
        }
    }

    private void enterName() {
        ImGui.inputText("Project Name", projectName);

        if (projectName.isNotEmpty()) {
            if (ImGui.button("Create")) {
                this.createProject();
            }
        }
    }

    private void createProject() {
        System.out.println("Creating project with parameters: " + geometry);
        PropertiesLinearRectWindow propertiesLinear1D =
            new PropertiesLinearRectWindow("Properties - " + projectName.get(), projectName, geometry, ImGuiWindowFlags.AlwaysAutoResize);
        ContinuumRenderWindow renderWindow = new ContinuumRenderWindow(500, 400, propertiesLinear1D);

        Main.imGuiRenderer.addWindowToQueue(propertiesLinear1D);
        Main.imGuiRenderer.addWindowToQueue(renderWindow);

        ImGui.setWindowCollapsed(true);
    }

    public enum Geometry {
        NONE(0),
        LINEAR(1),
        RECTANGLE(2),
        CIRCLE(3);

        final int index;

        Geometry(int index) {
            this.index = index;
        }
    }
}

// Footnote 1: Not happening.
