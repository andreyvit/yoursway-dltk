<html>
<head>
<?php

function endsWith( $str, $sub ) {

   return ( substr( $str, strlen( $str ) - strlen( $sub ) ) === $sub );
}

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
        $hasNotes = false;
        $aDirectory = dir(".");
        $index = 0;
        while ($anEntry = $aDirectory->read()) {
                if ($anEntry != "." && $anEntry != ".." && !endsWith(__FILE__, $anEntry)) {
                        $entries[$index] = $anEntry;
                        $index++;
                }
        }
        aDirectory.closedir();


        sort($entries);

        for ($i = 0; $i < $index; $i++) {
                $anEntry = $entries[$i];
                $logsize = filesize("$anEntry");


                $level = 0;
                if ($logsize > 20000) {
                        $level = 2;
                }
                else if ($logsize > 2000) {
                        $level = 1;
                }

                if ($level == 0) {
                        $line = "<td><a href=\"$anEntry\">$anEntry</a>  ($logsize bytes)</td>";
                }
                else if ($level == 1) {
                        $line = "<td><em><a href=\"$anEntry\">$anEntry</a>  ($logsize bytes)</em></td>";
                } else if ($level == 2) {
                        $line = "<td><strong><a href=\"$anEntry\">$anEntry</a>  ($logsize bytes)</strong></td>";
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
