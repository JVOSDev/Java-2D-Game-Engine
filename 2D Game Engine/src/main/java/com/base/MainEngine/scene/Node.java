package com.base.MainEngine.scene;

import java.util.ArrayList;

import com.base.MainEngine.MainEngine;

public abstract class Node
{
	public ArrayList<Node> children;
	public Node parent = null;

	public Node()
	{
		this.children = new ArrayList<>();
	}

	public void addChild(Node node)
	{
		this.children.add(node);
		node.setParent(this);
	}

	public ArrayList<Node> getChildren()
	{
		return this.children;
	}

	public Node getParent()
	{
		return this.parent;
	}

	public abstract void input(float delta, MainEngine engine);

	public void inputAll(float delta, MainEngine engine)
	{
		this.input(delta, engine);
		for(Node node : this.children)
		{
			node.inputAll(delta, engine);
		}
	}

	public abstract void render(MainEngine engine);

	public void renderAll(MainEngine engine)
	{
		this.render(engine);
		for(Node node : this.children)
		{
			node.renderAll(engine);
		}
	}

	public void setParent(Node parent)
	{
		this.parent = parent;
	}

	public abstract void update(float delta, MainEngine engine);

	public void updateAll(float delta, MainEngine engine)
	{
		this.update(delta, engine);
		for(Node node : this.children)
		{
			node.updateAll(delta, engine);
		}
	}
}
