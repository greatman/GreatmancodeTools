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
package com.greatmancode.tools.caller.sponge;

import com.google.common.base.Optional;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.commands.sponge.SpongeCommandReceiver;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.interfaces.Loader;
import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SpongeServerCaller extends ServerCaller {


    public SpongeServerCaller(Loader loader) {
        super(loader);
        addPlayerCaller(new SpongePlayerCaller(this));
        addSchedulerCaller(new SpongeSchedulerCaller(this));
    }

    @Override
    public void disablePlugin() {
        //TODO Impossible?
    }

    @Override
    public String addColor(String message) {
        return null;
    }

    @Override
    public boolean worldExist(String worldName) {
        return ((SpongeLoader)loader).getGame().getServer().get().getWorld(worldName) != null;
    }

    @Override
    public String getDefaultWorld() {
        return ((SpongeLoader)loader).getGame().getServer().get().getWorlds().iterator().next().getName();
    }

    @Override
    public File getDataFolder() {
        return null;
    }

    @Override
    public void addCommand(String name, String help, CommandReceiver manager) {
        ((SpongeLoader)loader).getGame().getCommandDispatcher().register(loader, ((SpongeCommandReceiver)manager), name);
    }

    @Override
    public String getServerVersion() {
        return ((SpongeLoader)loader).getGame().getImplementationVersion();
    }

    @Override
    public String getPluginVersion() {
        return null;
    }

    @Override
    public String getPluginName() {
        return null;
    }

    @Override
    public void loadLibrary(String path) {

    }

    @Override
    public void registerPermission(String permissionNode) {
       //TODO how?
    }

    @Override
    public boolean isOnlineMode() {
        return false;
    }

    @Override
    public Logger getLogger() {
        return Logger.getLogger(getPluginName());
    }

    @Override
    public void throwEvent(Event event) {

    }
}
