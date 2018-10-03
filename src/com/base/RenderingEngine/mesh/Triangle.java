/*
 *
 */
package com.base.RenderingEngine.mesh;

import org.joml.Vector2f;

// TODO: Auto-generated Javadoc
/**
 * The Class Triangle.
 */
public class Triangle extends Mesh
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4506833749579072170L;

	/**
	 * Instantiates a new triangle.
	 */
	public Triangle()
	{
		this.init();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.RenderingEngine.mesh.Mesh#initVertices()
	 */
	@Override
	protected void initVertices()
	{
		this.bufferVertices(new Vertex[]{new Vertex(new Vector2f(-0.5f, -0.5f)), new Vertex(new Vector2f(0.5f, -0.5f)), new Vertex(new Vector2f(0, 0.5f))}, new int[]{0, 1, 2});

	}
}
