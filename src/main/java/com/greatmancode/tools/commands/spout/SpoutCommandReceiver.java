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
package com.greatmancode.tools.commands.spout;

import java.util.List;

import com.greatmancode.tools.commands.CommandHandler;
import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;

import org.spout.api.command.Command;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.Executor;
import org.spout.api.exception.CommandException;

public class SpoutCommandReceiver implements Executor, CommandReceiver {
	private CommandHandler commandHandler;

	public SpoutCommandReceiver(CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	@Override
	public void execute(CommandSource source, Command command, CommandArguments args) throws CommandException {
		SubCommand subCommand = commandHandler.getCommand(command.getName());
		if (subCommand != null) {
			List<String> realArgs = args.get();
			String subCommandValue = "";
			String[] newArgs;
			if (args.length() <= 1) {
				newArgs = new String[0];
				if (args.length() != 0) {
					subCommandValue = realArgs.get(0);
				}
			} else {
				newArgs = new String[args.length() - 1];
				subCommandValue = realArgs.get(0);
				for (int i = 1; i <= newArgs.length; i++) {
					newArgs[i - 1] = realArgs.get(i);
				}
			}
			subCommand.execute(subCommandValue, source.getName(), newArgs);
		}
	}
}