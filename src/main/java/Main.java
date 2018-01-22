import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Entity;
import com.base.RenderingEngine.mesh.Triangle;

public class Main
{
	public static void main(String[] args)
	{
		MainEngine mainEngine = new MainEngine();
		mainEngine.getScene().addNode(new Entity(new Triangle()));
		mainEngine.start();
	}
}
