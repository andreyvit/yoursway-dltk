<?php
$projDetails = array (
	/* path => project's downloads path, downloads page path, includes path, and vanity name */
	"/technology/dltk" => array (
		"/dltk/downloads",
		"/dltk",
		"DLTK"
	),
	"/tools/emf" => array (
		"/emf/downloads",
		"/emf",
		"EMF"
	),
	"/technology/emft" => array (
		"/emft/downloads",
		"/emft",
		"EMFT"
	),
	"/modeling/mdt" => array (
		"/modeling/mdt/downloads",
		"/modeling",
		"MDT"
	)
);

foreach ($projDetails as $searchPath => $details)
{
	if (false !== strpos($_SERVER["SCRIPT_NAME"], $searchPath))
	{
		$projectDownloadsPath = $searchPath;
		$projectDownloadsPagePath = $details[0];
		$projectIncludesPath = $details[1];
		$projectName = $details[2];
		break;
	}
}

$doPhoenix = false;
if (is_file($_SERVER['DOCUMENT_ROOT'] . "/eclipse.org-common/system/app.class.php"))
{
	$doPhoenix = true;
}

//$doPhoenix = false;
$isBuildServer = preg_match("/^(build|emft).eclipse.org$/", $_SERVER["SERVER_NAME"]) || preg_match("/^emf(?:\.torolab\.ibm\.com)$/", $_SERVER["SERVER_NAME"]);

$buildDir = preg_replace("/(.+\/downloads\/drops\/.+\/.+)\/.+\.php/", "$1", $_SERVER["SCRIPT_NAME"]);
$buildName = preg_replace("/.+\/downloads\/drops\/(.+\/.+)/", "$1", $buildDir);
$buildID = preg_replace("/.+\/(.+)/", "$1", $buildName);
$subproj = preg_match("/" . str_replace("/", "\\/", $projectDownloadsPath) . "\/(.+)\/downloads\/drops\/(.+\/.+)/", $buildDir, $matches) ? $matches[1] : "";
$subprojName = strtoupper(str_replace("-", " ", $subproj));

if ($doPhoenix)
{
	require_once ($_SERVER['DOCUMENT_ROOT'] . "/eclipse.org-common/system/app.class.php");
	require_once ($_SERVER['DOCUMENT_ROOT'] . "/eclipse.org-common/system/nav.class.php");
	require_once ($_SERVER['DOCUMENT_ROOT'] . "/eclipse.org-common/system/menu.class.php");
	$App = new App();
	$Nav = new Nav();
	$Menu = new Menu();
	if (is_file($App->getProjectCommon()))
	{
		include ($App->getProjectCommon());
	}
	ob_start();
}
else
{
	print '<html>' . "\n" .
	'<head>' . "\n" .
	'  <title>Test Results for ' . $projectName . ($subprojName ? ' ' . $subprojName : '') . ' Build ' . $buildName . '</title>' . "\n" .
	'  <link rel="stylesheet" type="text/css" href="http://' . ($isBuildServer ? $_SERVER["SERVER_NAME"] : "www.eclipse.org") . $projectIncludesPath . '/includes/downloads.css"/>' . "\n" .
	'  <script src="' . $projectIncludesPath . '/includes/downloads.js" type="text/javascript"></script>' . "\n" .
	'</head>' . "\n" .
	'<body>' . "\n";
}

print '<div id="midcolumn">
<div class="homeitem3col">
<h3>Test Results for ' . $projectName . ($subprojName ? ' ' . $subprojName : '') . ' Build ' . $buildName . '</h3>
<p>For more information on this build, please go to <a href="http://' . ($isBuildServer ? $_SERVER["SERVER_NAME"] : "www.eclipse.org") . $projectDownloadsPagePath . '/?project=' . $subproj . '&amp;showAll=1&amp;hlbuild=' . $buildID . '#' . $buildID . '">this project\'s download page</a>.</p>
<ul class="releases">
';

