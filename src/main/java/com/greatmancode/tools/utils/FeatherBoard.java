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
package com.greatmancode.tools.utils;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import com.greatmancode.tools.interfaces.BukkitLoader;
import com.greatmancode.tools.interfaces.Loader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FeatherBoard {

    public static void registerPlaceHolder(Loader loader, String name, final FeatherBoardReplaceEvent event) {
        if (loader instanceof BukkitLoader) {
            if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
                PlaceholderAPI.registerPlaceholder((Plugin) loader, name, new PlaceholderReplacer() {
                    @Override
                    public String onPlaceholderReplace(PlaceholderReplaceEvent placeholderReplaceEvent) {
                        return event.getResult(placeholderReplaceEvent.getOfflinePlayer().getName(), placeholderReplaceEvent.isOnline());
                    }
                });
            }
        }
    }



        public abstract class FeatherBoardReplaceEvent {
            public abstract String getResult(String username, boolean isOnline);
        }
}
