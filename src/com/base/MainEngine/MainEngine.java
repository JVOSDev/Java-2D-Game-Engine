package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;

import com.base.MainEngine.scene.Scene;
import com.base.RenderingEngine.RenderingEngine;
import com.base.opengl.GLFWManager;
import com.base.opengl.OpenGLManager;
import com.base.opengl.Window;

public class MainEngine
{
	private Window window;
	private Scene scene;
	private RenderingEngine renderEngine;

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
		float delta = 0;
		while (!window.isCloseRequested())
		{
			float startTime = System.currentTimeMillis();
			OpenGLManager.clearScreen();
			
			//TODO: update input engine? maybe
			scene.input(delta, this);
			
			//update Objects(Scene)
			scene.update(delta, this);
			
			//TODO: update Physics engine
			//TODO: update Audio engine
			
			//update rendering engine(Render)
			scene.render(this);
			
			window.swapBuffers();
			
			delta = System.currentTimeMillis() - startTime;
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
		this.scene = new Scene();
		this.renderEngine = new RenderingEngine();
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

	public Scene getScene()
	{
		return scene;
	}

	public RenderingEngine getRenderEngine()
	{
		return renderEngine;
	}
}
