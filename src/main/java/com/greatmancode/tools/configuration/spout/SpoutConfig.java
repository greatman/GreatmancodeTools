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
/*
 * This file is part of Craftconomy3.
 *
 * Copyright (c) 2011-2013, Greatman <http://github.com/greatman/>
 *
 * Craftconomy3 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Craftconomy3 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Craftconomy3.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.tools.configuration.spout;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.interfaces.Caller;

import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.api.util.config.yaml.YamlConfiguration;

/**
 * This class handles YAML from Spout imports.
 */
public class SpoutConfig extends Config {
	private YamlConfiguration config = null;

	public SpoutConfig(InputStream is, Caller caller) {
		super(is, caller);
		config = new YamlConfiguration(is);
	}

	public SpoutConfig(File folder, String fileName, Caller caller, boolean create) {
		super(folder, fileName, caller, create);
		config = new YamlConfiguration(file);
		try {
			config.load();
		} catch (ConfigurationException e) {
			caller.getLogger().severe("Unable to load the configuration file! Message:" + e.getMessage());
		}
	}

	@Override
	public int getInt(String path) {
		return config.getNode(path).getInt();
	}

	@Override
	public long getLong(String path) {
		return config.getNode(path).getLong();
	}

	@Override
	public double getDouble(String path) {
		return config.getNode(path).getDouble();
	}

	@Override
	public String getString(String path) {
		return config.getNode(path).getString();
	}

	@Override
	public boolean getBoolean(String path) {
		return config.getNode(path).getBoolean();
	}

	@Override
	public void setValue(String path, Object value) {
		config.getNode(path).setValue(value);
		try {
			config.save();
		} catch (ConfigurationException e) {
			caller.getLogger().severe("Unable to save the file " + config.getFile().getName() + "! Message: " + e.getMessage());
		}
	}

	@Override
	public boolean has(String path) {
		return config.getNode(path).isAttached();
	}

	@Override
	public Map<String, String> getStringMap(String path) {
		Map<String, String> values = new HashMap<String, String>();
		Map<String, ConfigurationNode> list = config.getNode(path).getChildren();
		for (Map.Entry<String, ConfigurationNode> entry : list.entrySet()) {
			values.put(entry.getKey(), entry.getValue().getString());
		}
		return values;
	}
}
