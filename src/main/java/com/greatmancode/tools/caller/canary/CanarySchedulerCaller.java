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
package com.greatmancode.tools.caller.canary;

import com.greatmancode.tools.interfaces.CanaryLoader;
import com.greatmancode.tools.interfaces.caller.SchedulerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.ServerTaskManager;

public class CanarySchedulerCaller extends SchedulerCaller {
    public CanarySchedulerCaller(ServerCaller caller) {
        super(caller);
    }

    @Override
    public int schedule(final Runnable entry, long firstStart, long repeating) {

        if (repeating != 0) {
            ServerTaskManager.addTask(new ServerTask(((CanaryLoader) getCaller().getLoader()), repeating, true) {
                @Override
                public void run() {
                    entry.run();
                }
            });
        } else {
            ServerTaskManager.addTask(new ServerTask(((CanaryLoader) getCaller().getLoader()), firstStart, false) {
                @Override
                public void run() {
                    entry.run();
                }
            });
        }
        return 0;
    }

    @Override
    public int schedule(final Runnable entry, long firstStart, long repeating, boolean async) {
        if (repeating != 0) {
            ServerTaskManager.addTask(new ServerTask(((CanaryLoader) getCaller().getLoader()), repeating, true) {
                @Override
                public void run() {
                    entry.run();
                }
            });
        } else {
            ServerTaskManager.addTask(new ServerTask(((CanaryLoader) getCaller().getLoader()), firstStart, false) {
                @Override
                public void run() {
                    entry.run();
                }
            });
        }
        return 0;
    }

    @Override
    public void cancelSchedule(int id) {

    }

    @Override
    public int delay(final Runnable entry, long start) {

        ServerTaskManager.addTask(new ServerTask(((CanaryLoader) getCaller().getLoader()), start, false) {
            @Override
            public void run() {
                entry.run();
            }
        });
        return 0;
    }

    @Override
    public int delay(final Runnable entry, long start, boolean async) {
        ServerTaskManager.addTask(new ServerTask(((CanaryLoader) getCaller().getLoader()), start, false) {
            @Override
            public void run() {
                entry.run();
            }
        });
        return 0;
    }
}
