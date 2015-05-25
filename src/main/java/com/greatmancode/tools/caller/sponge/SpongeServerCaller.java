/**
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2015, Greatman <http://github.com/greatman/>
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

import com.google.common.base.Optional;
import com.greatmancode.tools.commands.SubCommand;
import com.greatmancode.tools.events.Event;
import com.greatmancode.tools.interfaces.Common;
import com.greatmancode.tools.interfaces.SpongeLoader;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import com.greatmancode.tools.utils.ServicePriority;
import com.greatmancode.tools.utils.VaultEconomy;
import org.spongepowered.api.Game;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextBuilder;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyle;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.source.ConsoleSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpongeServerCaller extends ServerCaller {
    private String name, version;

    public SpongeServerCaller(SpongeLoader spongeLoader, String name, String version) {
        super(spongeLoader);
        addPlayerCaller(new SpongePlayerCaller(this));
        addSchedulerCaller(new SpongeSchedulerCaller(this));
        this.name = name;
        this.version = version;
    }

    @Override
    public void disablePlugin() {
        //TODO Impossible?
    }

    @Override
    public String addColor(String message) {
        return message;
    }

    public Text addColorSponge(String message) {
        message = getCommandPrefix() + message;
        TextBuilder textMain = Texts.builder();
        Matcher m = Pattern.compile("(\\{\\{([^\\{\\}]+)\\}\\}|[^\\{\\}]+)").matcher(message);
        TextColor.Base color = null;
        TextStyle.Base style = null;
        while (m.find()) {
            
            String entry = m.group();
            if (entry.contains("{{")) {
                color = null;
                style = null;
                switch (entry) {
                    case "{{BLACK}}":
                        color = TextColors.BLACK;
                        break;
                    case "{{DARK_BLUE}}":
                        color = TextColors.DARK_BLUE;
                        break;
                    case "{{DARK_GREEN}}":
                        color = TextColors.DARK_GREEN;
                        break;
                    case "{{DARK_CYAN}}":
                        color = TextColors.DARK_AQUA;
                        break;
                    case "{{DARK_RED}}":
                        color = TextColors.DARK_RED;
                        break;
                    case "{{PURPLE}}":
                        color = TextColors.DARK_PURPLE;
                        break;
                    case "{{GOLD}}":
                        color = TextColors.GOLD;
                        break;
                    case "{{GRAY}}":
                        color = TextColors.GRAY;
                        break;
                    case "{{DARK_GRAY}}":
                        color = TextColors.DARK_GRAY;
                        break;
                    case "{{BLUE}}":
                        color = TextColors.AQUA; //TODO Wrong color
                        break;
                    case "{{BRIGHT_GREEN}}":
                        color = TextColors.GREEN;
                        break;
                    case "{{CYAN}}":
                        color = TextColors.AQUA;
                        break;
                    case "{{RED}}":
                        color = TextColors.RED;
                        break;
                    case "{{LIGHT_PURPLE}}":
                        color = TextColors.LIGHT_PURPLE;
                        break;
                    case "{{YELLOW}}":
                        color = TextColors.YELLOW;
                        break;
                    case "{{WHITE}}":
                        color = TextColors.WHITE;
                        break;
                    case "{{OBFUSCATED}}":
                        style = TextStyles.OBFUSCATED;
                        break;
                    case "{{BOLD}}":
                        style = TextStyles.BOLD;
                        break;
                    case "{{STRIKETHROUGH}}":
                        style = TextStyles.STRIKETHROUGH;
                        break;
                    case "{{UNDERLINE}}":
                        style = TextStyles.UNDERLINE;
                        break;
                    case "{{ITALIC}}":
                        style = TextStyles.ITALIC;
                        break;
                    case "{{RESET}}":
                        style = TextStyles.RESET;
                        break;
                }
            } else {
                TextBuilder text = Texts.builder(entry);
                if (color != null) {
                    text.color(color);
                }
                if (style != null) {
                    text.style(style);
                }
                textMain.append(text.build());
            }
        }
        return textMain.build();
    }

    @Override
    public boolean worldExist(String worldName) {
        return ((SpongeLoader)loader).getGame().getServer().getWorld(worldName) != null;
    }

    @Override
    public String getDefaultWorld() {
        return ((SpongeLoader)loader).getGame().getServer().getWorlds().iterator().next().getName();
    }

    @Override
    public File getDataFolder() {
        File data = new File("mods" + File.separator + "Craftconomy3");
        data.mkdirs();
        return data;
    }

    @Override
    public void addCommand(String name, String help, final SubCommand subCommand) {
        CommandCallable command = new CommandCallable() {
            @Override
            public Optional<CommandResult> process(CommandSource source, String arguments) throws CommandException {
                String subCommandValue = "";
                String[] newArgs;
                String[] args = arguments.split(" ");
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
                String username = source.getName();
                if (source instanceof ConsoleSource) {
                    username = "console";
                }
                subCommand.execute(subCommandValue, username, newArgs);
                return Optional.of(CommandResult.success());
            }

            @Override
            public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
                List<String> list = new ArrayList<>();
                list.addAll(subCommand.getSubCommandKeys());
                return list;
            }

            @Override
            public boolean testPermission(CommandSource source) {
                return true;
            }

            @Override
            public Optional<Text> getShortDescription(CommandSource source) {
                return Optional.absent();
            }

            @Override
            public Optional<Text> getHelp(CommandSource source) {
                return Optional.absent();
            }

            @Override
            public Text getUsage(CommandSource source) {
                return Texts.of();
            }
        };
        ((SpongeLoader)loader).getGame().getCommandDispatcher().register(loader, command, name);
    }

    @Override
    public String getServerVersion() {
        return String.format("%s %s", "Sponge", ((SpongeLoader)loader).getGame().getPlatform().getVersion());
    }

    @Override
    public String getPluginVersion() {
        return version;
    }

    @Override
    public String getPluginName() {
        return name;
    }

    @Override
    public void loadLibrary(String path) {

    }

    @Override
    public void registerPermission(String permissionNode) {
       //None
    }

    @Override
    public boolean isOnlineMode() {
        return ((SpongeLoader)loader).getGame().getServer().getOnlineMode();
    }

    @Override
    public Logger getLogger() {
        return Logger.getLogger(getPluginName());
    }

    @Override
    public void throwEvent(Event event) {
        //Not used
       // Game game = ((SpongeLoader) loader).getGame();
        //game.getEventManager().post(event);
    }

    @Override
    public Common retrievePlugin(String name) {

        Game game = ((SpongeLoader)loader).getGame();
        return (Common) game.getPluginManager().getPlugin(name).get().getInstance();
    }

    @Override
    public void setVaultEconomyHook(VaultEconomy instance, ServicePriority priority) {

    }
}
