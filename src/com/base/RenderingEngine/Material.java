package com.base.RenderingEngine;

import org.joml.Vector3f;

// TODO: Auto-generated Javadoc
/**
 * The Class Material.
 */
public class Material
{
	
	/** The base color. */
	private Vector3f baseColor;
	
	/** The diffuse texture. */
	private Texture diffTexture;

	/**
	 * Instantiates a new material with a white base color.
	 *
	 * @param texture the diffuse texture
	 */
	public Material(Texture texture)
	{
		this(new Vector3f(1, 1, 1), texture);
	}

	/**
	 * Instantiates a new material.
	 *
	 * @param baseColor the base color
	 * @param texture the diffuse texture
	 */
	public Material(Vector3f baseColor, Texture texture)
	{
		this.baseColor = baseColor;
		this.diffTexture = texture;
	}

	/**
	 * Gets the base color.
	 *
	 * @return the base color
	 */
	public Vector3f getBaseColor()
	{
		return this.baseColor;
	}

	/**
	 * Gets the diffuse texture.
	 *
	 * @return the diffuse texture
	 */
	public Texture getTexture()
	{
		return this.diffTexture;
	}

	/**
	 * Sets the base color.
	 *
	 * @param baseColor the new base color
	 */
	public void setBaseColor(Vector3f baseColor)
	{
		this.baseColor = baseColor;
	}

	/**
	 * Sets the diffuse texture.
	 *
	 * @param texture the new diffuse texture
	 */
	public void setTexture(Texture texture)
	{
		this.diffTexture = texture;
	}
}
