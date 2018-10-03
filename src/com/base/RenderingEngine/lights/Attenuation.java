package com.base.RenderingEngine.lights;

import java.io.Serializable;

public class Attenuation implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 961907379122776076L;

	private static final float DIST = .01f;

	private float a;
	private float b;
	private float c;

	public float getA()
	{
		return this.a;
	}

	public float getB()
	{
		return this.b;
	}

	public float getC()
	{
		return this.c;
	}

	public Attenuation(float a, float b, float c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public float calcDistance()
	{
		float sq = ((this.b * this.b) * (DIST * DIST)) - (4 * this.a * DIST * ((DIST / this.c) - 1));
		return (float) ((-1 * this.b * DIST) + Math.sqrt(sq)) / (2 * this.a * DIST);
	}
}
