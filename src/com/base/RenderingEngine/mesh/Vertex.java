/*
 *
 */
package com.base.RenderingEngine.mesh;

import java.io.Serializable;

import org.joml.Vector2f;

// TODO: Auto-generated Javadoc
/**
 * The Class Vertex.
 */
public class Vertex implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9158048780668748829L;

	/** The Constant SIZE. */
	public static final int SIZE = 4;

	/** The pos. */
	private Vector2f pos;

	/** The texture coordinate. */
	private Vector2f texCoord;

	/**
	 * Instantiates a new vertex.
	 *
	 * @param pos
	 *            the position of the vertex
	 */
	public Vertex(Vector2f pos)
	{
		this(pos, new Vector2f((float) Math.random(), (float) Math.random()));
	}

	/**
	 * Instantiates a new vertex.
	 *
	 * @param pos
	 *            the position of the vertex
	 * @param texCoord
	 *            the texture coordinate
	 */
	public Vertex(Vector2f pos, Vector2f texCoord)
	{
		this.pos = pos;
		this.texCoord = texCoord;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Vector2f getPos()
	{
		return this.pos;
	}

	/**
	 * Gets the texture coordinate.
	 *
	 * @return the texture coordinate
	 */
	public Vector2f getTexCoord()
	{
		return this.texCoord;
	}

}
