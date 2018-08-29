package com.base.MainEngine.scene;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.base.MainEngine.InputManager;
import com.base.MainEngine.MainEngine;

// TODO: Auto-generated Javadoc
/**
 * The Class Camera.
 */
public class Camera extends Node
{

	/** The orthogonal projection matrix. */
	private Matrix4f orthoProjection;
	private Vector2f moveDirection;

	/**
	 * Instantiates a new camera with aspect ratio correction.
	 *
	 * @param left
	 *            the distance from the center to the left frustum edge
	 * @param right
	 *            the distance from the center to the right frustum edge
	 * @param bottom
	 *            the distance from the center to the bottom frustum edge
	 * @param top
	 *            the distance from the center to the top frustum edge
	 * @param near
	 *            near clipping plane distance
	 * @param far
	 *            far clipping plane distance
	 * @param aspectRatio
	 *            the aspect ratio of the window
	 */
	public Camera(float left, float right, float top, float bottom, float near, float far, float aspectRatio)
	{
		this.orthoProjection = new Matrix4f().ortho(left * (aspectRatio), right * (aspectRatio), bottom, top, near, far);
		this.moveDirection = new Vector2f(0, 0);
	}

	/**
	 * Instantiates a new camera without aspect ratio correction. Should be used
	 * on square windows
	 *
	 * @param left
	 *            the distance from the center to the left frustum edge
	 * @param right
	 *            the distance from the center to the right frustum edge
	 * @param bottom
	 *            the distance from the center to the bottom frustum edge
	 * @param top
	 *            the distance from the center to the top frustum edge
	 * @param near
	 *            near clipping plane distance
	 * @param far
	 *            far clipping plane distance
	 */
	public Camera(float left, float right, float top, float bottom, float near, float far)
	{
		this.orthoProjection = new Matrix4f().ortho(left, right, bottom, top, near, far);
		this.moveDirection = new Vector2f(0, 0);
	}

	/**
	 * Gets the projection matrix of the camera.
	 *
	 * @return the projection matrix
	 */
	public Matrix4f getProjectionM()
	{
		Matrix4f c = this.orthoProjection;
		return c.setTranslation(this.transform.getTranslation().x, this.transform.getTranslation().y, 0);
	}

	/**
	 * Update projection matrix with aspect ratio correction.
	 *
	 * @param left
	 *            the distance from the center to the left frustum edge
	 * @param right
	 *            the distance from the center to the right frustum edge
	 * @param bottom
	 *            the distance from the center to the bottom frustum edge
	 * @param top
	 *            the distance from the center to the top frustum edge
	 * @param near
	 *            near clipping plane distance
	 * @param far
	 *            far clipping plane distance
	 * @param aspectRatio
	 *            the aspect ratio of the window
	 */
	public void updateProjectionM(float left, float right, float top, float bottom, float near, float far, float aspectRatio)
	{
		this.orthoProjection = new Matrix4f().ortho(left * (aspectRatio), right * (aspectRatio), bottom, top, near, far);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.Node#input(float,
	 * com.base.MainEngine.MainEngine)
	 */
	@Override
	public void input(float delta, MainEngine engine)
	{
		if(InputManager.pollKey(GLFW.GLFW_KEY_A))
		{
			this.moveDirection.add(-1 * delta, 0);
		}
		if(InputManager.pollKey(GLFW.GLFW_KEY_D))
		{
			this.moveDirection.add(1 * delta, 0);
		}
		if(InputManager.pollKey(GLFW.GLFW_KEY_W))
		{
			this.moveDirection.add(0, 1 * delta);
		}
		if(InputManager.pollKey(GLFW.GLFW_KEY_S))
		{
			this.moveDirection.add(0, -1 * delta);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.base.MainEngine.scene.Node#render(com.base.MainEngine.MainEngine)
	 */
	@Override
	public void render(MainEngine engine)
	{
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.Node#update(float,
	 * com.base.MainEngine.MainEngine)
	 */
	@Override
	public void update(float delta, MainEngine engine)
	{
		this.transform.translate(this.moveDirection);
		this.moveDirection.set(0);
	}

}
