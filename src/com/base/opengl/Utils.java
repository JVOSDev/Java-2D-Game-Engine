package com.base.opengl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import com.base.RenderingEngine.mesh.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils
{
	
	/**
	 * Buffers a matrix4f for OpenGL.
	 *
	 * @param value the matrix
	 * @return the float buffer of the matrix
	 */
	public static FloatBuffer bufferMatrix4f(Matrix4f value)
	{
		FloatBuffer buffer = createFloatBuffer(4 * 4);

		value.get(buffer);
		//buffer.flip();

		return buffer;
	}
	
	/**
	 * Buffers a matrix3f for OpenGL.
	 *
	 * @param value the matrix
	 * @return the float buffer of the matrix
	 */
	public static FloatBuffer bufferMatrix3f(Matrix3f value)
	{
		FloatBuffer buffer = createFloatBuffer(3 * 3);

		value.get(buffer);
		//buffer.flip();

		return buffer;
	}
	
	/**
	 * Buffers vertices for OpenGL.
	 *
	 * @param vertices the vertices
	 * @return the float buffer representation of the vertices
	 */
	public static FloatBuffer bufferVertices(Vertex[] vertices)
	{
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);

		for(Vertex vertice : vertices)
		{
			buffer.put(vertice.getPos().x);
			buffer.put(vertice.getPos().y);
			buffer.put(vertice.getTexCoord().x);
			buffer.put(vertice.getTexCoord().y);
		}

		buffer.flip();

		return buffer;
	}

	/**
	 * Buffers integers for OpenGL
	 *
	 * @param values the integer values
	 * @return the buffered values
	 */
	public static IntBuffer createFlippedBuffer(int... values)
	{
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();

		return buffer;
	}

	/**
	 * Creates a float buffer.
	 *
	 * @param size the size of the float buffer to be created
	 * @return the created float buffer
	 */
	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}

	/**
	 * Creates the int buffer.
	 *
	 * @param size the size
	 * @return the int buffer
	 */
	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}

	/**
	 * Loads a text file.
	 *
	 * @param filename the filename of the text file
	 * @return the string representation of the text
	 */
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

	/**
	 * Removes empty strings in a string array.
	 *
	 * @param data the string array
	 * @return the cleaned string array
	 */
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

	/**
	 * Converts an Integer array to an int array
	 *
	 * @param data the Integer data
	 * @return the int array
	 */
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
