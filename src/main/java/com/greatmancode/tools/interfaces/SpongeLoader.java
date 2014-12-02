package com.greatmancode.tools.interfaces;

import com.greatmancode.tools.ServerType;
import com.greatmancode.tools.caller.sponge.SpongeServerCaller;
import com.greatmancode.tools.events.EventManager;
import com.greatmancode.tools.interfaces.sponge.SpongeGToolsPluginContainer;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.util.event.Subscribe;
import org.spongepowered.mod.SpongeMod;
import org.spongepowered.mod.plugin.SpongePluginContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

@Plugin(id = "GreatmancodeToolsLoader", name = "GreatmancodeToolsLoader", version = "1.0")
public class SpongeLoader implements Loader {

    private Game game;
    private EventManager eventManager;
    private Common common;

    @Subscribe
    public void preInitialisationEvent(PreInitializationEvent event) {
        game = event.getGame();
        onEnable();
    }

    @Subscribe
    public void onShutdown(ServerStoppingEvent event){
        onDisable();
    }

    @Override
    public void onEnable() {
        SpongeServerCaller serverCaller = new SpongeServerCaller(this);
        eventManager = new EventManager(serverCaller);
        InputStream is = this.getClass().getResourceAsStream("/loader.yml");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String mainClass = br.readLine();
            mainClass = mainClass.split("main-class:")[1].trim();

            Class<?> clazz = Class.forName(mainClass);

            if (Common.class.isAssignableFrom(clazz)) {
                common = (Common) clazz.newInstance();
                common.onEnable(serverCaller, serverCaller.getLogger());
                //Since it's sponge, we need a bit more data to be able to load properly.
                String name = br.readLine().split("name:")[1].trim();
                String version = br.readLine().split("version:")[1].trim();
                //SpongeMod.instance.registerPluginContainer(new SpongePluginContainer(name, name, version, common), name, common); //TODO Fix that
            } else {
                serverCaller.getLogger().severe("The class " + mainClass + " is invalid!");
            }
        } catch (IOException e) {
            serverCaller.getLogger().log(Level.SEVERE, "Unable to load the main class!", e);
        } catch (ClassNotFoundException e) {
            serverCaller.getLogger().log(Level.SEVERE, "Unable to load the main class!", e);
        } catch (InstantiationException e) {
            serverCaller.getLogger().log(Level.SEVERE, "Unable to load the main class!", e);
        } catch (IllegalAccessException e) {
            serverCaller.getLogger().log(Level.SEVERE, "Unable to load the main class!", e);
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public ServerType getServerType() {
        return ServerType.SPONGE;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public Common getCommon() {
        return common
    }

    public Game getGame() {
        return game;
    }
}
