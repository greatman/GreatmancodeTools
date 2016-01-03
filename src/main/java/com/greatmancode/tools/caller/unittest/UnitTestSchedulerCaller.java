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
package com.greatmancode.tools.caller.unittest;

import com.greatmancode.tools.interfaces.caller.SchedulerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;

public class UnitTestSchedulerCaller extends SchedulerCaller {
    public UnitTestSchedulerCaller(ServerCaller caller) {
        super(caller);
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int schedule(Runnable entry, long firstStart, long repeating, boolean async) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void cancelSchedule(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public int delay(Runnable entry, long start) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delay(Runnable entry, long start, boolean async) {
        // TODO Auto-generated method stub
        return 0;
    }
}
