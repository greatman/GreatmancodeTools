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
package com.greatmancode.tools.configuration.bukkit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.interfaces.Caller;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * This class handles YAML files with the Bukkit imports.
 */
public class BukkitConfig extends Config {
	private final YamlConfiguration configFile;

	public BukkitConfig(File folder, String fileName, Caller caller, boolean create) {
		super(folder, fileName, caller, create);
		configFile = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public int getInt(String path) {
		return configFile.getInt(path);
	}

	@Override
	public long getLong(String path) {
		return configFile.getLong(path);
	}

	@Override
	public double getDouble(String path) {
		return configFile.getDouble(path);
	}

	@Override
	public String getString(String path) {
		return configFile.getString(path);
	}

	@Override
	public boolean getBoolean(String path) {
		return configFile.getBoolean(path);
	}

	@Override
	public void setValue(String path, Object value) {
		configFile.set(path, value);
		try {
			configFile.save(file);
		} catch (IOException e) {
			caller.getLogger().severe("Error while saving + " + file.getName() + ". Error: " + e.getMessage());
		}
	}

	@Override
	public boolean has(String path) {
		return configFile.contains(path);
	}

	@Override
	public Map<String,String> getStringMap(String path) {
		Map<String, String> values = new HashMap<String, String>();
		ConfigurationSection configurationSection = configFile.getConfigurationSection(path);
		if (configurationSection != null) {
			for (Map.Entry<String, Object> entry : configurationSection.getValues(false).entrySet()) {
				values.put(entry.getKey(), (String) entry.getValue());
			}
		}
		return values;
	}
}
