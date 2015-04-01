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
package com.greatmancode.tools.commands.sponge;

import com.google.common.base.Optional;
import com.greatmancode.tools.commands.CommandHandler;
import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.source.ConsoleSource;

import java.util.ArrayList;
import java.util.List;

public class SpongeCommandReceiver implements CommandCallable, CommandReceiver {

    private final CommandHandler commandHandler;

    public SpongeCommandReceiver(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    @Override
    public boolean call(CommandSource source, String arguments, List<String> parents) throws CommandException {
        String[] args = arguments.split("");
        SubCommand subCommand = commandHandler.getCommand(parents.get(0));
        if (subCommand != null) {
            String subCommandValue = "";
            String[] newArgs;
            if (args.length <= 1) {
                newArgs = new String[0];
                if (args.length != 0) {
                    subCommandValue = args[0];
                }
            } else {
                newArgs = new String[args.length - 1];
                subCommandValue = args[0];
                System.arraycopy(args, 1, newArgs, 0, args.length - 1);
            }
            if (source instanceof Player) {
                subCommand.execute(subCommandValue, ((Player) source).getName(), newArgs);
            } else if (source instanceof ConsoleSource) {
                subCommand.execute(subCommandValue, "CONSOLE", newArgs);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return true; //TODO: Always true, Plugins handle the perms
    }

    @Override
    public String getShortDescription(CommandSource source) {
        return null;
    }

    @Override
    public Text getHelp(CommandSource source) {
        return null;
    }

    @Override
    public String getUsage(CommandSource source) {
        return null;
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
        return new ArrayList<>();
    }
}
