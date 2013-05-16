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
package com.greatmancode.tools.utils;

import java.io.File;
import java.io.IOException;

import com.greatmancode.tools.caller.bukkit.BukkitCaller;
import com.greatmancode.tools.caller.spout.SpoutCaller;
import com.greatmancode.tools.interfaces.Caller;

public class Metrics extends org.mcstats.Metrics {
	private Caller caller;

	public Metrics(String pluginName, String pluginVersion, Caller caller) throws IOException {
		super(pluginName, pluginVersion);
		this.caller = caller;
	}

	@Override
	public String getFullServerVersion() {
		if (caller instanceof SpoutCaller) {
			return "Spout " + caller.getServerVersion();
		} else if (caller instanceof BukkitCaller) {
			return caller.getServerVersion();
		}
		return "UNKNOWN";
	}

	@Override
	public int getPlayersOnline() {
		return caller.getOnlinePlayers().size();
	}

	@Override
	public File getConfigFile() {
		return new File(new File(caller.getDataFolder().getParentFile(), "PluginMetrics"), "config.yml");
	}
}
