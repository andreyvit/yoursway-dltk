<html>
<head>
<link rel="stylesheet" href="http://dev.eclipse.org/default_style.css">
<title>Eclipse Dynamic Languages Toolkit (DLTK) Downloads</title>
</head>
<body>

<!-- heading start -->
<?php

// tiny banner to remind when looking at "local" machine results
$serverName = $_SERVER["SERVER_NAME"];

if (!stristr($serverName, "eclipse.org") && !stristr($serverName,"you.are.at.eclipsecon.org")) {
  echo '<center><p>Reminder: this is <font color="#FF0000">', $serverName,'</font>  See also <a href="http://download.eclipse.org/technology/dltk/downloads" target="_top">Live public eclipse site</a>.</center><hr />';
}

if (function_exists("date_default_timezone_set")) {
  date_default_timezone_set("UTC");
  //echo "<p>default timezone: ";
  //echo date_default_timezone_get();
  //echo "</p>";
}

?>


<?php
//ini_set("display_errors", "true");
//error_reporting (E_ALL);
?>

<?php
$QString = $_SERVER['QUERY_STRING'];
$C = strcmp($QString, "test");
?>


<table border=0 cellpadding=0 width="100%">
<tr>
<td width="35%"><font class=indextop> Dynamic Languages Toolkit<br>
downloads</font><br>
<font class=indexsub>Latest downloads from the Dynamic Languages Toolkit Project</font></td>
<td width="35%" align="right"><a href="http://www.eclipse.org/dltk"><img border="0"src="http://www.eclipse.org/images/egg-incubation.png" height="95" width="207"></a></td>
</tr>
</table>

<!-- heading end -->


<hr />
<table border=0 cellpadding=2 width="70%" align="center">
	<tr>
		<td>
		<p>This is the starting page for where you can find the latest <a
			href="http://wiki.eclipse.org/index.php/DLTK_Build_Types">declared build</a> produced by the <a
			href="http://www.eclipse.org/dltk">Eclipse Dynamic Languages Toolkit (DLTK) Project</a>.</p>
		<p>Please note that each declared-build page details the pre-reqs for
		that particular build. The DLTK 0.8.x builds go with Eclipse 3.2 based
		pre-reqs, and the DLTK 0.9.x and 1.0.x builds go with Eclipse 3.3 based pre-reqs.</p>

		<p>As an alternative to downloading zips from the build pages, our
		released builds can be <a
			href="http://download.eclipse.org/technology/dltk/updates/">installed via
		Update Manager</a>, from an existing installation of Eclipse.</p>
		</td>
	</tr>
</table>




<?php
$contents = substr(file_get_contents('dlconfig.txt'),0,-1);
$contents = str_replace("\n", "", $contents);

#split the content file by & and fill the arrays
$elements = explode("&",$contents);
$t = 0;
$p = 0;
for ($c = 0; $c < count($elements); $c++) {
  $tString = "dropType";
  $pString = "dropPrefix";
  if (strstr($elements[$c],$tString)) {
    $temp = preg_split("/=/",$elements[$c]);
    $dropType[$t] = trim($temp[1]);
    $t++;
  }
  if (strstr($elements[$c],$pString)) {
    $temp = preg_split("/=/",$elements[$c]);
    $dropPrefix[$p] = trim($temp[1]);
    $p++;
  }
}

// debug
// echo "Debug: droptype count: ", count($dropType), "<br />";

for ($i = 0; $i < count($dropType); $i++) {
  $dt = $dropType[$i];
  $dt = trim($dt);
  $typeToPrefix[$dt] = $dropPrefix[$i];

  //   echo "Debug prefix: ", $dropPrefix[$i], "<br />";
  //   echo "Debug dropType: ", $dropType[$i], "<br />";

}

$buildBranches = array();
$buildBranches[0]="R1.0";
$buildBranches[1]="R0.9";
$buildBranches[2]="R0.8";
include 'report.php';
include 'report2.php';

$latestTimeStamp=array();
$latestFile = array();

echo "<table border=0 cellpadding=2 width=\"100%\"><tr>";
echo "<td align=\"center\" bgcolor=\"#0080C0\"><b><font color=\"#FFFFFF\" face=\"Arial,Helvetica\">";
echo "Latest Downloads";
echo "</td></font></b>";
echo "</tr></table>";



