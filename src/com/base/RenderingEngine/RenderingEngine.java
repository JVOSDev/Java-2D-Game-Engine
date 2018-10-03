/*
 *
 */
package com.base.RenderingEngine;

import org.joml.Vector3f;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Camera;
import com.base.MainEngine.scene.Entity;
import com.base.RenderingEngine.lights.LightRenderer;
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

	private FrameBuffer diffuseBuffer;

	private LightRenderer lightRenderer;

	/**
	 * Instantiates a new rendering engine.
	 *
	 * @param camera
	 *            the camera
	 */
	public RenderingEngine(Camera camera)
	{
		this.shader = new Shader("./res/Shaders/basicVertex.vs", "./res/Shaders/basicFragment.fs");
		this.diffuseBuffer = new FrameBuffer(MainEngine.WIDTH, MainEngine.HEIGHT, 0);
		this.lightRenderer = new LightRenderer(new Vector3f(.1f, .1f, .1f));
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

	public void renderEntity(Entity entity)
	{
		this.diffuseBuffer.bind();
		this.shader.bind();
		this.shader.updateProjection(this.camera);
		this.shader.updateMaterial(entity.getMaterial());
		this.shader.updateTransform(entity.getTransform());
		Mesh m = entity.getMesh();
		m.draw();
		entity.getMaterial().getTexture().unbind();
		this.shader.unbind();
		this.diffuseBuffer.unbind();
	}

	public void renderLights()
	{
		this.lightRenderer.renderLights();
	}

	public void clear()
	{
		this.diffuseBuffer.clear();
		this.lightRenderer.clear();
	}

	public FrameBuffer getDiffuseBuffer()
	{
		return this.diffuseBuffer;
	}

	public LightRenderer getLightRenderer()
	{
		return this.lightRenderer;
	}
}
