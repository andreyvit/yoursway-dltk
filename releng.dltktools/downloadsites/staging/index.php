<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Eclipse Dynamic Languages Toolkit (DLTK) Downloads - for Committers and Early Testers</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <link href="downloads.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">
 <div class="grad">
   <div id="summary">
     <div id="header">
	   <h1>Dynamic Languages Toolkit downloads</h1>
	   <h4>Latest early trial downloads from the Dynamic Languages Toolkit project</h4>
	   <p>This is the starting page for where you can find the latest <a href="http://wiki.eclipse.org/index.php/DLTK_Build_Types">continuous build</a> produced by the <a href="http://www.eclipse.org/dltk">Eclipse Dynamic Languages Toolkit (DLTK) Project</a>.</p>
	   <p>Please note that each build page details the pre-reqs for that particular build.</p>
	   <p>&nbsp;</p>
	   <p>If you got here to this continuous-build site by accident or casual browsing, please be aware that <a href="http://download.eclipse.org/technology/dltk/downloads/">declared builds</a> are available!</p>
	 </div>
   </div>

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

   for ($i = 0; $i < count($dropType); $i++) {
     $dt = $dropType[$i];
     $dt = trim($dt);
     $typeToPrefix[$dt] = $dropPrefix[$i];
   }

  $buildBranches = array();
  $buildBranches[0]="R1.0";
  $buildBranches[1]="R0.9";
  include 'report.php';
  include 'report2.php';
 
  $latestTimeStamp=array();
  $latestFile = array();

  foreach ($buildBranches as $buildBranch ) {

    $aBranchDirectoryName = "drops/".$buildBranch;
    $latestFile[$buildBranch] = array(); //moved here from inside the loop
    if (file_exists($aBranchDirectoryName) && is_dir($aBranchDirectoryName)) {
      $aDirectory = dir($aBranchDirectoryName);
      $latestTimeStamp[$buildBranch] = array();

      while (false !== ($anEntry = $aDirectory->read())) {

       if ($anEntry != "." && $anEntry!="..") {
         $aDropDirectoryName = "drops/".$buildBranch."/".$anEntry;

         if (is_dir($aDropDirectoryName) && is_Readable($aDropDirectoryName)) {
           $aDropDirectory = dir($aDropDirectoryName);
	
		   $fileCount = 0;
		   while ($aDropEntry = $aDropDirectory->read()) {
		     if ( (stristr($aDropEntry, ".tar.gz")) || (stristr($aDropEntry, ".zip")) )  {
		       $fileCount = $fileCount + 1;
			 }
		   }
		
           $aDropDirectory->close();

         }
         $countFile = "drops/".$buildBranch."/".$anEntry."/files.count";
         $indexFile = "drops/".$buildBranch."/".$anEntry."/index.html";


         if (!file_exists($indexFile)) {
           $indexFile = "drops/".$buildBranch."/".$anEntry."/index.php";
         }


         if (file_exists($countFile) && file_exists($indexFile)) {
           $anArray = file($countFile);
           if ($anArray[0] <= $fileCount) {

             $entryParts = explode("-", $anEntry);
             if (count($entryParts) == 3) {
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


			   if ((sizeof($latestTimeStamp[$buildBranch]) > 0) &&
			      (isset($latestTimeStamp[$buildBranch][$buildTypePart])))  {
			     if ($timeStamp > $latestTimeStamp[$buildBranch][$buildTypePart]) {
	               $latestTimeStamp[$buildBranch][$buildTypePart] = $timeStamp;
	               $latestFile[$buildBranch][$buildTypePart] = $anEntry;
	             }
	           }else {
	             $latestTimeStamp[$buildBranch][$buildTypePart] = $timeStamp;
	             $latestFile[$buildBranch][$buildTypePart] = $anEntry;
               }
	         }
           }
         }
       }
     }
    $aDirectory->close();
  }
}
?>
<?php	$dropTypesOrdered = array();
  $dropTypesOrdered[0] = "Maintenance";
  $dropTypesOrdered[1] = "Released";
  $dropTypesOrdered[2] = "Stable";
  $dropTypesOrdered[3] = "Integration";

  echo "<div class=\"blue\">";
  echo "<div id=\"latest\">";
  echo "<table>";
  for($i=0;$i<2;$i++){
    echo "<tr>";
    for($j=0;$j<2;$j++){
      $value = $dropTypesOrdered[$j+$i*2];
      echo "<td id=\"$value[0]\">";
        
	  $prefix=$typeToPrefix[$value];

	  echo "<h3>$value</h3>";
      $alreadyFound = 0;
	  foreach($buildBranches as $bValue) {
        if (array_key_exists($prefix, $latestFile[$bValue]) && ($alreadyFound==0)) {
	      $fileName = $latestFile[$bValue][$prefix];
	      $fileNameParts = explode("-", $fileName);
	      if (sizeof($fileNameParts) > 1) {
	        $alreadyFound = 1;
		    echo "<p>Stream: $bValue</p>";
            echo "<p>Build Name: $fileNameParts[1]</p>";
	        echo "<p>$timeStamps[$fileName]</p>";
	        echo "<a href=\"drops/$bValue/$fileName/\">Download</a>";
	      }
	    }
	  }
	//Not found in any branch
	if ($alreadyFound == 0){
	  echo "<p>Stream: ---</p>";
	  echo "<p>Build Name: ---</p>";
    }
    echo "</td>";    
  }
  echo "</tr>";
}
echo "</table>";
echo "</div>";
echo "</div>";
?>
  </div>
  <div class="white">
   <div id="list">

