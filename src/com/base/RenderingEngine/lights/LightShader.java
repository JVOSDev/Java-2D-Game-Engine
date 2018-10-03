package com.base.RenderingEngine.lights;

import com.base.RenderingEngine.shader.Shader;

public class LightShader extends Shader
{

	public LightShader()
	{
		super("./res/Shaders/lightVertex.vs", "./res/Shaders/lightFragment.fs", 0, "ambientLight", "light.lightColor", "light.lightPos", "light.atten.a", "light.atten.b", "light.atten.c", "transformM", "projectionM");
	}

}
