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
package com.greatmancode.tools.caller.sponge;

import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.SchedulerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

import java.util.concurrent.TimeUnit;

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
        if (async) {
            if (repeating > 0) {
                loader.getGame().getScheduler().createTaskBuilder().delay(firstStart, TimeUnit.MILLISECONDS).async().interval(repeating, TimeUnit.MILLISECONDS).execute(entry).submit(loader.getGame().getPluginManager().fromInstance(loader).get());
            } else {
                loader.getGame().getScheduler().createTaskBuilder().delay(firstStart, TimeUnit.MILLISECONDS).async().execute(entry).submit(loader.getGame().getPluginManager().fromInstance(loader).get());
            }
        } else {
            if (repeating > 0) {
                loader.getGame().getScheduler().createTaskBuilder().delay(firstStart, TimeUnit.MILLISECONDS).interval(repeating, TimeUnit.MILLISECONDS).execute(entry).submit(loader.getGame().getPluginManager().fromInstance(loader).get());
            } else {
                loader.getGame().getScheduler().createTaskBuilder().delay(firstStart, TimeUnit.MILLISECONDS).execute(entry).submit(loader.getGame().getPluginManager().fromInstance(loader).get());
            }
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
        schedule(entry,start,0,async);
        return 0;
    }
}
