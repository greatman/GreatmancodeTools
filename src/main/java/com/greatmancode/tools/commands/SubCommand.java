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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.greatmancode.tools.commands.interfaces.Command;
import com.greatmancode.tools.commands.interfaces.CommandExecutor;

public class SubCommand implements Command {
	private Map<String, Command> commandList = new HashMap<String, Command>();
	private CommandHandler commandHandler;
	private SubCommand parent;
	private String name;
	private int level;

	public SubCommand(String name, CommandHandler commandHandler, SubCommand parent, int level) {
		this.name = name;
		this.commandHandler = commandHandler;
		this.parent = parent;
		this.level = level;
	}
	public SubCommand(String name, CommandHandler commandHandler, SubCommand parent) {
		this(name, commandHandler, parent, 0);
	}

	public void addCommand(String name, Command command) {
		commandList.put(name, command);
		if (command instanceof CommandExecutor) {
			commandHandler.getCaller().registerPermission(((CommandExecutor) command).getPermissionNode());
		}
	}

	public boolean commandExist(String name) {
		return commandList.containsKey(name);
	}

	public void execute(String command, String sender, String[] args) {
		if (level <= commandHandler.getLevel()) {
			if (commandExist(command)) {
				Command entry = commandList.get(command);
				if (entry instanceof CommandExecutor) {
					CommandExecutor cmd = ((CommandExecutor) entry);
					if (commandHandler.getCaller().checkPermission(sender, cmd.getPermissionNode())) {
						if (args.length >= cmd.minArgs() && args.length <= cmd.maxArgs()) {
							cmd.execute(sender, args);
						} else {
							commandHandler.getCaller().sendMessage(sender, cmd.help());
						}
					} else {
						commandHandler.getCaller().sendMessage(sender, "{{DARK_RED}}Permission denied!");
					}
				} else if (entry instanceof SubCommand) {
					SubCommand subCommand = (SubCommand) entry;

					String subSubCommand = "";
					if (args.length != 0) {
						subSubCommand = args[0];
					}

					if (subCommand.commandExist(subSubCommand)) {
						String[] newArgs;
						if (args.length == 0) {
							newArgs = args;
						} else {
							newArgs = new String[args.length - 1];
							System.arraycopy(args, 1, newArgs, 0, args.length - 1);
						}
						((SubCommand) entry).execute(subSubCommand, sender, newArgs);
					} else {
						commandHandler.getCaller().sendMessage(sender, "{{DARK_GREEN}}========= {{WHITE}}Help {{DARK_GREEN}}========");
						for (Map.Entry<String, Command> iteratorEntry : commandList.entrySet()) {
							if (iteratorEntry instanceof CommandExecutor) {
								CommandExecutor cmd = ((CommandExecutor) iteratorEntry.getValue());
								if (commandHandler.getCaller().checkPermission(sender, cmd.getPermissionNode())) {
									commandHandler.getCaller().sendMessage(sender, cmd.help());
								}
							} else {
								String subCommandResult = "";
								SubCommand subCmd = this;
								while (subCmd.parent != null) {
									subCommandResult = subCmd.parent.name + "" + subCommandResult;
								}
								subCommandResult = "/" + subCommandResult + " " + name;
								commandHandler.getCaller().sendMessage(sender, subCommandResult);
							}
						}
					}
				}
			}
		} else {
			commandHandler.getCaller().sendMessage(sender, commandHandler.getWrongLevelMsg());
		}
	}

	public String getSubCommandList() {
		return Arrays.toString(commandList.keySet().toArray());
	}
}
