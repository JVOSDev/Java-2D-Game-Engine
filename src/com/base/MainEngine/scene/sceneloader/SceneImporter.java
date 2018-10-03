/*
 *
 */
package com.base.MainEngine.scene.sceneloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.base.MainEngine.scene.Scene;

// TODO: Auto-generated Javadoc
/**
 * The Class SceneImporter.
 */
public class SceneImporter
{

	/**
	 * Imports a scene from a file. Automatically initializes everything saved.
	 *
	 * @param filename
	 *            the filename
	 * @return the loaded scene
	 */
	public static Scene importScene(String filename)
	{
		try
		{
			ObjectInputStream os = new ObjectInputStream(new FileInputStream(filename));
			Scene s = (Scene) os.readObject();
			s.reInit();
			os.close();
			return s;
		}
		catch(FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
