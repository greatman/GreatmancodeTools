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
package com.greatmancode.tools.utils;

import com.greatmancode.tools.entities.Player;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;

public abstract class VaultEconomy implements Economy {

    @Override
    public boolean hasAccount(String s) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return hasAccount(new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()));
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String world) {
        return hasAccount(offlinePlayer);
    }

    public abstract boolean hasAccount(Player player);

    @Override
    public double getBalance(String playerName) {
        return getBalance(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return getBalance(offlinePlayer, null);
    }

    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(Bukkit.getOfflinePlayer(playerName), world);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String world) {
        return getBalance(new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()), world);
    }

    public abstract double getBalance(Player player, String world);

    @Override
    public boolean has(String playerName, double amount) {
        return has(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double amount) {
        return has(offlinePlayer, null, amount);
    }

    @Override
    public boolean has(String playerName, String world, double amount) {
        return has(Bukkit.getPlayer(playerName), world, amount);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String world, double amount) {
        return has(new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()), world, amount);
    }

    public abstract boolean has(Player player, String world, double amount);

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
        return withdrawPlayer(offlinePlayer, null, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String world, double amount) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(playerName), world, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
        return withdrawPlayer(new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()), world, amount);
    }

    public abstract EconomyResponse withdrawPlayer(Player p, String world, double amount);

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return depositPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        return depositPlayer(offlinePlayer, null, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String world, double amount) {
        return depositPlayer(Bukkit.getOfflinePlayer(playerName), world, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
        return depositPlayer(new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()), world, amount);
    }

    public abstract EconomyResponse depositPlayer(Player player, String world, double amount);

    @Override
    public EconomyResponse createBank(String name, String playerName) {
        return createBank(name, Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer offlinePlayer) {
        return createBank(name, new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()));
    }

    public abstract EconomyResponse createBank(String name, Player player);

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return isBankOwner(name, Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer offlinePlayer) {
        return isBankOwner(name, new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()));
    }

    public abstract EconomyResponse isBankOwner(String name, Player player);

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return isBankMember(name, Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer offlinePlayer) {
        return isBankMember(name, new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()));
    }

    public abstract EconomyResponse isBankMember(String name, Player player);

    @Override
    public boolean createPlayerAccount(String playerName) {
        return createPlayerAccount(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return createPlayerAccount(new Player(offlinePlayer.getName(), null, null, offlinePlayer.getUniqueId()));
    }

    public abstract boolean createPlayerAccount(Player player);

    @Override
    public boolean createPlayerAccount(String playerName, String world) {
        return createPlayerAccount(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String world) {
        return createPlayerAccount(offlinePlayer);
    }
}
