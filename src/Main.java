import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Entity;
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
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		MainEngine mainEngine = new MainEngine();
		mainEngine.getScene().addNode(new Entity(new Circle(1,1000)));
		mainEngine.start();
	}
}
