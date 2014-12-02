package com.greatmancode.tools.commands.sponge;

import com.google.common.base.Optional;
import com.greatmancode.tools.commands.CommandHandler;
import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.source.ConsoleSource;

import java.util.ArrayList;
import java.util.List;

public class SpongeCommandReceiver implements CommandCallable {

    private final CommandHandler commandHandler;

    public SpongeCommandReceiver(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    @Override
    public boolean call(CommandSource source, String arguments, List<String> parents) throws CommandException {
        String[] args = arguments.split("");
        SubCommand subCommand = commandHandler.getCommand(parents.get(0));
        if (subCommand != null) {
            String subCommandValue = "";
            String[] newArgs;
            if (args.length <= 1) {
                newArgs = new String[0];
                if (args.length != 0) {
                    subCommandValue = args[0];
                }
            } else {
                newArgs = new String[args.length - 1];
                subCommandValue = args[0];
                System.arraycopy(args, 1, newArgs, 0, args.length - 1);
            }
            if (source instanceof Player) {
                subCommand.execute(subCommandValue, ((Player) source).getName(), newArgs);
            } else if (source instanceof ConsoleSource) {
                subCommand.execute(subCommandValue, "CONSOLE", newArgs);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return true; //TODO: Always true, Plugins handle the perms
    }

    @Override
    public Optional<String> getShortDescription() {
        return null;
    }

    @Override
    public Optional<String> getHelp() {
        return null;
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
        return new ArrayList<>();
    }
}
