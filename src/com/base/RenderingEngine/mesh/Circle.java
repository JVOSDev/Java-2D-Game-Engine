package com.base.RenderingEngine.mesh;

import java.util.Stack;

import org.joml.Vector2f;

// TODO: Auto-generated Javadoc
/**
 * The Class Circle.
 */
public class Circle extends Mesh
{
	
	/** The radius. */
	private float radius;
	
	/** The resolution. */
	private int resolution;
	
	/**
	 * Instantiates a new circle.
	 *
	 * @param radius the radius
	 * @param resolution the resolution of the triangles
	 */
	public Circle(float radius, int resolution)
	{
		this.radius = radius;
		this.resolution = resolution;
		this.init();
	}
	
	/* (non-Javadoc)
	 * @see com.base.RenderingEngine.mesh.Mesh#initVertices()
	 */
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
