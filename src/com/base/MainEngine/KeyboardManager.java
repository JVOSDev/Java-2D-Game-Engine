/*
 *
 */
package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyboardManager. This class acts as the interface between GLFW and
 * the rest of the program
 */
public class KeyboardManager extends GLFWKeyCallback
{

	/** The keys. */
	private static boolean[] keys = new boolean[65536];

	/**
	 * Poll a key code.
	 *
	 * @param keyCode
	 *            the GLFW key code
	 *
	 * @return true, if key is pressed
	 */
	public static boolean pollKey(int keyCode)
	{
		if(keyCode >= keys.length)
		{
			return false;
		}
		return keys[keyCode];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.lwjgl.glfw.GLFWKeyCallbackI#invoke(long, int, int, int, int)
	 */
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods)
	{
		keys[key] = action != GLFW.GLFW_RELEASE;
	}
}
