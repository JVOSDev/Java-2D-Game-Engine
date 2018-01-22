package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;

import com.base.RenderingEngine.mesh.Triangle;
import com.base.opengl.GLFWManager;
import com.base.opengl.OpenGLManager;
import com.base.opengl.Window;

public class MainEngine
{
	private Window window;

	public MainEngine()
	{
		init();
	}

	public void start()
	{
		loop();
	}

	public void stop()
	{
		cleanUp();
	}

	private void loop()
	{
		Triangle t = new Triangle();
		while (!window.isCloseRequested())
		{
			OpenGLManager.clearScreen();
			//TODO: update input engine? maybe
			//TODO: update Objects
			//TODO: update Physics engine
			//TODO: update Audio engine
			//TODO: update rendering engine(Render)
			t.draw();

			window.swapBuffers();
		}
	}

	private void init()
	{
		GLFWManager.initGLFW();
		window = Window.createWindow(1280, 720, "Test 2D Game");
		window.makeWindowCurrent();

		GLFW.glfwSetKeyCallback(window.getId(), (window, key, scancode, action, mods) ->
		{
			if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
				this.window.closeWindow();
			this.stop();
		});

		OpenGLManager.initOpenGL(window);
	}

	private void cleanUp()
	{
		window.destroy();
		GLFWManager.destroyGLFW();
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		cleanUp();
	}

	public Window getWindow()
	{
		return window;
	}
}
