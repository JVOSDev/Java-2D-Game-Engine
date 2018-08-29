package com.base.opengl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

// TODO: Auto-generated Javadoc
/**
 * The Class GLFWManager.
 */
public class GLFWManager
{

	/**
	 * Destroys GLFW data
	 */
	public static void destroyGLFW()
	{
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}

	/**
	 * Initializes GLFW.
	 */
	public static void initGLFW()
	{
		GLFWErrorCallback.createPrint(System.err).set();
		if(!GLFW.glfwInit())
		{
			throw new IllegalStateException("Unable to initialize GLFW");
		}
	}
}
