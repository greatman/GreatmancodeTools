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
package com.greatmancode.tools.events;

import com.greatmancode.tools.events.event.EconomyChangeEvent;
import com.greatmancode.tools.events.interfaces.EventHandler;
import com.greatmancode.tools.events.interfaces.Listener;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

public class EventListener implements Listener {
    private ServerCaller serverCaller;

    public EventListener(ServerCaller serverCaller) {
        this.serverCaller = serverCaller;
    }

    @EventHandler
    public void onEconomyChange(EconomyChangeEvent event) {
        serverCaller.throwEvent(event);
    }
}
