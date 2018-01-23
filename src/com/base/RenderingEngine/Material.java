package com.base.RenderingEngine;

import org.joml.Vector3f;

public class Material
{
	private Vector3f baseColor;
	private Texture diffTexture;

	public Material(Texture texture)
	{
		this(new Vector3f(1, 1, 1), texture);
	}

	public Material(Vector3f baseColor, Texture texture)
	{
		this.baseColor = baseColor;
		this.diffTexture = texture;
	}

	public Vector3f getBaseColor()
	{
		return this.baseColor;
	}

	public Texture getTexture()
	{
		return this.diffTexture;
	}

	public void setBaseColor(Vector3f baseColor)
	{
		this.baseColor = baseColor;
	}

	public void setTexture(Texture texture)
	{
		this.diffTexture = texture;
	}
}
