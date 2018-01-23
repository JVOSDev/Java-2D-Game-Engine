package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;

import com.base.MainEngine.scene.Camera;
import com.base.MainEngine.scene.Scene;
import com.base.RenderingEngine.RenderingEngine;
import com.base.opengl.GLFWManager;
import com.base.opengl.OpenGLManager;
import com.base.opengl.Window;

public class MainEngine
{
	private int LENGTH = 1280;
	private int WIDTH = 720;
	
	private Window window;
	private Scene scene;
	private RenderingEngine renderEngine;
	private Camera camera;

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
			float startTime = System.nanoTime();
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
			
			delta = (System.nanoTime() - startTime)/1000000000f;
		}
	}

	private void init()
	{
		GLFWManager.initGLFW();
		window = Window.createWindow(LENGTH, WIDTH, "Test 2D Game");
		window.makeWindowCurrent();

		GLFW.glfwSetKeyCallback(window.getId(), (window, key, scancode, action, mods) ->
		{
			if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
				this.window.closeWindow();
			this.stop();
		});

		OpenGLManager.initOpenGL(window);
		this.scene = new Scene();
		this.camera = new Camera(1,-1,-1,1,-10, 10, ((float)LENGTH/(float)WIDTH));
		scene.addNode(camera);
		this.renderEngine = new RenderingEngine(camera);
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
