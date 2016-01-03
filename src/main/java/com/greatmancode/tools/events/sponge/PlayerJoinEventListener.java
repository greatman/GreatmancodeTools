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
package com.greatmancode.tools.events.sponge;

import com.greatmancode.tools.entities.Player;
import com.greatmancode.tools.events.EventManager;
import com.greatmancode.tools.events.playerEvent.PlayerJoinEvent;
import org.spongepowered.api.event.Listener;

public class PlayerJoinEventListener {

    @Listener
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        com.greatmancode.tools.events.playerEvent.PlayerJoinEvent pEvent = new com.greatmancode.tools.events.playerEvent.PlayerJoinEvent(new Player(event.getP().getName(), event.getP().getDisplayName(), event.getP().getWorldName(), event.getP().getUuid()));
        EventManager.getInstance().callEvent(pEvent);
    }
}
