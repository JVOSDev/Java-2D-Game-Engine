package com.base.MainEngine.scene;

import org.joml.Matrix3x2f;
import org.joml.Vector2f;

// TODO: Auto-generated Javadoc
/**
 * The Class Transform.
 */
public class Transform
{

	/** The translation. */
	private Vector2f translation;

	/** The rotation. */
	private float rotation;

	/** The scale. */
	private Vector2f scale;

	/**
	 * Instantiates a new transform.
	 *
	 * @param translation
	 *            the initial translation
	 * @param rotation
	 *            the initial rotation
	 * @param scale
	 *            the initial scale
	 */
	public Transform(Vector2f translation, float rotation, Vector2f scale)
	{
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
	}

	/**
	 * Instantiates a new transform with no rotation and a scale of 1.
	 *
	 * @param translation
	 *            the initial translation
	 */
	public Transform(Vector2f translation)
	{
		this(translation, 0, new Vector2f(1, 1));
	}

	/**
	 * Instantiates a new transform with the center of 0, no rotation and a
	 * scale of 1.
	 */
	public Transform()
	{
		this(new Vector2f(0, 0));
	}

	/**
	 * Gets the translation.
	 *
	 * @return the translation
	 */
	public Vector2f getTranslation()
	{
		return this.translation;
	}

	/**
	 * Gets the rotation.
	 *
	 * @return the rotation
	 */
	public float getRotation()
	{
		return this.rotation;
	}

	/**
	 * Gets the scale.
	 *
	 * @return the scale
	 */
	public Vector2f getScale()
	{
		return this.scale;
	}

	/**
	 * Sets the translation.
	 *
	 * @param translation
	 *            the new translation
	 */
	public void setTranslation(Vector2f translation)
	{
		this.translation = translation;
	}

	/**
	 * Sets the rotation.
	 *
	 * @param rotation
	 *            the new rotation
	 */
	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}

	/**
	 * Sets the scale.
	 *
	 * @param scale
	 *            the new scale
	 */
	public void setScale(Vector2f scale)
	{
		this.scale = scale;
	}

	/**
	 * https://gamedev.stackexchange.com/questions/25411/basics-of-drawing-in-2d
	 * -with-opengl-3-shaders
	 *
	 * Position Rotation Scale |1, 0, tx| |cos(theta), -sin(theta), 0| |sx, 0,
	 * 0| |0, 1, ty| * |sin(theta), cos(theta), 0| * |0, sy, 0| |0, 0, 1 | |0,
	 * 0, 1| |0, 0, 1|
	 *
	 * @return the entire transform matrix
	 */

	public Matrix3x2f getTransformMatrix()
	{
		return new Matrix3x2f().translate(this.translation).rotate(this.rotation).scale(this.scale.x, this.scale.y);
	}

	/**
	 * Translates by a vector.
	 *
	 * @param translation
	 *            the translation
	 */
	public void translate(Vector2f translation)
	{
		this.translation = this.translation.add(translation);
	}
}
