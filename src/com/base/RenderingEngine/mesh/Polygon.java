package com.base.RenderingEngine.mesh;

// TODO: Auto-generated Javadoc
/**
 * The Class Polygon.
 */
public class Polygon extends Mesh
{

	/**
	 * Instantiates a new polygon.
	 *
	 * @param vertices
	 *            the vertices
	 * @param indices
	 *            the indices
	 */
	public Polygon(Vertex[] vertices, int[] indices)
	{
		init();
		this.bufferVertices(vertices, indices);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.base.RenderingEngine.mesh.Mesh#initVertices()
	 */
	@Override
	protected void initVertices()
	{
		// TODO Auto-generated method stub

	}
}
