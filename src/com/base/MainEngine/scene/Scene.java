package com.base.MainEngine.scene;

import java.util.ArrayList;

import com.base.MainEngine.MainEngine;

// TODO: Auto-generated Javadoc
/**
 * The Class Scene.
 */
public class Scene
{

	/** The root node. */
	private Node root;

	/**
	 * Instantiates a new scene.
	 */
	public Scene()
	{
		this.setRoot(new Node()
		{

			@Override
			public void input(float delta, MainEngine engine)
			{
			}

			@Override
			public void render(MainEngine engine)
			{
			}

			@Override
			public void update(float delta, MainEngine engine)
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
	public void input(float delta, MainEngine engine)
	{
		this.getRoot().inputAll(delta, engine);
	}

	/**
	 * Render.
	 *
	 * @param engine
	 *            the engine
	 */
	public void render(MainEngine engine)
	{
		this.getRoot().renderAll(engine);
	}

	/**
	 * Update.
	 *
	 * @param delta
	 *            the delta
	 * @param engine
	 *            the engine
	 */
	public void update(float delta, MainEngine engine)
	{
		this.getRoot().updateAll(delta, engine);
	}

	/**
	 * Gets the all nodes in the scene.
	 *
	 * @return the all nodes
	 */
	public ArrayList<Node> getAllNodes()
	{
		return getRoot().children;
	}

	/**
	 * Gets the root node.
	 *
	 * @return the root
	 */
	public Node getRoot()
	{
		return root;
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
}