foreach ($buildBranches as $buildBranch ) {

  $aBranchDirectoryName = "drops/".$buildBranch;
  if (file_exists($aBranchDirectoryName) && is_dir($aBranchDirectoryName)) {
    $aDirectory = dir($aBranchDirectoryName);
    $latestTimeStamp[$buildBranch] = array();
    $latestFile[$buildBranch] = array();


    while (false !== ($anEntry = $aDirectory->read())) {

      // Short cut because we know aDirectory only contains other directories.
      if ($anEntry != "." && $anEntry!="..") {
        // echo "Debug anEntry: $anEntry<br />" ;
        $aDropDirectoryName = "drops/".$buildBranch."/".$anEntry;


        if (is_dir($aDropDirectoryName) && is_Readable($aDropDirectoryName)) {
          $aDropDirectory = dir($aDropDirectoryName);
          //echo "Debug aDropDirectory: $aDropDirectory->path <br />" ;

          $fileCount = 0;
          while ($aDropEntry = $aDropDirectory->read()) {
            // echo "Debug aDropEntry: $aDropEntry<br />" ;
            if ( (stristr($aDropEntry, ".tar.gz")) || (stristr($aDropEntry, ".zip")) )  {
              // Count the dropfile entry in the directory (so we won't display links, if not all there
              $fileCount = $fileCount + 1;
            }
          }

          $aDropDirectory->close();

        }
        // Read the count file
        $countFile = "drops/".$buildBranch."/".$anEntry."/files.count";
        $indexFile = "drops/".$buildBranch."/".$anEntry."/index.html";


        if (!file_exists($indexFile)) {
          $indexFile = "drops/".$buildBranch."/".$anEntry."/index.php";
        }


        if (file_exists($countFile) && file_exists($indexFile)) {
          $anArray = file($countFile);
          // debug
          //echo "Number according to files.count: ", $anArray[0];
          //echo "   actual counted files: ", $fileCount;

          // If a match - process the directoryVV -- we simply look that there's more
          // zip's than we expect, since it frequently breaks where the count is slighly
          // off, such as when we add, after the fact, an all-in-one zip.
          if ($anArray[0] <= $fileCount) {
            // debug
            //echo "yes, counted equaled expected count<br>";

            $entryParts = explode("-", $anEntry);
            if (count($entryParts) == 3) {
              // debug
              //echo "yes, counted parts was 3<br>";
              $buildTypePart = $entryParts[0];
              $buckets[$buildBranch][$buildTypePart][] = $anEntry;

              $timePart = $entryParts[2];
              $year = substr($timePart, 0, 4);
              $month = substr($timePart, 4, 2);
              $day = substr($timePart, 6, 2);
              $hour = substr($timePart,8,2);
              $minute = substr($timePart,10,2);

              $newTimePart = "$year-$month-$day $hour:$minute UTC";

              $timeStamp = strtotime($newTimePart);

              $timeStamps[$anEntry] = gmdate("D, j M Y -- H:i  \(\U\T\C\)", $timeStamp);

              // debug
              //  echo "<br />buildBranch:  $buildBranch <br />";
              //  echo "<br />parts[0]:  -$buildTypePart- <br />";
              //  echo "latestTimeStamp[buildBranch]:";
              //  echo $latestTimeStamp[$buildBranch];
              //  echo "latestTimeStamp:";
              //  echo $latestTimeStamp;

              if ((sizeof($latestTimeStamp[$buildBranch]) > 0) &&
              (isset($latestTimeStamp[$buildBranch][$buildTypePart])))
              {
                if ($timeStamp > $latestTimeStamp[$buildBranch][$buildTypePart])
                {
                  $latestTimeStamp[$buildBranch][$buildTypePart] = $timeStamp;
                  $latestFile[$buildBranch][$buildTypePart] = $anEntry;
                }
              }
              else
              {
                $latestTimeStamp[$buildBranch][$buildTypePart] = $timeStamp;
                $latestFile[$buildBranch][$buildTypePart] = $anEntry;

              }
            }

          }
        }

      }
    }

    $aDirectory->close();
  }}

?>


<table width="70%" align="center" cellpadding=2>
<tr>
<td width="25%"><b>Build Type</b></td>
<td width="25%"><b>Build Name</b></td>
<td width="15%"><b>Stream</b></td>
<td width="40%"><b>Build Date</b></td>
</tr>



