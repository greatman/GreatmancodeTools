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
package com.greatmancode.tools.interfaces.caller;

public abstract class SchedulerCaller {
    protected final ServerCaller caller;

    public SchedulerCaller(ServerCaller caller) {
        this.caller = caller;
    }

    public ServerCaller getCaller() {
        return caller;
    }

    /**
     * Schedule a repeating task to be run in non-async mode.
     *
     * @param entry      The Runnable to be run.
     * @param firstStart When should the task be run (In seconds)
     * @param repeating  How much seconds to be waiting bewtween each repeats? (0 to disable)
     * @return the task ID
     */
    public abstract int schedule(Runnable entry, long firstStart, long repeating);

    /**
     * Schedule a repeating task to be run.
     *
     * @param entry      The Runnable to be run.
     * @param firstStart When should the task be run (In seconds)
     * @param repeating  How much seconds to be waiting bewtween each repeats? (0 to disable)
     * @param async      Should the task be async? (Threaded)
     * @return the task ID
     */
    public abstract int schedule(Runnable entry, long firstStart, long repeating, boolean async);

    /**
     * Cancel a current scheduled task
     *
     * @param id The task ID.
     */
    public abstract void cancelSchedule(int id);

    /**
     * Delay a task
     *
     * @param entry The task to delay
     * @param start When should the task be started? (In seconds)
     * @return The task ID
     */
    public abstract int delay(Runnable entry, long start);

    /**
     * Delay a task
     *
     * @param entry The task to delay
     * @param start When should the task be started? (In seconds)
     * @param async Should the task be Async? (Threaded)
     * @return The task ID
     */
    public abstract int delay(Runnable entry, long start, boolean async);
}
