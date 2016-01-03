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
package com.greatmancode.tools.caller.bukkit;

import com.greatmancode.tools.interfaces.BukkitLoader;
import com.greatmancode.tools.interfaces.caller.SchedulerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

public class BukkitSchedulerCaller extends SchedulerCaller {
    private static final long TICK_LENGTH = 20L;

    public BukkitSchedulerCaller(ServerCaller caller) {
        super(caller);
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating) {
        return schedule(entry, firstStart, repeating, false);
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating, boolean async) {
        if (!async) {
            if (repeating == 0) {
                return ((BukkitLoader) getCaller().getLoader()).getServer().getScheduler().runTaskLater(((BukkitLoader) getCaller().getLoader()), entry, firstStart * TICK_LENGTH).getTaskId();
            } else {
                return ((BukkitLoader) getCaller().getLoader()).getServer().getScheduler().scheduleSyncRepeatingTask(((BukkitLoader) getCaller().getLoader()), entry, firstStart * TICK_LENGTH, repeating * TICK_LENGTH);
            }
        } else {
            if (repeating == 0) {
                return ((BukkitLoader) getCaller().getLoader()).getServer().getScheduler().runTaskLaterAsynchronously(((BukkitLoader) getCaller().getLoader()), entry, firstStart * TICK_LENGTH).getTaskId();
            } else {
                return ((BukkitLoader) getCaller().getLoader()).getServer().getScheduler().runTaskTimerAsynchronously(((BukkitLoader) getCaller().getLoader()), entry, firstStart * TICK_LENGTH, repeating * TICK_LENGTH).getTaskId();
            }
        }
    }

    @Override
    public void cancelSchedule(int id) {
        ((BukkitLoader) getCaller().getLoader()).getServer().getScheduler().cancelTask(id);
    }

    @Override
    public int delay(Runnable entry, long start) {
        return delay(entry, start, false);
    }

    @Override
    public int delay(Runnable entry, long start, boolean async) {
        if (!async) {
            return ((BukkitLoader) getCaller().getLoader()).getServer().getScheduler().scheduleSyncDelayedTask(((BukkitLoader) getCaller().getLoader()), entry, start * TICK_LENGTH);
        } else {
            return ((BukkitLoader) getCaller().getLoader()).getServer().getScheduler().runTaskLaterAsynchronously(((BukkitLoader) getCaller().getLoader()), entry, start * TICK_LENGTH).getTaskId();
        }
    }
}
