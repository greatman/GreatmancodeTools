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
package com.greatmancode.tools.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.tools.caller.bukkit.BukkitServerCaller;
import com.greatmancode.tools.caller.canary.CanaryServerCaller;
import com.greatmancode.tools.caller.spout.SpoutServerCaller;
import com.greatmancode.tools.caller.unittest.UnitTestServerCaller;
import com.greatmancode.tools.events.bukkit.BukkitEventManager;
import com.greatmancode.tools.events.canary.CanaryEventManager;
import com.greatmancode.tools.events.interfaces.EventHandler;
import com.greatmancode.tools.events.interfaces.Listener;
import com.greatmancode.tools.events.interfaces.ServerEventManager;
import com.greatmancode.tools.events.spout.SpoutEventManager;
import com.greatmancode.tools.events.unittest.UnitTestEventManager;
import com.greatmancode.tools.interfaces.Common;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

public class EventManager {
	private Map<String, ListenerRegistration> eventList = new HashMap<String, ListenerRegistration>();
	private static EventManager instance;
	private ServerEventManager eventManager;
	private ServerCaller serverCaller;

	public EventManager(ServerCaller serverCaller) {
		instance = this;
		this.serverCaller = serverCaller;

		if (serverCaller instanceof SpoutServerCaller) {
			eventManager = new SpoutEventManager();
		} else if (serverCaller instanceof BukkitServerCaller) {
			eventManager = new BukkitEventManager();
		} else if (serverCaller instanceof CanaryServerCaller) {
			eventManager = new CanaryEventManager();
		} else if (serverCaller instanceof UnitTestServerCaller) {
			eventManager = new UnitTestEventManager();
		}
	}

	public static EventManager getInstance() {
		return instance;
	}

	public Event callEvent(Event event) {
		if (eventList.containsKey(event.getClass().getName())) {
			eventList.get(event.getClass().getName()).callEvent(event);
		}
		return event;
	}

	protected void registerEvents(Listener listener) {
		if (listener != null) {
			internalRegisterEvents(listener);
		}
	}

	//TODO: Do something with the plugin var.
	public void registerEvents(Common commonInterface, Listener listener) {
		if (commonInterface != null && listener != null) {
			internalRegisterEvents(listener);
		}
	}

	private void internalRegisterEvents(Listener listener) {
		if (listener != null) {
			Method[] methods = listener.getClass().getMethods();
			for (Method method : methods) {
				if (method.getAnnotation(EventHandler.class) != null) {
					Class<?>[] parameters = method.getParameterTypes();
					if (parameters.length == 1) {
						for (Class<?> parameter : parameters) {
							if (Event.class.isAssignableFrom(parameter)) {
								eventManager.eventRegistered(((Class<Event>) parameter).getName(), serverCaller);
								if (eventList.containsKey(parameter.getName())) {
									eventList.get(parameter.getName()).addListener(listener, method);
								} else {
									ListenerRegistration registration = new ListenerRegistration();
									registration.addListener(listener, method);
									eventList.put(parameter.getName(), registration);
								}
							}
						}
					}
				}
			}
		}
	}
}
