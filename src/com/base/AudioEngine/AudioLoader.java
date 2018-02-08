package com.base.AudioEngine;

import static org.lwjgl.stb.STBVorbis.stb_vorbis_close;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_get_info;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_get_samples_short_interleaved;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_open_memory;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_stream_length_in_samples;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBVorbisInfo;

import com.base.opengl.Utils;

public class AudioLoader
{
	/**
	 * Loads a vorbis audio file into the engine.
	 * 
	 * Code from
	 * https://github.com/LWJGL/lwjgl3/blob/master/modules/samples/src/test/java
	 * /org/lwjgl/demo/openal/ALCDemo.java
	 *
	 * @param filename
	 *            the filename of the vorbis file
	 * @return the audio source representation of the vorbis file
	 */
	public static AudioBuffer loadOGGFile(String filename)
	{
		STBVorbisInfo info = STBVorbisInfo.malloc();
		System.out.println("Allocated STB Vorbis Info");

		ByteBuffer vorbis;
		try
		{
			vorbis = Utils.fileToByteBuffer(filename);
			System.out.println(vorbis.capacity());
			System.out.println("Loaded File");
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}

		IntBuffer error = BufferUtils.createIntBuffer(1);
		long decoder = stb_vorbis_open_memory(vorbis, error, null);
		System.out.println("Allocated STB Vorbis decoder");
		if(decoder == NULL)
		{
			throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
		}

		stb_vorbis_get_info(decoder, info);
		System.out.println("Got vorbis info");

		int channels = info.channels();
		System.out.println("Got Channels");

		int lengthSamples = stb_vorbis_stream_length_in_samples(decoder);
		System.out.println("get Sample length");

		ShortBuffer pcm = BufferUtils.createShortBuffer(lengthSamples);
		System.out.println("Created Byte Buffer");

		pcm.limit(stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm) * channels);
		System.out.println("Finalized buffer");
		stb_vorbis_close(decoder);
		System.out.println("closed vorbis");

		return new AudioBuffer(pcm, info);
	}

	public static AudioBuffer loadWAVFile(String filename)
	{
		return null;
	}
}
