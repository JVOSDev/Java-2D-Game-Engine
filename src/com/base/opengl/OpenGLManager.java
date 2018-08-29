package com.base.opengl;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenGLManager.
 */
public class OpenGLManager
{

	/**
	 * Clears the screen.
	 */
	public static void clearScreen()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	/**
	 * Initializes OpenGL to the current window;
	 *
	 * @param window
	 *            the current window
	 */
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

	/**
	 * Sets the color the screen clears to
	 *
	 * @param r
	 *            the red component of the color
	 * @param g
	 *            the green component of the color
	 * @param b
	 *            the blue component of the color
	 */
	public static void setClearColor(float r, float g, float b)
	{
		GL11.glClearColor(r, g, b, 1f);
	}
}
