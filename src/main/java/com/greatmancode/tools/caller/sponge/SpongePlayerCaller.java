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
package com.greatmancode.tools.caller.sponge;

import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.PlayerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.permission.PermissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpongePlayerCaller extends PlayerCaller {

    private SpongeLoader loader;

    public SpongePlayerCaller(ServerCaller caller) {
        super(caller);

        loader = ((SpongeLoader) caller.getLoader());
    }

    @Override
    public boolean checkPermission(String playerName, String perm) {
        if (playerName.equals("console")) {
            return true;
        }
        UUID uuid = getUUID(playerName);
        return loader.getGame().getServiceManager().provide(PermissionService.class).get().getUserSubjects().get(uuid.toString()).hasPermission(perm);
    }

    @Override
    public void sendMessage(String playerName, String message) {
        if (playerName.equals("console")) {
            caller.getLogger().info(message);
            return;
        }
        loader.getGame().getServer().getPlayer(playerName).get().sendMessage(((SpongeServerCaller)getCaller()).addColorSponge(message));
    }

    @Override
    public String getPlayerWorld(String playerName) {
        return loader.getGame().getServer().getPlayer(playerName).get().getWorld().getName();
    }

    @Override
    public String getPlayerWorld(UUID uuid) {
        return loader.getGame().getServer().getPlayer(uuid).get().getWorld().getName();
    }

    @Override
    public boolean isOnline(String playerName) {
        return loader.getGame().getServer().getPlayer(playerName).isPresent();
    }

    @Override
    public List<String> getOnlinePlayers() {
        List<String> playerList = new ArrayList<>();
        for (Player p : loader.getGame().getServer().getOnlinePlayers()) {
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
        return loader.getGame().getServer().getPlayer(playerName).get().getUniqueId();
    }
}
