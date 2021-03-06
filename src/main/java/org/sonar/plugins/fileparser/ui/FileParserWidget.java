/*
 * File Parser Plugin
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
package org.sonar.plugins.fileparser.ui;

import org.sonar.api.web.*;

@UserRole(UserRole.USER)
@Description("Dynamically display values from a property file")
@WidgetCategory("File Parser")
@WidgetProperties({
        @WidgetProperty(key ="caption",
                defaultValue = "File Parser Plugin",
                description= "Possibility to insert a custom caption"),
        @WidgetProperty(key ="fileNr",
                type = WidgetPropertyType.INTEGER,
                defaultValue = "1",
                description= "If more than one file is parsed: Which file shall be displayed in this widget?"),
        @WidgetProperty(key ="boolAsImage",
                type = WidgetPropertyType.BOOLEAN,
                defaultValue = "True",
                description= "Do you want to display boolean values as pictures?")
})
public class FileParserWidget extends AbstractRubyTemplate implements RubyRailsWidget {

    @Override
    public String getId() {
        return "FileParserWidget";
    }

    @Override
    public String getTitle() {
        return "File Parser Widget";
    }

    @Override
    protected String getTemplatePath() {
        // DEBUG
        //return "/tmp/widget.html.erb";
        return "/fileparser/fileparser_widget.html.erb";
    }
}