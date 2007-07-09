<html>
<head>

<?php
    $parts = explode("/", realpath(".."));
    $label = $parts[count($parts) - 1];
?>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="http://dev.eclipse.org/default_style.css" type="text/css">
</head>
<body>
<p><b><font face="Verdana" size="+3">Test Results</font></b> </p>
<table border=0 cellspacing=5 cellpadding=2 width="100%" >
  <tr> 
    <td align=LEFT valign=TOP colspan="3" bgcolor="#0080C0"><b><font color="#FFFFFF" face="Arial,Helvetica">Unit 
      Test Results for <?php echo "$label"; ?> </font></b></td>
  </tr>
</table>
<p></p><table border="0">
</table>

<table width="77%" border="1">
  <tr> 
    <td width="81%"><b>Tests Performed</b></td>
    <td width="19%"><b>Errors &amp; Failures</b></td>
  </tr>

  <?
    $dir = dir("html");
    while ($anEntry = $dir->read())
    {
      if ($anEntry != "." && $anEntry != "..")
      {
        $link = "html/".$anEntry;
        $xml = "xml/".substr($anEntry, 0, strlen($anEntry)-4)."xml";
        $count = 0;
        $fileHandle = fopen($xml, "r");
        while (!feof($fileHandle))
        {
          $aLine = fgets($fileHandle, 4096); // Length parameter only optional after 4.2.0
          $count = $count + substr_count($aLine, '<error');
        }
        fclose($fileHandle);
        if ($count > 0)
        {
          echo "<tr><td><a href=\"$link\"><b><font color=\"red\">";
          echo "$anEntry";
          echo "</font></b></a></td>";
          echo "<td><b><font color=\"red\">$count</font></b></td></tr>";
        }
        else
        {
          echo "<tr><td><a href=\"$link\">";
          echo "$anEntry";
          echo "</a></td>";
          echo "<td>$count</td></tr>";
        }
      }
    }
  ?>
  
</table>
<p></p>
<br>
<table border=0 cellspacing=5 cellpadding=2 width="100%" >
  <tr> 
    <td align=LEFT valign=TOP colspan="3" bgcolor="#0080C0"><b><font color="#FFFFFF" face="Arial,Helvetica"> 
      Console output logs 
      <?php echo "$label"; ?>
      </font></b></td>
  </tr>
</table>
<br>
These <a href="consolelogs/wtptestlog.txt">logs</a> contain the console output captured while 
running the JUnit automated tests. <br>
<br>

</body>
</html>
