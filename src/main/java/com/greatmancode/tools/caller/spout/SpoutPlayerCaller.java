/*
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2013, Greatman <http://github.com/greatman/>
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
package com.greatmancode.tools.caller.spout;

import java.util.ArrayList;
import java.util.List;

import com.greatmancode.tools.interfaces.SpoutLoader;
import com.greatmancode.tools.interfaces.caller.PlayerCaller;

import org.spout.api.Server;
import org.spout.api.entity.Player;

public class SpoutPlayerCaller extends PlayerCaller {


	public SpoutPlayerCaller(SpoutServerCaller caller) {
		super(caller);
	}

	@Override
	public boolean checkPermission(String playerName, String perm) {
		boolean result;
		Player p = ((Server)((SpoutLoader)getCaller().getLoader()).getEngine()).getPlayer(playerName, true);
		if (p != null) {
			result = p.hasPermission(perm);
		} else {
			// It's the console
			result = true;
		}
		return result;
	}

	@Override
	public void sendMessage(String playerName, String message) {
		Player p = ((Server)((SpoutLoader)getCaller().getLoader()).getEngine()).getPlayer(playerName, true);
		if (p != null) {

			p.sendMessage(getCaller().addColor(getCaller().getCommandPrefix() + message));
		} else {
			((SpoutLoader)getCaller().getLoader()).getEngine().getCommandSource().sendMessage(getCaller().addColor(getCaller().getCommandPrefix() + message));
		}
	}

	@Override
	public String getPlayerWorld(String playerName) {
		String worldName = "";
		Player p = ((Server)((SpoutLoader)getCaller().getLoader()).getEngine()).getPlayer(playerName, true);
		if (p != null) {
			worldName = p.getWorld().getName();
		}
		return worldName;
	}

	@Override
	public boolean isOnline(String playerName) {
		return ((Server)((SpoutLoader)getCaller().getLoader()).getEngine()).getPlayer(playerName, true) != null;
	}

	@Override
	public List<String> getOnlinePlayers() {
		List<String> list = new ArrayList<String>();
		Player[] pList = ((Server) ((SpoutLoader)getCaller().getLoader()).getEngine()).getOnlinePlayers();
		for (Player p : pList) {
			list.add(p.getName());
		}
		return list;
	}


	@Override
	public boolean isOp(String playerName) {
		// TODO: Hmm... There's not really a OP in Spout. Maybe use a permission flag?
		return false;
	}
}