<?php
  foreach($dropTypesOrdered as $value) {
  $prefix=$typeToPrefix[$value];

  echo "<div id=\"$value[0]_list\">";
  echo"<h3>$value</h3>";
  echo "<table><tr>";
  echo "<th width=\"162\">Build Name</th>";
  echo "<th width=\"127\">Stream</th>";
  echo "<th width=\"190\">Build Date</th>";
  echo "<th width=\"339\">&nbsp;</th>";
  echo "</tr>";
	      
  foreach($buildBranches as $bValue) {
  if (array_key_exists($bValue, $buckets) && $buckets[$bValue] != NULL
     && array_key_exists($prefix, $buckets[$bValue])) {
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

	   echo "<td><a href=\"drops/$bValue/$innerValue/\">$innerValueParts[1]</a></td>";
       echo "<td>$bValue</td>";
       echo "<td>$timeStamps[$innerValue]</td>";

       if (file_exists("drops/$bValue/$innerValue/compilelogsSummary.xml"))
	   {
	     $filename = "drops/$bValue/$innerValue/compilelogsSummary.xml";
	     $prefix = "code_";
		 $compileSummary = simplexml_load_file($filename);
		 foreach ($compileSummary->summaryItem as $summaryItem) {
		   $name = $summaryItem->name;
		   $value = $summaryItem->value;
		   $code= "\$" . $prefix . $name . " = " . $value . ";";
		   eval($code);
		 }
         $filename = "drops/$bValue/$innerValue/testcompilelogsSummary.xml";
         $prefix = "test_";
         $compileSummary = simplexml_load_file($filename);
	     foreach ($compileSummary->summaryItem as $summaryItem) {
		   $name = $summaryItem->name;
		   $value = $summaryItem->value;
		   $code= "\$" . $prefix . $name . " = " . $value . ";";
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

		 echo "<td><img src=\"plugins.png\"/>$totalBundles";
				                 
		 echo "<img src=\"compile_err";
		 if ($totalCommpileErrors <= 0) echo "_false";
		   echo ".png\"/>$totalCommpileErrors";
			  
		   echo "<img src=\"compile_warn";
		   if ($totalCompileOtherWarnings <= 0) echo "_false";
			 echo ".png\"/>$totalCompileOtherWarnings";
																	                 
			 echo "<img src=\"access_err";
			 if ($totalForbidden <= 0) echo "_false";
			   echo ".png\"/>$totalForbidden";
																							                 
			   echo "<img src=\"access_warn";
               if ($totalDiscouraged <= 0) echo "_false";
		         echo ".png\"/>$totalDiscouraged";
			                 
				 if (isset($unittest_grandTotalErrors)) {
				   echo "<img src=\"junit_err";
 				   if ($unittest_grandTotalErrors <= 0) echo "_false";
                   echo ".png\"/>$unittest_grandTotalErrors ($unittest_grandTotalTests)";
                 }
		         echo "</td>";
			   }
			   echo "</tr>";
		     }
		   }
		 }
	   }
	 echo "</table>";
     echo "</div>";
   }?>
  <div id="legend">
    <table>
      <tr>
      <td><img src="plugins.png"/>- Plug-Ins</td>
      <td><img src="compile_err.png"/>- Compilation Errors</td>
    </tr>
    <tr>
      <td><img src="compile_warn.png"/>- Compilation Warnings</td>
 	  <td><img src="access_err.png"/>- Access Violation</td>
    </tr>
    <tr>
	  <td><img src="access_warn.png"/>- Access Warning</td>
      <td><img src="junit_err.png"/>- JUnit Failures</td>
    </tr>
  </table>
  </div>
</div>
  <div style="clear:both;height:30px"/>
   </div>
    <div class="blue">
  <div id="footer">
  <p>All downloads are provided under the terms and conditions of the Eclipse.org Software User Agreement unless otherwise specified.</p>
  <p>If you have problems downloading the drops, contact the webmaster.</p>
</div>
 </div>
 </div>
 </body>
 </html>
