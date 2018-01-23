package com.base.MainEngine.scene;

import com.base.MainEngine.MainEngine;
import com.base.RenderingEngine.Material;
import com.base.RenderingEngine.Texture;
import com.base.RenderingEngine.mesh.Mesh;

public class Entity extends Node
{
	protected Mesh mesh;
	protected Material material;

	public Entity(Mesh mesh, Material material)
	{
		this.mesh = mesh;
		this.material = material;
	}
	
	public Entity(Mesh mesh)
	{
		this.mesh = mesh;
		this.material = new Material(new Texture("./res/textures/base.png"));
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

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
}
