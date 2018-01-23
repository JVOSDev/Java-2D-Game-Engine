package com.base.opengl;

import org.lwjgl.opengl.*;

public class OpenGLManager
{
	public static void clearScreen()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	public static void initOpenGL(Window window)
	{
		window.makeWindowCurrent();

		GL.createCapabilities();

		GL11.glFrontFace(GL11.GL_CCW);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void setClearColor(float r, float g, float b)
	{
		GL11.glClearColor(r, g, b, 1f);
	}
}
