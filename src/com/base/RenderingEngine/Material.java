package com.base.RenderingEngine;

import org.joml.Vector3f;

public class Material
{
	private Vector3f baseColor;
	private Texture texture;

	public Material(Texture texture)
	{
		this(new Vector3f(1, 1, 1), texture);
	}

	public Material(Vector3f baseColor, Texture texture)
	{
		this.baseColor = baseColor;
		this.texture = texture;
	}

	public Vector3f getBaseColor()
	{
		return this.baseColor;
	}

	public Texture getTexture()
	{
		return this.texture;
	}

	public void setBaseColor(Vector3f baseColor)
	{
		this.baseColor = baseColor;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
}
