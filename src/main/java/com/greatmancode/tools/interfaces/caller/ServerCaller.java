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
package com.greatmancode.tools.interfaces.caller;

import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.interfaces.Common;
import com.greatmancode.tools.interfaces.Loader;
import com.greatmancode.tools.utils.ServicePriority;
import com.greatmancode.tools.utils.VaultEconomy;

import java.io.File;
import java.util.logging.Logger;

/**
 * Represents a server ServerCaller
 *
 * @author greatman
 */
public abstract class ServerCaller {
    private String commandPrefix = "";
    private PlayerCaller playerCaller;
    private SchedulerCaller schedulerCaller;
    protected final Loader loader;

    public ServerCaller(Loader loader) {
        this.loader = loader;
    }

    protected void addPlayerCaller(PlayerCaller caller) {
        if (this.playerCaller == null) {
            this.playerCaller = caller;
        }
    }

    protected void addSchedulerCaller(SchedulerCaller caller) {
        if (this.schedulerCaller == null) {
            this.schedulerCaller = caller;
        }
    }

    public Loader getLoader() {
        return loader;
    }

    public PlayerCaller getPlayerCaller() {
        return playerCaller;
    }

    public SchedulerCaller getSchedulerCaller() {
        return schedulerCaller;
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
     * Add color in a message
     *
     * @param message The message to add color in
     * @return The message with colors.
     */
    public abstract String addColor(String message);

    /**
     * Checks if a world exist.
     *
     * @param worldName The world name to check
     * @return True if the world exist. Else false.
     */
    public abstract boolean worldExist(String worldName);

    /**
     * Retrieve the default world of the server
     *
     * @return The default world name
     */
    public abstract String getDefaultWorld();

    /**
     * Get the data folder (Aka. the plugin folder)
     *
     * @return The data folder
     */
    public abstract File getDataFolder();

    /**
     * Add a command in the server
     *
     * @param name    The name of the command
     * @param help    The help line of the command
     * @param manager The manager that manage the command.
     */
    public abstract void addCommand(String name, String help, SubCommand command);

    /**
     * Retrieve the server version.
     *
     * @return The server version.
     */
    public abstract String getServerVersion();

    /**
     * Retrieve the plugin version.
     *
     * @return The plugin version.
     */
    public abstract String getPluginVersion();

    /**
     * Retrieve the plugin name.
     *
     * @return The plugin name.
     */
    public abstract String getPluginName();

    /**
     * Load a library.
     *
     * @param path The path to the .jar of the library.
     */
    public abstract void loadLibrary(String path);

    /**
     * Register a permission on the server.
     *
     * @param permissionNode The permission node to register.
     */
    public abstract void registerPermission(String permissionNode);

    /**
     * Gives if the server is in online mode or not.
     *
     * @return True if the server is in online mode. Else false.
     */
    public abstract boolean isOnlineMode();

    public abstract Logger getLogger();

    public abstract void throwEvent(Event event);

    public abstract Common retrievePlugin(String name);

    /**
     * Set the Vault Economy hook. Only working on Bukkit
     * @param instance The vault economy instance
     */
    public abstract void setVaultEconomyHook(VaultEconomy instance, ServicePriority priority);
}
