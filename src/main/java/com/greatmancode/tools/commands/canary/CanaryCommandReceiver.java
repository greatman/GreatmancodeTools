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
package com.greatmancode.tools.commands.canary;

import com.greatmancode.tools.commands.CommandHandler;
import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.util.api.plugin.commands.CommandExecute;

import java.util.Collections;
import java.util.List;

public class CanaryCommandReceiver implements CommandReceiver, CommandExecute {
    private CommandHandler commandHandler;

    public CanaryCommandReceiver(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void execute(MessageReceiver messageReceiver, String[] args) {
        SubCommand subCommand = commandHandler.getCommand(args[0].replace("/", ""));
        if (subCommand != null) {
            String subCommandValue = "";
            String[] newArgs;
            if (args.length <= 2) {
                newArgs = new String[0];
                if (args.length == 2) {
                    subCommandValue = args[1];
                }
            } else {
                newArgs = new String[args.length - 2];
                subCommandValue = args[1];
                System.arraycopy(args, 2, newArgs, 0, args.length - 2);
            }
            com.greatmancode.tools.commands.CommandSender sender;
            if (messageReceiver instanceof Server) {
                sender = new com.greatmancode.tools.commands.ConsoleCommandSender();
            } else {
                Player p = (Player) messageReceiver;
                sender = new com.greatmancode.tools.entities.Player(p.getName(), p.getDisplayName(), p.getWorld().getName(), p.getUUID());
            }
            subCommand.execute(subCommandValue, sender, newArgs);
        }
    }

    @Override
    public List<String> tabComplete(MessageReceiver messageReceiver, String[] strings) {
        //TODO : Complete that
        return Collections.emptyList();
    }
}
