package com.base.RenderingEngine.mesh;

import com.base.math.Vector2f;

public class Vertex
{
	public static final int SIZE = 4;

	private Vector2f pos;
	private Vector2f texCoord;

	public Vertex(Vector2f pos)
	{
		this(pos, new Vector2f((float) Math.random(), (float) Math.random()));
	}

	public Vertex(Vector2f pos, Vector2f texCoord)
	{
		this.pos = pos;
		this.texCoord = texCoord;
	}

	public Vector2f getPos()
	{
		return this.pos;
	}

	public Vector2f getTexCoord()
	{
		return this.texCoord;
	}

}
