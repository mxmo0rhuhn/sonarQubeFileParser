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

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.Arrays;
import java.util.List;

public final class FileParserMetrics implements Metrics {

    public static final String FILEPARSER_VALUES_KEY = "fileParser-values";

    // Format: Key, name, value type
    public static final Metric STRING_MAP =
        new Metric.Builder(FILEPARSER_VALUES_KEY, "Information from the file parser plugin", Metric.ValueType.STRING)
    	.setDescription("All metrics read by the file")
                    .setQualitative(false)
                    .create();

    // getMetrics() method is defined in the Metrics interface and is used by
    // Sonar to retrieve the list of new metrics
    public List<Metric> getMetrics() {
        return Arrays.asList(STRING_MAP);
    }
}
