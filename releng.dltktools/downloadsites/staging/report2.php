
        <?php

function parse2_testResults($filename)
{
        $junitFailures = -1;
        if (is_file($filename)) {
                $handle = @fopen($filename, "r");
                if ($handle)
                {
                        $junitFailures = 0;
                        $size = filesize($filename);
                        $content = fread($handle, $size);
                        fclose($handle);
                        $junitStart = strpos($content, "Errors &amp; Failures");
                        $junitEnd = strpos($content, "</table>", $junitStart);
                        $junitInfo = substr($content, $junitStart, $junitEnd - $junitStart);
                        $start = strpos($junitInfo, "<td><b><font color=\"#ff0000\">");
                        while ($start !== false)
                        {
                                $start += 29;
                                $stop = strpos($junitInfo, "</font></b></td>", $start);
                                if ($stop !== false)
                                {
                                        $result = substr($junitInfo, $start, $stop - $start);
                                        if (is_numeric($result))
                                        {
                                                $junitFailures += $result;
                                        }
                                        else if (strcmp($result, "DNF") == 0)
                                        {
                                                $junitFailures++;
                                        }
                                }
                                $start = strpos($junitInfo, "<td><b><font color=\"#ff0000\">", $stop);
                        }
                        $results = array($junitFailures);
                        return $results;
                }
        }
}

function parse2_compileResults($filename)
{
        $compileErrors = 0;
        $compileAccessWarnings = 0;
        $compileOtherWarnings = 0;
        if (is_file($filename)) {
                //echo "$filename<br />";
                $handle = @fopen($filename, "r");
                if ($handle)
                {
                        $size = filesize($filename);
                        //echo "size: $size<br />";
                        $content = fread($handle, $size);
                        fclose($handle);

                        //echo "$content";
                        $compileStart = strpos($content, "<table id=tabledata");
                        $compileEnd = strpos($content, "</table", $compileStart);
                        $compileInfo = substr($content, $compileStart, $compileEnd - $compileStart);
                        //echo "compileInfo: $compileInfo<br />";
                        $rowStart = strpos($compileInfo, "<tr>");
                        $rowStart = strpos($compileInfo, "<tr>");
                        $start = $rowStart+4;
                        while ($rowStart !== false)
                        {

                                $start += 4;
                                $rowStop = strpos($compileInfo, "</tr>", $rowStart);
                                //if ($rowStop !== false)
                                //{
                                        $row = substr($compileInfo, $rowStart, $rowStop - $rowStart);
                                        //echo "$row";
                                        //while ($cellStart !== false)
                                        //{
                                                // this parsing logic got a bit more complicated in M5_33 basebuild, as the
                                                // a whole different structure was used.
                                                // we'll try to quick fix this, but need our own index task
                                                $cellStart = strpos($row, "#ERROR");
                                                $cellStart = strpos($row, ">", $cellStart);
                                                $cellStart = $cellStart + 1;
                                                $cellStop = strpos($row, "<", $cellStart);
                                                if ($cellStop !== false)
                                                {
                                                        $cell = substr($row, $cellStart, $cellStop - $cellStart);
                                                        if (is_numeric($cell))
                                                        {
                                                                        $compileErrors += $cell;
                                                        }
                                                        $cellStart = strpos($row, "#ACCESSRULES_WARNINGS");
                                                        $cellStart = strpos($row, ">", $cellStart);
                                                        $cellStart = $cellStart + 1;
                                                        $cellStop = strpos($row, "<", $cellStart);
                                                        $cell = substr($row, $cellStart, $cellStop - $cellStart);
                                                        if (is_numeric($cell))
                                                        {
                                                                        $compileAccessWarnings += $cell;
                                                        }
                                                        $cellStart = strpos($row, "#OTHER_WARNINGS");
                                                        $cellStart = strpos($row, ">", $cellStart);
                                                        $cellStart = $cellStart + 1;
                                                        $cellStop = strpos($row, "<", $cellStart);
                                                        $cell = substr($row, $cellStart, $cellStop - $cellStart);
                                                        if (is_numeric($cell))
                                                        {
                                                                        $compileOtherWarnings += $cell;
                                                        }
                                                }
                                                // look for next row.
                                                //$cellStart = strpos($row, "<tr", $cellStop);
                                        //}
                                //}
                                $rowStart = strpos($compileInfo, "<tr>", $rowStop);
                        }
                }
        }

        $results = array($compileErrors, $compileAccessWarnings, $compileOtherWarnings);
        return $results;
}



?>


