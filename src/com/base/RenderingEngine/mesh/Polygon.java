package com.base.RenderingEngine.mesh;

// TODO: Auto-generated Javadoc
/**
 * The Class Polygon.
 */
public class Polygon extends Mesh
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1920519682535643817L;

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
		this.init();
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
