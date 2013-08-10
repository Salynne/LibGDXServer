/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package net.pevnostgames.lwjglserver;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class ServerApplication implements Application {
	protected final ServerGraphics graphics;
	protected final ServerAudio audio;
	protected final ServerFiles files;
	protected final ServerInput input;
	protected final ServerNet net;
	protected final ApplicationListener listener;
	protected final Array<Runnable> runnables = new Array<Runnable>();
	protected final Array<Runnable> executedRunnables = new Array<Runnable>();
	protected final Array<LifecycleListener> lifecycleListeners = new Array<LifecycleListener>();
	protected Thread mainLoopThread;
	protected boolean running = true;
	protected int logLevel = LOG_INFO;
	Map<String, Preferences> preferences = new HashMap<String, Preferences>();

	protected long lastTime = 0;
	protected float deltaTime = 0;
	protected float targetDelta = 0;

	public ServerApplication(ApplicationListener listener, float targetDelta) {
		audio = new ServerAudio();
		graphics = new ServerGraphics();
		files = new ServerFiles();
		input = new ServerInput();
		net = new ServerNet();
		this.listener = listener;

		Gdx.app = this;
		Gdx.graphics = graphics;
		Gdx.audio = audio;
		Gdx.files = files;
		Gdx.input = input;
		Gdx.net = net;
		this.targetDelta = targetDelta;
		initialize();
	}

	private void initialize() {
		mainLoopThread = new Thread("LWJGL Application") {
			public void run() {
				try {
					ServerApplication.this.mainLoop();
				} catch (Throwable t) {
					if (t instanceof RuntimeException)
						throw (RuntimeException) t;
					else
						throw new GdxRuntimeException(t);
				}
			}
		};
		mainLoopThread.start();
	}

	private void mainLoop() {
		listener.create();

		lastTime = System.nanoTime();

		while (running) {
			boolean shouldRender = (System.nanoTime() - lastTime) / 1000000000.0f >= targetDelta;

			synchronized (runnables) {
				executedRunnables.clear();
				executedRunnables.addAll(runnables);
				runnables.clear();
			}

			for (int i = 0; i < executedRunnables.size; i++) {
				shouldRender = true;
				executedRunnables.get(i).run(); // calls out to random app code that could do anything ...
			}

			// If one of the runnables set running to false, for example after an exit().
			if (!running) break;

			if (shouldRender) {
				long time = System.nanoTime();
				deltaTime = (time - lastTime) / 1000000000.0f;
				lastTime = time;
				listener.render();
			}
		}

		Array<LifecycleListener> listeners = lifecycleListeners;
		synchronized (listeners) {
			for (LifecycleListener listener : listeners) {
				listener.pause();
				listener.dispose();
			}
		}
		listener.pause();
		listener.dispose();
	}

	public float getDeltaTime() {
		return deltaTime;
	}

	public float getTargetDelta() {
		return targetDelta;
	}

	@Override
	public ApplicationListener getApplicationListener() {
		return listener;
	}

	@Override
	public ServerGraphics getGraphics() {
		return graphics;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public Files getFiles() {
		return files;
	}

	@Override
	public Net getNet() {
		return net;
	}

	public void log(String tag, String message) {
		if (logLevel >= LOG_INFO) {
			System.out.println(tag + ": " + message);
		}
	}

	@Override
	public void log(String tag, String message, Exception exception) {
		if (logLevel >= LOG_INFO) {
			System.out.println(tag + ": " + message);
			exception.printStackTrace(System.out);
		}
	}

	@Override
	public void error(String tag, String message) {
		if (logLevel >= LOG_ERROR) {
			System.err.println(tag + ": " + message);
		}
	}

	@Override
	public void error(String tag, String message, Throwable exception) {
		if (logLevel >= LOG_ERROR) {
			System.err.println(tag + ": " + message);
			exception.printStackTrace(System.err);
		}
	}

	@Override
	public void debug(String tag, String message) {
		if (logLevel >= LOG_DEBUG) {
			System.out.println(tag + ": " + message);
		}
	}

	@Override
	public void debug(String tag, String message, Throwable exception) {
		if (logLevel >= LOG_DEBUG) {
			System.out.println(tag + ": " + message);
			exception.printStackTrace(System.out);
		}
	}

	@Override
	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public ApplicationType getType() {
		return ApplicationType.Desktop;
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override
	public long getJavaHeap() {
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	@Override
	public long getNativeHeap() {
		return getJavaHeap();
	}

	@Override
	public Preferences getPreferences(String name) {
		if (preferences.containsKey(name)) {
			return preferences.get(name);
		} else {
			Preferences prefs = new ServerPreferences(name);
			preferences.put(name, prefs);
			return prefs;
		}
	}

	@Override
	public Clipboard getClipboard() {
		return new ServerClipboard();
	}

	@Override
	public void postRunnable(Runnable runnable) {
		synchronized (runnables) {
			runnables.add(runnable);
			Gdx.graphics.requestRendering();
		}
	}

	@Override
	public void exit() {
		postRunnable(new Runnable() {
			@Override
			public void run() {
				running = false;
			}
		});
	}

	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		synchronized (lifecycleListeners) {
			lifecycleListeners.add(listener);
		}
	}

	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		synchronized (lifecycleListeners) {
			lifecycleListeners.removeValue(listener, true);
		}
	}

	public void stop() {
		running = false;
		try {
			mainLoopThread.join();
		} catch (Exception ex) {
		}
	}
}
