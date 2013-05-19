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
package com.greatmancode.tools.interfaces;

import com.greatmancode.tools.ServerType;
import com.greatmancode.tools.caller.spout.SpoutCaller;
import com.greatmancode.tools.configuration.spout.SpoutConfig;

import org.spout.api.plugin.CommonPlugin;

public class SpoutLoader extends CommonPlugin implements Loader {

	private Common common;
	@Override
	public void onEnable() {

		SpoutCaller spoutCaller = new SpoutCaller(this);
		SpoutConfig spoutConfig = new SpoutConfig(this.getClass().getResourceAsStream("/loader.yml"), spoutCaller);
		String mainClass = spoutConfig.getString("main-class");
		try {
			Class<?> clazz = Class.forName(mainClass);
			if (Common.class.isAssignableFrom(clazz)) {
				common = (Common) clazz.newInstance();
				common.onEnable(spoutCaller, getLogger());
			} else {
				getLogger().severe("The class " + mainClass + " is invalid!");
				this.getEngine().getPluginManager().disablePlugin(this);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.getEngine().getPluginManager().disablePlugin(this);
		} catch (InstantiationException e) {
			e.printStackTrace();
			this.getEngine().getPluginManager().disablePlugin(this);
		} catch (IllegalAccessException e) {
			this.getEngine().getPluginManager().disablePlugin(this);
		}
	}

	@Override
	public void onDisable() {
		common.onDisable();
	}

	@Override
	public ServerType getServerType() {
		return ServerType.SPOUT;
	}
}
