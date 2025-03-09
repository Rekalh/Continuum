package me.rekalh.continuum.imgui;

import me.rekalh.continuum.util.FBOManager;

public abstract class RenderWindow extends ImGuiWindow {

    protected FBOManager manager;

    public RenderWindow(int width, int height, String title, int... flags) {
        super(width, height, title, flags);
        create();
    }

    public RenderWindow(String title, int... flags) {
        super(title, flags);
        create();
    }

    private void create() {
        manager = new FBOManager(this.width, this.height);
    }

    @Override
    public abstract void render();
}
