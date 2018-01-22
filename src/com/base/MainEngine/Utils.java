package com.base.MainEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import com.base.RenderingEngine.mesh.Vertex;

public class Utils
{
	public static FloatBuffer bufferVertices(Vertex[] vertices)
	{
		FloatBuffer buffer = createFloatBuffer(vertices.length * 5);

		for(Vertex vertice : vertices)
		{
			buffer.put(vertice.getPos().getX());
			buffer.put(vertice.getPos().getY());
			buffer.put(0);		//Opengl only likes 3D data, 0 for the z component
			buffer.put(vertice.getTexCoord().getX());
			buffer.put(vertice.getTexCoord().getY());
		}

		buffer.flip();

		return buffer;
	}

	public static IntBuffer createFlippedBuffer(int... values)
	{
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();

		return buffer;
	}

	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}

	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}

	public static String loadTextFile(String filename)
	{
		StringBuilder text = new StringBuilder();
		BufferedReader textReader = null;

		try
		{
			textReader = new BufferedReader(new FileReader(filename));
			String line;

			while((line = textReader.readLine()) != null)
			{
				text.append(line).append("\n");
			}

			textReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		return text.toString();
	}

	public static String[] removeEmptyStrings(String[] data)
	{
		ArrayList<String> result = new ArrayList<>();

		for(int i = 0; i < data.length; i++)
		{
			if(!data[i].equals(""))
			{
				result.add(data[i]);
			}
		}

		String[] res = new String[result.size()];
		result.toArray(res);

		return res;
	}

	public static int[] toIntArray(Integer[] data)
	{
		int[] result = new int[data.length];

		for(int i = 0; i < data.length; i++)
		{
			result[i] = data[i].intValue();
		}

		return result;
	}

}
