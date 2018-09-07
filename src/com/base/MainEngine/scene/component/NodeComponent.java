/*
 * 
 */
package com.base.MainEngine.scene.component;

import java.io.Serializable;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Node;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeComponent.
 */
public abstract class NodeComponent implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Input the component
	 *
	 * @param node the parent node
	 * @param delta the delta time
	 * @param mainEngine the main engine
	 */
	public abstract void input(Node node, float delta, MainEngine mainEngine);
	
	/**
	 * Update the component
	 *
	 * @param node the parent node
	 * @param delta the delta time
	 * @param mainEngine the main engine
	 */
	public abstract void update(Node node, float delta, MainEngine mainEngine);
	
	/**
	 * Render.
	 *
	 * @param node the parent node
	 * @param mainEngine the main engine
	 */
	public abstract void render(Node node, MainEngine mainEngine);
}
