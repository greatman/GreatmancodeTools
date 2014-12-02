package com.greatmancode.tools.caller.sponge;

import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.PlayerCaller;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.service.permission.PermissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpongePlayerCaller extends PlayerCaller {

    private SpongeLoader loader;
    public SpongePlayerCaller(ServerCaller caller) {
        super(caller);
        loader = ((SpongeLoader) caller.getLoader());
    }

    @Override
    public boolean checkPermission(String playerName, String perm) {
        return loader.getGame().getServiceManager().provide(PermissionService.class).get().login(loader.getGame().getPlayer("playerName").get()).isPermitted(perm);
    }

    @Override
    public void sendMessage(String playerName, String message) {
        loader.getGame().getPlayer(playerName).get().sendMessage(message);
    }

    @Override
    public String getPlayerWorld(String playerName) {
        return loader.getGame().getPlayer("test").get().getWorld().getName();
    }

    @Override
    public boolean isOnline(String playerName) {
        return false;
    }

    @Override
    public List<String> getOnlinePlayers() {
        List<String> playerList = new ArrayList<>();
        for (Player p : loader.getGame().getOnlinePlayers()) {
            playerList.add(p.getName());
        }
        return playerList;
    }

    @Override
    public boolean isOp(String playerName) {
        return false;
    }

    @Override
    public UUID getUUID(String playerName) {
        return loader.getGame().getPlayer(playerName).get().getUniqueId();
    }
}
