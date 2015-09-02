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
package com.greatmancode.tools.configuration;

import com.greatmancode.tools.caller.bukkit.BukkitServerCaller;
import com.greatmancode.tools.caller.sponge.SpongeServerCaller;
import com.greatmancode.tools.caller.unittest.UnitTestServerCaller;
import com.greatmancode.tools.configuration.bukkit.BukkitConfig;
import com.greatmancode.tools.configuration.sponge.SpongeConfig;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

import java.io.File;

/**
 * Configuration Loader. Load the configuration with the Server configuration manager.
 *
 * @author greatman
 */
public class ConfigurationManager {
    private ServerCaller serverCaller;

    public ConfigurationManager(ServerCaller serverCaller) {
        this.serverCaller = serverCaller;
    }

    public Config loadFile(File folder, String fileName) {
        Config file = null;

        if (serverCaller instanceof BukkitServerCaller || serverCaller instanceof UnitTestServerCaller) {
            file = new BukkitConfig(folder, fileName, serverCaller);
        } else if (serverCaller instanceof SpongeServerCaller) {
            file = new SpongeConfig(folder, fileName, serverCaller);
        }
        return file;
    }
}
