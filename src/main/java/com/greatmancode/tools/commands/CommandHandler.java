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
package com.greatmancode.tools.commands;

import com.greatmancode.tools.caller.bukkit.BukkitServerCaller;
import com.greatmancode.tools.caller.canary.CanaryServerCaller;
import com.greatmancode.tools.commands.bukkit.BukkitCommandReceiver;
import com.greatmancode.tools.commands.canary.CanaryCommandReceiver;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private ServerCaller serverCaller;
    private CommandReceiver commandReceiver;
    private Map<String, SubCommand> commandList = new HashMap<String, SubCommand>();
    private int currentLevel = 0;
    private String wrongLevelMsg = "Wrong level!";

    public CommandHandler(ServerCaller serverCaller) {
        this.serverCaller = serverCaller;
        if (this.serverCaller instanceof BukkitServerCaller) {
            commandReceiver = new BukkitCommandReceiver(this);
        } else if (this.serverCaller instanceof CanaryServerCaller) {
            commandReceiver = new CanaryCommandReceiver(this);
        }
    }

    public ServerCaller getServerCaller() {
        return serverCaller;
    }

    public void setLevel(int level) {
        currentLevel = level;
    }

    /**
     * Current Command Handler level. Level allows you to block certain commands.
     *
     * @return The current level
     */
    public int getLevel() {
        return currentLevel;
    }

    public String getWrongLevelMsg() {
        return wrongLevelMsg;
    }

    public void setWrongLevelMsg(String msg) {
        wrongLevelMsg = msg;
    }

    public CommandHandler registerMainCommand(String name, SubCommand subCommand) {
        commandList.put(name, subCommand);
        serverCaller.addCommand(name, "", commandReceiver);
        return this;
    }

    public SubCommand getCommand(String name) {
        return commandList.get(name);
    }
}
