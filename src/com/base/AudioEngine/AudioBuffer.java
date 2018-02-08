package com.base.AudioEngine;

import java.nio.ShortBuffer;

import org.lwjgl.openal.AL10;
import org.lwjgl.stb.STBVorbisInfo;

import com.base.opengl.Utils;

public class AudioBuffer
{
	private int audioBuffer;
	private int sourceBuffer;

	public AudioBuffer()
	{

	}

	public AudioBuffer(ShortBuffer pcmData, STBVorbisInfo info)
	{
		this.genBuffers();
		this.loadVorbisPCMData(pcmData, info);
	}

	public void genBuffers()
	{
		this.audioBuffer = AL10.alGenBuffers();
		this.sourceBuffer = AL10.alGenSources();
		Utils.checkALError();
	}

	public void loadVorbisPCMData(ShortBuffer data, STBVorbisInfo info)
	{
		AL10.alBufferData(audioBuffer, info.channels() == 1 ? AL10.AL_FORMAT_MONO16 : AL10.AL_FORMAT_STEREO16, data, info.sample_rate());
		Utils.checkALError();
	}
	
	public void loadSource()
	{
		AL10.alSourcei(sourceBuffer, AL10.AL_BUFFER, audioBuffer);
		Utils.checkALError();
	}
	
	public void cleanUp()
	{
		  AL10.alDeleteBuffers(audioBuffer);
	      Utils.checkALError();
	      AL10.alDeleteSources(sourceBuffer);
	      Utils.checkALError();
	}

	public int getAudioBuffer()
	{
		return audioBuffer;
	}

	public int getSourceBuffer()
	{
		return sourceBuffer;
	}
	
	
}
