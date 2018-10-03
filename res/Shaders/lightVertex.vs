#version 330 core

layout(location = 0) in vec2 position;
layout(location = 1) in vec2 texCoord;

out vec2 pos0;

uniform mat4 transformM;
uniform mat4 projectionM;

void main()
{
	vec4 pos = vec4(position, 0, 1) * transformM;
	pos0 = pos.xy;
    gl_Position = pos * projectionM;;
}