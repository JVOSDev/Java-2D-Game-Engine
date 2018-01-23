package com.base.RenderingEngine.mesh;

import java.util.Stack;

import org.joml.Vector2f;

public class Circle extends Mesh
{
	private float radius;
	private int resolution;
	
	public Circle(float radius, int resolution)
	{
		this.radius = radius;
		this.resolution = resolution;
		this.init();
	}
	
	@Override
	protected void initVertices() 
	{
		Vertex[] cVertex = new Vertex[resolution + 1];
		cVertex[0] = new Vertex(new Vector2f(0,0), new Vector2f(.5f,.5f));
		float angle = 360.0f/(float)resolution;
		
		for(int i = 0; i < resolution; i++)
		{
			float a = i*angle;
			float cosX = (float)Math.cos(a);
			float sinY = (float)Math.sin(a);
			
			cVertex[i+1] = new Vertex(new Vector2f(cosX, sinY).mul(radius), new Vector2f((cosX+1)/2, (sinY+1)/2));
		}
		
		Stack<Integer> indStack = new Stack<>();
		for(int i = 2; i < ((3*resolution)-1); i++)
		{
			indStack.push(0);
			indStack.push(i-1);
			indStack.push(i);
		}
		
		Object[] inds = indStack.toArray();
		
		int[] indices = new int[inds.length];
		for(int i = 0; i < inds.length; i++)
		{
			indices[i] = (Integer)inds[i];
		}
		
		this.bufferVertices(cVertex, indices);
	}

}
