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
package com.greatmancode.tools;

import com.greatmancode.tools.caller.unittest.UnitTestServerCaller;
import com.greatmancode.tools.database.DatabaseManager;
import com.greatmancode.tools.database.interfaces.DatabaseType;
import com.greatmancode.tools.database.throwable.InvalidDatabaseConstructor;
import com.greatmancode.tools.interfaces.UnitTestLoader;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class DatabaseTest {
	@Test
	public void test() throws URISyntaxException, InvalidDatabaseConstructor, SQLException {
		DatabaseManager dbManager = new DatabaseManager(DatabaseType.SQLITE, "test_", new File(new File(ConfigurationTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()), "testConfig.db"), new UnitTestServerCaller(new UnitTestLoader()));
        Connection connection = dbManager.getDatabase().getConnection();
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE "+dbManager.getTablePrefix()+"test ( " +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  test varchar(255) NOT NULL" +
                ");");
        statement.executeUpdate();
        statement.close();
        statement = connection.prepareStatement("INSERT INTO "+dbManager.getTablePrefix()+"test(test) VALUES ('wow')");
        statement.executeUpdate();
        statement.close();
        statement = connection.prepareStatement("SELECT * FROM "+dbManager.getTablePrefix()+"test WHERE test='wow'");
        ResultSet set = statement.executeQuery();
        set.next();
        assertEquals("wow", set.getString("test"));
	}
}

