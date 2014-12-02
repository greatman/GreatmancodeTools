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
