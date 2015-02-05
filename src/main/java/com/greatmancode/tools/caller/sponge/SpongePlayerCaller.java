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

import com.greatmancode.tools.commands.CommandSender;
import com.greatmancode.tools.commands.ConsoleCommandSender;
import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.PlayerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.permission.PermissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class SpongePlayerCaller extends PlayerCaller {

    private SpongeLoader loader;
    public SpongePlayerCaller(ServerCaller caller) {
        super(caller);
        loader = ((SpongeLoader) caller.getLoader());
    }

    @Override
    public boolean checkPermission(CommandSender commandSender, String perm) {
        if (commandSender instanceof ConsoleCommandSender) {
            return true;
        }
        return loader.getGame().getServiceManager().provide(PermissionService.class).get().getUserSubjects().get(((com.greatmancode.tools.entities.Player)commandSender).getName()).hasPermission(perm);
    }

    @Override
    public void sendMessage(CommandSender commandSender, String message) {
        if (commandSender instanceof ConsoleCommandSender) {
            Logger.getLogger("GreatmancodeTools").info(message);
        } else {
            loader.getGame().getServer().get().getPlayer(((com.greatmancode.tools.entities.Player)commandSender).getUuid()).get().sendMessage(message);
        }
    }

    @Override
    public String getPlayerWorld(String playerName) {
        return loader.getGame().getServer().get().getPlayer(playerName).get().getWorld().getName();
    }

    @Override
    public String getPlayerWorld(UUID uuid) {
        return loader.getGame().getServer().get().getPlayer(uuid).get().getWorld().getName();
    }

    @Override
    public boolean isOnline(String playerName) {
        return false;
    }

    @Override
    public List<String> getOnlinePlayers() {
        List<String> playerList = new ArrayList<String>();
        for (Player p : loader.getGame().getServer().get().getOnlinePlayers()) {
            playerList.add(p.getName());
        }
        return playerList;
    }

    @Override
    public boolean isOp(String playerName) {
        return false;
    }

    @Override
    public UUID getUUID(String playerName) {
        return loader.getGame().getServer().get().getPlayer(playerName).get().getUniqueId();
    }
}
