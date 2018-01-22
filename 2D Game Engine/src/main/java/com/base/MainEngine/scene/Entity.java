package com.base.MainEngine.scene;

import com.base.MainEngine.MainEngine;
import com.base.RenderingEngine.mesh.Mesh;

public class Entity extends Node
{
	public Mesh mesh;

	public Entity(Mesh mesh)
	{
		this.mesh = mesh;
	}

	public Mesh getMesh()
	{
		return this.mesh;
	}

	@Override
	public void input(float delta, MainEngine engine)
	{

	}

	@Override
	public void render(MainEngine engine)
	{
		engine.getRenderEngine().renderEntity(this, engine);
	}

	public void setMesh(Mesh mesh)
	{
		this.mesh = mesh;
	}

	@Override
	public void update(float delta, MainEngine engine)
	{

	}
}
