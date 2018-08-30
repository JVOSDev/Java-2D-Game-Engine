
import com.base.MainEngine.MainEngine;

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
		MainEngine mainEngine = new MainEngine("testScene.sc");
		//mainEngine.getScene().addNode(new Entity(new Circle()));
		//SceneExporter.exportScene(mainEngine.getScene(), "testScene.sc");
		mainEngine.start();	
	}
}
