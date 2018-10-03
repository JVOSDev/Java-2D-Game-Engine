package com.base.RenderingEngine.lights;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Node;
import com.base.RenderingEngine.shader.Shader;

public class PointLight extends Node
{
	// Remember light falloff of 1/distance^2
	// Intensity at a point = i*1/(Pl - p)^2
	// Cap intensity between 1 and ambientLight
	// Use a culling of d = 1/.01 to 1/.005
	// Use that distance to stop light calculations

	/**
	 *
	 */
	private static final long serialVersionUID = 8432886953715702444L;

	private Vector2f position;
	private Vector3f color;
	private Attenuation attenuation;

	public PointLight(Vector2f position, Vector3f color, Attenuation attenuation)
	{
		this.position = position;
		this.color = color;
		this.attenuation = attenuation;
		this.transform.setTranslation(position);

	}

	public void addToLightRenderer()
	{
		MainEngine.getRenderEngine().getLightRenderer().addPointLight(this);
	}

	public void updateUniforms(Shader shader)
	{
		shader.updateUniformVector2f(this.position, "light.lightPos");
		shader.updateUniformVector3f(this.color, "light.lightColor");
		shader.updateUniform1f(this.attenuation.getA(), "light.atten.a");
		shader.updateUniform1f(this.attenuation.getB(), "light.atten.b");
		shader.updateUniform1f(this.attenuation.getC(), "light.atten.c");
	}

	@Override
	public void input(double delta)
	{

	}

	@Override
	public void render()
	{

	}

	double counter = 0;

	@Override
	public void update(double delta)
	{
		// this.color.x = (float) Math.abs(Math.sin(counter));
		// this.color.y = (float) Math.abs(Math.cos(counter));
		// this.color.z = (float) Math.abs((Math.sin(counter) *
		// Math.cos(counter)));
		// counter += delta;
	}

	@Override
	public void setParent(Node node)
	{
		this.parent = node;
		this.addToLightRenderer();
	}

}