<?php
foreach($dropType as $value) {
  $prefix=$typeToPrefix[$value];

  foreach($buildBranches as $bValue) {

    if (array_key_exists($prefix, $latestFile[$bValue])) {
      $fileName = $latestFile[$bValue][$prefix];
      echo "<tr>";
      echo "<td width=\"25%\">$value</td>";


      $fileNameParts = explode("-", $fileName);

      if (sizeof($fileNameParts) > 1) {
        // Uncomment the line below if we need click through licenses.
        // echo "<td><a href=license.php?license=drops/$bValue/$fileName>$parts[1]</a></td>";

        // Comment the line below if we need click through licenses.
        echo "<td  width=\"25%\"><a href=\"drops/$bValue/$fileName/\">$fileNameParts[1]</a></td>";
        echo "<td width=\"15%\">$bValue</td>";
        echo "<td width=\"40%\">$timeStamps[$fileName]</td>";
        echo "</tr>";
      }
    }
  }
}
?>

<table border="0" cellpadding="2" width="100%">
<tr>
<td bgcolor="#0080C0">
&nbsp;
</td>
</tr></table>


</table>

<table border="0" cellpadding="2" width="100%">
	<tr>
		<td align="center" bgcolor="#999999"><b><font color="#FFFFFF" face="Arial,Helvetica">Recent History</b></font></td>
	</tr>
</table>


