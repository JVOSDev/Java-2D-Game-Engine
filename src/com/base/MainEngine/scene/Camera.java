package com.base.MainEngine.scene;

import org.joml.Matrix4f;

import com.base.MainEngine.MainEngine;

// TODO: Auto-generated Javadoc
/**
 * The Class Camera.
 */
public class Camera extends Node
{
	
	/** The orthogonal projection matrix. */
	private Matrix4f orthoProjection;
	
	/**
	 * Instantiates a new camera with aspect ratio correction.
	 *
     * @param left the distance from the center to the left frustum edge
     * @param right the distance from the center to the right frustum edge
     * @param bottom the distance from the center to the bottom frustum edge
     * @param top the distance from the center to the top frustum edge
     * @param near near clipping plane distance
     * @param far far clipping plane distance
	 * @param aspectRatio the aspect ratio of the window
	 */
	public Camera(float left, float right, float top, float bottom, float near, float far, float aspectRatio)
	{
		orthoProjection = new Matrix4f().ortho(left*(aspectRatio), right*(aspectRatio), bottom, top, near, far);
	}
	
	/**
	 * Instantiates a new camera without aspect ratio correction. Should be used on square windows
	 *
     * @param left the distance from the center to the left frustum edge
     * @param right the distance from the center to the right frustum edge
     * @param bottom the distance from the center to the bottom frustum edge
     * @param top the distance from the center to the top frustum edge
     * @param near near clipping plane distance
     * @param far far clipping plane distance
	 */
	public Camera(float left, float right, float top, float bottom, float near, float far)
	{
		orthoProjection = new Matrix4f().ortho(left, right, bottom, top, near, far);
	}
	
	/**
	 * Gets the projection matrix of the camera.
	 *
	 * @return the projection matrix
	 */
	public Matrix4f getProjectionM()
	{
		Matrix4f c = orthoProjection;
		return c.setTranslation(this.transform.getTranslation().x, this.transform.getTranslation().y, 0);
	}
	
	/**
	 * Update projection matrix with aspect ratio correction.
	 *
     * @param left the distance from the center to the left frustum edge
     * @param right the distance from the center to the right frustum edge
     * @param bottom the distance from the center to the bottom frustum edge
     * @param top the distance from the center to the top frustum edge
     * @param near near clipping plane distance
     * @param far far clipping plane distance
	 * @param aspectRatio the aspect ratio of the window
	 */
	public void updateProjectionM(float left, float right, float top, float bottom, float near, float far, float aspectRatio)
	{
		orthoProjection = new Matrix4f().ortho(left*(aspectRatio), right*(aspectRatio), bottom, top, near, far);
	}
	
	/* (non-Javadoc)
	 * @see com.base.MainEngine.scene.Node#input(float, com.base.MainEngine.MainEngine)
	 */
	@Override
	public void input(float delta, MainEngine engine) 
	{
	}

	/* (non-Javadoc)
	 * @see com.base.MainEngine.scene.Node#render(com.base.MainEngine.MainEngine)
	 */
	@Override
	public void render(MainEngine engine) 
	{
	}
	
	/* (non-Javadoc)
	 * @see com.base.MainEngine.scene.Node#update(float, com.base.MainEngine.MainEngine)
	 */
	@Override
	public void update(float delta, MainEngine engine) 
	{
		
	}

}
