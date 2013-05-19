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
package com.greatmancode.tools.configuration.canary;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.interfaces.Caller;
import com.greatmancode.tools.interfaces.CanaryLoader;

import net.canarymod.config.Configuration;

public class CanaryConfig extends Config {

	public CanaryConfig(File folder, String fileName, Caller caller, boolean create) {
		super(folder, fileName, caller, create);
	}

	@Override
	public int getInt(String path) {
		return Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).getInt(path);
	}

	@Override
	public long getLong(String path) {
		return Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).getLong(path);
	}

	@Override
	public double getDouble(String path) {
		return Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).getDouble(path);
	}

	@Override
	public String getString(String path) {
		return Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).getString(path);
	}

	@Override
	public boolean getBoolean(String path) {
		return Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).getBoolean(path);
	}

	@Override
	public void setValue(String path, Object value) {
		if (value instanceof Boolean) {
			Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).setBoolean(path, (Boolean) value);
		} else if (value instanceof Long) {
			Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).setLong(path, (Long) value);
		} else if (value instanceof Double) {
			Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).setDouble(path, (Double) value);
		} else if (value instanceof String) {
			Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).setString(path, (String) value);
		} else if (value instanceof Integer) {
			Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).setInt(path, (Integer) value);
		}
	}

	@Override
	public boolean has(String path) {
		return Configuration.getPluginConfig(((CanaryLoader)caller.getLoader()).getName()).containsKey(path);
	}

	@Override
	public Map<String, String> getStringMap(String path) {
		return Collections.EMPTY_MAP; //TODO: Fix this
	}
}