<?php
foreach($dropType as $value) {
  $prefix=$typeToPrefix[$value];


  echo "
                <table width=\"100%\" cellpadding=2>
                <tr bgcolor=\"#999999\">
                <td align=left><b><a name=\"$value\"><font color=\"#FFFFFF\" face=\"Arial,Helvetica\">";
  echo "$value";
  echo "</font></a></b></td>";
  echo "</tr>";

  echo "<tr>
                <td align=left>
                <table  width=\"100%\" cellpadding=2>
                <tr>
                <td width=\"13%\"><b>Build Name</b></td>
								<td width=\"8%\"><b>Stream</b></td>
                <td width=\"20%\"><b>Build Date</b></td>
                <td></td>
                </tr>";

  foreach($buildBranches as $bValue) {
    if (array_key_exists($bValue, $buckets) && $buckets[$bValue] != NULL
    && array_key_exists($prefix, $buckets[$bValue])) {
      echo "<tr><td colspan=\"11\"/><hr/></tr>";
      $aBucket = $buckets[$bValue][$prefix];
      if (isset($aBucket)) {
        rsort($aBucket);

        $i = 0;
        $ts = array();
        $ts2iv = array();
        foreach($aBucket as $iv) {
          $ivParts = explode("-", $iv);
          $ts[$i] = $ivParts[2];
          $ts2iv[$ts[$i]] = $iv;
          $i++;
        }

        rsort($ts);
        $i = 0;
        $aBucket = array();
        foreach($ts as $tsvalue) {
          $aBucket[$i] = $ts2iv[$tsvalue];
          $i++;
        }

        foreach($aBucket as $innerValue) {
          
          $innerValueParts = explode("-", $innerValue);
          echo "<tr>";

          // Uncomment the line below if we need click through licenses.
          // echo "<td><a href=\"license.php?license=drops/$bValue/$innerValue\">$innerValueParts[1]</a></td>";

          // Comment the line below if we need click through licenses.
          echo "<td width=\"13%\"><a href=\"drops/$bValue/$innerValue/\">$innerValueParts[1]</a></td>";
          echo "<td width=\"8%\">$bValue</td>";
          echo "<td width=\"20%\">$timeStamps[$innerValue]</td>";
          echo "<td></td>";

          // if compilelogsSummary.xml exists, assume the "new way" (summary in xml file).
          // else, assume old way
          //echo "drops/$bValue/$innerValue/compilelogsSummary.xml";
          if (file_exists("drops/$bValue/$innerValue/compilelogsSummary.xml"))
          {
            $filename = "drops/$bValue/$innerValue/compilelogsSummary.xml";
            $prefix = "code_";
            $compileSummary = simplexml_load_file($filename);
            foreach ($compileSummary->summaryItem as $summaryItem) {
              $name = $summaryItem->name;
              $value = $summaryItem->value;
              $code= "\$" . $prefix . $name . " = " . $value . ";";
              //echo "<br />code: " . $code;
              eval($code);
            }
            //echo "drops/$bValue/$innerValue/testcompilelogsSummary.xml";
            $filename = "drops/$bValue/$innerValue/testcompilelogsSummary.xml";
            $prefix = "test_";
            $compileSummary = simplexml_load_file($filename);
            foreach ($compileSummary->summaryItem as $summaryItem) {
              $name = $summaryItem->name;
              $value = $summaryItem->value;
              $code= "\$" . $prefix . $name . " = " . $value . ";";
              //echo "<br />code: " . $code;
              eval($code);
            }

            if (file_exists("drops/$bValue/$innerValue/unitTestsSummary.xml")) {
              $filename = "drops/$bValue/$innerValue/unitTestsSummary.xml";
              $prefix = "unittest_";
              $unitTestsSummary = simplexml_load_file($filename);
              foreach ($unitTestsSummary->summaryItem as $summaryItem) {
                $name = $summaryItem->name;
                $value = $summaryItem->value;
                $code= "\$" . $prefix . $name . " = " . $value . ";";
                // echo "<br />code: " . $code;
                eval($code);
              }
            }
            else {
              unset($unittest_grandTotalErrors, $unittest_grandTotalTests);
            }

            $totalCommpileErrors = $code_totalErrors + $test_totalErrors;
            $totalCompileOtherWarnings = $code_totalWarnings;
            $totalBundles = $code_totalBundles + $test_totalBundles;
            $totalForbidden = $code_totalforbiddenAccessWarningCount + $test_totalforbiddenAccessWarningCount;
            $totalDiscouraged = $code_totaldiscouragedAccessWarningCount + $test_totaldiscouragedAccessWarningCount;

            echo "<td width=\"6%\">($totalBundles)</td>";
            echo "<td width=\"6%\"><img src=\"compile_err.gif\" width=\"16\" height=\"16\"/><font color=red>$totalCommpileErrors</font></td>";
            echo "<td width=\"6%\"><img src=\"compile_warn.gif\" width=\"16\" height=\"16\"/><font color=orange>$totalCompileOtherWarnings</font></td>";
            echo "<td width=\"6%\"><img src=\"access_err.gif\" width=\"16\" height=\"16\"/><font color=red>$totalForbidden</font></td>";
            echo "<td width=\"6%\"><img src=\"access_warn.gif\" width=\"16\" height=\"16\"/><font color=orange>$totalDiscouraged</font></td>";

            if (isset($unittest_grandTotalErrors)) {
              echo "<td width=\"6%\"><img src=\"junit_err.gif\" width=\"16\" height=\"16\"/><font color=red>$unittest_grandTotalErrors</font></td>";
              echo "<td width=\"6%\">($unittest_grandTotalTests)</td>";
            }
            else {
              echo "<td width=\"6%\"><img src=\"pending.gif\" width=\"16\" height=\"16\"/></td>";
              echo "<td width=\"6%\"><img src=\"pending.gif\" width=\"16\" height=\"16\"/></td>";
            }
            echo "</tr>";
          }
          // if compileResults.php exists, assume the "new way" (testResults and compileResult seperated).
          // else, assume old way
          else if (file_exists("drops/$bValue/$innerValue/compileResults.php"))
          {
            $testResults = parse2_testResults("drops/$bValue/$innerValue/testResults.php");
            list ($junitFailures) = $testResults;

            $compileResults = parse2_compileResults("drops/$bValue/$innerValue/compileResults.php");
            list ($compileErrors, $compileAccessWarnings, $compileOtherWarnings) = $compileResults;
            $testCompileResults = parse2_compileResults("drops/$bValue/$innerValue/testCompileResults.php");
            list ($testCompileErrors, $testCompileAccessWarnings, $testCompileOtherWarnings) = $testCompileResults;

            $totalCommpileErrors = $compileErrors + $testCompileErrors;
            // we'll just use code for warnning summaries, for now
            $totalAccessWarnings = $compileAccessWarnings;
            $totalCompileOtherWarnings = $compileOtherWarnings;
             
            echo "<td width=\"6%\">&nbsp;</td>";
            echo "<td width=\"6%\"><img src=\"compile_err.gif\" width=\"16\" height=\"16\"/><font color=red>$totalCommpileErrors</font></td>";
            echo "<td width=\"6%\"><img src=\"compile_warn.gif\" width=\"16\" height=\"16\"/><font color=orange>$totalCompileOtherWarnings</font></td>";

            if ($junitFailures < 0) {
            	echo "<td width=\"6%\"><img src=\"pending.gif\" width=\"16\" height=\"16\"/><font color=red>&nbsp;</font></td>";
            }
            else {
            	echo "<td width=\"6%\"><img src=\"junit_err.gif\" width=\"16\" height=\"16\"/><font color=red>$junitFailures</font></td>";
            }

            echo "</tr>";

          }
          else {
            $testResults = parse_testResult("drops/$bValue/$innerValue/testResults.php");
            list ($compileErrors, $compileWarnings, $junitFailures) = $testResults;

            echo "<td width=\"6%\">&nbsp;</td>";
            echo "<td width=\"6%\"><img src=\"compile_err.gif\" width=\"16\" height=\"16\"/><font color=red>$compileErrors</font></td>";
            echo "<td width=\"6%\"><img src=\"compile_warn.gif\" width=\"16\" height=\"16\"/><font color=orange>$compileWarnings</font></td>";
           	echo "<td width=\"6%\"><img src=\"junit_err.gif\" width=\"16\" height=\"16\"/><font color=red>$junitFailures</font></td>";

            echo "</tr>";
          }
        }
      }}}
      echo "</table></table>";
}
?>

