package com.base.opengl;

import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import java.nio.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Window.
 */
public class Window
{
	
	/** The id pointer of the window. */
	private long id;

	/**
	 * Instantiates a new window.
	 *
	 * @param id 
	 * 				the id of the window
	 */
	private Window(long id)
	{
		this.id = id;

		GLFW.glfwSetWindowSizeCallback(id, (long window, int width, int height) ->
		{
			GLFW.glfwSetWindowSize(id, width, height);
		});

	}

	/**
	 * Creates a window.
	 *
	 * @param width 
	 * 				the width of the window
	 * @param height 
	 * 				the height of the window
	 * @param title 
	 * 				the title of the window
	 * @return the window instance
	 */
	public static Window createWindow(int width, int height, String title)
	{
		long windowId = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		if (windowId == 0)
		{
			System.err.println("Error: Error in window creation");
			new Exception().printStackTrace();
			throw new RuntimeException("Failure to create window");
		}

		return new Window(windowId);
	}

	/**
	 * Destroys the window data
	 */
	public void destroy()
	{
		Callbacks.glfwFreeCallbacks(this.id);
		GLFW.glfwDestroyWindow(this.id);
	}

	/**
	 * Closes the window
	 */
	public void closeWindow()
	{
		GLFW.glfwSetWindowShouldClose(id, true);
	}

	/**
	 * Gets the dimensions of the window;
	 *
	 * @return the dimensions of the window (x,y)
	 */
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

	/**
	 * Gets the height of the window
	 *
	 * @return the height
	 */
	public int getHeight()
	{
		return this.getDimensions()[1];
	}

	/**
	 * Gets the id pointer of the window
	 * For use with GLFW
	 *
	 * @return the id
	 */
	public long getId()
	{
		return this.id;
	}

	/**
	 * Gets the width of the window
	 *
	 * @return the width
	 */
	public int getWidth()
	{
		return this.getDimensions()[0];
	}

	/**
	 * Checks the close flag of the specified window.
	 *
	 * @return true, if the window has been closed
	 */
	public boolean isCloseRequested()
	{
		return GLFW.glfwWindowShouldClose(this.id);
	}

	/**
	 * Makes the window current.
	 */
	public void makeWindowCurrent()
	{
		GLFW.glfwMakeContextCurrent(this.id);
	}

	/**
	 * Swap buffers. Updates the window
	 */
	public void swapBuffers()
	{
		this.makeWindowCurrent();
		GLFW.glfwPollEvents();
		GLFW.glfwSwapBuffers(this.id);
	}
}
