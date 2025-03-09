package me.rekalh.continuum.imgui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.List;

public class ImGuiRenderer {

    ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    List<ImGuiWindow> activeWindows = new ArrayList<>();
    List<ImGuiWindow> queuedWindows = new ArrayList<>();

    public ImGuiRenderer() {
        long windowHandle = ((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();
        GLFW.glfwMakeContextCurrent(windowHandle);
        GL.createCapabilities();
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.setWantCaptureKeyboard(true);
        io.setIniFilename(null);
        io.getFonts().addFontDefault();
        io.getFonts().build();

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init("#version 330 core");
    }

    final int windowOffsetX = 20;
    final int windowOffsetY = 20;
    int currentX = 40;
    int currentY = 40;

    public void render() {
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        if (queuedWindows.size() > 0) {
            activeWindows.addAll(queuedWindows);
            queuedWindows.clear();
        }

        activeWindows.removeIf(window -> !window.isOpen());
        for (ImGuiWindow window : activeWindows) {
            if (!window.hasBeenPlaced()) {
                ImGui.setNextWindowPos(currentX, currentY);
                window.setPlaced();
            }

            window.renderSetup();
        }

        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());
    }

    public void dispose() {
        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();
    }

    public void addWindowToQueue(ImGuiWindow window) { queuedWindows.add(window); }
}
