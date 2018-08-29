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
	 * Instantiates a new main engine.\ The main engine controls all of the
	 * other parts of the gameengine. It loads the audio, physics and rendering
	 * engine as well as the the scene graph.
	 */
	public MainEngine()
	{
		this.init();
	}

	/**
	 * Starts the engine.
	 */
	public void start()
	{
		this.loop();
	}

	/**
	 * Stops the engine. Runs the clean up procedure for the engine.
	 */
	public void stop()
	{
		this.cleanUp();
		System.exit(0);
	}

	/**
	 * The main game loop.
	 */
	private void loop()
	{
		float delta = 0;
		float tTime = 0;
		int frameCount = 0;
		while(!this.window.isCloseRequested())
		{
			float startTime = System.nanoTime();
			OpenGLManager.clearScreen();

			// TODO: update input engine? maybe
			this.scene.input(delta, this);
			if(InputManager.pollKey(GLFW.GLFW_KEY_ESCAPE))
			{
				this.window.closeWindow();
				this.stop();
			}

			// update Objects(Scene)
			this.scene.update(delta, this);

			// TODO: update Physics engine

			// update Audio engine
			// this.audioEngine.updateListener(camera.getTransform().getTranslation());

			// update rendering engine(Render)
			this.scene.render(this);

			this.window.swapBuffers();
			frameCount++;

			delta = (System.nanoTime() - startTime) / 1000000000f;
			tTime += delta;
			if(tTime >= 1)
			{
				System.out.println(frameCount);
				tTime = 0;
				frameCount = 0;
			}
		}
	}

	/**
	 * Inits the engine with a window, opengl, a scene and a camera.
	 */
	private void init()
	{
		GLFWManager.initGLFW();
		this.window = Window.createWindow(this.LENGTH, this.WIDTH, "Test 2D Game");
		this.window.makeWindowCurrent();

		OpenGLManager.initOpenGL(this.window);
		this.scene = new Scene();
		this.camera = new Camera(1, -1, -1, 1, -10, 10, ((float) this.LENGTH / (float) this.WIDTH));
		this.scene.addNode(this.camera);
		this.renderEngine = new RenderingEngine(this.camera);

		GLFW.glfwSetKeyCallback(this.window.getId(), new InputManager());
	}

	/**
	 * Cleans up. Destroys all objects and pointers
	 */
	private void cleanUp()
	{
		this.window.destroy();
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
		this.cleanUp();
	}

	/**
	 * Gets the window.
	 *
	 * @return the window
	 */
	public Window getWindow()
	{
		return this.window;
	}

	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public Scene getScene()
	{
		return this.scene;
	}

	/**
	 * Gets the rendering engine.
	 *
	 * @return the render engine
	 */
	public RenderingEngine getRenderEngine()
	{
		return this.renderEngine;
	}
}
