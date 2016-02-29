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
package com.greatmancode.tools.caller.bukkit;

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
    public boolean checkPermission(String playerName, String perm) {
        boolean result;
        Player p = ((BukkitLoader) getCaller().getLoader()).getServer().getPlayerExact(playerName);
        if (p != null) {
            result = p.isOp() || p.hasPermission(perm);
        } else {
            // It's the console
            result = true;
        }
        return result;
    }

    @Override
    public void sendMessage(String playerName, String message) {
        Player p = ((BukkitLoader) getCaller().getLoader()).getServer().getPlayerExact(playerName);
        if (p != null) {
            p.sendMessage(getCaller().addColor(getCaller().getCommandPrefix() + message));
        } else {
            ((BukkitLoader) getCaller().getLoader()).getServer().getConsoleSender().sendMessage(getCaller().addColor(getCaller().getCommandPrefix() + message));
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
        return Bukkit.getOfflinePlayer(playerName).getUniqueId();
    }
}
