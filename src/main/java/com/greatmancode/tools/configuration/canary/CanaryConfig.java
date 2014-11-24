/**
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2014, Greatman <http://github.com/greatman/>
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

import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.interfaces.CanaryLoader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import net.canarymod.config.Configuration;

import java.io.File;
import java.util.*;

public class CanaryConfig extends Config {
    public CanaryConfig(File folder, String fileName, ServerCaller serverCaller) {
        super(folder, fileName, serverCaller);
    }

    @Override
    public int getInt(String path) {
        return Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).getInt(path);
    }

    @Override
    public long getLong(String path) {
        return Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).getLong(path);
    }

    @Override
    public double getDouble(String path) {
        return Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).getDouble(path);
    }

    @Override
    public String getString(String path) {
        return Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).getString(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).getBoolean(path);
    }

    @Override
    public void setValue(String path, Object value) {
        if (value instanceof Boolean) {
            Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).setBoolean(path, (Boolean) value);
        } else if (value instanceof Long) {
            Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).setLong(path, (Long) value);
        } else if (value instanceof Double) {
            Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).setDouble(path, (Double) value);
        } else if (value instanceof String) {
            Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).setString(path, (String) value);
        } else if (value instanceof Integer) {
            Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).setInt(path, (Integer) value);
        }
        Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).save();
    }

    @Override
    public boolean has(String path) {
        return Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).containsKey(path);
    }

    @Override
    public Map<String, String> getStringMap(String path) {
        Map<String, String> result = new HashMap<String, String>();
        Map<String, String> map = Configuration.getPluginConfig((CanaryLoader) serverCaller.getLoader()).getPropertiesMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().contains(path)) {
                String key = entry.getKey().replace(path + ".", "");
                result.put(key, entry.getValue());
            }
        }
        return result;
    }

    @Override
    public List<String> getStringList(String path) {
        return Arrays.asList(Configuration.getPluginConfig((CanaryLoader)serverCaller.getLoader()).getStringArray(path));
    }
}
