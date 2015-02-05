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
package com.greatmancode.tools.interfaces.caller;

import com.greatmancode.tools.commands.CommandSender;

import java.util.List;
import java.util.UUID;

public abstract class PlayerCaller {
    protected final ServerCaller caller;

    public PlayerCaller(ServerCaller caller) {
        this.caller = caller;
    }

    public ServerCaller getCaller() {
        return caller;
    }

    /**
     * Check the permissions of a player
     *
     * @param commandSender The player name to check
     * @param perm       The permission node to check
     * @return True if the player have the permission. Else false (Always true for the Console)
     */
    public abstract boolean checkPermission(CommandSender commandSender, String perm);

    /**
     * Sends a message to a player
     *
     * @param commandSender The player name to send the message
     * @param message    The message to send
     */
    public abstract void sendMessage(CommandSender commandSender, String message);

    /**
     * Retrieve the world name that a player is currently in
     *
     * @param playerName The player name to retrieve the world
     * @return The world name the player is currently in. Returns "" when the player is offline
     */
    public abstract String getPlayerWorld(String playerName);


    public abstract String getPlayerWorld(UUID uuid);


    /**
     * Checks if a player is online
     *
     * @param playerName The player name
     * @return True if the player is online. Else false.
     */
    public abstract boolean isOnline(String playerName);

    /**
     * Retrieve a list of online players
     *
     * @return A list of all players online.
     */
    public abstract List<String> getOnlinePlayers();

    /**
     * Check if the user is a Operator.
     *
     * @param playerName The player name to check
     * @return True if the player is a OP else false.
     */
    public abstract boolean isOp(String playerName);

    /**
     * Retrieve the UUID of a player.
     * @param playerName The player name
     * @return The UUID of the player
     */
    public abstract UUID getUUID(String playerName);
}
