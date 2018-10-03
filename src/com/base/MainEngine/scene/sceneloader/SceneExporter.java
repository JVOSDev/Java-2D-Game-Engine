/*
 *
 */
package com.base.MainEngine.scene.sceneloader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.base.MainEngine.scene.Scene;

// TODO: Auto-generated Javadoc
/**
 * The Class SceneExporter.
 */
public class SceneExporter
{

	/**
	 * Export scene.
	 *
	 * @param scene
	 *            the scene to be exported
	 * @param filename
	 *            the filename to export the scene to
	 */
	public static void exportScene(Scene scene, String filename)
	{
		try
		{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
			os.writeObject(scene);
			os.close();
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
	}
}
