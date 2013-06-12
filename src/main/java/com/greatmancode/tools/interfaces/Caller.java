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

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.events.Event;

/**
 * Represents a server Caller
 * @author greatman
 */
public abstract class Caller {
	private String commandPrefix = "";
	protected final Loader loader;

	public Caller(Loader loader) {
		this.loader = loader;
	}

	public Loader getLoader() {
		return loader;
	}

	/**
	 * Disable the plugin
	 */
	public abstract void disablePlugin();

	public void setCommandPrefix(String prefix) {
		commandPrefix = prefix;
	}

	public String getCommandPrefix() {
		return commandPrefix;
	}

	/**
	 * Check the permissions of a player
	 * @param playerName The player name to check
	 * @param perm The permission node to check
	 * @return True if the player have the permission. Else false (Always true for the Console)
	 */
	public abstract boolean checkPermission(String playerName, String perm);

	/**
	 * Sends a message to a player
	 * @param playerName The player name to send the message
	 * @param message The message to send
	 */
	public abstract void sendMessage(String playerName, String message);

	/**
	 * Retrieve the world name that a player is currently in
	 * @param playerName The player name to retrieve the world
	 * @return The world name the player is currently in. Returns "" when the player is offline
	 */
	public abstract String getPlayerWorld(String playerName);

	/**
	 * Checks if a player is online
	 * @param playerName The player name
	 * @return True if the player is online. Else false.
	 */
	public abstract boolean isOnline(String playerName);

	/**
	 * Add color in a message
	 * @param message The message to add color in
	 * @return The message with colors.
	 */
	public abstract String addColor(String message);

	/**
	 * Checks if a world exist.
	 * @param worldName The world name to check
	 * @return True if the world exist. Else false.
	 */
	public abstract boolean worldExist(String worldName);

	/**
	 * Retrieve the default world of the server
	 * @return The default world name
	 */
	public abstract String getDefaultWorld();

	/**
	 * Get the data folder (Aka. the plugin folder)
	 * @return The data folder
	 */
	public abstract File getDataFolder();

	/**
	 * Schedule something to be run each X seconds.
	 *
	 * @param entry the runnable class
	 * @param firstStart When we should run this class first?
	 * @param repeating What is the interval to be run at? (In seconds)
	 */
	/**
	 * Schedule a repeating task to be run in non-async mode.
	 * @param entry The Runnable to be run.
	 * @param firstStart When should the task be run (In seconds)
	 * @param repeating How much seconds to be waiting bewtween each repeats? (0 to disable)
	 * @return the task ID
	 */
	public abstract int schedule(Runnable entry, long firstStart, long repeating);

	/**
	 * Schedule a repeating task to be run.
	 * @param entry The Runnable to be run.
	 * @param firstStart When should the task be run (In seconds)
	 * @param repeating How much seconds to be waiting bewtween each repeats? (0 to disable)
	 * @param async Should the task be async? (Threaded)
	 * @return the task ID
	 */
	public abstract int schedule(Runnable entry, long firstStart, long repeating, boolean async);

	/**
	 * Cancel a current scheduled task
	 * @param id The task ID.
	 */
	public abstract void cancelSchedule(int id);

	/**
	 * Delay a task
	 * @param entry The task to delay
	 * @param start When should the task be started? (In seconds)
	 * @return The task ID
	 */
	public abstract int delay(Runnable entry, long start);

	/**
	 * Delay a task
	 * @param entry The task to delay
	 * @param start When should the task be started? (In seconds)
	 * @param async Should the task be Async? (Threaded)
	 * @return The task ID
	 */
	public abstract int delay(Runnable entry, long start, boolean async);

	/**
	 * Retrieve a list of online players
	 * @return A list of all players online.
	 */
	public abstract List<String> getOnlinePlayers();

	/**
	 * Add a command in the server
	 * @param name The name of the command
	 * @param help The help line of the command
	 * @param manager The manager that manage the command.
	 */
	public abstract void addCommand(String name, String help, CommandReceiver manager);

	/**
	 * Retrieve the server version.
	 * @return The server version.
	 */
	public abstract String getServerVersion();

	/**
	 * Retrieve the plugin version.
	 * @return The plugin version.
	 */
	public abstract String getPluginVersion();

	/**
	 * Check if the user is a Operator.
	 * @param playerName The player name to check
	 * @return True if the player is a OP else false.
	 */
	public abstract boolean isOp(String playerName);

	/**
	 * Load a library.
	 * @param path The path to the .jar of the library.
	 */
	public abstract void loadLibrary(String path);

	/**
	 * Register a permission on the server.
	 * @param permissionNode The permission node to register.
	 */
	public abstract void registerPermission(String permissionNode);

	/**
	 * Gives if the server is in online mode or not.
	 * @return True if the server is in online mode. Else false.
	 */
	public abstract boolean isOnlineMode();

	public abstract Logger getLogger();

	public abstract void throwEvent(Event event);
}
