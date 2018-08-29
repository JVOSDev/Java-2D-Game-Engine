package com.base.MainEngine.scene;

import java.util.ArrayList;

import com.base.MainEngine.MainEngine;

// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 */
public abstract class Node
{

	/** The children. */
	protected ArrayList<Node> children;

	/** The transform. */
	protected Transform transform;

	/** The parent. */
	protected Node parent = null;

	/**
	 * Instantiates a new node.
	 */
	public Node()
	{
		this.children = new ArrayList<>();
		this.transform = new Transform();
	}

	/**
	 * Adds a child node.
	 *
	 * @param node
	 *            the node
	 */
	public void addChild(Node node)
	{
		this.children.add(node);
		node.setParent(this);
	}

	/**
	 * Gets the children nodes.
	 *
	 * @return the children
	 */
	public ArrayList<Node> getChildren()
	{
		return this.children;
	}

	/**
	 * Gets the transform. The transform is combined with the parent transform
	 *
	 * @return the transform
	 */
	public Transform getTransform()
	{
		if(this.parent != null)
		{
			Transform combine = new Transform();

			combine.setRotation(this.transform.getRotation() + this.parent.getTransform().getRotation());
			combine.setTranslation(this.transform.getTranslation().add(this.parent.getTransform().getTranslation()));
			combine.setScale(this.transform.getScale().mul(this.parent.getTransform().getScale()));

			return combine;
		}
		else
		{
			return this.transform;
		}
	}

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public Node getParent()
	{
		return this.parent;
	}

	/**
	 * Get input.
	 *
	 * @param delta
	 *            the delta time between frames
	 * @param engine
	 *            the main engine
	 */
	public abstract void input(float delta, MainEngine engine);

	/**
	 * Inputs all children.
	 *
	 * @param delta
	 *            the delta time between frames
	 * @param engine
	 *            the main engine
	 */
	public void inputAll(float delta, MainEngine engine)
	{
		this.input(delta, engine);
		for(Node node : this.children)
		{
			node.inputAll(delta, engine);
		}
	}

	/**
	 * Render.
	 *
	 * @param engine
	 *            the main engine
	 */
	public abstract void render(MainEngine engine);

	/**
	 * Renders all the children nodes.
	 *
	 * @param engine
	 *            the main engine
	 */
	public void renderAll(MainEngine engine)
	{
		this.render(engine);
		for(Node node : this.children)
		{
			node.renderAll(engine);
		}
	}

	/**
	 * Sets the parent of the node.
	 *
	 * @param parent
	 *            the new parent
	 */
	public void setParent(Node parent)
	{
		this.parent = parent;
	}

	/**
	 * Updates the node.
	 *
	 * @param delta
	 *            the delta time between frames
	 * @param engine
	 *            the main engine
	 */
	public abstract void update(float delta, MainEngine engine);

	/**
	 * Updates all the child nodes.
	 *
	 * @param delta
	 *            the delta time between frames
	 * @param engine
	 *            the main engine
	 */
	public void updateAll(float delta, MainEngine engine)
	{
		this.update(delta, engine);
		for(Node node : this.children)
		{
			node.updateAll(delta, engine);
		}
	}
}
