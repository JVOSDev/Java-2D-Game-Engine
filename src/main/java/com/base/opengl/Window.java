package com.base.opengl;

import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import java.nio.*;

public class Window
{
	private long id;

	private Window(long id)
	{
		this.id = id;

		GLFW.glfwSetWindowSizeCallback(id, (long window, int width, int height) ->
		{
			GLFW.glfwSetWindowSize(id, width, height);
		});

	}

	public static Window createWindow(int width, int height, String title)
	{
		long windowId = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		if (windowId == 0)
		{
			System.err.println("Error: Error in window creation");
			new Exception().printStackTrace();
			throw new RuntimeException("Failure to create window");
		}

		return new Window(windowId);
	}

	public void destroy()
	{
		Callbacks.glfwFreeCallbacks(this.id);
		GLFW.glfwDestroyWindow(this.id);
	}

	public void closeWindow()
	{
		GLFW.glfwSetWindowShouldClose(id, true);
	}

	public int[] getDimensions()
	{
		int[] ret = new int[2];

		try (MemoryStack stack = MemoryStack.stackPush())
		{
			IntBuffer width = stack.mallocInt(1);
			IntBuffer height = stack.mallocInt(1);

			GLFW.glfwGetWindowSize(this.id, width, height);
			ret[0] = width.get(0);
			ret[1] = height.get(0);
		}

		return ret;
	}

	public int getHeight()
	{
		return this.getDimensions()[1];
	}

	public long getId()
	{
		return this.id;
	}

	public int getWidth()
	{
		return this.getDimensions()[0];
	}

	public boolean isCloseRequested()
	{
		return GLFW.glfwWindowShouldClose(this.id);
	}

	public void makeWindowCurrent()
	{
		GLFW.glfwMakeContextCurrent(this.id);
	}

	public void swapBuffers()
	{
		this.makeWindowCurrent();
		GLFW.glfwPollEvents();
		GLFW.glfwSwapBuffers(this.id);
	}
}
