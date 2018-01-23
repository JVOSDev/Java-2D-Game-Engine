package com.base.RenderingEngine.mesh;

import org.joml.Vector2f;

public class Rectangle extends Mesh
{
	private float xLength;
	private float yLength;
	
	public Rectangle(float xLength, float yLength)
	{
		this.xLength = xLength;
		this.yLength = yLength;
		this.init();
	}
	
	/**
	 * draw order and vertex data from: 
	 * https://stackoverflow.com/questions/11806690/android-opengl-es-2-drawing-squares
	 * 
	 * static float squareCoords[] = { -0.5f,  0.5f, 0.0f,   // top left
                                       -0.5f, -0.5f, 0.0f,   // bottom left
                                       0.5f, -0.5f, 0.0f,   // bottom right
                                       0.5f,  0.5f, 0.0f }; // top right

                private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices
	 */
	
	@Override
	protected void initVertices() 
	{
		float halfXLength = xLength / 2f;
		float halfYLength = yLength / 2f;
		
		this.bufferVertices(new Vertex[]{new Vertex(new Vector2f(-halfXLength,halfYLength)),
										 new Vertex(new Vector2f(-halfXLength,-halfYLength)),
										 new Vertex(new Vector2f(halfXLength,-halfYLength)),
										 new Vertex(new Vector2f(halfXLength,halfYLength))}, new int[]{0,1,2,0,2,3});
	}

}
