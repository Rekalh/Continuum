package me.rekalh.continuum.imgui.windows;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import me.rekalh.continuum.Main;
import me.rekalh.continuum.imgui.ImGuiWindow;

public class TestWindow extends ImGuiWindow {

    private final float[] color;

    public TestWindow(int width, int height, String title) {
        super(width, height, title);

        isOpen = false;

        color = new float[] {0, 0, 0};
    }

    @Override
    public void render() {
        if (!hasCustomSize)
            ImGui.setNextWindowSize((float)width, (float)height);

        if (!ImGui.begin(title, ImGuiWindowFlags.AlwaysAutoResize)) {
            ImGui.end();
            return;
        }

        ImGui.text("Test Text");

        if (ImGui.colorPicker3("Select background color", color)) {
            changeBackgroundColor(color);
        }

        if (ImGui.button("Close")) {
            close();
        }

        ImGui.end();
    }

    private void changeBackgroundColor(float[] color) {
        Main.color = color;
    }
}
