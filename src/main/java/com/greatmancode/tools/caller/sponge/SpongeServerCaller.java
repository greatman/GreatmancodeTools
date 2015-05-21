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
package com.greatmancode.tools.caller.sponge;

import com.google.common.base.Optional;
import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.interfaces.Common;
import com.greatmancode.tools.interfaces.Loader;
import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import com.greatmancode.tools.utils.ServicePriority;
import com.greatmancode.tools.utils.VaultEconomy;
import org.spongepowered.api.Game;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.source.ConsoleSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SpongeServerCaller extends ServerCaller {
    private String name, version;

    public SpongeServerCaller(SpongeLoader spongeLoader, String name, String version) {
        super(spongeLoader);
        addPlayerCaller(new SpongePlayerCaller(this));
        addSchedulerCaller(new SpongeSchedulerCaller(this));
        this.name = name;
        this.version = version;
    }

    @Override
    public void disablePlugin() {
        //TODO Impossible?
    }

    @Override
    public String addColor(String message) {
        return message;
    }

    @Override
    public boolean worldExist(String worldName) {
        return ((SpongeLoader)loader).getGame().getServer().getWorld(worldName) != null;
    }

    @Override
    public String getDefaultWorld() {
        return ((SpongeLoader)loader).getGame().getServer().getWorlds().iterator().next().getName();
    }

    @Override
    public File getDataFolder() {
        return new File(".");
    }

    @Override
    public void addCommand(String name, String help, final SubCommand subCommand) {
        CommandCallable command = new CommandCallable() {
            @Override
            public Optional<CommandResult> process(CommandSource source, String arguments) throws CommandException {
                String subCommandValue = "";
                String[] newArgs;
                String[] args = arguments.split(" ");
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
                String username = source.getName();
                if (source instanceof ConsoleSource) {
                    username = "console";
                }
                subCommand.execute(subCommandValue, username, newArgs);
                return Optional.of(CommandResult.success());
            }

            @Override
            public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
                List<String> list = new ArrayList<>();
                list.addAll(subCommand.getSubCommandKeys());
                return list;
            }

            @Override
            public boolean testPermission(CommandSource source) {
                return true;
            }

            @Override
            public Optional<Text> getShortDescription(CommandSource source) {
                return Optional.absent();
            }

            @Override
            public Optional<Text> getHelp(CommandSource source) {
                return Optional.absent();
            }

            @Override
            public Text getUsage(CommandSource source) {
                return Texts.of();
            }
        };
        ((SpongeLoader)loader).getGame().getCommandDispatcher().register(loader, command, name);
    }

    @Override
    public String getServerVersion() {
        return ((SpongeLoader)loader).getGame().getImplementationVersion();
    }

    @Override
    public String getPluginVersion() {
        return version;
    }

    @Override
    public String getPluginName() {
        return name;
    }

    @Override
    public void loadLibrary(String path) {

    }

    @Override
    public void registerPermission(String permissionNode) {
       //None
    }

    @Override
    public boolean isOnlineMode() {
        return true;
    }

    @Override
    public Logger getLogger() {
        return Logger.getLogger(getPluginName());
    }

    @Override
    public void throwEvent(Event event) {
        //Not used
       // Game game = ((SpongeLoader) loader).getGame();
        //game.getEventManager().post(event);
    }

    @Override
    public Common retrievePlugin(String name) {

        Game game = ((SpongeLoader)loader).getGame();
        return (Common) game.getPluginManager().getPlugin(name).get().getInstance();
    }

    @Override
    public void setVaultEconomyHook(VaultEconomy instance, ServicePriority priority) {

    }
}
