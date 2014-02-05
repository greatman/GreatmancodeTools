/*
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
package com.greatmancode.tools.caller.spout;

import com.greatmancode.tools.interfaces.SpoutLoader;
import com.greatmancode.tools.interfaces.caller.SchedulerCaller;
import org.spout.api.scheduler.TaskPriority;

import java.util.concurrent.TimeUnit;

public class SpoutSchedulerCaller extends SchedulerCaller {
    public SpoutSchedulerCaller(SpoutServerCaller caller) {
        super(caller);
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating) {
        return schedule(entry, firstStart, repeating, false);
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating, boolean async) {
        //TODO: Spout don't have the Async anymore for some reasons..
        //if (!async) {
        return ((SpoutLoader) getCaller().getLoader()).getEngine().getScheduler().scheduleSyncRepeatingTask(getCaller().getLoader(), entry, TimeUnit.MILLISECONDS.convert(firstStart, TimeUnit.SECONDS), TimeUnit.MILLISECONDS.convert(repeating, TimeUnit.SECONDS), TaskPriority.NORMAL).getTaskId();
        //} else {
        //return ((SpoutLoader)loader).getEngine().getScheduler().scheduleAsyncRepeatingTask(loader, entry, TimeUnit.MILLISECONDS.convert(firstStart, TimeUnit.SECONDS), TimeUnit.MILLISECONDS.convert(repeating, TimeUnit.SECONDS), TaskPriority.NORMAL);
        //}
    }

    @Override
    public void cancelSchedule(int id) {
        ((SpoutLoader) getCaller().getLoader()).getEngine().getScheduler().cancelTask(id);
    }

    @Override
    public int delay(Runnable entry, long start) {
        return delay(entry, start, false);
    }

    @Override
    public int delay(Runnable entry, long start, boolean async) {
        if (!async) {
            return ((SpoutLoader) getCaller().getLoader()).getEngine().getScheduler().scheduleSyncDelayedTask(getCaller().getLoader(), entry, TimeUnit.MILLISECONDS.convert(start, TimeUnit.SECONDS), TaskPriority.NORMAL).getTaskId();
        } else {
            return ((SpoutLoader) getCaller().getLoader()).getEngine().getScheduler().scheduleAsyncDelayedTask(getCaller().getLoader(), entry, TimeUnit.MILLISECONDS.convert(start, TimeUnit.SECONDS), TaskPriority.NORMAL).getTaskId();
        }
    }
}
