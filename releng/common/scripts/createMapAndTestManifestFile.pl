use warnings;
use strict;

package M_TM;

# [143365] TODO: replace this whole script two custom Ant tasks - one for map file and one for test manifest file

sub loadProperties()
{
	my $buildConfigFile = shift;

	my @properties = ("cvsHost", "cvsReadProtocol", "cvsReadUser", "cvsWriteUser", "cvsWriteRelengUser", "cvsRep");
	my $valueByProperty = &getProperties($buildConfigFile, \@properties);

	my $cvsHost         = $valueByProperty->{"cvsHost"};
	my $cvsReadProtocol = $valueByProperty->{"cvsReadProtocol"};
	my $cvsReadUser     = $valueByProperty->{"cvsReadUser"};
	my $cvsWriteUser    = $valueByProperty->{"cvsWriteUser"};
	my $cvsWriteRelengUser = $valueByProperty->{"cvsWriteRelengUser"};
	my $cvsRep       = $valueByProperty->{"cvsRep"};

	$M_TM::SSH_HOST = "$cvsWriteUser\@$cvsHost";
	$M_TM::CVS_REP1 = "$cvsRep";
	$M_TM::CVSROOT1 = ":$cvsReadProtocol:$cvsReadUser\@$cvsHost:".$M_TM::CVS_REP1;
}

sub getProperties()
{
	my $file = shift;
	my $properties = shift;

        return unless (open(FILE, "<$file"));
	my @lines = <FILE>;
	close(FILE);

	my %valueByProperty = ();

	foreach my $line (@lines)
	{
		foreach my $property (@$properties)
		{
			if($line =~ m/${property}=/)
			{
				chop($line);
				$line =~ s/^${property}=//;
				$valueByProperty{$property} = $line;
			}
		}
	}

	
	return \%valueByProperty;
}

# Returns the last segment of a path.
sub getLastSegment()
{
	my $path = shift;

	$path =~ s/\/$//;
	$path = reverse($path);
	$path =~ s/\/.*$//;
	
	return reverse($path);
}

# Retrieves the directories available at the $M_TM::SSH_PATH directory of the
# $M_TM::SSH_HOST.
# return: Reference to an array with the directories.
sub getEclipseElementDirectories()
{
	my $proj = shift;	
	my $command = "ssh $M_TM::SSH_HOST \'cd $M_TM::CVS_REP1";
	# hack to get fragment folders to be named with "-fragment" as a suffix
	my $dir="org.eclipse.dltk/$proj"; 
	$command .= " && for f in `find $dir -maxdepth 3 -name plugin.xml,v -o -name feature.xml,v -o -name fragment.xml,v -o -name test.xml,v -o -name META-INF | sort`;";
	$command .= " do if [ \"\${f##*/Attic/*}\" = \"\$f\" ]; then folder=\${f%/*}; type=\${f##*/}; if [ \"\$type\" = \"fragment.xml,v\" ]; then echo \$folder\"-fragment\"; else echo \$folder; fi; fi; done | uniq";
	$command .= "\'";
	my @directories = split("\n", `$command`);
	return \@directories;
}

# Creates the map entries given a list of directories and the CVS tag to be
# used.  The last segment of a  directories name must be either a feture id 
# with the sufix "-feature" or a plugin id.
# param: Reference to an array with the directories
# param: the CVS tag
# return: Reference to an array with the map entries
sub createMapEntries()
{
	my $directories = shift;
	my $cvsTag = shift;

	my @entries = ();
	foreach my $directory (@$directories)
	{
		my $entry = "";
		my $elementId = &getLastSegment($directory);
		
		if($directory =~ m/-feature$/)
		{
			$entry = "feature\@";
			$elementId =~ s/-feature$//;
		}
		elsif($directory =~ m/-fragment$/)
		{
			$entry = "fragment\@";
			$elementId =~ s/-fragment$//;
			$directory =~ s/-fragment$//;
		}
		else
		{
			$entry = "plugin\@";
		}

		$entry .= "$elementId=$cvsTag,";
		$entry .= "$M_TM::CVSROOT1,,";
		$entry .= "$directory";

		push(@entries, $entry);
	}

	return \@entries;
}

