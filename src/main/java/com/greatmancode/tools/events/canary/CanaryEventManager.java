/*
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
package com.greatmancode.tools.events.canary;

import java.util.HashMap;
import java.util.Map;

import com.greatmancode.tools.events.interfaces.ServerEventManager;
import com.greatmancode.tools.events.playerEvent.PlayerJoinEvent;
import com.greatmancode.tools.interfaces.CanaryLoader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

import net.canarymod.Canary;
import net.canarymod.plugin.PluginListener;

public class CanaryEventManager implements ServerEventManager {
	private Map<String, PluginListener> map = new HashMap<String, PluginListener>();

	public CanaryEventManager() {
		map.put(PlayerJoinEvent.class.getName(), new PlayerJoinEventListener());
	}

	public void eventRegistered(String event, ServerCaller serverCaller) {
		if (map.containsKey(event)) {
			Canary.hooks().registerListener(map.get(event), ((CanaryLoader) serverCaller.getLoader()));
		}
	}
}
