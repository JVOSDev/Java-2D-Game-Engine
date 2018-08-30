package com.base.RenderingEngine.mesh;

import org.joml.Vector2f;

// TODO: Auto-generated Javadoc
/**
 * The Class Rectangle.
 */
public class Rectangle extends Mesh
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4887460056711971205L;

	/** The x length. */
	private float xLength;

	/** The y length. */
	private float yLength;

	/**
	 * Instantiates a new rectangle.
	 *
	 * @param xLength
	 *            the x length
	 * @param yLength
	 *            the y length
	 */
	public Rectangle(float xLength, float yLength)
	{
		this.xLength = xLength;
		this.yLength = yLength;
		this.init();
	}

	/**
	 * Instantiates a new rectangle.
	 */
	public Rectangle()
	{
		this(1, 1);
	}

	/**
	 * draw order and vertex data from:
	 * https://stackoverflow.com/questions/11806690/android-opengl-es-2-drawing-
	 * squares
	 *
	 * static float squareCoords[] = { -0.5f, 0.5f, 0.0f, // top left -0.5f,
	 * -0.5f, 0.0f, // bottom left 0.5f, -0.5f, 0.0f, // bottom right 0.5f,
	 * 0.5f, 0.0f }; // top right
	 *
	 * private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw
	 * vertices
	 */

	@Override
	protected void initVertices()
	{
		float halfXLength = this.xLength / 2f;
		float halfYLength = this.yLength / 2f;

		this.bufferVertices(new Vertex[]{new Vertex(new Vector2f(-halfXLength, halfYLength), new Vector2f(0, this.yLength)), new Vertex(new Vector2f(-halfXLength, -halfYLength), new Vector2f(0, 0)),
				new Vertex(new Vector2f(halfXLength, -halfYLength), new Vector2f(this.xLength, 0)), new Vertex(new Vector2f(halfXLength, halfYLength), new Vector2f(this.xLength, this.yLength))}, new int[]{0, 1, 2, 0, 2, 3});
	}

}
