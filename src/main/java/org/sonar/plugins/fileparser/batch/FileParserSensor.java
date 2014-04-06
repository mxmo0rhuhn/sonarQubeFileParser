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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

public class FileParserSensor implements Sensor {

    private final String path;
    private final String name;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    public FileParserSensor(Settings settings) {
        String path = settings.getString(FileParserPlugin.FOLDER_PATH);
        String name = settings.getString(FileParserPlugin.FILE_NAME);

        if(path.equals(FileParserPlugin.DEFAULT_PATH)) {
            path = settings.getString("sonar.projectBaseDir");
        }

        if(name.equals(FileParserPlugin.DEFAULT_FILE)) {
            name = settings.getString("POM_ARTIFACTID") + ".fileParser";
        }
        this.path = path;
        this.name = name;
    }

    public boolean shouldExecuteOnProject(Project project) {
        // This sensor is executed on any type of projects
        // Modify the code if the sensor should be executed based on a condition
        return true;
    }

    public void analyse(Project project, SensorContext sensorContext) {
        LOGGER.info("------------------------------------------------------------------");
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
        LOGGER.info("Extracted information: " );
        LOGGER.info(fileData);
        sensorContext.saveMeasure(new Measure(FileParserMetrics.STRING_MAP, fileData));
        LOGGER.info("------------------------------------------------------------------");
    }
}
