package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;

import com.base.AudioEngine.AudioEngine;
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
	
	private AudioEngine audioEngine;

	/** The camera. */
	private Camera camera;

	/**
	 * Instantiates a new main engine.\ The main engine controls all of the
	 * other parts of the gameengine. It loads the audio, physics and rendering
	 * engine as well as the the scene graph.
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
	 * Stops the engine. Runs the clean up procedure for the engine.
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
		while(!window.isCloseRequested())
		{
			float startTime = System.nanoTime();
			OpenGLManager.clearScreen();

			// TODO: update input engine? maybe
			scene.input(delta, this);

			// update Objects(Scene)
			scene.update(delta, this);

			// TODO: update Physics engine
			
			//update Audio engine
			this.audioEngine.updateListener(camera.getTransform().getTranslation());

			// update rendering engine(Render)
			scene.render(this);

			window.swapBuffers();

			delta = (System.nanoTime() - startTime) / 1000000000f;
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
			if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
				this.window.closeWindow();
			this.stop();
		});

		OpenGLManager.initOpenGL(window);
		this.scene = new Scene();
		this.camera = new Camera(1, -1, -1, 1, -10, 10, ((float) LENGTH / (float) WIDTH));
		scene.addNode(camera);
		this.renderEngine = new RenderingEngine(camera);
		this.audioEngine = new AudioEngine();
	}

	/**
	 * Cleans up. Destroys all objects and pointers
	 */
	private void cleanUp()
	{
		window.destroy();
		audioEngine.cleanUp();
		GLFWManager.destroyGLFW();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	/**
	 * 
	 */
	@Override
	protected void finalize() throws Throwable
	{
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
