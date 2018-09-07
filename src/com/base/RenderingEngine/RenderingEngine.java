/*
 * 
 */
package com.base.RenderingEngine;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Camera;
import com.base.MainEngine.scene.Entity;
import com.base.RenderingEngine.mesh.Mesh;
import com.base.RenderingEngine.shader.Shader;

// TODO: Auto-generated Javadoc
/**
 * The Class RenderingEngine.
 */
public class RenderingEngine
{

	/** The shader. */
	private Shader shader;

	/** The camera. */
	private Camera camera;

	/**
	 * Instantiates a new rendering engine.
	 *
	 * @param camera
	 *            the camera
	 */
	public RenderingEngine(Camera camera)
	{
		this.shader = new Shader("./res/Shaders/basicVertex.vs", "./res/Shaders/basicFragment.fs");
		this.camera = camera;
	}

	/**
	 * Renders the entity.
	 *
	 * @param entity
	 *            the entity to be rendered
	 * @param engine
	 *            the main engine
	 */
	public void renderEntity(Entity entity, MainEngine engine)
	{
		this.shader.bind();
		this.shader.updateProjection(this.camera);
		this.shader.updateMaterial(entity.getMaterial());
		this.shader.updateTransform(entity.getTransform());
		Mesh m = entity.getMesh();
		m.draw();
		this.shader.unbind();
	}
}
