package com.base.RenderingEngine;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Entity;
import com.base.RenderingEngine.mesh.Mesh;

public class RenderingEngine
{
	public RenderingEngine()
	{
		
	}
	
	public void renderEntity(Entity entity, MainEngine engine)
	{
		Mesh m = entity.getMesh();
		m.draw();
	}
}
