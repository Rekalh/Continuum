#version 330 core

in vec4 a_position;
in vec2 a_texCoord0;

out vec2 v_texCoord;

void main() {
    v_texCoord = a_texCoord0;
    gl_Position = a_position;
}
