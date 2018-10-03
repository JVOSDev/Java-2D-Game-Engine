package com.base.RenderingEngine.lights;

import com.base.RenderingEngine.shader.Shader;

public class ShadowShader extends Shader
{
	public ShadowShader()
	{
		super("./res/Shaders/shadowVertex.vs", "./res/Shaders/shadowFragment.fs", 0);
	}
}
