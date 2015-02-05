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
package com.greatmancode.tools.caller.canary;

import com.greatmancode.tools.commands.CommandSender;
import com.greatmancode.tools.commands.ConsoleCommandSender;
import com.greatmancode.tools.entities.Player;
import com.greatmancode.tools.interfaces.caller.PlayerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import net.canarymod.Canary;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CanaryPlayerCaller extends PlayerCaller {
    public CanaryPlayerCaller(ServerCaller caller) {
        super(caller);
    }

    @Override
    public boolean checkPermission(CommandSender commandSender, String perm) {
        if (commandSender instanceof ConsoleCommandSender) {
            return true;
        }
        return Canary.getServer().getPlayerFromUUID(((Player)commandSender).getUuid()).hasPermission(perm) || isOp(((Player)commandSender).getName());
    }

    @Override
    public void sendMessage(CommandSender playerName, String message) {
        if (playerName.equals("Console")) {
            Canary.getServer().message(getCaller().addColor(getCaller().getCommandPrefix() + message));
        } else {
            Canary.getServer().getPlayerFromUUID(((Player)playerName).getUuid()).message(getCaller().addColor(getCaller().getCommandPrefix() + message));
        }
    }

    @Override
    public String getPlayerWorld(String playerName) {
        return Canary.getServer().getPlayer(playerName).getWorld().getName();
    }

    @Override
    public String getPlayerWorld(UUID uuid) {
        return Canary.getServer().getPlayerFromUUID(uuid).getWorld().getName();
    }

    @Override
    public boolean isOnline(String playerName) {
        return Canary.getServer().getPlayer(playerName) != null;
    }

    @Override
    public List<String> getOnlinePlayers() {
        return Arrays.asList(Canary.getServer().getPlayerNameList());
    }

    @Override
    public boolean isOp(String playerName) {
        return Canary.ops().isOpped(playerName);
    }

    @Override
    public UUID getUUID(String playerName) {
        return null;
    }
}
