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
package org.sonar.plugins.fileparser.batch;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;
import org.sonar.plugins.fileparser.FileParserMetrics;
import org.sonar.plugins.fileparser.FileParserPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileParserSensor implements Sensor {

    private final String path;
    private final String name;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileParserSensor.class);

    public FileParserSensor(Settings settings) {
        String pathFromSettings = settings.getString(FileParserPlugin.FOLDER_PATH);
        String nameFromSettings = settings.getString(FileParserPlugin.FILE_NAME);

        //
        if(pathFromSettings.equals(FileParserPlugin.DEFAULT_PATH)) {
            pathFromSettings = settings.getString("sonar.projectBaseDir");
        }

        if(nameFromSettings.equals(FileParserPlugin.DEFAULT_FILE)) {
            nameFromSettings = settings.getString("sonar.projectKey") + ".fileParser";
        }
        this.path = pathFromSettings;
        this.name = nameFromSettings;
    }

    @Override
    public boolean shouldExecuteOnProject(Project project) {
        // This sensor is executed on any type of projects
        // Modify the code if the sensor should be executed based on a condition
        return true;
    }

    @Override
    public void analyse(Project project, SensorContext sensorContext) {
        LOGGER.debug("------------------------------------------------------------------");
        LOGGER.info("File Parser Plugin");
        String filePath = path + "/" + name;
        LOGGER.info("Searching for File: " + filePath);

        String fileData = "";
        
        try {
            File metricsFile = new File(filePath);
            fileData = FileUtils.readFileToString(metricsFile);

        } catch (FileNotFoundException e) {
            LOGGER.error("No file found at the specified destination");
        } catch (IOException e) {
            LOGGER.error("Could not read file");
        }
        LOGGER.debug("Extracted information: " );
        LOGGER.debug(fileData);
        sensorContext.saveMeasure(new Measure(FileParserMetrics.STRING_MAP, fileData));
        LOGGER.debug("------------------------------------------------------------------");
    }
}
