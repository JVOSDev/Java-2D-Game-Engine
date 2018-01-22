package com.base.MainEngine.scene;

import java.util.ArrayList;

import com.base.MainEngine.MainEngine;

public abstract class Node
{
	protected ArrayList<Node> children;
	protected Transform transform;
	protected Node parent = null;

	public Node()
	{
		this.children = new ArrayList<>();
		this.transform = new Transform();
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
	
	public Transform getTransform()
	{
		if(this.parent != null)
		{
			Transform combine = new Transform();

			combine.setRotation(this.transform.getRotation()+ this.parent.getTransform().getRotation());
			combine.setTranslation(this.transform.getTranslation().add(this.parent.getTransform().getTranslation()));
			combine.setScale(this.transform.getScale().mul(this.parent.getTransform().getScale()));

			return combine;
		}
		else
		{
			return this.transform;
		}
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
