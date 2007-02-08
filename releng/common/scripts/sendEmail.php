<?php // script to deliver any queued mail to users alerting them that their build is ready.
/*
/usr/bin/php -q /home/www-data/emf-build/scripts/sendEmail.php -email codeslave@ca.ibm.com -projectName emf
for a build:
	-buildType R
	-buildTimestamp 200507070200
for a promote:
	-promote true
	-branch 2.1.0
	-buildID R200507070200
for a test:
	-testType [perf|jdk13|jdk14|jdk50] 
	-testTimestamp 200507071234
*/
?>

<?php 
	$mailEnabled = 1;

	$perfServerDefault = "emftest03.torolab.ibm.com";
	$testServerDefault = "emf.torolab.ibm.com";
	$buildServerDefault= "emf.torolab.ibm.com";
	$promoServerDefault= "download.eclipse.org";

	/* ******************************************************************************* */

	// shouldn't have to change anything below here.

	$date = date('H:i');
	
	$args = getPairs();	//wArr($args); // get cmdline options
	$HOSTNAME			= array_key_exists("hostname",$args)?$args["hostname"]:0;
	$debug				= array_key_exists("debug",$args)?$args["debug"]:0;
	$email				= array_key_exists("email",$args)?$args["email"]:"";
	$projectName		= array_key_exists("projectName",$args)?$args["projectName"]:"";
	$projectPath		= array_key_exists("projectPath",$args)?$args["projectPath"]:"";

	$promote			= array_key_exists("promote",$args)?$args["promote"]:"";

	$branch				= array_key_exists("branch",$args)?$args["branch"]:"";
	$buildType			= array_key_exists("buildType",$args)?$args["buildType"]:"";
	$buildTimestamp		= array_key_exists("buildTimestamp",$args)?$args["buildTimestamp"]:"";
	$buildID			= array_key_exists("buildID",$args)?$args["buildID"]:"";

	$testType			= array_key_exists("testType",$args)?$args["testType"]:"";
	$testTimestamp		= array_key_exists("testTimestamp",$args)?$args["testTimestamp"]:"";
	$continuous			= array_key_exists("continuous",$args)?$args["continuous"]:"";

	$server = $HOSTNAME ? $HOSTNAME : getServerName();

	$ID = $buildID?$buildID:$buildType.$buildTimestamp;

	$proj = explode("/",$projectPath);
	$proj = $proj[sizeof($proj)-1];
	$projectPath = str_replace("/".$proj,"",$projectPath);
	
	if ($email) { 
		/* message */
		if ($testTimestamp) { 
			$subject = ($testType?ucfirst($testType)." ":"")."EMFT $proj Test ".($branch?"$branch / ":"").($ID?"$ID / ":"")."$testTimestamp done [$date]";
			if ($continuous) { $subject .= " [$continuous left]"; }
			$body = ($server?"http://".$server."/":"http://".($testType=="perf"?$perfServerDefault:$testServerDefault)."/").
				"tests/results".($testType?"-".$testType:"").".php?showAllResults=&showAll=&sortBy=date#latest";
		} else if ($promote) { 
			$projectPath = $projectPath ? $projectPath : "technology/".$projectName."/scripts";
			$subject = "EMFT $proj Build ".($branch?"$branch / ":"")."$ID is available [$date]";
			$body = ($server?"http://".$server."/":"http://".$promoServerDefault."/").
				$projectPath."/downloads/index.php?proj=$proj&showAll=&sortBy=date#latest";
		} else {
			$projectPath = $projectPath ? $projectPath : "technology/".$projectName."/scripts";
			$subject = "EMFT $proj Build ".($branch?"$branch / ":"")."$ID done [$date]";
			$body = ($server?"http://".$server."/":"http://".$buildServerDefault."/").
				$projectPath."/downloads/index.php?proj=$proj&showAll=&sortBy=date#latest";
		}
		echo "[send] Sending mail from $server ...\n";
		$ret = sendMail($subject,$body);
		echo $ret;
	} else {
		echo "[send] Nothing to do! No email found for build ".$ID."\n"; 
	}

	if ($debug || $email) { 
		echo '[send] Mail delivery completed '.date('\a\t h:i:sa')."\n\n"; 
	}

/* ******************************************************************************* */

// methods 

function getPairs() { // collect cmdline options in -foo bar -baz foobar into array: $args["foo"]="bar", etc.
	ini_set("display_errors","0"); // suppress file not found errors
	global $_SERVER;
	$argv = $_SERVER["argv"];
	$args = array();
	if (!is_array($argv) || sizeof($argv)<2) { 
		echo "[send] No commandline params specified! Exiting...\n";
		return array();
	} else {
		for ($i=1;$i<sizeof($argv);$i+=2) { // skip $i=0, that's the script name
			if (array_key_exists($i+1,$argv)) {
				$args[substr($argv[$i],1)] = $argv[$i+1]."";
			}
		}
	}
	ini_set("display_errors","1"); // and turn 'em back on.
	return $args;
}

function sendMail($subject,$body) { 
	global $debug,$email,$proj,$mailEnabled;

	$pref = "[mail] ";

	$retString = "";

	$sender = "DO-NOT-REPLY@NO-SUCH-ADDRESS";
	$senderN = "EMFT ($proj) Build Team";

	/* headers */
	$headers = "Content-Type: text/plain;\n";							
	$headers .= "Reply-To: \"$senderN\" <".$sender.">\n";
	$headers .= "From: \"$senderN\" <".$sender.">\n";

	/* recipient(s) */
	$to = $email; 

	/* and now mail it */
	$retString = ($debug?$pref."Sending Email to $to ... \n":""); 
	if ($debug) { 
		$retString .= $pref."Subject: $subject\n";
		$retString .= $pref."Body: $body\n";
		//w($retString); 
	}
	if ($mailEnabled) { 
		ini_set("sendmail_from",$sender);
		$ret = mail($to, $subject, $body, $headers); 
		$retString .= $pref.($ret?"Success!\n":"Failed!\nCould not send the email\n"); 
	} else {
		$retString .= $pref.("Email delivery disabled by script (mailEnabled=false).\n");
	}
	return $retString."\n";
}

function getServerName() { 
	global $_SERVER,$SERVER_NAME;
	if ($SERVER_NAME) { 
		return $SERVER_NAME; 
	} else {
		$s = array_key_exists("SSH_CONNECTION",$_SERVER) ? $_SERVER["SSH_CONNECTION"] : "";
		if ($s) { 
			if (false!==strpos($s," ")) { $s = explode(" ",$s); $s = $s[2]; }
            if (false!==strpos($s,":")) { $s = explode(":",$s); $s = $s[3]; }
			return $s;
	}
	return "";
	}
}
?>