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
package com.greatmancode.tools.configuration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.tools.caller.bukkit.BukkitCaller;
import com.greatmancode.tools.caller.canary.CanaryCaller;
import com.greatmancode.tools.caller.spout.SpoutCaller;
import com.greatmancode.tools.caller.unittest.UnitTestCaller;
import com.greatmancode.tools.configuration.bukkit.BukkitConfig;
import com.greatmancode.tools.configuration.canary.CanaryConfig;
import com.greatmancode.tools.configuration.spout.SpoutConfig;
import com.greatmancode.tools.interfaces.Caller;

/**
 * Configuration Loader. Load the configuration with the Server configuration manager.
 * @author greatman
 */
public class ConfigurationManager {
	private Caller caller;

	public ConfigurationManager(Caller caller) {
		this.caller = caller;
	}

	public Config loadFile(File folder, String fileName) {
		Config file = null;

		if (caller instanceof BukkitCaller || caller instanceof UnitTestCaller) {
			file = new BukkitConfig(folder, fileName, caller);
		} else if (caller instanceof SpoutCaller) {
			file = new SpoutConfig(folder, fileName, caller);
		} else if (caller instanceof CanaryCaller) {
			file = new CanaryConfig(folder, fileName, caller);
		}
		return file;
	}
}
