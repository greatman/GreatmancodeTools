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
package com.greatmancode.tools;

import java.io.File;
import java.net.URISyntaxException;

import com.greatmancode.tools.caller.unittest.UnitTestCaller;
import com.greatmancode.tools.interfaces.UnitTestLoader;
import com.greatmancode.tools.language.LanguageManager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageTest {
	@Test
	public void test() throws URISyntaxException {
		LanguageManager languageManager = new LanguageManager(new UnitTestCaller(new UnitTestLoader()), new File(ConfigurationTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()), "languageTest.yml");
		languageManager.addLanguageEntry("test", "this is a test");
		assertEquals("this is a test", languageManager.parse("test", null));
		languageManager = null;
		languageManager = new LanguageManager(new UnitTestCaller(new UnitTestLoader()), new File(ConfigurationTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()), "languageTest.yml");
		assertEquals("this is a test", languageManager.parse("test", null));
		languageManager.addLanguageEntry("test2", "This is a %s");
		assertEquals("This is a wow", languageManager.parse("test2", "wow"));
		languageManager = null;
		languageManager = new LanguageManager(new UnitTestCaller(new UnitTestLoader()), new File(ConfigurationTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()), "languageTest.yml");
		assertEquals("This is a wow", languageManager.parse("test2", "wow"));
	}
}
