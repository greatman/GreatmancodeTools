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
package com.greatmancode.tools.events.sponge.events;

import com.greatmancode.tools.caller.sponge.SpongeServerCaller;
import com.greatmancode.tools.interfaces.SpongeLoader;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.GameEvent;
import org.spongepowered.api.util.event.callback.CallbackList;

public class EconomyChangeEvent implements GameEvent {

    private Game game;
    private String account;
    private double amount;

    public EconomyChangeEvent(SpongeServerCaller caller, String account, double amount) {
        this.game = ((SpongeLoader)caller.getLoader()).getGame();
        this.account = account;
        this.amount = amount;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public CallbackList getCallbacks() {
        return new CallbackList();
    }
}
