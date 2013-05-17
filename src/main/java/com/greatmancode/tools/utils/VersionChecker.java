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
package com.greatmancode.tools.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class that allow to check if the plugin is on the latest version.
 */
public class VersionChecker {
	private boolean oldVersion = false;
	private String newVersion = "";

	/**
	 * Initialize the VersionChecker by checking if the plugin is outdated.
	 * @param currentVersion The current plugin version.
	 */
	public VersionChecker(String currentVersion) {
		if (currentVersion.contains("SNAPSHOT")) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "You are running a dev-build! Be sure that you check on the website if there's a new version!");
			return;
		}
		String pluginUrlString = "http://build.greatmancode.com/guestAuth/app/rest/builds?locator=pinned:true&buildType:bt2";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document dom;
		try {
			URL url = new URL(pluginUrlString);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openConnection().getInputStream());
			Element root = doc.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			if (nodeList.getLength() >= 1) {
				Node node = nodeList.item(0);
				if (node instanceof Element) {
					String data = ((Element)node).getAttribute("number");
					if (!data.contains(currentVersion)) {
						oldVersion = true;
						newVersion = data;
					}
				}
			}

		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while trying to check for the latest version. The error is: " + e.getMessage());
		}
	}

	/**
	 * Check if the current version is a old one.
	 * @return True if the current plugin version is old. Else false.
	 */
	public boolean isOld() {
		return oldVersion;
	}

	/**
	 * Returns the new version of the plugin.
	 * @return The new version of the plugin.
	 */
	public String getNewVersion() {
		return newVersion;
	}
}
