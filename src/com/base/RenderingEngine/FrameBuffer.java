package com.base.RenderingEngine;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import com.base.MainEngine.MainEngine;

public class FrameBuffer
{
	private int fbo;
	private int width;
	private int height;
	private Texture texture;

	public FrameBuffer(int width, int height, int location)
	{
		// Creates the framebuffer pointer
		this.fbo = GL30.glGenFramebuffers();

		this.width = width;
		this.height = height;

		// Start using this framebuffer
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.fbo);

		// Tell opengl that we would like to draw to and read from the buffer
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
		GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0);

		// Creates the texture this will output too
		this.texture = this.createTextureAttachment(location);

		this.unbind();
	}

	public void delete()
	{
		GL30.glDeleteFramebuffers(this.fbo);
	}

	public void bind()
	{
		// Reset the texture just in case this texture is bound anywhere
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.fbo);
		GL11.glViewport(0, 0, this.width, this.height);
	}

	public void unbind()
	{
		// Makes sure that the rendering is done in the framebuffer
		GL11.glFinish();

		// Reset to the default framebuffer and reset the viewport
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, MainEngine.WIDTH, MainEngine.HEIGHT);
	}

	public void clear()
	{
		this.bind();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		this.unbind();
	}

	private Texture createTextureAttachment(int location)
	{
		// Create an empty texture at the location provided
		Texture ret = new Texture(location);

		// Use the texture
		ret.bind();

		// Tell opengl that this is an image texture with width and height and
		// no pixel data
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.width, this.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

		// Tell the framebuffer to draw to the texture
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, ret.getId(), 0);

		// Stop using the texture
		ret.unbind();
		return ret;
	}

	public Texture getTexture()
	{
		return this.texture;
	}

}
