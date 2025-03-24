package me.rekalh.continuum;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import imgui.flag.ImGuiWindowFlags;
import me.rekalh.continuum.imgui.ImGuiRenderer;
import me.rekalh.continuum.imgui.windows.CreateProjectWindow;
import me.rekalh.continuum.imgui.windows.TestWindow;

public class Main extends ApplicationAdapter {

    public static final float ASPECT_RATIO = 16f / 9f;
    private static final int WINDOW_HEIGHT = 720;
    public static final int[] RESOLUTION = {(int)(WINDOW_HEIGHT * ASPECT_RATIO), 720};

    public static ImGuiRenderer imGuiRenderer;

    public static float[] color = {0.09f, 0.11f, 0.18f};

    @Override
    public void create() {
        imGuiRenderer = new ImGuiRenderer();

        TestWindow window = new TestWindow(500, 500, "Test");
        CreateProjectWindow createWindow = new CreateProjectWindow("Create new project", ImGuiWindowFlags.AlwaysAutoResize);

        imGuiRenderer.addWindowToQueue(window);
        imGuiRenderer.addWindowToQueue(createWindow);
    }

    @Override
    public void render() {
        ScreenUtils.clear(color[0], color[1], color[2], 1f);
        imGuiRenderer.render();
    }

    @Override
    public void dispose() { imGuiRenderer.dispose(); }
}
