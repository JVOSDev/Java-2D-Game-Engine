/*
 *
 */
package com.base.MainEngine.scene;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Scene.
 */
public class Scene implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8443020325922176378L;
	/** The root node. */
	private Node root;

	/**
	 * Instantiates a new scene.
	 */
	public Scene()
	{
		this.setRoot(new Node()
		{
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void input(double delta)
			{
			}

			@Override
			public void render()
			{
			}

			@Override
			public void update(double delta)
			{
			}
		});
	}

	/**
	 * Adds a node to the scene graph.
	 *
	 * @param node
	 *            the node
	 */
	public void addNode(Node node)
	{
		this.getRoot().addChild(node);
	}

	/**
	 * Inputs all nodes.
	 *
	 * @param delta
	 *            the delta
	 * @param engine
	 *            the engine
	 */
	public void input(double delta)
	{
		this.getRoot().inputAll(delta);
	}

	/**
	 * Render.
	 *
	 * @param engine
	 *            the engine
	 */
	public void render()
	{
		this.getRoot().renderAll();
	}

	/**
	 * Update.
	 *
	 * @param delta
	 *            the delta
	 * @param engine
	 *            the engine
	 */
	public void update(double delta)
	{
		this.getRoot().updateAll(delta);
	}

	/**
	 * Gets the all nodes in the scene.
	 *
	 * @return the all nodes
	 */
	public ArrayList<Node> getAllNodes()
	{
		return this.getRoot().children;
	}

	/**
	 * Gets the root node.
	 *
	 * @return the root
	 */
	public Node getRoot()
	{
		return this.root;
	}

	/**
	 * Sets the root node.
	 *
	 * @param root
	 *            the new root
	 */
	public void setRoot(Node root)
	{
		this.root = root;
	}

	/**
	 * Reinitializes the scene after loading it from a saved scene
	 */
	public void reInit()
	{
		this.root.reInit();
	}
}
