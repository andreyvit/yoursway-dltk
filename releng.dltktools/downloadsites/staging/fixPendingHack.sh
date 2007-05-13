#!/bin/sh



fromString="\<font size=\"-1\" color=\"#FF0000\"\>pending\</font\>"
toString="\<img src = \"OK.gif\" width=19 height=23\>"
replaceCommand="s!${fromString}!${toString}!g"
echo "replaceCommand: ${replaceCommand}"
perl -w -pi -e "${replaceCommand}" index.php
 
