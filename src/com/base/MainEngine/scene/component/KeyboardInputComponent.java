/*
 *
 */
package com.base.MainEngine.scene.component;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.base.MainEngine.KeyboardManager;
import com.base.MainEngine.scene.Node;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyboardInputComponent.
 */
public class KeyboardInputComponent extends NodeComponent
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6421396509020290970L;

	/** The direction. */
	private Vector2f direction;

	/** The rotation. */
	private boolean rotation;

	/**
	 * Instantiates a new keyboard input component without the rotation enabled
	 *
	 * @param direction
	 *            the direction to check keys in
	 */
	public KeyboardInputComponent(Vector2f direction)
	{
		this.direction = direction;
		this.rotation = false;
	}

	/**
	 * Instantiates a new keyboard input component with the ability to use arrow
	 * keys for roatation
	 *
	 * @param direction
	 *            the direction to check keys in
	 * @param rotation
	 *            enable rotation
	 */
	public KeyboardInputComponent(Vector2f direction, boolean rotation)
	{
		this.direction = direction;
		this.rotation = rotation;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.component.NodeComponent#input(com.base.
	 * MainEngine.scene.Node, float, com.base.MainEngine.MainEngine)
	 */
	@Override
	public void input(Node node, double delta)
	{
		if(KeyboardManager.pollKey(GLFW.GLFW_KEY_D))
		{
			node.getTransform().translate(new Vector2f((float) (1f * delta), 0).mul(this.direction));
		}
		if(KeyboardManager.pollKey(GLFW.GLFW_KEY_A))
		{
			node.getTransform().translate(new Vector2f((float) (-1f * delta), 0).mul(this.direction));
		}
		if(KeyboardManager.pollKey(GLFW.GLFW_KEY_W))
		{
			node.getTransform().translate(new Vector2f(0, (float) (-1f * delta)).mul(this.direction));
		}
		if(KeyboardManager.pollKey(GLFW.GLFW_KEY_S))
		{
			node.getTransform().translate(new Vector2f(0, (float) (1f * delta)).mul(this.direction));
		}

		if(this.rotation)
		{
			if(KeyboardManager.pollKey(GLFW.GLFW_KEY_LEFT))
			{
				node.getTransform().setRotation(node.getTransform().getRotation() + (float) delta);
			}
			if(KeyboardManager.pollKey(GLFW.GLFW_KEY_RIGHT))
			{
				node.getTransform().setRotation(node.getTransform().getRotation() - (float) delta);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.component.NodeComponent#update(com.base.
	 * MainEngine.scene.Node, float, com.base.MainEngine.MainEngine)
	 */
	@Override
	public void update(Node node, double delta)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.component.NodeComponent#render(com.base.
	 * MainEngine.scene.Node, com.base.MainEngine.MainEngine)
	 */
	@Override
	public void render(Node node)
	{
		// TODO Auto-generated method stub

	}

}
