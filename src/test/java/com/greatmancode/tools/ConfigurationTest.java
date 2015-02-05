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
package com.greatmancode.tools;

import java.io.File;
import java.net.URISyntaxException;

import com.greatmancode.tools.caller.unittest.UnitTestServerCaller;
import com.greatmancode.tools.configuration.Config;
import com.greatmancode.tools.configuration.ConfigurationManager;
import com.greatmancode.tools.interfaces.UnitTestLoader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ConfigurationTest {
	@Test
	public void test() throws URISyntaxException {
		ConfigurationManager configurationManager = new ConfigurationManager(new UnitTestServerCaller(new UnitTestLoader()));
		Config config = configurationManager.loadFile(new File(ConfigurationTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()), "testConfig.yml");
		config.setValue("test", 1);
		assertEquals(1, config.getInt("test"));
		config.setValue("test", "test3");
		assertEquals("test3", config.getString("test"));
		assertNull(config.getString("test3"));
		config.setValue("test", 0.3);
		assertEquals(0.3, config.getDouble("test"), 0);
		config.setValue("test", true);
		assertTrue(config.getBoolean("test"));
		config.setValue("test", 3L);
		assertEquals(3L, config.getLong("test"));
	}
}
