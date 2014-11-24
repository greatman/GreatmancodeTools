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
package com.greatmancode.tools.interfaces;

import com.greatmancode.tools.ServerType;
import com.greatmancode.tools.events.EventManager;

/**
 * A loader is called when the plugin is launched on the server.
 *
 * @author greatman
 */
public interface Loader {
    void onEnable();

    void onDisable();

    /**
     * Give the current server backend.
     *
     * @return The current server backend.
     */
    ServerType getServerType();

    EventManager getEventManager();

    /**
     * Retrieve the common class. Usually you would cast it to the plugin version.
     *
     * @return The common class
     */
    Common getCommon();
}