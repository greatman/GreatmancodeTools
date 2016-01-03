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

public class Event {
    private boolean cancelled = false;

    protected String getEventName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getEventName() + " (" + this.getClass().getName() + ")";
    }

    /**
     * Set cancelled status. Events which wish to be cancellable should implement Cancellable and implement setCancelled as:
     * <pre>
     * public void setCancelled(boolean cancelled) {
     * 	super.setCancelled(cancelled);
     * }
     * </pre>
     *
     * @param cancelled True to cancel event
     */
    protected void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returning true will prevent calling.
     *
     * @return false if the event is propogating; events which do not implement Cancellable should never return true here.
     */
    public boolean isCancelled() {
        return cancelled;
    }
}
