/**
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2016, Greatman <http://github.com/greatman/>
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
package com.greatmancode.tools.caller.unittest;

import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.commands.interfaces.CommandReceiver;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.interfaces.Common;
import com.greatmancode.tools.interfaces.Loader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import com.greatmancode.tools.utils.ServicePriority;
import com.greatmancode.tools.utils.VaultEconomy;

import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Special serverCaller for unit tests.
 *
 * @author Greatman
 */
public class UnitTestServerCaller extends ServerCaller {
    public static final String worldName = "UnitTestWorld";
    public static final String worldName2 = "UnitTestWorld2";
    public static final int dir = new Random().nextInt(9999999);

    public UnitTestServerCaller(Loader loader) {
        super(loader);
        addPlayerCaller(new UnitTestPlayerCaller(this));
        addSchedulerCaller(new UnitTestSchedulerCaller(this));
    }

    @Override
    public void disablePlugin() {
    }

    @Override
    public String addColor(String message) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean worldExist(String worldName) {
        return worldName.equalsIgnoreCase(this.worldName) || worldName.equalsIgnoreCase(worldName2);
    }

    @Override
    public String getDefaultWorld() {
        return "UnitTestWorld";
    }

    @Override
    public File getDataFolder() {
        File file = new File("target","testfiles");
        file.mkdirs();
        return file;
    }

    @Override
    public void addCommand(String name, String help, SubCommand command) {

    }

    @Override
    public String getServerVersion() {
        return "UnitTest 1.0";
    }

    @Override
    public String getPluginVersion() {
        return "1.0";
    }

    @Override
    public String getPluginName() {
        return "UnitTestPlugin";
    }

    @Override
    public void loadLibrary(String path) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerPermission(String permissionNode) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isOnlineMode() {
        return true;
    }

    @Override
    public Logger getLogger() {
        return Logger.getLogger(this.getClass().getName());
    }

    @Override
    public void throwEvent(Event event) {
        //TODO: Something here
    }

    @Override
    public Common retrievePlugin(String name) {
        return null;
    }

    @Override
    public boolean isPluginEnabled(String name) {
        return false;
    }

    @Override
    public void setVaultEconomyHook(VaultEconomy instance, ServicePriority priority) {

    }
}
