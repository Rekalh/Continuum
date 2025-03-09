package me.rekalh.continuum.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderManager {

    public static ShaderProgram loadShader(String vertex, String fragment) {
        ShaderProgram.pedantic = false;

        String vertexShader = Gdx.files.internal("shaders/" + vertex).readString();
        String fragmentShader = Gdx.files.internal("shaders/" + fragment).readString();

        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

        if (!shader.isCompiled()) {
            System.out.println("Shader " + vertex + " / " + fragment + " could not compile!");
            System.out.println(shader.getLog());
            return null;
        }

        return shader;
    }
}
