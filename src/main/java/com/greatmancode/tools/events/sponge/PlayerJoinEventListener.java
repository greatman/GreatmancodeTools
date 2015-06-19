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
package com.greatmancode.tools.events.sponge;

import com.greatmancode.tools.entities.Player;
import com.greatmancode.tools.events.EventManager;
import org.spongepowered.api.data.manipulator.DisplayNameData;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;

public class PlayerJoinEventListener {

    @Subscribe
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        com.greatmancode.tools.events.playerEvent.PlayerJoinEvent pEvent = new com.greatmancode.tools.events.playerEvent.PlayerJoinEvent(new Player(event.getUser().getName(), event.getUser().getOrCreate(DisplayNameData.class).get().getDisplayName().toString(), event.getUser().getWorld().getName(), null));
        EventManager.getInstance().callEvent(pEvent);
    }
}
