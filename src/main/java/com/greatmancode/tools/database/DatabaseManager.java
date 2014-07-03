/*
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2014, Greatman <http://github.com/greatman/>
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

import com.greatmancode.tools.database.interfaces.DatabaseType;
import com.greatmancode.tools.database.throwable.InvalidDatabaseConstructor;
import com.greatmancode.tools.interfaces.caller.ServerCaller;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;

public class DatabaseManager {
    private HikariDataSource db = null;
    private String tablePrefix = null;
    private ServerCaller serverCaller;

    public DatabaseManager(DatabaseType type, String tablePrefix, File path, ServerCaller serverCaller) throws InvalidDatabaseConstructor {
        if (type.equals(DatabaseType.H2) || type.equals(DatabaseType.SQLITE)) {
            this.serverCaller = serverCaller;
            HikariConfig config = new HikariConfig();
            config.setMaximumPoolSize(10);
            config.setDataSourceClassName("org.sqlite.SQLiteDataSource");
            config.addDataSourceProperty("databaseName", path.getAbsolutePath());
            this.tablePrefix = tablePrefix;
            initialiseDatabase(config);
        } else {
            throw new InvalidDatabaseConstructor();
        }
    }

    public DatabaseManager(DatabaseType type, String address, int port, String username, String password, String database, String tablePrefix, ServerCaller serverCaller) throws InvalidDatabaseConstructor {
        if (type.equals(DatabaseType.MYSQL)) {
            this.serverCaller = serverCaller;
            HikariConfig config = new HikariConfig();
            config.setMaximumPoolSize(10);
            config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            config.addDataSourceProperty("serverName", address);
            config.addDataSourceProperty("port", port);
            config.addDataSourceProperty("databaseName", database);
            config.addDataSourceProperty("user", username);
            config.addDataSourceProperty("password", password);
            config.addDataSourceProperty("autoDeserialize", true);
            this.tablePrefix = tablePrefix;
            initialiseDatabase(config);
        } else {
            throw new InvalidDatabaseConstructor();
        }
    }

    public HikariDataSource getDatabase() {
        return db;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    private void initialiseDatabase(HikariConfig config) {
        db = new HikariDataSource(config);
    }
}
