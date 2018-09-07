/*
 * 
 */
import org.joml.Vector2f;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Entity;
import com.base.MainEngine.scene.component.KeyboardInputComponent;
import com.base.MainEngine.scene.sceneloader.SceneExporter;
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
		MainEngine mainEngine = new MainEngine();
		mainEngine.getScene().addNode(new Entity(new Circle()).addComponent(new KeyboardInputComponent(new Vector2f(0,1))));
		SceneExporter.exportScene(mainEngine.getScene(), "testScene.sc");
		mainEngine.start();	
	}
}
