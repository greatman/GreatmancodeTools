/**
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2016, Greatman <http://github.com/greatman/>
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
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpongeConfig extends Config {

    private CommentedConfigurationNode file;
    private HoconConfigurationLoader loader;
    public SpongeConfig(InputStream is, ServerCaller serverCaller) {
        super(is, serverCaller);
        //No can't do.
    }

    public SpongeConfig(File folder, String fileName, ServerCaller serverCaller) {
        super(folder, fileName, serverCaller);
        try {
            loader = HoconConfigurationLoader.builder().setFile(new File(folder, fileName)).build();
            file = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInt(String path) {
        return file.getNode(path).getInt();
    }

    @Override
    public long getLong(String path) {
        return file.getNode(path).getLong();
    }

    @Override
    public double getDouble(String path) {
        return file.getNode(path).getDouble();
    }

    @Override
    public String getString(String path) {
        return file.getNode(path).getString();
    }

    @Override
    public boolean getBoolean(String path) {
        return file.getNode(path).getBoolean();
    }

    @Override
    public void setValue(String path, Object value) {
        file.getNode(path).setValue(value);
        try {
            loader.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean has(String path) {
        return !file.getNode(path).isVirtual();
    }

    @Override
    public Map<String, String> getStringMap(String path) {
        Map<String, String> map = new HashMap<>();
        Set<? extends Map.Entry<Object, ? extends CommentedConfigurationNode>> childrens = path.equals("") ? file.getChildrenMap().entrySet(): file.getNode(path).getChildrenMap().entrySet();
        for (Map.Entry<Object, ? extends CommentedConfigurationNode> entry : childrens) {
            map.put(entry.getKey().toString(), entry.getValue().getString());
        }
        return map;
    }

    @Override
    public List<String> getStringList(String path) {
        return null;
    }
}
