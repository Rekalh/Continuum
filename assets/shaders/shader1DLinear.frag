#ifdef GL_ES
precision mediump float;
#endif

uniform vec3 u_color1;
uniform vec3 u_color2;
uniform vec4 u_region;

void main() {
    vec2 fragPos = gl_FragCoord.xy;

    if (fragPos.x < u_region.x || fragPos.x > u_region.z || fragPos.y < u_region.y || fragPos.y > u_region.w) {
        discard;
    }

    float t = (fragPos.x - u_region.x) / (u_region.z - u_region.x);

    vec3 color = vec3(0.);

    if (t < 0.5) {
        color = mix(u_color1, u_color2, t * 2.0);
    } else {
        color = mix(u_color1, u_color2, (t - 0.5) * 2.0);
    }

    gl_FragColor = vec4(color, 1.0);
}
