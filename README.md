# SonarQube File Parser PlugIn

This SonarQube PlugIn parses a property file and dynamicly displays the content.
It can be useful if you want to display custom configuration parameters in the SonarQube dashboard or so on.
You can specify titles for groups, single lines or key value pairs.

## Example
A property file like this:

```
G=This is a title 
K=Key value pairs=Possible
L=Or just a simple lines
L=The strings are not escaped - so you can use your own <em>HTML Code</em> inside
G=This is another title
K=Even cool booleans=true
K=With pictures if you like=false
G=Titles help you to display content well structured
K=Question=42
K=My important KPI=1
K=Another important KPI=2
K=Some other stuff i'd like to show=3
K=Followed by some numbers=5
```

... will be displayed as:

![alt text](https://raw.githubusercontent.com/mxmo0rhuhn/sonarQubeFileParser/master/screenshots/sample.png "A sample of some Properties")

All seperators or prefixes are configurable so you can change it to fit your needs.
E.g. you can change the seperator to any other string to have the possibility to bisplay hyperlinks.

## Multiple files
If you like you can display more than one file with this PlugIn.
All you need is to store the different files as filename + {number}.
This feature might be particularly useful if you want to display some static and some dynamic data in your dashboard.

This configuration allows you to store more than one file.
![alt text](https://raw.githubusercontent.com/mxmo0rhuhn/sonarQubeFileParser/master/screenshots/sample_config.png "A sample configuration for more than one file")

The PlugIn will now parse the two files named:

```
/tmp/fileparser1
/tmp/fileparser2
```

Afterwards you can configure the File Parser widget to display a certain file.
![alt text](https://raw.githubusercontent.com/mxmo0rhuhn/sonarQubeFileParser/master/screenshots/sample_widget.png "A sample configuration for more than one file")

## License
Boolean pictures: "Must Have" by [Visual Pharm](http://icons8.com/) distributed under [Creative Commons (Attribution 3.0 Unported)](http://creativecommons.org/licenses/by-nd/3.0/)

This PlugIn is free software: You can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

The PlugIn is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
