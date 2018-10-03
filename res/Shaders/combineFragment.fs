#version 330 core

in vec2 texCoord0;

out vec4 fragColor;

uniform sampler2D texture0;
uniform sampler2D texture1;

void main()
{
	vec4 tex0 = texture(texture0, texCoord0);
	vec4 tex1 = texture(texture1, texCoord0);
	
    fragColor = tex0 * tex1;
}