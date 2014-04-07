/*
 * SonarQube File Parser Plugin
 * Copyright (C) 2014 Max Schrimpf
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.fileparser;

import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;
import org.sonar.plugins.fileparser.batch.FileParserSensor;
import org.sonar.plugins.fileparser.ui.FileParserWidget;

import java.util.Arrays;
import java.util.List;

@Properties({
        @Property(
                key = FileParserPlugin.FOLDER_PATH,
                name = "File Path",
                description = "Specify if you want a special folder to contain the file that shall be displayed",
                defaultValue = FileParserPlugin.DEFAULT_PATH,
                global = true,
                project = true,
                module = false),
        @Property(
                key = FileParserPlugin.FILE_NAME,
                name = "File Name",
                description = "Specify if you want a special name for the file to be displayed",
                defaultValue = FileParserPlugin.DEFAULT_FILE,
                global = true,
                project = true,
                module = false),
        @Property(
                key = FileParserPlugin.REGEX_STRING,
                name = "Regular Expression",
                description = "Regular Expression to split each line of the file. Only the first two results of the split will be considered",
                defaultValue = "=",
                global = true,
                project = true,
                module = false),
        @Property(
                key = FileParserPlugin.GROUP_IDENTIFIER,
                name = "Group Identifier",
                description = "Identifer for a Groupvalue",
                defaultValue = "G",
                global = true,
                project = true,
                module = false),
        @Property(
                key = FileParserPlugin.KEY_VALUE_IDENTIFIER,
                name = "KeyValue Identifier",
                description = "Identifer for a Key/Value pair",
                defaultValue = "K",
                global = true,
                project = true,
                module = false),
        @Property(
                key = FileParserPlugin.LINE_IDENTIFIER,
                name = "Line Identifier",
                description = "Identifer for a Linevalue",
                defaultValue = "L",
                global = true,
                project = true,
                module = false)
})

public final class FileParserPlugin extends SonarPlugin {


    public static final String DEFAULT_PATH = "SonarQube working directory";
    public static final String DEFAULT_FILE = "SonarQube project key + .fileParser";
    public static final String FOLDER_PATH = "sonar.fileParser.filePath";
    public static final String FILE_NAME = "sonar.fileParser.fileName";
    public static final String REGEX_STRING = "sonar.fileParser.regex";
    public static final String GROUP_IDENTIFIER = "sonar.fileParser.groupIdentifier";
    public static final String KEY_VALUE_IDENTIFIER = "sonar.fileParser.keyValueIdentifier";
    public static final String LINE_IDENTIFIER = "sonar.fileParser.lineIdentifier";
    
    // This is where you're going to declare all your Sonar extensions
    public List getExtensions() {
        return Arrays.asList(
                // Definitions
                FileParserMetrics.class,

                // Batch
                FileParserSensor.class,

                // UI
                FileParserWidget.class);
    }
}