# Creates the test manifest entries given a list of directories used.  
# The last segment of a  directories name must be either a feture id 
# with the sufix "-feature" or a plugin id.   Also the test, example
# and "runtime and sdk" are located in the tests, examples and plugins
# subdirectories respectively.
# param: Reference to an array with the directories
# return: Reference to an array with the test manifest entries
sub createTestManifestEntries()
{
	my $directories = shift;

	my @entries = ();
	foreach my $directory (@$directories)
	{		
		if($directory !~ m/-feature$/)
		{
			my $elementId = &getLastSegment($directory);
			my $entry = "   <logFile name=\"plugins/$elementId"."_*/*.bin.log\">";
			
			if($directory =~ m/tests/)
			{
				$entry .= "\n      <effectedFile id=\"T\"><\/effectedFile>";
			}
			elsif($directory =~ m/examples/)
			{
				$entry .= "\n      <effectedFile id=\"EX\"><\/effectedFile>";
			}
			elsif($directory =~/plugins/)
			{
				if($directory =~/\/examples\//)
				{
					$entry .= "\n      <effectedFile id=\"EX\"><\/effectedFile>";
				}
				elsif($directory =~/\/tests\//)
				{
					$entry .= "\n      <effectedFile id=\"T\"><\/effectedFile>";
				}
				else
				{
					$entry .= "\n      <effectedFile id=\"SDK\"><\/effectedFile>";
					$entry .= "\n      <effectedFile id=\"projRUN\"><\/effectedFile>";
				}
			}
			else
			{
				next;
			}
		
			$entry .= "\n   <\/logFile>";
			push(@entries, $entry);
		}
	}

	return \@entries;
}

# Creates the map file based on a template.  The tokens @mapEntries@, @cvsRoot1@,
# @cvsRoot2@ and @cvsTag@ in the template are replaced by the appropriate values.
# param: The file to be created
# param: The template file
# param: The CVS tag
# param: A reference to an array with the map entries
sub createMapFile()
{
	my $file = shift;
	my $templateFile = shift;
	my $cvsTag = shift;
	my $entries = shift;

	return unless (open(TEMPLATE, "<$templateFile"));
	my $template = join("", <TEMPLATE>);
	close(TEMPLATE);

	my $entriesText = join("\n",@$entries);
	$template =~ s/\@entries\@/$entriesText/g;
	$template =~ s/\@cvsTag\@/$cvsTag/g;
	$template =~ s/\@cvsRoot1\@/$M_TM::CVSROOT1/g;

	return unless (open(MAP, ">$file"));
	print MAP $template;
	close(MAP);
}

# Creates the test manifest file based on a template.  The tokens @logEntries@
# in the template is replaced by the appropriate value.
# param: The file to be created
# param: The template file
# param: A reference to an array with the test manifest entries
sub createTestManifestFile()
{
	my $file = shift;
	my $templateFile = shift;
	my $entries = shift;

	return unless (open(TEMPLATE, "<$templateFile"));
	my $template = join("", <TEMPLATE>);
	close(TEMPLATE);

	my $entriesText = join("\n",@$entries);
	$template =~ s/\@entries\@/$entriesText/g;

	return unless (open(MAP, ">$file"));
	print MAP $template;
	close(MAP);
}

sub main()
{
	if(@ARGV."" != 7)
	{
		die("Usage: perl createMapAndTestManifestFile.pl <subprojectName> <buildConfigFile> <map file> <map file template> <test manifest file> <test manifest file template> <CVS target of the files that will be extracted>\n");
	}

	my $buildConfigFile          = $ARGV[0];
	my $mapFile                  = $ARGV[1];
	my $mapFileTemplate          = $ARGV[2];
	my $testManifestFile         = $ARGV[3];
	my $testManifestFileTemplate = $ARGV[4];
	my $cvsTag                   = $ARGV[5];
	my $subprojectName           = $ARGV[6];

	die("Invalid build configuration file.\n") unless (-f $buildConfigFile);
	die("Invalid map template file.\n") unless (-f $mapFileTemplate);
	die("Invalid test manifest template file.\n") unless (-f $testManifestFileTemplate);
	
	&loadProperties($buildConfigFile);
	my $directories = &getEclipseElementDirectories($subprojectName);

	my $mapEntries = &createMapEntries($directories, $cvsTag);
	&createMapFile($mapFile, $mapFileTemplate, $cvsTag, $mapEntries);

	my $testManifestEntries = &createTestManifestEntries($directories, $cvsTag);
	&createTestManifestFile($testManifestFile, $testManifestFileTemplate, $testManifestEntries);
}

main();
