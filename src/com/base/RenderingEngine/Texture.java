package com.base.RenderingEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

// TODO: Auto-generated Javadoc
/**
 * The Class Texture.
 */
public class Texture implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8799378377605432082L;

	/** The id of the texture. */
	private transient int id;

	/** The location of the texture object (eg. TEXTURE0, TEXTURE17). */
	private transient int location;
	
	private String filename;

	/**
	 * Instantiates a new texture.
	 *
	 * @param filename
	 *            the filename of the texture image(PNG)
	 * @param location
	 *            the location for the texture
	 */
	public Texture(String filename, int location)
	{
		this.filename = filename;
		this.location = location;
		PNGDecoder decoder = null;
		try
		{
			decoder = new PNGDecoder(new FileInputStream(new File(filename)));
			ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
			buf.flip();
			this.id = GL11.glGenTextures();

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.id);

			GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Binds the texture to be used.
	 */
	public void bind()
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + this.location);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.id);
	}

	/**
	 * Destroys the texture data.
	 */
	public void destroy()
	{
		GL15.glDeleteBuffers(this.id);
	}

	/**
	 * Unbinds the texture.
	 */
	public void unbind()
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
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

	public String getFilename()
	{
		return filename;
	}
	
	public void reInit()
	{
		PNGDecoder decoder = null;
		try
		{
			decoder = new PNGDecoder(new FileInputStream(new File(filename)));
			ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
			buf.flip();
			this.id = GL11.glGenTextures();

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.id);

			GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
}
