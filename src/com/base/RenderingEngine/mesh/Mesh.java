package com.base.RenderingEngine.mesh;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import com.base.opengl.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class Mesh.
 */
public abstract class Mesh
{

	/** The pointer to the vertex array object. */
	protected int vao;

	/** The pointer to the vertex buffer object. */
	protected int vbo;

	/** The pointer to the index buffer object. */
	protected int ibo;

	/** The size of the mesh data. */
	protected int size;

	/**
	 * Inits the mesh pointers
	 */
	protected void init()
	{
		this.vao = glGenVertexArrays();
		glBindVertexArray(this.vao);

		this.vbo = glGenBuffers();
		this.ibo = glGenBuffers();
		this.initVertices();
	}

	/**
	 * Inits the vertices.
	 *
	 * Recomended implementation:
	 *
	 * A vertex array with vertex position data and an int array with the draw
	 * pattern this.bufferVertices(Vertex[]..., int[]...);
	 */
	protected abstract void initVertices();

	/**
	 * Destroys the mesh data
	 */
	public void destroy()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDeleteBuffers(this.vbo);
		glDeleteBuffers(this.ibo);
		glBindVertexArray(0);
		glDeleteVertexArrays(this.vao);
	}

	/**
	 * Bind vertex array.
	 */
	public void bindVertexArray()
	{
		glBindVertexArray(this.vao);
	}

	/**
	 * Draw.
	 */
	public void draw()
	{
		this.bindVertexArray();

		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

		glDrawElements(GL_TRIANGLES, this.size, GL_UNSIGNED_INT, 0);

		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);

		this.unbindVertexArray();
	}

	/**
	 * Buffer vertices.
	 *
	 * @param vertices
	 *            the vertices
	 * @param indices
	 *            the indices
	 */
	protected void bufferVertices(Vertex[] vertices, int[] indices)
	{
		glBindVertexArray(this.vao);

		glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
		glBufferData(GL_ARRAY_BUFFER, Utils.bufferVertices(vertices), GL_STATIC_DRAW);

		glVertexAttribPointer(0, 2, GL_FLOAT, false, 16, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 16, 8);

		this.size = indices.length;

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.createFlippedBuffer(indices), GL_STATIC_DRAW);

		glBindVertexArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	/**
	 * Unbind vertex array.
	 */
	public void unbindVertexArray()
	{
		glBindVertexArray(0);
	}

	/**
	 * Gen indices.
	 *
	 * @param length
	 *            the length
	 * @return the int[]
	 */
	protected static int[] genIndices(int length)
	{
		int[] inds = new int[length];
		for(int i = 0; i < inds.length; i++)
		{
			inds[i] = i;
		}
		return inds;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize()
	{
		this.destroy();
	}
}
