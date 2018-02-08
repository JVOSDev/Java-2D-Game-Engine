package com.base.RenderingEngine.shader;

import java.util.HashMap;

import org.joml.Matrix3x2f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import com.base.MainEngine.scene.Camera;
import com.base.MainEngine.scene.Transform;
import com.base.RenderingEngine.Material;
import com.base.opengl.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class Shader.
 */
public class Shader
{

	/** The Constant MATERIAL_COLOR. */
	public static final String MATERIAL_COLOR = "baseColor";

	/** The Constant TRANSFORM_MATRIX. */
	public static final String TRANSFORM_MATRIX = "transformM";

	/** The Constant PROJECTION_MATRIX. */
	public static final String PROJECTION_MATRIX = "projectionM";

	/** The program. */
	private int program;

	/** The uniform locations. */
	private HashMap<String, Integer> uniformLocations;

	/**
	 * Instantiates a new shader.
	 *
	 * @param vertexFilename
	 *            the vertex shader filename
	 * @param fragFilename
	 *            the fragment shader filename
	 */
	public Shader(String vertexFilename, String fragFilename)
	{
		this.uniformLocations = new HashMap<>();
		this.program = GL20.glCreateProgram();

		if(this.program == 0)
		{
			System.err.println("Shader creation failed: Could not find valid memory location in constructor");
			System.exit(1);
		}

		this.loadShader(GL20.GL_VERTEX_SHADER, Utils.loadTextFile(vertexFilename));
		this.loadShader(GL20.GL_FRAGMENT_SHADER, Utils.loadTextFile(fragFilename));

		this.compile();

		this.addUniform(TRANSFORM_MATRIX);
		this.addUniform(MATERIAL_COLOR);
		this.addUniform(PROJECTION_MATRIX);
	}

	/**
	 * Adds the uniform to the shader by name
	 *
	 * @param name
	 *            the name of the uniform
	 */
	public void addUniform(String name)
	{
		int uniformLocation = GL20.glGetUniformLocation(this.program, name);

		if(uniformLocation == 0xFFFFFFFF)
		{
			System.err.println("Error: Could not find uniform: " + name);
			new Exception().printStackTrace();
			System.exit(1);
		}

		this.uniformLocations.put(name, uniformLocation);
	}

	/**
	 * Bind the shader program
	 */
	public void bind()
	{
		GL20.glUseProgram(this.program);
	}

	/**
	 * Destroys the shader
	 */
	public void destroy()
	{
		GL15.glDeleteBuffers(this.program);
	}

	/**
	 * Unbinds the shader program
	 */
	public void unbind()
	{
		GL20.glUseProgram(0);
	}

	/**
	 * Updatest the material.
	 *
	 * @param material
	 *            the material to be updated
	 */
	public void updateMaterial(Material material)
	{
		this.updateUniformVector3f(material.getBaseColor(), MATERIAL_COLOR);
		material.getTexture().bind();
	}

	/**
	 * Updates the transform
	 *
	 * @param transform
	 *            the transform
	 */
	public void updateTransform(Transform transform)
	{
		this.updateUniformMatrix4f(transform.getTransformMatrix(), TRANSFORM_MATRIX);
	}

	/**
	 * Updates the projection matrix.
	 *
	 * @param camera
	 *            the camera
	 */
	public void updateProjection(Camera camera)
	{
		this.updateUniformMatrix4f(camera.getProjectionM(), PROJECTION_MATRIX);
	}

	/**
	 * Updates uniform matrix3f.
	 *
	 * @param mat
	 *            the matrix
	 * @param uniform
	 *            the uniform name
	 */
	public void updateUniformMatrix4f(Matrix3x2f mat, String uniform)
	{
		Utils.createFloatBuffer(4 * 4);
		GL20.glUniformMatrix4fv(this.uniformLocations.get(uniform), true, mat.get4x4(Utils.createFloatBuffer(4 * 4)));
	}

	/**
	 * Updates uniform matrix4f.
	 *
	 * @param mat
	 *            the matrix
	 * @param uniform
	 *            the uniform name
	 */
	public void updateUniformMatrix4f(Matrix4f mat, String uniform)
	{
		GL20.glUniformMatrix4fv(this.uniformLocations.get(uniform), true, Utils.bufferMatrix4f(mat));
	}

	/**
	 * Update uniform vector3f.
	 *
	 * @param vec
	 *            the vector
	 * @param uniform
	 *            the uniform name
	 */
	public void updateUniformVector3f(Vector3f vec, String uniform)
	{
		GL20.glUniform3f(this.uniformLocations.get(uniform), vec.x, vec.y, vec.z);
	}

	/**
	 * Update uniform vector2f.
	 *
	 * @param vec
	 *            the vector
	 * @param uniform
	 *            the uniform name
	 */
	public void updateUniformVector2f(Vector2f vec, String uniform)
	{
		GL20.glUniform2f(this.uniformLocations.get(uniform), vec.x, vec.y);
	}

	/**
	 * Compiles the shader program
	 */
	private void compile()
	{
		GL20.glLinkProgram(this.program);

		if(GL20.glGetProgrami(this.program, GL20.GL_LINK_STATUS) == 0)
		{
			System.err.println(GL20.glGetProgramInfoLog(this.program, 1024));
			System.exit(1);
		}

		GL20.glValidateProgram(this.program);

		if(GL20.glGetProgrami(this.program, GL20.GL_VALIDATE_STATUS) == 0)
		{
			System.err.println(GL20.glGetProgramInfoLog(this.program, 1024));
			System.exit(1);
		}
	}

	/**
	 * Loads the shader program from a text file
	 *
	 * @param type
	 *            the type
	 * @param source
	 *            the source text
	 */
	private void loadShader(int type, String source)
	{
		int shader = GL20.glCreateShader(type);

		if(shader == 0)
		{
			System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
			System.exit(1);
		}

		GL20.glShaderSource(shader, source);
		GL20.glCompileShader(shader);

		if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == 0)
		{
			System.err.println(GL20.glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}

		GL20.glAttachShader(this.program, shader);
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
