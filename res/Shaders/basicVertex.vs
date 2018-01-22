#version 330 core

layout(location = 0) in vec2 position;
layout(location = 1) in vec2 texCoord;

out vec4 vColor;
out vec2 texCoord0;

uniform mat3 transformM;

uniform mat4 projectionM;

void main()
{
	texCoord0 = texCoord;
	vColor = 2*vec4(position, position.x * position.y, 1.0);
	
    gl_Position = vec4(vec3(position, 0) * transformM, 1.0) * projectionM;
}