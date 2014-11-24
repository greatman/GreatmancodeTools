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
package com.greatmancode.tools.caller.bukkit;

import com.greatmancode.tools.commands.bukkit.BukkitCommandReceiver;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.events.event.EconomyChangeEvent;
import com.greatmancode.tools.interfaces.BukkitLoader;
import com.greatmancode.tools.interfaces.Loader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

import java.io.File;
import java.util.logging.Logger;

/**
 * Server serverCaller for Craftbukkit
 *
 * @author greatman
 */
public class BukkitServerCaller extends ServerCaller {
    public BukkitServerCaller(Loader loader) {
        super(loader);
        addPlayerCaller(new BukkitPlayerCaller(this));
        addSchedulerCaller(new BukkitSchedulerCaller(this));
    }

    @Override
    public void disablePlugin() {
        ((BukkitLoader) loader).getPluginLoader().disablePlugin(((BukkitLoader) loader));
    }

    @Override
    public String addColor(String str) {
        String coloredString = str;
        coloredString = coloredString.replace("{{BLACK}}", ChatColor.BLACK.toString());
        coloredString = coloredString.replace("{{DARK_BLUE}}", ChatColor.DARK_BLUE.toString());
        coloredString = coloredString.replace("{{DARK_GREEN}}", ChatColor.DARK_GREEN.toString());
        coloredString = coloredString.replace("{{DARK_CYAN}}", ChatColor.DARK_AQUA.toString());
        coloredString = coloredString.replace("{{DARK_RED}}", ChatColor.DARK_RED.toString());
        coloredString = coloredString.replace("{{PURPLE}}", ChatColor.DARK_PURPLE.toString());
        coloredString = coloredString.replace("{{GOLD}}", ChatColor.GOLD.toString());
        coloredString = coloredString.replace("{{GRAY}}", ChatColor.GRAY.toString());
        coloredString = coloredString.replace("{{DARK_GRAY}}", ChatColor.DARK_GRAY.toString());
        coloredString = coloredString.replace("{{BLUE}}", ChatColor.BLUE.toString());
        coloredString = coloredString.replace("{{BRIGHT_GREEN}}", ChatColor.GREEN.toString());
        coloredString = coloredString.replace("{{CYAN}}", ChatColor.AQUA.toString());
        coloredString = coloredString.replace("{{RED}}", ChatColor.RED.toString());
        coloredString = coloredString.replace("{{PINK}}", ChatColor.LIGHT_PURPLE.toString());
        coloredString = coloredString.replace("{{YELLOW}}", ChatColor.YELLOW.toString());
        coloredString = coloredString.replace("{{WHITE}}", ChatColor.WHITE.toString());
        coloredString = coloredString.replace("{{OBFUSCATED}}", ChatColor.MAGIC.toString());
        coloredString = coloredString.replace("{{BOLD}}", ChatColor.BOLD.toString());
        coloredString = coloredString.replace("{{STRIKETHROUGH}}", ChatColor.STRIKETHROUGH.toString());
        coloredString = coloredString.replace("{{UNDERLINE}}", ChatColor.UNDERLINE.toString());
        coloredString = coloredString.replace("{{ITALIC}}", ChatColor.ITALIC.toString());
        coloredString = coloredString.replace("{{RESET}}", ChatColor.RESET.toString());
        coloredString = ChatColor.translateAlternateColorCodes('&', coloredString);
        return coloredString;
    }

    @Override
    public String getDefaultWorld() {
        return ((BukkitLoader) loader).getServer().getWorlds().get(0).getName();
    }

    @Override
    public boolean worldExist(String worldName) {
        return ((BukkitLoader) loader).getServer().getWorld(worldName) != null;
    }

    @Override
    public File getDataFolder() {
        return ((BukkitLoader) loader).getDataFolder();
    }

    @Override
    public void addCommand(String name, String help, CommandReceiver manager) {
        if (manager instanceof BukkitCommandReceiver) {
            ((BukkitLoader) loader).getCommand(name).setExecutor((BukkitCommandReceiver) manager);
        }
    }

    @Override
    public String getServerVersion() {
        return Bukkit.getBukkitVersion();
    }

    @Override
    public String getPluginVersion() {
        return ((BukkitLoader) loader).getDescription().getVersion();
    }

    @Override
    public String getPluginName() {
        return ((BukkitLoader) loader).getName();
    }

    @Override
    public void loadLibrary(String path) {
        throw new UnsupportedOperationException("Loading libraries are not supported on Bukkit.");
    }

    @Override
    public void registerPermission(String permissionNode) {
        if (permissionNode != null) {
            try {
                ((BukkitLoader) loader).getServer().getPluginManager().addPermission(new Permission(permissionNode));
            } catch (IllegalArgumentException e) {
                //Do nothing. We don't care if the permission was already registered.
            }
        }
    }

    @Override
    public boolean isOnlineMode() {
        return Bukkit.getServer().getOnlineMode();
    }

    @Override
    public Logger getLogger() {
        return Bukkit.getLogger();
    }

    @Override
    public void throwEvent(Event event) {
        if (event instanceof EconomyChangeEvent) {
            ((BukkitLoader) loader).getServer().getPluginManager().callEvent(new com.greatmancode.tools.events.bukkit.events.EconomyChangeEvent(((EconomyChangeEvent) event).getAccount(), ((EconomyChangeEvent) event).getAmount()));
        }
    }
}
