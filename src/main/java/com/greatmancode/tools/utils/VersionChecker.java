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
package com.greatmancode.tools.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that allow to check if the plugin is on the latest version.
 *
 * @Deprecated No longer used
 */
public class VersionChecker {
    private boolean oldVersion = false;
    private int newVersion = 0;

    /**
     * Initialize the VersionChecker by checking if the plugin is outdated.
     *
     * @param currentVersion The current plugin version.
     * @deprecated
     */
    public VersionChecker(String jobName, String currentVersion) {
        if (currentVersion.contains("unknown-unknown-unknown")) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "You are running a self-built version! Be sure that you check on the website if there's a new version!");
            return;
        }
        String pluginUrlString = "http://build.greatmancode.com/job/" + jobName + "/promotion/api/xml?depth=1";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            URL url = new URL(pluginUrlString);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openConnection().getInputStream());
            Node list = doc.getElementsByTagName("lastBuild").item(0);
            String buildURL = ((Element) list).getElementsByTagName("url").item(0).getTextContent();
            doc = factory.newDocumentBuilder().parse(buildURL + "api/xml?depth=1");
            Node entry = ((Element) doc.getElementsByTagName("number").item(1));
            String data = entry.getTextContent();

            //We check if the version retrieved is higher than the one we run on
            int buildNumber = Integer.parseInt(currentVersion.split("jenkins-Craftconomy3-")[1]);
            int jenkinsBuildNumber = Integer.parseInt(data);
            if (buildNumber < jenkinsBuildNumber) {
                oldVersion = true;
                newVersion = jenkinsBuildNumber;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error while trying to check for the latest version. The error is: " + e.getMessage());
        }
    }

    /**
     * Check if the current version is a old one.
     *
     * @return True if the current plugin version is old. Else false.
     */
    public boolean isOld() {
        return oldVersion;
    }

    /**
     * Returns the new version of the plugin.
     *
     * @return The new version of the plugin.
     */
    public int getNewVersion() {
        return newVersion;
    }
}
