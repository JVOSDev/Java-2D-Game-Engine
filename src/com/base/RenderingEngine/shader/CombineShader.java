package com.base.RenderingEngine.shader;

public class CombineShader extends Shader
{
	public CombineShader()
	{
		super("./res/Shaders/combineVertex.vs", "./res/Shaders/combineFragment.fs", 0, "texture0", "texture1");

		this.bind();
		this.updateUniform1i(0, "texture0");
		this.updateUniform1i(1, "texture1");
	}
}