$catgs = array (
	array (
		"Console Logs",
		"./testresults/consolelogs/",
		".txt"
	),
	array (
		"JUnit Test Results",
		"./testresults/xml/",
		".xml",
		"Errors &amp; Failures"
	),
	array (
		"Compilation Errors",
		"./compilelogs/plugins/",
		".log",
		"Errors &amp; Warnings"
	)
);
foreach ($catgs as $num => $dirBits)
{
	if ($num === 0)
	{
		$files = loadDir($dirBits[1], $dirBits[2]);
		$out = "";
		if (sizeof($files) > 0)
		{
			foreach ($files as $file)
			{
				$out .= '<li><a href="' . $dirBits[1] . $file . '">' . str_replace("_consolelog.txt", "", $file) . '</a></li>' . "\n";

			}
		} else
		{
			$out .= '<li><i><a href="' . $dirBits[1] . '">No console logs found</a></i>.</li>' . "\n";
		}
		print '<li><a href="javascript:toggle(\'e' . $num . '\')">' . $dirBits[0] . '</a>';
		print '<ul id="e' . $num . '"' . '>' . "\n";
		print $out;
		print '</ul>' . "\n";
	} else
		if ($num === 1)
		{
			$files = loadDirChildren($dirBits[1], $dirBits[2]);
			$out = "";
			$noProblems = true;
			foreach ($files as $file)
			{
				$results = getTestResults("./testresults/xml/" . $file);
				$noProblems = $noProblems && !$results;
				$out .= '<li><div>' . $results . '</div><a href="./testresults/html/' . preg_replace("/\.xml$/", ".html", $file) . '">' .
				preg_replace("/\.xml$/", "", $file) . '</a></li>' . "\n";
			}
			print '<li><div><b style="color:' . ($noProblems ? "green" : "red") . '">' . ($noProblems ? "0 " : "") . $dirBits[3] . '</b></div>' .
			'<a href="javascript:toggle(\'e' . $num . '\')">' . $dirBits[0] . '</a>';
			print '<ul id="e' . $num . '"' . ($noProblems ? ' style="display: none"' : '') . '>' . "\n";
			print $out;
			print '</ul>' . "\n";
		} else
			if ($num === 2)
			{
				$files = loadDirChildren($dirBits[1], $dirBits[2]);
				$out = "";
				$summary = getCompileResultsSummary($dirBits[1] . "../summary.txt");
				$noProblems = !$summary;
				foreach ($files as $file)
				{
					$results = getCompileResults($dirBits[1] . $file);
					$noProblems = $noProblems && !$results;
					$out .= '<li><div>' . $results . '</div><a href="' . $dirBits[1] . $file . '">' .
					preg_replace("/((\/@dot|.jar).bin.log|_\d+\.\d+\.\d+\.v\d+)/", "", $file) . '</a></li>' . "\n";
				}
				print '<li><div><b style="color:' . ($noProblems ? "green" : "red") . '">' . ($noProblems ? "0 " : "") . ($summary? $summary : $dirBits[3]) . '</b></div>' .
				'<a href="javascript:toggle(\'e' . $num . '\')">' . $dirBits[0] . '</a>';
				print '<ul id="e' . $num . '"' . ($noProblems ? ' style="display: none"' : '') . '>' . "\n";
				print $out;
				print '</ul>' . "\n";
			}
}

if ($doPhoenix)
{
	$html = ob_get_contents();
	ob_end_clean();

	$pageTitle = "Test Results for " . $projectName . ($subprojName ? ' ' . $subprojName : '') . " Build " . $buildName;
	$pageKeywords = "";
	$pageAuthor = "Nick Boldt";

	$App->AddExtraHtmlHeader('<link rel="stylesheet" type="text/css" href="http://' . ($isBuildServer ? $_SERVER["SERVER_NAME"] : "www.eclipse.org") . $projectIncludesPath . '/includes/downloads.css"/>' . "\n");
	$App->AddExtraHtmlHeader('<script src="http://' . ($isBuildServer ? $_SERVER["SERVER_NAME"] : "www.eclipse.org") . $projectIncludesPath . '/includes/downloads.js" type="text/javascript"></script>' . "\n"); //ie doesn't understand self closing script tags, and won't even try to render the page if you use one
	$App->generatePage(isset ($theme) ? $theme : "", $Menu, $Nav, $pageAuthor, $pageKeywords, $pageTitle, $html);
} else
{
	print "</body></html>\n";

}

function getCompileResultsSummary($file)
{
	if (is_file($file))
	{
		$results = file($file);
		$results = join($results, "");
		$patterns_str = array (
			", 0W",
			", 0E",
			", 0F"
		);
		$patterns = array (
			"/(\d+)P, /" => " Problems (",
			"/(\d+)W/" => " Warnings",
			"/(\d+)E/" => " Errors",
			"/(\d+)F/" => " Failures",
			"/(\d+)P/" => " Problems (?",
		);
		foreach ($patterns_str as $find)
		{
			$results = str_replace($find, "", $results);
		}
		foreach ($patterns as $find => $replace)
		{
			$results = preg_replace($find, "$1" . $replace, $results);
		}
		$results = trim($results);
		return $results ? $results . ")" : null;
	}
	return null;
}

function getCompileResults($file)
{
	$results = "";
	if (is_file($file))
	{
		$results = exec("tail -1 $file");
	}
	if (preg_match("/problem|error|warning/", $results))
	{
		return $results;
	} else
	{
		return "";
	}
}

function getTestResults($file)
{
	$results = "";
	$data = file($file);
	foreach ($data as $line)
	{
		// <testsuite errors="0" failures="0" ...>
		preg_match("/<testsuite errors=\"(\d+)\" failures=\"(\d+).+\"/", $line, $matches);
		if (isset ($matches) && is_array($matches) && sizeof($matches) >= 3)
		{
			$results = $matches[1] === "0" && $matches[2] === "0" ? "" : $matches[1] . "E, " . $matches[2] . "F";
			return $results;
		}
	}
	return $results;
}

function loadDir($dir, $ext)
{
	$stuff = array ();

	if (is_dir($dir) && is_readable($dir))
	{
		$handle = opendir($dir);
		while (($file = readdir($handle)) !== false)
		{
			if (preg_match("/$ext$/", $file) && !preg_match("/^\.{1,2}$/", $file) && is_file("$dir/$file"))
			{
				$stuff[] = $file;

			}
		}
		closedir($handle);
	}

	return $stuff;
}

function loadDirChildren($dir, $ext)
{
	$stuff = array ();

	if (is_dir($dir) && is_readable($dir))
	{
		$handle = opendir($dir);
		while (($file = readdir($handle)) !== false)
		{
			if (preg_match("/$ext$/", $file) && !preg_match("/^\.{1,2}$/", $file) && is_file("$dir/$file"))
			{
				$stuff[] = $file;

			} else
				if (!preg_match("/^\.{1,2}$/", $file) && is_dir("$dir/$file"))
				{
					$children = loadDirChildren("$dir/$file", $ext);
					foreach ($children as $child)
					{
						$stuff[] = $file . "/" . $child;
					}

				}
		}
		closedir($handle);
	}

	return $stuff;
}
?>