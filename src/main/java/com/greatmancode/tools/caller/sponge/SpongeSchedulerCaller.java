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
package com.greatmancode.tools.caller.sponge;

import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.SchedulerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

public class SpongeSchedulerCaller extends SchedulerCaller {

    private SpongeLoader loader;
    public SpongeSchedulerCaller(ServerCaller caller) {
        super(caller);
        loader = ((SpongeLoader) caller.getLoader());
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating) {
        return schedule(entry, firstStart, repeating, false);
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating, boolean async) {
        //TODO Async?
        if (repeating > 0) {
            loader.getGame().getScheduler().runRepeatingTaskAfter(loader.getGame().getPluginManager().fromInstance(loader).get(), entry, repeating, firstStart).get();
        } else {
            loader.getGame().getScheduler().runRepeatingTask(loader.getGame().getPluginManager().fromInstance(loader).get(), entry, repeating);
        }
        return 0;
    }

    @Override
    public void cancelSchedule(int id) {

    }

    @Override
    public int delay(Runnable entry, long start) {
        return delay(entry,start,false);
    }

    @Override
    public int delay(Runnable entry, long start, boolean async) {
        //TODO Async?
        loader.getGame().getScheduler().runTaskAfter(loader.getGame().getPluginManager().fromInstance(loader).get(), entry, start);
        return 0;
    }
}
