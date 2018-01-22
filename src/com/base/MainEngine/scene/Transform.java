package com.base.MainEngine.scene;

import org.joml.Matrix3f;
import org.joml.Vector2f;

public class Transform 
{
	private Vector2f translation;
	private float rotation;
	private Vector2f scale;
	
	public Transform(Vector2f translation, float rotation, Vector2f scale) 
	{
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Transform(Vector2f translation) 
	{
		this(translation, 0, new Vector2f(1,1));
	}
	
	public Transform()
	{
		this(new Vector2f(0,0));
	}

	public Vector2f getTranslation() {
		return translation;
	}

	public float getRotation() {
		return rotation;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setTranslation(Vector2f translation) {
		this.translation = translation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	/**
	 * https://gamedev.stackexchange.com/questions/25411/basics-of-drawing-in-2d-with-opengl-3-shaders
	 *  
	 *   Position             Rotation                 Scale
	 *  |1, 0, tx|   |cos(theta), -sin(theta), 0|   |sx, 0,  0|
	 *	|0, 1, ty| * |sin(theta), cos(theta),  0| * |0,  sy, 0|
	 *	|0, 0, 1 |   |0,          0,           1|   |0,  0,  1|
	 *
	 * @return
	 */
	
	public Matrix3f getTransformMatrix()
	{
		Matrix3f translationM = new Matrix3f().identity();
		Matrix3f rotationM = new Matrix3f().identity();
		Matrix3f scaleM = new Matrix3f().identity();
		
		translationM.m20 = translation.x;
		translationM.m21 = translation.y;
		
		rotationM.rotateZ(rotation);
		
		scaleM.scale(scale.x, scale.y, 1);
		
		return translationM.mul(rotationM.mul(scaleM));
	}
}
