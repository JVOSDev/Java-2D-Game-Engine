#version 330 core

in vec4 vColor;
in vec2 texCoord0;

out vec4 fragColor;

uniform vec3 baseColor;
uniform sampler2D texture0;

void main()
{
	vec3 col = texture(texture0, texCoord0).xyz * baseColor * vColor.xyz;
    fragColor = vec4(col, 1.0);
}