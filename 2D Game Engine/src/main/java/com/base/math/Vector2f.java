package com.base.math;

public class Vector2f
{
	private float x;
	private float y;

	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2f abs()
	{
		return new Vector2f(Math.abs(this.x), Math.abs(this.y));
	}

	public Vector2f add(float r)
	{
		return new Vector2f(this.x + r, this.y + r);
	}

	public Vector2f add(Vector2f r)
	{
		return new Vector2f(this.x + r.getX(), this.y + r.getY());
	}

	public Vector2f div(float r)
	{
		return new Vector2f(this.x / r, this.y / r);
	}

	public Vector2f div(Vector2f r)
	{
		return new Vector2f(this.x / r.getX(), this.y / r.getY());
	}

	public float dot(Vector2f r)
	{
		return this.x * r.getX() + this.y * r.getY();
	}

	public float getX()
	{
		return this.x;
	}

	public float getY()
	{
		return this.y;
	}

	public float length()
	{
		return (float) Math.sqrt(this.x * this.x + this.y * this.y);
	}

	public Vector2f mul(float r)
	{
		return new Vector2f(this.x * r, this.y * r);
	}

	public Vector2f mul(Vector2f r)
	{
		return new Vector2f(this.x * r.getX(), this.y * r.getY());
	}

	public Vector2f normalized()
	{
		float length = this.length();

		return new Vector2f(this.x / length, this.y / length);
	}

	public Vector2f rotate(float angle)
	{
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);

		return new Vector2f((float) (this.x * cos - this.y * sin), (float) (this.x * sin + this.y * cos));
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public Vector2f sub(float r)
	{
		return new Vector2f(this.x - r, this.y - r);
	}

	public Vector2f sub(Vector2f r)
	{
		return new Vector2f(this.x - r.getX(), this.y - r.getY());
	}

	@Override
	public String toString()
	{
		return "(" + this.x + " " + this.y + ")";
	}
}
