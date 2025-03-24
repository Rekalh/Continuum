package me.rekalh.continuum.imgui.windows;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImInt;
import imgui.type.ImString;
import me.rekalh.continuum.Main;
import me.rekalh.continuum.imgui.ImGuiWindow;
import me.rekalh.continuum.imgui.windows.project1d.Linear1DRenderWindow;
import me.rekalh.continuum.imgui.windows.project1d.PropertiesLinear1DWindow;

/*
    /!\ Absolute mess. Please proceed with caution. /!\

    This is the Create Project window. You can specify the project type, and it automagically sets everything up for you.
    The implementation is really awful, and it should be reworked in the future.

    Won't bother commenting much.
 */

public class CreateProjectWindow extends ImGuiWindow {

    private ProjectType type;
    private Shape1D shape1D;
    private Shape2D shape2D;

    private final ImString projectName = new ImString(128);

    public CreateProjectWindow(int width, int height, String title, int... flags) {
        super(width, height, title, flags);
        type = ProjectType.NONE;
        shape1D = Shape1D.NONE;
        shape2D = Shape2D.NONE;
    }

    public CreateProjectWindow(String title, int... flags) {
        super(title, flags);
        type = ProjectType.NONE;
        shape1D = Shape1D.NONE;
        shape2D = Shape2D.NONE;
    }

    @Override
    public void render() {
        // Select project type (1-D or 2-D (3-D coming soon in stores near you! -> See footnote 1))
        boolean hasSelectedType = (type != ProjectType.NONE);
        selectType();

        if (hasSelectedType) {
            // Select geometry (1-D: Linear, 2-D: Rectangular and Circular)
            boolean hasSelectedGeometry = ((shape1D != Shape1D.NONE && type == ProjectType.D1) || (shape2D != Shape2D.NONE && type == ProjectType.D2));
            selectGeometry();

            if (hasSelectedGeometry) {
                // Enter project name
                enterName();
            }
        } else {
            shape1D = Shape1D.NONE;
            shape2D = Shape2D.NONE;
        }
    }

    ImInt projectIndexSelected = new ImInt(0);
    final String[] projectOptions = new String[] {"Select Project Type", "1D - One Dimensional Geometry", "2D - Two Dimensional Geometry"};

    private void selectType() {
        if (ImGui.combo("Project Type", projectIndexSelected, projectOptions)) {
            type = ProjectType.values()[projectIndexSelected.get()];
        }
    }

    ImInt shape1DIndexSelected = new ImInt(0);
    ImInt shape2DIndexSelected = new ImInt(0);
    final String[] shapeOptionsD1 = new String[] {"Select Geometry", "Linear"};
    final String[] shapeOptionsD2 = new String[] {"Select Geometry", "Rectangular", "Circular"};

    private void selectGeometry() {
        switch (type) {
            case D1 -> {
                if (ImGui.combo("Geometry", shape1DIndexSelected, shapeOptionsD1)) {
                    shape1D = Shape1D.values()[shape1DIndexSelected.get()];
                }
            }
            case D2 -> {
                if (ImGui.combo("Geometry", shape2DIndexSelected, shapeOptionsD2)) {
                    shape2D = Shape2D.values()[shape2DIndexSelected.get()];
                }
            }
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
        if (type == ProjectType.D1) {
            System.out.println("Creating project with parameters: " + type + ", " + shape1D);
            PropertiesLinear1DWindow propertiesLinear1D =
                new PropertiesLinear1DWindow("Properties - " + projectName.get(), projectName, ImGuiWindowFlags.AlwaysAutoResize);
            Linear1DRenderWindow renderWindow = new Linear1DRenderWindow(500, 400, propertiesLinear1D);

            Main.imGuiRenderer.addWindowToQueue(propertiesLinear1D);
            Main.imGuiRenderer.addWindowToQueue(renderWindow);
        }
        else if (type == ProjectType.D2) {
            System.out.println("Creating project with parameters: " + type + ", " + shape2D);
        }

        ImGui.setWindowCollapsed(true);
    }

    public enum ProjectType {
        NONE(0),
        D1(1),
        D2(2);

        final int index;

        ProjectType(int index) {
            this.index = index;
        }
    }

    private enum Shape1D {
        NONE(0),
        LINE(1);

        final int index;

        Shape1D(int index) {
            this.index = index;
        }
    }

    private enum Shape2D {
        NONE(0),
        RECTANGLE(1),
        CIRCLE(2);

        final int index;

        Shape2D(int index) {
            this.index = index;
        }
    }
}

// Footnote 1: Not happening.
