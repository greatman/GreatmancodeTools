/*
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2013, Greatman <http://github.com/greatman/>
 *
 * GreatmancodeTools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GreatmancodeTools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GreatmancodeTools.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.tools.language;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.configuration.ConfigurationManager;
import com.greatmancode.tools.interfaces.Caller;

public class LanguageManager {

	private static final String MAIN_KEY = "language";
	private static final String SEPARATOR = ".";
	private Config languageFile = null;
	private Map<String, String> languageList = new HashMap<String, String>();

	public LanguageManager(Caller caller, File path, String fileName) {
		languageFile = new ConfigurationManager(caller).loadFile(path, fileName, false, false);
		loadLanguage();
	}

	private void loadLanguage() {
		languageList = languageFile.getStringMap(MAIN_KEY);
	}

	public void addLanguageEntry(String key, String value) {
		if (!languageList.containsKey(key)) {
			languageFile.setValue(MAIN_KEY + SEPARATOR + key, value);
			loadLanguage();
		}

	}

	public String parse(String key, Object... args) {
		return String.format(languageList.get(key), args);
	}
}
