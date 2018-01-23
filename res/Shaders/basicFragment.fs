#version 330 core

in vec2 texCoord0;

out vec4 fragColor;

uniform vec3 baseColor;
uniform sampler2D texture0;
uniform sampler2D texture1;

void main()
{
	vec3 col = baseColor;
    fragColor = texture(texture0, texCoord0) * vec4(baseColor, 1);
}