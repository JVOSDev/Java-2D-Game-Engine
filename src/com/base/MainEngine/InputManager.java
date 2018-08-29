package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class InputManager extends GLFWKeyCallback
{
	private static boolean[] keys = new boolean[65536];

	public static boolean pollKey(int keyCode)
	{
		return keys[keyCode];
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods)
	{
		keys[key] = action != GLFW.GLFW_RELEASE;
	}
}
