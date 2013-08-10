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

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.File;

public class ServerFileHandle extends FileHandle {
	public ServerFileHandle(String fileName, Files.FileType type) {
		super(fileName, type);
	}

	public ServerFileHandle(File file, Files.FileType type) {
		super(file, type);
	}

	public FileHandle child(String name) {
		if (file.getPath().length() == 0) return new ServerFileHandle(new File(name), type);
		return new ServerFileHandle(new File(file, name), type);
	}

	public FileHandle sibling(String name) {
		if (file.getPath().length() == 0) throw new GdxRuntimeException("Cannot get the sibling of the root.");
		return new ServerFileHandle(new File(file.getParent(), name), type);
	}

	public FileHandle parent() {
		File parent = file.getParentFile();
		if (parent == null) {
			if (type == Files.FileType.Absolute)
				parent = new File("/");
			else
				parent = new File("");
		}
		return new ServerFileHandle(parent, type);
	}

	public File file() {
		if (type == Files.FileType.External) return new File(ServerFiles.externalPath, file.getPath());
		return file;
	}
}
