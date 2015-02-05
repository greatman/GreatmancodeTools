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
package com.greatmancode.tools.caller.bukkit;

import com.greatmancode.tools.commands.CommandSender;
import com.greatmancode.tools.commands.ConsoleCommandSender;
import com.greatmancode.tools.interfaces.BukkitLoader;
import com.greatmancode.tools.interfaces.caller.PlayerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BukkitPlayerCaller extends PlayerCaller {
    public BukkitPlayerCaller(ServerCaller caller) {
        super(caller);
    }

    @Override
    public boolean checkPermission(CommandSender commandSender, String perm) {
        boolean result;
        if (commandSender instanceof ConsoleCommandSender) {
            return true;
        } else {
            Player p = ((BukkitLoader) getCaller().getLoader()).getServer().getPlayer(((com.greatmancode.tools.entities.Player)commandSender).getUuid());
            return p != null ? (p.isOp() || p.hasPermission(perm)) : false;
        }
    }

    @Override
    public void sendMessage(CommandSender commandSender, String message) {
        if (commandSender instanceof ConsoleCommandSender) {
            Bukkit.getConsoleSender().sendMessage(getCaller().addColor(getCaller().getCommandPrefix() + message));
        } else {
            Player p = ((BukkitLoader) getCaller().getLoader()).getServer().getPlayer(((com.greatmancode.tools.entities.Player)commandSender).getUuid());
        }
    }

    @Override
    public String getPlayerWorld(String playerName) {
        Player p = ((BukkitLoader) getCaller().getLoader()).getServer().getPlayerExact(playerName);
        return p != null ? p.getWorld().getName() : "";
    }

    @Override
    public String getPlayerWorld(UUID uuid) {
        Player p = ((BukkitLoader) getCaller().getLoader()).getServer().getPlayer(uuid);
        return (p != null) ? p.getWorld().getName() : "";
    }

    @Override
    public boolean isOnline(String playerName) {
        return ((BukkitLoader) getCaller().getLoader()).getServer().getPlayerExact(playerName) != null;
    }

    @Override
    public List<String> getOnlinePlayers() {
        List<String> list = new ArrayList<String>();
        for (Player p : ((BukkitLoader) getCaller().getLoader()).getServer().getOnlinePlayers()) {
            list.add(p.getName());
        }
        return list;
    }

    @Override
    public boolean isOp(String playerName) {
        return ((BukkitLoader) getCaller().getLoader()).getServer().getOfflinePlayer(playerName).isOp();
    }

    @Override
    public UUID getUUID(String playerName) {
        return Bukkit.getPlayer(playerName).getUniqueId();
    }
}
