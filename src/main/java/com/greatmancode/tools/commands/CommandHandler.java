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
package com.greatmancode.tools.commands;

import java.util.HashMap;
import java.util.Map;

import com.greatmancode.tools.caller.bukkit.BukkitCaller;
import com.greatmancode.tools.caller.spout.SpoutCaller;
import com.greatmancode.tools.commands.bukkit.BukkitCommandReceiver;
import com.greatmancode.tools.commands.interfaces.Command;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.commands.spout.SpoutCommandReceiver;
import com.greatmancode.tools.interfaces.Caller;

public class CommandHandler {

	private Caller caller;
	private CommandReceiver commandReceiver;
	private Map<String, SubCommand> commandList = new HashMap<String, SubCommand>();

	public CommandHandler(Caller caller) {
		this.caller = caller;
		if (this.caller instanceof BukkitCaller) {
			commandReceiver = new BukkitCommandReceiver(this);
		} else if (this.caller instanceof SpoutCaller) {
			commandReceiver = new SpoutCommandReceiver(this);
		}
	}

	public Caller getCaller() {
		return caller;
	}

	public void registerMainCommand(String name, SubCommand subCommand) {
		commandList.put(name, subCommand);
	}

	public SubCommand getCommand(String name) {
		return commandList.get(name);
	}

}
