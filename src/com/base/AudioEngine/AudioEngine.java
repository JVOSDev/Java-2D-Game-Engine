package com.base.AudioEngine;

import static org.lwjgl.openal.AL10.AL_NO_ERROR;
import static org.lwjgl.openal.AL10.alGetError;
import static org.lwjgl.openal.AL10.alGetString;
import static org.lwjgl.openal.ALC10.ALC_DEFAULT_DEVICE_SPECIFIER;
import static org.lwjgl.openal.ALC10.ALC_FREQUENCY;
import static org.lwjgl.openal.ALC10.ALC_NO_ERROR;
import static org.lwjgl.openal.ALC10.ALC_REFRESH;
import static org.lwjgl.openal.ALC10.ALC_SYNC;
import static org.lwjgl.openal.ALC10.ALC_TRUE;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcGetError;
import static org.lwjgl.openal.ALC10.alcGetInteger;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.openal.ALC10.alcOpenDevice;
import static org.lwjgl.openal.ALC11.ALC_ALL_DEVICES_SPECIFIER;
import static org.lwjgl.openal.ALC11.ALC_MONO_SOURCES;
import static org.lwjgl.openal.ALC11.ALC_STEREO_SOURCES;
import static org.lwjgl.openal.EXTThreadLocalContext.alcSetThreadContext;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Objects;

import org.joml.Vector2f;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALUtil;

import com.base.opengl.Utils;

/**
 * The Class AudioEngine.
 */
public class AudioEngine
{
	private long device;
	private long context;
	private Vector2f lastPos = new Vector2f();

	public AudioEngine()
	{
		initOpenAL();
	}

	private void initOpenAL()
	{
		device = alcOpenDevice((ByteBuffer) null);
		if(device == NULL)
		{
			throw new IllegalStateException("Failed to open the default device.");
		}

		ALCCapabilities deviceCaps = ALC.createCapabilities(device);

		if(!deviceCaps.OpenALC10)
		{
			throw new IllegalStateException();
		}

		System.out.println("OpenALC10: " + deviceCaps.OpenALC10);
		System.out.println("OpenALC11: " + deviceCaps.OpenALC11);
		System.out.println("caps.ALC_EXT_EFX = " + deviceCaps.ALC_EXT_EFX);

		if(deviceCaps.OpenALC11)
		{
			List<String> devices = ALUtil.getStringList(NULL, ALC_ALL_DEVICES_SPECIFIER);
			if(devices == null)
			{
				checkALCError(NULL);
			}
			else
			{
				for(int i = 0; i < devices.size(); i++)
				{
					System.out.println(i + ": " + devices.get(i));
				}
			}
		}

		String defaultDeviceSpecifier = Objects.requireNonNull(alcGetString(NULL, ALC_DEFAULT_DEVICE_SPECIFIER));
		System.out.println("Default device: " + defaultDeviceSpecifier);

		context = alcCreateContext(device, (IntBuffer) null);
		alcSetThreadContext(context);
		AL.createCapabilities(deviceCaps);

		System.out.println("ALC_FREQUENCY: " + alcGetInteger(device, ALC_FREQUENCY) + "Hz");
		System.out.println("ALC_REFRESH: " + alcGetInteger(device, ALC_REFRESH) + "Hz");
		System.out.println("ALC_SYNC: " + (alcGetInteger(device, ALC_SYNC) == ALC_TRUE));
		System.out.println("ALC_MONO_SOURCES: " + alcGetInteger(device, ALC_MONO_SOURCES));
		System.out.println("ALC_STEREO_SOURCES: " + alcGetInteger(device, ALC_STEREO_SOURCES));
	}

	public void updateListener(Vector2f position)
	{
		AL10.alListenerfv(AL10.AL_POSITION, new float[] {position.x, position.y});
		AL10.alListenerfv(AL10.AL_VELOCITY, new float[] {lastPos.x - position.x, lastPos.y - position.y});
		this.lastPos = position;
	}

	public void playAudio(AudioBuffer audio)
	{
		AL10.alSourcePlay(audio.getSourceBuffer());
        Utils.checkALError();
	}

	public void stopAudio(AudioBuffer audio)
	{

	}

	static void checkALCError(long device)
	{
		int err = alcGetError(device);
		if(err != ALC_NO_ERROR)
		{
			throw new RuntimeException(alcGetString(device, err));
		}
	}

	static void checkALError()
	{
		int err = alGetError();
		if(err != AL_NO_ERROR)
		{
			throw new RuntimeException(alGetString(err));
		}
	}
	
	public void cleanUp()
	{
		alcSetThreadContext(NULL);
		ALC10.alcDestroyContext(context);
		ALC10.alcCloseDevice(device);
	}
}
