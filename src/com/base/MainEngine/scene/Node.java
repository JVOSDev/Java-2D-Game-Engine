/*
 *
 */
package com.base.MainEngine.scene;

import java.io.Serializable;
import java.util.ArrayList;

import com.base.MainEngine.scene.component.NodeComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 */
public abstract class Node implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 123785902093600034L;

	/** The children. */
	protected ArrayList<Node> children;

	/** The components. */
	protected ArrayList<NodeComponent> components;

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
		this.components = new ArrayList<>();
		this.transform = new Transform();
	}

	/**
	 * Adds a child node.
	 *
	 * @param node
	 *            the node
	 * @return this node
	 */
	public Node addChild(Node node)
	{
		this.children.add(node);
		node.setParent(this);
		return this;
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
	 * Adds the component.
	 *
	 * @param NodeComponent
	 *            the component
	 * @return this node
	 */
	public Node addComponent(NodeComponent comp)
	{
		this.components.add(comp);
		return this;
	}

	/**
	 * Gets the components.
	 *
	 * @return the components
	 */
	public ArrayList<NodeComponent> getComponents()
	{
		return this.components;
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
	public abstract void input(double delta);

	/**
	 * Inputs all children.
	 *
	 * @param delta
	 *            the delta time between frames
	 * @param engine
	 *            the main engine
	 */
	public void inputAll(double delta)
	{

		this.input(delta);

		for(NodeComponent comp : this.components)
		{
			comp.input(this, delta);
		}

		for(Node node : this.children)
		{
			node.inputAll(delta);
		}
	}

	/**
	 * Render.
	 *
	 * @param engine
	 *            the main engine
	 */
	public abstract void render();

	/**
	 * Renders all the children nodes.
	 *
	 * @param engine
	 *            the main engine
	 */
	public void renderAll()
	{
		this.render();

		for(NodeComponent comp : this.components)
		{
			comp.render(this);
		}

		for(Node node : this.children)
		{
			node.renderAll();
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
	public abstract void update(double delta);

	/**
	 * Updates all the child nodes.
	 *
	 * @param delta
	 *            the delta time between frames
	 * @param engine
	 *            the main engine
	 */
	public void updateAll(double delta)
	{
		this.update(delta);

		for(NodeComponent comp : this.components)
		{
			comp.update(this, delta);
		}

		for(Node node : this.children)
		{
			node.updateAll(delta);
		}
	}

	/**
	 * Reinitializes the node after loading it from a saved scene
	 */
	void reInit()
	{
		for(Node node : this.children)
		{
			node.reInit();
		}
	}
}
