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
package com.greatmancode.tools.commands.interfaces;

import com.greatmancode.tools.commands.CommandSender;

public abstract class CommandExecutor implements Command {
    /**
     * The execution of the command
     *
     * @param sender The sender of the command
     * @param args   A String array of all the arguments
     */
    public abstract void execute(CommandSender sender, String[] args);

    /**
     * Returns a usage/help line about the command
     *
     * @return A string containing the usage/help about the command.
     */
    public abstract String help();

    /**
     * The maximum number of arguments that this command take
     *
     * @return The maximum number of arguments
     */
    public abstract int maxArgs();

    /**
     * The minimum number of arguments this command take
     *
     * @return The minimum number of arguments
     */
    public abstract int minArgs();

    /**
     * State if this command is for Players only
     *
     * @return True if the command is for player only else false.
     */
    public abstract boolean playerOnly();

    /**
     * Returns the permission node of this command.
     *
     * @return The permission node of this command.
     */
    public abstract String getPermissionNode();
}
