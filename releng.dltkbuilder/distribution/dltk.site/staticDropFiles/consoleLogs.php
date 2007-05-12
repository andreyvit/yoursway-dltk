<html>
<head>
<?php
        $parts = explode("/", getcwd());
        $parts2 = explode("-", $parts[count($parts) - 1]);
        $buildName = $parts2[1];

        echo "<title>Test Console Output for $buildName </title>";
?>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="http://dev.eclipse.org/default_style.css" type="text/css">
<title>Console Logs from Running JUnit Plug-in Tests</title></head>
<body>

<p><b><font face="Verdana" size="+3">Test Console Output</font></b> </p>

<table border=0 cellspacing=5 cellpadding=2 width="100%" >
  <tr>
    <td align=LEFT valign=TOP colspan="3" bgcolor="#0080C0"><b><font color="#FFFFFF" face="Arial,Helvetica">Console
      output from running JUnit plugin tests for
      <?php echo "$buildName"; ?>
      </font></b></td>
  </tr>
</table>
<table border="0">

<?php
		$rootDir = "testResults/consolelogs";
        $hasNotes = false;
        $aDirectory = dir($rootDir);
        $index = 0;
        $dirindex = 0;
        while ($anEntry = $aDirectory->read()) {
                if ($anEntry != "." && $anEntry != "..") {
                        if (is_file("$rootDir/$anEntry")) {
                                $entries[$index] = $anEntry;
                                $index++;
                        } else if (is_dir("$rootDir/$anEntry")) {
                                $direntries[$dirindex] = $anEntry;
                                $dirindex++;
                        }
                }
        }
        $aDirectory.closedir();


        sort($entries);
        sort($direntries);

        for ($i = 0; $i < $dirindex; $i++) {
                $anEntry = $direntries[$i];
                $line = "<td><a href=\"testResults/consolelogs/$anEntry/logIndex.php\">$anEntry</a></td>";
                echo "<tr>";
                echo "$line";
                echo "</tr>";
        }
        for ($i = 0; $i < $index; $i++) {
                $anEntry = $entries[$i];
                $logsize = filesize("testResults/consolelogs/$anEntry");


                $level = 0;
                if ($logsize > 20000) {
                        $level = 2;
                }
                else if ($logsize > 2000) {
                        $level = 1;
                }

                if ($level == 0) {
                        $line = "<td><a href=\"testResults/consolelogs/$anEntry\">$anEntry</a>  ($logsize bytes)</td>";
                }
                else if ($level == 1) {
                        $line = "<td><em><a href=\"testResults/consolelogs/$anEntry\">$anEntry</a>  ($logsize bytes)</em></td>";
                } else if ($level == 2) {
                        $line = "<td><strong><a href=\"testResults/consolelogs/$anEntry\">$anEntry</a>  ($logsize bytes)</strong></td>";
                }


                echo "<tr>";
                echo "$line";
                echo "</tr>";
                $hasNotes = true;
        }

        if (!$hasNotes) {
                echo "<br>There are no test logs for this build.";
        }
?>

</table>
</body>
</html>
