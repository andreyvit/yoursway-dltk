<?php
  function count_pattern($directory, $filenameFilter, $pattern)
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
          $count += count_pattern($anEntry, $filenameFilter, $pattern);
        }
        else
        {
          if (stristr($anEntry, $filenameFilter))
          {
            $handle = @fopen($anEntry, "r");
            if (FALSE !== $handle) {
                   $size = filesize($anEntry);
                   $content = fread($handle, $size);
                   fclose($handle);
                   $count += substr_count($content, $pattern);
          }
        }
      }
    }
    }
    return $count;
  }

  function parse_testResult($filename)
  {
    $junitFailures = 0;
    $compileErrors = 0;
    $compileWarnings = 0;
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
      $compileStart = strpos($content, "Compile Logs (Jar Files)");
      $compileEnd = strpos($content, "</table>", $compileStart);
      $compileInfo = substr($content, $compileStart, $compileEnd - $compileStart);
      $rowStart = strpos($compileInfo, "<tr>");
      while ($rowStart !== false)
      {
        $start += 4;
        $rowStop = strpos($compileInfo, "</tr>", $rowStart);
        if ($rowStop !== false)
        {
          $row = substr($compileInfo, $rowStart, $rowStop - $rowStart);
          $cellStart = strpos($row, "<td");
          $gotError = false;
          $gotWarning = false;
          while ($cellStart !== false && (!$gotError || !$gotWarning))
          {
            // this parsing logic got a bit more complicated in M2_33 basebuild, as the
            // tag <td align="center"> was used, instead of <td>
            // $cellStart += 4;
            $cellStart = strpos($row, ">", $cellStart);
            $cellStart = $cellStart + 1;
            $cellStop = strpos($row, "</td>", $cellStart);
            if ($cellStop !== false)
            {
              $cell = substr($row, $cellStart, $cellStop - $cellStart);
              if (is_numeric($cell))
              {
                if (!$gotError)
                {
                  $compileErrors += $cell;
                  $gotError = true;
                }
                else if (!$gotWarning)
                {
                  $compileWarnings += $cell;
                  $gotWarning = true;
                }
              }
            }
             // this parsing logic got a bit more complicated in M2_33 basebuild, as the
            // tag <td align="center"> was used, instead of <td>
            $cellStart = strpos($row, "<td", $cellStop);
          }
        }
        $rowStart = strpos($compileInfo, "<tr>", $rowStop);
      }
    }
    }
    $results = array($compileErrors, $compileWarnings, $junitFailures);
    return $results;
  }

  function parse($filename, $key)
  {
    if (!is_readable($filename))
    {
      return 0;
    }
    $value;
    $handle = @fopen($filename, "r");
    if (!$handle)
    {
      return 0;
    }
    $size = filesize($filename);
    $content = fread($handle, $size);
    fclose($handle);
    $start = strpos($content, $key);
    while ($start !== false)
    {
      $start += strlen($key);
      $stop = strpos($content, "\"", $start);
      if ($stop !== false)
      {
        $value += substr($content, $start, $stop - $start);
      }
      $start = strpos($content, $key, $stop);
    }
    return $value;
  }
?>
