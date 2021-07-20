#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 inColor;

out vec3 exColour;

uniform mat4 projectionMatrix;

void main()
{
	gl_Position = projectionMatrix * vec4(position, 5);
	exColour = inColor;
}