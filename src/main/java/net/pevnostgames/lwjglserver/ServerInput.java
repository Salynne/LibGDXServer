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

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import net.pevnostgames.lwjglserver.exception.ServerInputException;

public class ServerInput implements Input {

	@Override
	public float getAccelerometerX() {
		throw new ServerInputException();
	}

	@Override
	public float getAccelerometerY() {
		throw new ServerInputException();
	}

	@Override
	public float getAccelerometerZ() {
		throw new ServerInputException();
	}

	@Override
	public int getX() {
		throw new ServerInputException();
	}

	@Override
	public int getX(int pointer) {
		throw new ServerInputException();
	}

	@Override
	public int getDeltaX() {
		throw new ServerInputException();
	}

	@Override
	public int getDeltaX(int pointer) {
		throw new ServerInputException();
	}

	@Override
	public int getY() {
		throw new ServerInputException();
	}

	@Override
	public int getY(int pointer) {
		throw new ServerInputException();
	}

	@Override
	public int getDeltaY() {
		throw new ServerInputException();
	}

	@Override
	public int getDeltaY(int pointer) {
		throw new ServerInputException();
	}

	@Override
	public boolean isTouched() {
		throw new ServerInputException();
	}

	@Override
	public boolean justTouched() {
		throw new ServerInputException();
	}

	@Override
	public boolean isTouched(int pointer) {
		throw new ServerInputException();
	}

	@Override
	public boolean isButtonPressed(int button) {
		throw new ServerInputException();
	}

	@Override
	public boolean isKeyPressed(int key) {
		throw new ServerInputException();
	}

	@Override
	public void getTextInput(TextInputListener listener, String title, String text) {
		throw new ServerInputException();
	}

	@Override
	public void getPlaceholderTextInput(TextInputListener listener, String title, String placeholder) {
		throw new ServerInputException();
	}

	@Override
	public void setOnscreenKeyboardVisible(boolean visible) {
		throw new ServerInputException();
	}

	@Override
	public void vibrate(int milliseconds) {
		throw new ServerInputException();
	}

	@Override
	public void vibrate(long[] pattern, int repeat) {
		throw new ServerInputException();
	}

	@Override
	public void cancelVibrate() {
		throw new ServerInputException();
	}

	@Override
	public float getAzimuth() {
		throw new ServerInputException();
	}

	@Override
	public float getPitch() {
		throw new ServerInputException();
	}

	@Override
	public float getRoll() {
		throw new ServerInputException();
	}

	@Override
	public void getRotationMatrix(float[] matrix) {
		throw new ServerInputException();
	}

	@Override
	public long getCurrentEventTime() {
		throw new ServerInputException();
	}

	@Override
	public void setCatchBackKey(boolean catchBack) {
		throw new ServerInputException();
	}

	@Override
	public void setCatchMenuKey(boolean catchMenu) {
		throw new ServerInputException();
	}

	@Override
	public void setInputProcessor(InputProcessor processor) {
		throw new ServerInputException();
	}

	@Override
	public InputProcessor getInputProcessor() {
		throw new ServerInputException();
	}

	@Override
	public boolean isPeripheralAvailable(Peripheral peripheral) {
		throw new ServerInputException();
	}

	@Override
	public int getRotation() {
		throw new ServerInputException();
	}

	@Override
	public Orientation getNativeOrientation() {
		throw new ServerInputException();
	}

	@Override
	public void setCursorCatched(boolean catched) {
		throw new ServerInputException();
	}

	@Override
	public boolean isCursorCatched() {
		throw new ServerInputException();
	}

	@Override
	public void setCursorPosition(int x, int y) {
		throw new ServerInputException();
	}
}
