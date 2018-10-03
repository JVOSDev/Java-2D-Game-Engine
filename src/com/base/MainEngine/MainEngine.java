/*
 *
 */
package com.base.MainEngine;

import org.lwjgl.glfw.GLFW;

import com.base.MainEngine.scene.Camera;
import com.base.MainEngine.scene.Scene;
import com.base.MainEngine.scene.sceneloader.SceneImporter;
import com.base.RenderingEngine.RenderingEngine;
import com.base.RenderingEngine.Texture;
import com.base.RenderingEngine.mesh.Mesh;
import com.base.RenderingEngine.mesh.Rectangle;
import com.base.RenderingEngine.shader.CombineShader;
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
	public static final int WIDTH = 1280;

	/** The width. */
	public static final int HEIGHT = 720;

	/** The window. */
	private static Window window;

	/** The scene. */
	private static Scene scene;

	/** The render engine. */
	private static RenderingEngine renderEngine;

	/** The camera. */
	private static Camera camera;

	private static Mesh displayMesh;

	private static CombineShader combineShader;

	/**
	 * Starts the engine.
	 */
	public static void start()
	{
		loop();
	}

	/**
	 * Stops the engine. Runs the clean up procedure for the engine.
	 */
	public static void stop()
	{
		cleanUp();
		System.exit(0);
	}

	/**
	 * The main game loop.
	 */
	private static void loop()
	{
		double delta = 0;
		float tTime = 0;
		int frameCount = 0;
		while(!window.isCloseRequested())
		{
			double startTime = System.nanoTime();
			OpenGLManager.clearScreen();

			// TODO: update input engine? maybe
			scene.input(delta);
			if(KeyboardManager.pollKey(GLFW.GLFW_KEY_ESCAPE))
			{
				window.closeWindow();
				stop();
			}

			// update Objects(Scene)
			scene.update(delta);

			// TODO: update Physics engine

			// update Audio engine
			// this.audioEngine.updateListener(camera.getTransform().getTranslation());

			// update rendering engine(Render)

			// Clear all of the framebuffers
			renderEngine.clear();

			scene.render();

			renderEngine.renderLights();

			// Combine All of the buffers

			// Combine will multiply diffuse with the lighting texture(Shadow
			// and light)
			Texture diffuse = renderEngine.getDiffuseBuffer().getTexture();
			Texture lighting = renderEngine.getLightRenderer().getCombinedTexture();

			// Bind the textures
			combineShader.bind();

			lighting.bind(1);
			diffuse.bind(0);

			displayMesh.draw();

			lighting.unbind();
			diffuse.unbind();

			combineShader.unbind();

			// Draw the window
			window.swapBuffers();
			frameCount++;

			delta = (System.nanoTime() - startTime) / 1000000000.0;
			tTime += delta;
			if(tTime >= 1)
			{
				System.out.println(frameCount);
				System.out.println(delta);
				tTime = 0;
				frameCount = 0;
			}
		}
	}

	/**
	 * Inits the engine with a window, opengl, a scene and a camera.
	 *
	 * @param filename
	 *            the filename
	 */
	public static void init(String filename)
	{
		GLFWManager.initGLFW();
		window = Window.createWindow(WIDTH, HEIGHT, "Test 2D Game");
		window.makeWindowCurrent();

		OpenGLManager.initOpenGL(window);
		if(filename == null)
		{
			scene = new Scene();
		}
		else
		{
			scene = SceneImporter.importScene(filename);
		}
		camera = new Camera(1, -1, -1, 1, -10, 10, ((float) WIDTH / (float) HEIGHT));
		// camera.addComponent(new KeyboardInputComponent(new Vector2f(1, 1)));
		scene.addNode(camera);
		renderEngine = new RenderingEngine(camera);

		GLFW.glfwSetKeyCallback(window.getId(), new KeyboardManager());
		GLFW.glfwSetMouseButtonCallback(window.getId(), new MouseButtonManager());
		GLFW.glfwSetCursorPosCallback(window.getId(), new MouseManager());

		displayMesh = new Rectangle(2, 2);
		combineShader = new CombineShader();
	}

	/**
	 * Cleans up. Destroys all objects and pointers
	 */
	private static void cleanUp()
	{
		window.destroy();
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
	public static Window getWindow()
	{
		return window;
	}

	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public static Scene getScene()
	{
		return scene;
	}

	/**
	 * Gets the rendering engine.
	 *
	 * @return the render engine
	 */
	public static RenderingEngine getRenderEngine()
	{
		return renderEngine;
	}

	public static Camera getCamera()
	{
		return camera;
	}

	public static Mesh getDisplayMesh()
	{
		return displayMesh;
	}
}
