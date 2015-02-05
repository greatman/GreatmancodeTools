/**
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2015, Greatman <http://github.com/greatman/>
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

import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.configuration.ConfigurationManager;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
    private static final String MAIN_KEY = "language";
    private static final String SEPARATOR = ".";
    private Config languageFile = null;
    private Map<String, String> languageList = new HashMap<String, String>();

    public LanguageManager(ServerCaller serverCaller, File path, String fileName) {
        languageFile = new ConfigurationManager(serverCaller).loadFile(path, fileName);
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

    public String getString(String key) {
        return languageList.get(key);
    }
}
