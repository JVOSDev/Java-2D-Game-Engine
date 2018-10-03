
/*
 *
 */
import org.joml.Vector2f;
import org.joml.Vector3f;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Entity;
import com.base.MainEngine.scene.component.KeyboardInputComponent;
import com.base.MainEngine.scene.sceneloader.SceneExporter;
import com.base.RenderingEngine.lights.Attenuation;
import com.base.RenderingEngine.lights.PointLight;
import com.base.RenderingEngine.mesh.Circle;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main
{

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */

	public static void main(String[] args)
	{
		MainEngine.init(null);
		Entity e = new Entity(new Circle(.1f, 50));
		e.addComponent(new KeyboardInputComponent(new Vector2f(-1, 1)));
		MainEngine.getScene().addNode(e);
		MainEngine.getScene().addNode(new PointLight(new Vector2f(.2f, 0), new Vector3f(1, 0, 0), new Attenuation(40, 1, 1)));
		MainEngine.getScene().addNode(new PointLight(new Vector2f(-.2f, 0), new Vector3f(0, 1, 0), new Attenuation(40, 20, 1)));
		MainEngine.getScene().addNode(new PointLight(new Vector2f(0, -.2f), new Vector3f(0, 0, 1), new Attenuation(40, 10, 1)));
		MainEngine.getScene().addNode(new PointLight(new Vector2f(0, .2f), new Vector3f(1, 0, 1), new Attenuation(10, 30, 1)));
		SceneExporter.exportScene(MainEngine.getScene(), "testScene.sc");
		MainEngine.start();
	}
}
