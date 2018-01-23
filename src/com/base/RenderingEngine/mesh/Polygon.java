package com.base.RenderingEngine.mesh;

public class Polygon extends Mesh
{
	public Polygon(Vertex[] vertices, int[] indices)
	{
		init();
		this.bufferVertices(vertices, indices);
	}

	@Override
	protected void initVertices() 
	{
		// TODO Auto-generated method stub
		
	}
}
