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
package com.greatmancode.tools.commands.bukkit;

import com.greatmancode.tools.commands.CommandHandler;
import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BukkitCommandReceiver implements CommandReceiver, CommandExecutor {
	private CommandHandler commandHandler;

	public BukkitCommandReceiver(CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		SubCommand subCommand = commandHandler.getCommand(command.getName());
		if (subCommand != null) {
			String subCommandValue = "";
			String[] newArgs;
			if (args.length <= 1) {
				newArgs = new String[0];
				subCommandValue = args[0];
			} else {
				newArgs = new String[args.length - 1];
				subCommandValue = args[0];
				System.arraycopy(args, 1, newArgs, 0, args.length - 1);
			}
			subCommand.execute(subCommandValue, commandSender.getName(), newArgs);
			return true;
		}
		return false;
	}
}
