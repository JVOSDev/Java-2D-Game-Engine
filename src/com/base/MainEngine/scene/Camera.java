package com.base.MainEngine.scene;

import org.joml.Matrix4f;

import com.base.MainEngine.MainEngine;

public class Camera extends Node
{
	private Matrix4f orthoProjection;
	
	public Camera(float top, float bottom, float left, float right, float near, float far, float aspectRatio)
	{
		orthoProjection = new Matrix4f().ortho(left*(aspectRatio), right*(aspectRatio), bottom, top, near, far);
	}
	
	public Camera(float top, float bottom, float left, float right, float near, float far)
	{
		orthoProjection = new Matrix4f().ortho(left, right, bottom, top, near, far);
	}
	
	public Matrix4f getProjectionM()
	{
		Matrix4f c = orthoProjection;
		return c.setTranslation(this.transform.getTranslation().x, this.transform.getTranslation().y, 0);
	}
	
	public void updateProjectionM(float top, float bottom, float left, float right, float near, float far, float aspectRatio)
	{
		orthoProjection = new Matrix4f().ortho(left*(aspectRatio), right*(aspectRatio), bottom, top, near, far);
	}
	
	@Override
	public void input(float delta, MainEngine engine) 
	{
	}

	@Override
	public void render(MainEngine engine) 
	{
	}
	
	float v = 0;
	
	@Override
	public void update(float delta, MainEngine engine) 
	{
		
	}

}
