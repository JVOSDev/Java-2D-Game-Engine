package com.base.MainEngine.scene;

import java.util.ArrayList;

import com.base.MainEngine.MainEngine;

public class Scene
{
	private Node root;

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

	public void addNode(Node node)
	{
		this.getRoot().addChild(node);
	}

	public void input(float delta, MainEngine engine)
	{
		this.getRoot().inputAll(delta, engine);
	}

	public void render(MainEngine engine)
	{
		this.getRoot().renderAll(engine);
	}

	public void update(float delta, MainEngine engine)
	{
		this.getRoot().updateAll(delta, engine);
	}
	
	public ArrayList<Node> getAllNodes()
	{
		return getRoot().children;
	}

	public Node getRoot()
	{
		return root;
	}

	public void setRoot(Node root)
	{
		this.root = root;
	}
}
