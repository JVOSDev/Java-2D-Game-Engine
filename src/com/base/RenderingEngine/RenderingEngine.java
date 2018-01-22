package com.base.RenderingEngine;

import com.base.MainEngine.MainEngine;
import com.base.MainEngine.scene.Camera;
import com.base.MainEngine.scene.Entity;
import com.base.RenderingEngine.mesh.Mesh;
import com.base.RenderingEngine.shader.Shader;

public class RenderingEngine
{
	private Shader shader;
	private Camera camera;
	
	public RenderingEngine(Camera camera)
	{
		this.shader = new Shader("./res/Shaders/basicVertex.vs", "./res/Shaders/basicFragment.fs");
		this.camera = camera;
	}
	
	public void renderEntity(Entity entity, MainEngine engine)
	{
		shader.bind();
		shader.updateProjection(camera);
		shader.updateMaterial(entity.getMaterial());
		shader.updateTransform(entity.getTransform());
		Mesh m = entity.getMesh();
		m.draw();
		shader.unbind();
	}
}