<table border="0" cellpadding="2" width="100%">
	<tr>
		<td bgcolor="#999999">&nbsp;</td>
	</tr>
</table>


<table border="0" width="100%" cellpadding="2">
	<tbody>
		<tr>
			<td valign="top" width="40%">
			
			
			<table border="0" cellpadding="2">
				<tbody>
					<tr>
						<td width="100%" bgcolor="#0080c0" valign="top"><b><font
							face="Arial,Helvetica"><font color="#ffffff">Download Related
						Links</font> </font></b></td>
					</tr>
					<tr>
						<td valign="top">


						<p><a
							href="http://archive.eclipse.org/webtools/downloads/index.php"
							target="_top">Archived Builds</a> are previously significant
						builds that are no longer required, but which we keep on a
						non-mirrored site, for historical and academic use.</p>

						<p><a href="http://download.eclipse.org/webtools/committers/"
							target="_top">Continuous builds</a> are also available which are for
						committers and early testers.</p>	
						</td>
					</tr>
				</tbody>
			</table>
			</td>
			
			<td />
			
			<td width="45%" valign="top">
			<table border="0" cellpadding="2">
				<tbody>
					<tr>
						<td width="100%" colspan="4" bgcolor="#0080c0" valign="top"><b><font
							face="Arial,Helvetica"><font color="#ffffff">Metrics Legend</font>
						</font></b></td>
					</tr>




					<tr>
							<td valign="top"><img src="compile_err.gif" width="16" height="16"  /></td>
							<td valign="top" width="50%">compilation errors.</td>
							<td valign="top"><img src="compile_warn.gif" width="16" height="16" /></td>
							<td valign="top" width="50%">compilation warnings.</td>
					</tr>
					<tr>
							<td valign="top"><img src="access_err.gif"  width="16" height="16"  /></td>
							<td valign="top" width="50%">Access Rule Violations</td>
							<td valign="top"><img src="access_warn.gif" width="16" height="16"  /></td>
							<td valign="top" width="50%">Access Rule Warnings</td>
					</tr>
					<tr>
							<td valign="top"><img src="junit_err.gif"  width="16" height="16" /></td>
							<td valign="top" width="50%">junit failures.</td>
						<td valign="top"><img src="pending1.gif" width="16" height="16" /></td>
							<td valign="top" width="50%">Pending results</td>
					</tr>
					<tr>
						  <td valign="top"><img src="api_err.gif"  width="16" height="16" /></td>
							<td valign="top" width="50%">API violations.</td>
							
							<td valign="top"><img src="api_junit.gif"  width="16" height="16" /></td>
							<td valign="top" width="50%">APIs without junit testcases.</td>
						</tr>
						<tr>						
							<td valign="top"><img src="api_removed.gif"  width="16" height="16" /></td>
							<td valign="top" width="50%">removed APIs.</td>
					</tr>
					<tr>
						<td valign="top"><b>(</b>nnn<b>)</b></td>
						<td valign="top">Number of bundles, jars, and wars, or number of
						unit tests.</td>
						<td valign="top"><b>NA</b></td>
						<td valign="top"><b>N</b>ot <b>A</b>vailable</td>
					</tr>




				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>


<!-- footer -->
<center>
<hr />
<p>All downloads are provided under the terms and conditions of the <a
	href="http://www.eclipse.org/legal/notice.html">Eclipse.org Software
User Agreement</a> unless otherwise specified.</p>

<p>If you have problems downloading the drops, contact the <font
	size="-1" face="arial,helvetica,geneva"><a
	href="mailto:webmaster@eclipse.org">webmaster</a></font>.</p>
</center>
<!-- end footer -->


</body>
</html>
<?php
if (isset($old_error_handler) && sizeof($old_error_handler)) {
  set_error_handler($old_error_handler);
}
?>
