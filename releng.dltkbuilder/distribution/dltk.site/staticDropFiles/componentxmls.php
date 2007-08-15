<html>
<head>
<?php
  function find_component_xml($directory)
  {
    $count = 0;
    $dir = dir($directory);
    while ($anEntry = $dir->read())
    {
      if ($anEntry != "." && $anEntry != "..")
      {
        $anEntry = $directory."/".$anEntry;
        if (stristr($anEntry, '.source') === FALSE)
        {
          if (is_dir($anEntry))
          {
            find_component_xml($anEntry);
          }
          else
          {
            echo "<tr><td><a href=\"$anEntry\">";
            echo substr($anEntry, 22);
            echo "</a></td>";
          }
        }
      }
    }
  }
?>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="http://dev.eclipse.org/default_style.css" type="text/css">
</head>
<body>

<p><b><font face="Verdana" size="+3">component.xml</font></b> </p>

<table border=0 cellspacing=5 cellpadding=2 width="100%" >
  <tr> 
    <td align=LEFT valign=TOP colspan="3" bgcolor="#0080C0"><b><font color="#FFFFFF" face="Arial,Helvetica">
	   component.xml files for <?php echo "$buildType $buildName"; ?></font></b></td>
  </tr>
</table>

<table border="1" width="100%">
  <tr>
    <th>component.xml</th>
  </tr>
<?php
  find_component_xml("apitools/componentxmls");
?>
</table>
</body>
</html>
