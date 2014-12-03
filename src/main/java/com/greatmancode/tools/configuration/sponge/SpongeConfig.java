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
package com.greatmancode.tools.configuration.sponge;

import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import com.typesafe.config.ConfigValueFactory;
import org.spongepowered.api.util.config.ConfigFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class SpongeConfig extends Config {

    private ConfigFile file;
    public SpongeConfig(InputStream is, ServerCaller serverCaller) {
        super(is, serverCaller);
        //No can't do.
    }

    public SpongeConfig(File folder, String fileName, ServerCaller serverCaller) {
        super(folder, fileName, serverCaller);
        file = ConfigFile.parseFile(new File(folder, fileName));
    }

    @Override
    public int getInt(String path) {
        return file.getInt(path);
    }

    @Override
    public long getLong(String path) {
        return file.getLong(path);
    }

    @Override
    public double getDouble(String path) {
        return file.getDouble(path);
    }

    @Override
    public String getString(String path) {
        return file.getString(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return file.getBoolean(path);
    }

    @Override
    public void setValue(String path, Object value) {
        file = (ConfigFile) file.withValue(path, ConfigValueFactory.fromAnyRef(value));
        file.save(false);
    }

    @Override
    public boolean has(String path) {
        return file.hasPath(path);
    }

    @Override
    public Map<String, String> getStringMap(String path) {
        return null;
    }

    @Override
    public List<String> getStringList(String path) {
        return file.getStringList(path);
    }
}
