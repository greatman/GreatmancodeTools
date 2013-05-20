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
package com.greatmancode.tools.database;

import java.io.File;

import com.alta189.simplesave.Configuration;
import com.alta189.simplesave.Database;
import com.alta189.simplesave.DatabaseFactory;
import com.alta189.simplesave.exceptions.ConnectionException;
import com.alta189.simplesave.exceptions.TableRegistrationException;
import com.alta189.simplesave.h2.H2Configuration;
import com.alta189.simplesave.mysql.MySQLConfiguration;
import com.alta189.simplesave.sqlite.SQLiteConfiguration;
import com.greatmancode.tools.caller.canary.CanaryCaller;
import com.greatmancode.tools.caller.spout.SpoutCaller;
import com.greatmancode.tools.database.interfaces.DatabaseType;
import com.greatmancode.tools.database.throwable.InvalidDatabaseConstructor;
import com.greatmancode.tools.interfaces.Caller;

public class DatabaseManager {
	private Database db = null;
	private Caller caller;

	public DatabaseManager(DatabaseType type, String tablePrefix, File path, Caller caller) throws InvalidDatabaseConstructor {
		if (type.equals(DatabaseType.H2) || type.equals(DatabaseType.SQLITE)) {
			this.caller = caller;
			Configuration config;
			if (type.equals(DatabaseType.H2)) {
				caller.loadLibrary(caller.getDataFolder() + File.separator + "h2.jar");
				config = new H2Configuration();
				((H2Configuration) config).setDatabase(path.getAbsolutePath());
			} else {
				if (caller instanceof SpoutCaller) {
					caller.loadLibrary(caller.getDataFolder() + File.separator + "sqlite.jar");
				}
				config = new SQLiteConfiguration(path.getAbsolutePath());
				((SQLiteConfiguration) config).setPrefix(tablePrefix);
			}
			initialiseDatabase(config);
		} else {
			throw new InvalidDatabaseConstructor();
		}
	}

	public DatabaseManager(DatabaseType type, String address, int port, String username, String password, String database, String tablePrefix, Caller caller) throws InvalidDatabaseConstructor {
		if (type.equals(DatabaseType.MYSQL)) {
			this.caller = caller;
			if (caller instanceof SpoutCaller) {
				caller.loadLibrary(caller.getDataFolder() + File.separator + "mysql.jar");
			}
			MySQLConfiguration config = new MySQLConfiguration();
			config.setHost(address);
			config.setPort(port);
			config.setUser(username);
			config.setPassword(password);
			config.setDatabase(database);
			config.setPrefix(tablePrefix);
			initialiseDatabase(config);
		} else {
			throw new InvalidDatabaseConstructor();
		}
	}

	public void registerTable(Class<?> tableClass) throws TableRegistrationException {
		if (db != null) {
			db.registerTable(tableClass);
		}
	}

	public void connect() throws ConnectionException {
		if (db != null) {
			db.connect();
		}
	}

	public Database getDatabase() {
		return db;
	}

	private void initialiseDatabase(Configuration config) {
		db = DatabaseFactory.createNewDatabase(config);
	}
}
