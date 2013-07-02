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
package com.greatmancode.tools.interfaces;

import com.greatmancode.tools.ServerType;
import com.greatmancode.tools.caller.bukkit.BukkitServerCaller;
import com.greatmancode.tools.configuration.bukkit.BukkitConfig;
import com.greatmancode.tools.events.EventManager;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.PluginClassLoader;

public class BukkitLoader extends JavaPlugin implements Loader {

	private Common common;
	private EventManager eventManager;

	@Override
	public void onEnable() {

		BukkitServerCaller bukkitCaller = new BukkitServerCaller(this);
		eventManager = new EventManager(bukkitCaller);
		BukkitConfig bukkitConfig = new BukkitConfig(this.getClass().getResourceAsStream("/loader.yml"), bukkitCaller);
		String mainClass = bukkitConfig.getString("main-class");
		try {
			Class<?> clazz = Class.forName(mainClass);
			if (Common.class.isAssignableFrom(clazz)) {
				common = (Common) clazz.newInstance();
				common.onEnable(bukkitCaller, getLogger());
			} else {
				getLogger().severe("The class " + mainClass + " is invalid!");
				this.getServer().getPluginManager().disablePlugin(this);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.getServer().getPluginManager().disablePlugin(this);
		} catch (InstantiationException e) {
			e.printStackTrace();
			this.getServer().getPluginManager().disablePlugin(this);
		} catch (IllegalAccessException e) {
			this.getServer().getPluginManager().disablePlugin(this);
		}
	}

	@Override
	public void onDisable() {
		common.onDisable();
	}

	@Override
	public ServerType getServerType() {
		return ServerType.BUKKIT;
	}

	/**
	 * Retrieve the PluginClassLoader of Bukkit
	 * @return The PluginClassLoader of Bukkit
	 */
	public PluginClassLoader getPluginClassLoader() {
		return (PluginClassLoader) this.getClassLoader();
	}

	public EventManager getEventManager() {
		return eventManager;
	}
}
