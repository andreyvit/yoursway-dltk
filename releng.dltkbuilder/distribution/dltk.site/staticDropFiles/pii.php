<html>
<head>
<?php
  function write_unused_property($directory)
  {
    $count = 0;
    $dir = dir($directory);
    while ($anEntry = $dir->read())
    {
      if ($anEntry != "." && $anEntry != "..")
      {
        $anEntry = $directory."/".$anEntry;
        if (is_dir($anEntry))
        {
          write_unused_property($anEntry);
        }
        else
        {
          echo "<tr><td><a href=\"$anEntry\">";
          echo substr($anEntry, 16);
          echo "</a></td>";
          $lines = file($anEntry);
          $count = count($lines);
          echo "<td>$count</td></tr>";
        }
      }
    }
  }
?>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="http://dev.eclipse.org/default_style.css" type="text/css">
</head>
<body>

<p><b><font face="Verdana" size="+3">Unused property messages</font></b> </p>

<table border=0 cellspacing=5 cellpadding=2 width="100%" >
  <tr> 
    <td align=LEFT valign=TOP colspan="3" bgcolor="#0080C0"><b><font color="#FFFFFF" face="Arial,Helvetica">
	   Unused property messages for <?php echo "$buildType $buildName"; ?></font></b></td>
  </tr>
</table>

<table border="1">
  <tr>
    <th>Properties file</th>
    <th>Unused strings</th>
  </tr>
<?php
  write_unused_property("piitools");
?>
</table>
</body>
</html>
