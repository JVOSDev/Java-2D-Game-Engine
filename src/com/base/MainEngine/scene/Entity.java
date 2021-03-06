/*
 *
 */
package com.base.MainEngine.scene;

import java.io.Serializable;

import com.base.MainEngine.MainEngine;
import com.base.RenderingEngine.Material;
import com.base.RenderingEngine.Texture;
import com.base.RenderingEngine.mesh.Mesh;

// TODO: Auto-generated Javadoc
/**
 * The Class Entity.
 */
public class Entity extends Node implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5039008086591398196L;

	/** The mesh. */
	protected Mesh mesh;

	/** The material. */
	protected Material material;

	/**
	 * Instantiates a new entity.
	 *
	 * @param mesh
	 *            the mesh for the entity
	 * @param material
	 *            the material for the entity
	 */
	public Entity(Mesh mesh, Material material)
	{
		this.mesh = mesh;
		this.material = material;
	}

	/**
	 * Instantiates a new entity with a basic material.
	 *
	 * @param mesh
	 *            the mesh for the entity
	 */
	public Entity(Mesh mesh)
	{
		this.mesh = mesh;
		this.material = new Material(new Texture("./res/textures/white.png", 0));
	}

	/**
	 * Gets the mesh.
	 *
	 * @return the mesh
	 */
	public Mesh getMesh()
	{
		return this.mesh;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.Node#input(float,
	 * com.base.MainEngine.MainEngine)
	 */
	@Override
	public void input(double delta)
	{

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.base.MainEngine.scene.Node#render(com.base.MainEngine.MainEngine)
	 */
	@Override
	public void render()
	{
		MainEngine.getRenderEngine().renderEntity(this);
	}

	/**
	 * Sets the mesh.
	 *
	 * @param mesh
	 *            the new mesh for the entity
	 */
	public void setMesh(Mesh mesh)
	{
		this.mesh = mesh;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.Node#update(float,
	 * com.base.MainEngine.MainEngine)
	 */
	@Override
	public void update(double delta)
	{

	}

	/**
	 * Gets the material.
	 *
	 * @return the material
	 */
	public Material getMaterial()
	{
		return this.material;
	}

	/**
	 * Sets the material.
	 *
	 * @param material
	 *            the new material for the entity
	 */
	public void setMaterial(Material material)
	{
		this.material = material;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.base.MainEngine.scene.Node#reInit()
	 */
	@Override
	void reInit()
	{
		this.mesh.reInit();
		this.material.reInit();
	}

}
