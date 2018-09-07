/*
 * 
 */
package com.base.MainEngine;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

// TODO: Auto-generated Javadoc
/**
 * The Class MouseManager.
 */
public class MouseManager extends GLFWCursorPosCallback
{

	/** The mouse direction. */
	private static Vector2f mouseDx = new Vector2f(0, 0);

	/** The mouse posistion. */
	private static Vector2f mousePos = new Vector2f(0, 0);

	/** The mouse button. */
	static boolean[] mouseButton = new boolean[10];

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lwjgl.glfw.GLFWCursorPosCallbackI#invoke(long, double, double)
	 */
	@Override
	public void invoke(long window, double xpos, double ypos)
	{
		mouseDx = mousePos.sub(new Vector2f((float) xpos, (float) ypos));
		mousePos = new Vector2f((float) xpos, (float) ypos);
	}

	/**
	 * Get the direction the mouse is moving. Already has the delta time
	 * Incorporated into the value returned.
	 *
	 * @return the mouse direction
	 */
	public static Vector2f getMouseDx()
	{
		return mouseDx;
	}

	/**
	 * Get the mouse position.
	 *
	 * @return the mouse position
	 */
	public static Vector2f getMousePos()
	{
		return mousePos;
	}

	/**
	 * Checks if a mouse button is pressed.
	 *
	 * @param button
	 *            the GLFW keycode of the button to be checked
	 * @return true, if the button is pressed
	 */
	public static boolean isButton(int button)
	{
		if(button >= mouseButton.length)
		{
			return false;
		}
		return mouseButton[button];
	}

}

class MouseButtonManager extends GLFWMouseButtonCallback
{

	@Override
	public void invoke(long window, int button, int action, int mods)
	{
		MouseManager.mouseButton[button] = action != GLFW.GLFW_RELEASE;
	}
}
