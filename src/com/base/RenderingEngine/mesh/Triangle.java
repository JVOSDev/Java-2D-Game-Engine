package com.base.RenderingEngine.mesh;

import org.joml.Vector2f;

public class Triangle extends Mesh
{
	public Triangle()
	{
		this.init();
	}

	@Override
	protected void initVertices()
	{
		this.bufferVertices(new Vertex[]{new Vertex(new Vector2f(-0.5f,-0.5f)),
										 new Vertex(new Vector2f(0.5f,-0.5f)),
										 new Vertex(new Vector2f(0,0.5f))}, new int[]{0,1,2});
		
	}
}
