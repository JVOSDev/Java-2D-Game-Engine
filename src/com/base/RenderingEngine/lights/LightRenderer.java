package com.base.RenderingEngine.lights;

import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Entity;
import com.base.RenderingEngine.FrameBuffer;
import com.base.RenderingEngine.Texture;
import com.base.RenderingEngine.mesh.Mesh;
import com.base.RenderingEngine.mesh.Rectangle;

public class LightRenderer
{
	// TODO: Render lights to a framebuffer
	// Use Blending to blend all the shaded lights together
	// TODO: Draw shadow mask to a framebuffer

	// TODO: Shader to combine lights and shadowMask

	private static Mesh drawMesh;

	static
	{
		drawMesh = new Rectangle(100, 100);
	}

	private FrameBuffer lightBuffer;
	private FrameBuffer shadowBuffer;
	private FrameBuffer lightingBuffer;

	private LightShader lightShader;
	private ShadowShader shadowShader;

	private ArrayList<PointLight> pointLights;
	private Vector3f ambientLight;

	public LightRenderer(Vector3f ambientLight)
	{
		this.lightShader = new LightShader();
		// this.shadowShader = new ShadowShader();
		this.pointLights = new ArrayList<>();

		this.lightBuffer = new FrameBuffer(MainEngine.WIDTH, MainEngine.HEIGHT, 1);
		this.shadowBuffer = new FrameBuffer(MainEngine.WIDTH / 2, MainEngine.HEIGHT / 2, 1);
		this.lightingBuffer = new FrameBuffer(MainEngine.WIDTH, MainEngine.HEIGHT, 1);
		this.ambientLight = ambientLight;

		// this.addPointLight(new PointLight(new Vector2f(0,0), new
		// Vector3f(0,0,0), 0, 1));
	}

	public void addPointLight(PointLight pLight)
	{
		this.pointLights.add(pLight);
	}

	public void shadowEntity(Entity e)
	{
		this.shadowBuffer.bind();
		this.shadowShader.bind();

		for(PointLight p : this.pointLights)
		{

		}

		this.shadowShader.unbind();
		this.shadowBuffer.unbind();
	}

	public void renderLights()
	{

		this.ambientLight();
		// GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		// GL11.glDepthMask(false);
		// GL11.glDepthFunc(GL11.GL_EQUAL);

		for(PointLight p : this.pointLights)
		{
			this.renderLight(p);
		}

		// GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void ambientLight()
	{
		this.lightBuffer.bind();
		this.lightShader.bind();

		PointLight light = new PointLight(new Vector2f(0, 0), new Vector3f(0, 0, 0), new Attenuation(1, 1, 1));

		light.updateUniforms(this.lightShader);
		this.lightShader.updateUniformVector3f(this.ambientLight, "ambientLight");
		this.lightShader.updateTransform(light.getTransform());
		this.lightShader.updateProjection(MainEngine.getCamera());

		drawMesh.draw();

		this.lightShader.unbind();
		this.lightBuffer.unbind();
	}

	public void renderLight(PointLight light)
	{
		this.lightBuffer.bind();
		this.lightShader.bind();

		light.updateUniforms(this.lightShader);
		this.lightShader.updateUniformVector3f(new Vector3f(0, 0, 0), "ambientLight");
		this.lightShader.updateTransform(light.getTransform());
		this.lightShader.updateProjection(MainEngine.getCamera());

		drawMesh.draw();

		this.lightShader.unbind();
		this.lightBuffer.unbind();
	}

	public void clear()
	{
		this.lightBuffer.clear();
		this.shadowBuffer.clear();
		this.lightingBuffer.clear();
	}

	public Texture getCombinedTexture()
	{
		// return this.lightingBuffer.getTexture();
		return this.lightBuffer.getTexture();
	}

	public Vector3f getAmbientLight()
	{
		return this.ambientLight;
	}

	public void setAmbientLight(Vector3f ambientLight)
	{
		this.ambientLight = ambientLight;
	}
}
