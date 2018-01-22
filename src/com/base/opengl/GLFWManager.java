package com.base.opengl;

import org.lwjgl.glfw.*;

public class GLFWManager
{
	public static void destroyGLFW()
	{
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}

	public static void initGLFW()
	{
		GLFWErrorCallback.createPrint(System.err).set();
		if (!GLFW.glfwInit())
		{
			throw new IllegalStateException("Unable to initialize GLFW");
		}
	}
}
