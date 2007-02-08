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
	'  <title>' . $projectName . ($subprojName ? ' ' . $subprojName : '') . ' Build ' . $buildName . '</title>' . "\n" .
	'  <link rel="stylesheet" type="text/css" href="http://' . ($isBuildServer ? $_SERVER["SERVER_NAME"] : "www.eclipse.org") . $projectIncludesPath . '/includes/downloads.css"/>' . "\n" .
	'</head>' . "\n" .
	'<body>' . "\n";
}

print '<div id="midcolumn">
<div class="homeitem3col">
<h3>' . $projectName . ($subprojName ? ' ' . $subprojName : '') . ' Build ' . $buildName . '</h3>
<p>For more information on this build, please go to <a href="http://' . ($isBuildServer ? $_SERVER["SERVER_NAME"] : "www.eclipse.org") . $projectDownloadsPagePath . '/?project=' . $subproj . '&amp;showAll=1&amp;hlbuild=' . $buildID . '#' . $buildID . '">this project\'s download page</a>.</p>
<ul class="releases">
';

$files = loadDownloadsDir("./", "\.(cfg|txt|php|html|zip)$");
if (sizeof($files) > 0)
{
	foreach ($files as $catg => $fileset)
	{
		print '<li>' . $catg . '<ul>' . "\n";
		foreach ($fileset as $file)
		{
			if (false === strpos($file, "index.")) // exclude index file
			{
				if (preg_match("/.+\.(txt|cfg|html)$/", $file))
				{
					$filesize = pretty_size(filesize($_SERVER['DOCUMENT_ROOT'] . '/' . $buildDir . '/' . $file));
					if ($doPhoenix)
					{
						print '<li><div>' . $filesize . '</div><a href="' . $file . '">' . $file . '</a></li>' . "\n";
					}
					else
					{
						print '<li><a href="' . $file . '">' . $file . '</a> ' . $filesize . '</li>' . "\n";
					}
				}
				else
				{
					if (false !== strpos($file, ".zip"))
					{
						$filesize = pretty_size(filesize($_SERVER['DOCUMENT_ROOT'] . '/' . $buildDir . '/' . $file));
						if ($doPhoenix)
						{
							print '<li><div>' . $filesize . ' (<a href="' . $file . '.md5">md5</a>)</div><a href="' . ($isBuildServer ? "" : 'http://www.eclipse.org/downloads/download.php?file=') . $buildDir . '/' . $file . '">' . $file . '</a></li>' . "\n";
						}
						else
						{
							print '<li><a href="' . ($isBuildServer ? "" : 'http://www.eclipse.org/downloads/download.php?file=') . $buildDir . '/' . $file . '">' . $file . '</a> ' . $filesize . ' (<a href="' . $file . '.md5">md5</a>)</li>' . "\n";
						}
					}
					else
					{
						print '<li><a href="' . $file . '">' . $file . '</a></li>' . "\n";
					}
				}
			}
		}
		print '</ul></li>' . "\n";
		}
	}
else
{
	print "<li>No files found!</li>\n";
}
print "</ul>\n";

print "</div></div>\n";

if ($doPhoenix)
{
	$html = ob_get_contents();
	ob_end_clean();

	$pageTitle = $projectName . ($subprojName ? ' ' . $subprojName : '') . " Build " . $buildName;
	$pageKeywords = "";
	$pageAuthor = "Nick Boldt";

	$App->AddExtraHtmlHeader('<link rel="stylesheet" type="text/css" href="http://' . ($isBuildServer ? $_SERVER["SERVER_NAME"] : "www.eclipse.org") . $projectIncludesPath . '/includes/downloads.css"/>' . "\n");
	$App->generatePage(isset ($theme) ? $theme : "", $Menu, $Nav, $pageAuthor, $pageKeywords, $pageTitle, $html);
}
else
{
	print "</body></html>\n";

}

/* this function copied from /www/modeling/includes/downloads-common.php */
function pretty_size($bytes)
{
	$sufs = array (
		"B",
		"K",
		"M",
		"G",
		"T",
		"P"
	); //we shouldn't be larger than 999.9 petabytes any time soon, hopefully
	$suf = 0;

	while ($bytes >= 1000)
	{
		$bytes /= 1024;
		$suf++;
	}

	return sprintf("%3.1f%s", $bytes, $sufs[$suf]);
}

function loadDownloadsDir($dir, $ext)
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

	if (sizeof($stuff) > 0)
	{
		/* sort by extension then filename, zips first */
		$sortedstuff = array (
			"Zip Files" => array (),
			"Build Details" => array ()
		);
		foreach ($stuff as $file)
		{
			$sortedstuff[(false !== strpos($file, ".zip") ? "Zip Files" : "Build Details")][preg_replace("/.+\.([^\.]+)/", "$1", $file) . $file] = $file;
		}
		ksort($sortedstuff["Zip Files"]);
		reset($sortedstuff["Zip Files"]);
		ksort($sortedstuff["Build Details"]);
		reset($sortedstuff["Build Details"]);
		return $sortedstuff;
	}
	else
	{
		return $stuff;
	}
}
?>