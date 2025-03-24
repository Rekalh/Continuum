#ifdef GL_ES
precision mediump float;
#endif

uniform vec3 u_color1;
uniform vec3 u_color2;
uniform vec3 u_color3;
uniform vec2 u_size;

uniform vec2 u_resolution;

void main() {
    vec2 normalized_coords = gl_FragCoord.xy / u_resolution;
    vec2 normalized_size = u_size / u_resolution;

    float progress = (normalized_coords.x + normalized_size.x / 2 - 0.5) / normalized_size.x;

    if (normalized_coords.x > 0.5 - normalized_size.x / 2 && normalized_coords.x < 0.5 + normalized_size.x / 2
    && normalized_coords.y > 0.5 - normalized_size.y / 2 && normalized_coords.y < 0.5 + normalized_size.y / 2) {
        if (progress < 0.5) {
            gl_FragColor = vec4(mix(u_color1, u_color3, 2 * progress), 1.0);
        } else {
            gl_FragColor = vec4(mix(u_color3, u_color2, 2 * progress - 1), 1.0);
        }
    }
}
