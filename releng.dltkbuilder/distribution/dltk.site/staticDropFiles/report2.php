
        <?php

function parse_testResults($filename)
{
        $junitFailures = 0;
        if (is_file($filename)) {
                $handle = @fopen($filename, "r");
                if ($handle)
                {
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


?>


