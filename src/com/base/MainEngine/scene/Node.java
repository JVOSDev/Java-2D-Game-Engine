/*
 * 
 */
package com.base.MainEngine.scene;

import java.io.Serializable;
import java.util.ArrayList;

import com.base.MainEngine.MainEngine;
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
	 * @param node            the node
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
	 * @param NodeComponent the component
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
		return components;
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
		
		for(NodeComponent comp : this.components)
		{
			comp.input(this, delta, engine);
		}
		
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
		
		for(NodeComponent comp : this.components)
		{
			comp.render(this, engine);
		}
		
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
		
		for(NodeComponent comp : this.components)
		{
			comp.update(this, delta, engine);
		}
		
		for(Node node : this.children)
		{
			node.updateAll(delta, engine);
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
