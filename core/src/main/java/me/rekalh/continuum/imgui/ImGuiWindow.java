package me.rekalh.continuum.imgui;

import imgui.ImGui;
import imgui.ImVec2;

/*
    Parent class (abstract) of all custom windows. It uses ImGui integration in order to create a window.
    All children implement the render function, where ImGui elements can be added. The class handles the sizing and
    positioning of the window. What gets drawn is handled in the implementation of this class for each specific case.
 */

public abstract class ImGuiWindow {

    protected int height;
    protected int width;

    private int prevWidth = -1;
    private int prevHeight = -1;

    protected ImVec2 position;

    protected boolean hasCustomSize;
    protected boolean isOpen = true;

    protected boolean hasBeenPlaced = false;

    protected int flags;

    protected String title;

    public ImGuiWindow(int width, int height, String title, int... flags) {
        this.height = height;
        this.width = width;
        this.title = title;

        this.hasCustomSize = false;

        for (int flag : flags) {
            this.flags = this.flags | flag;
        }
    }

    public ImGuiWindow(String title, int... flags) {
        this.hasCustomSize = true;

        this.title = title;

        for (int flag : flags) {
            this.flags = this.flags | flag;
        }
    }

    public void setup() {
        if (!this.hasCustomSize)
            ImGui.setNextWindowSize((float)width, (float)height);

        if (!ImGui.begin(title, flags)) {
            ImGui.end();
            return;
        }

        this.position = ImGui.getWindowPos();
        this.height = (int)ImGui.getWindowHeight();
        this.width = (int)ImGui.getWindowWidth();

        if (this.height != this.prevHeight || this.width != this.prevWidth) {
            this.onResize();
            this.prevHeight = height;
            this.prevWidth = width;
        }

        this.render();
        ImGui.end();
    }

    public abstract void render();

    public void onResize() {

    }

    public void close() {
        this.isOpen = false;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public boolean hasBeenPlaced() {
        return this.hasBeenPlaced;
    }

    public void setPlaced() {
        this.hasBeenPlaced = true;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ImVec2 getPosition() {
        return this.position;
    }

    public String getTitle() {
        return title;
    }
}
