#version 400

in vec2 pos0;

struct Attenuation
{
	float a;
	float b;
	float c;
};

struct PointLight
{
	vec3 lightColor;
	vec2 lightPos;
	Attenuation atten;
};

uniform vec3 ambientLight;
uniform PointLight light;

out vec4 fragColor;

void main()
{
	vec4 lColor = vec4(light.lightColor.xyz, 1);
	
	float distance = distance(pos0, light.lightPos);
	
	float power = 1/(light.atten.a*pow(distance, 2)+light.atten.b*distance+(1/light.atten.c));
	
    fragColor = (lColor * power) + vec4(ambientLight, 1);
}