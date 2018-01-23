package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;

import com.base.MainEngine.scene.Camera;
import com.base.MainEngine.scene.Scene;
import com.base.RenderingEngine.RenderingEngine;
import com.base.opengl.GLFWManager;
import com.base.opengl.OpenGLManager;
import com.base.opengl.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class MainEngine.
 */
public class MainEngine
{
	
	/** The length. */
	private int LENGTH = 1280;
	
	/** The width. */
	private int WIDTH = 720;
	
	/** The window. */
	private Window window;
	
	/** The scene. */
	private Scene scene;
	
	/** The render engine. */
	private RenderingEngine renderEngine;
	
	/** The camera. */
	private Camera camera;

	/**
	 * Instantiates a new main engine.
	 */
	public MainEngine()
	{
		init();
	}

	/**
	 * Starts the engine.
	 */
	public void start()
	{
		loop();
	}

	/**
	 * Stops the engine.
	 */
	public void stop()
	{
		cleanUp();
	}

	/**
	 * The main game loop.
	 */
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

	/**
	 * Inits the engine with a window, opengl, a scene and a camera.
	 */
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

	/**
	 * Cleans up. Destroys all objects and pointers
	 */
	private void cleanUp()
	{
		window.destroy();
		GLFWManager.destroyGLFW();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		cleanUp();
	}

	/**
	 * Gets the window.
	 *
	 * @return the window
	 */
	public Window getWindow()
	{
		return window;
	}

	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public Scene getScene()
	{
		return scene;
	}

	/**
	 * Gets the renderengine.
	 *
	 * @return the renderengine
	 */
	public RenderingEngine getRenderEngine()
	{
		return renderEngine;
	}
}
