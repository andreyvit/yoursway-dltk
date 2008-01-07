###############################################################################
#!/bin/sh
# find-pkg-src.tcl \
exec tclsh "$0" ${1+"$@"}

rename package package-org
proc package {subcmd args} {
	global pkg pkg_list pkg_stack
	
	switch -exact -- $subcmd {
		"ifneeded" {
			set name [lindex $args 0]
			set vers [lindex $args 1]
			set body [lindex $args 2]
						set pkg_list([list $name $vers]) {}
			return [uplevel 1 "package-org $subcmd $args"]
		}
		"require" {
			set name [lindex $args 0]
			set vers [lindex $args 1]
			if {[string equal $name "-exact"]} {
				set name [lindex $args 1]
				set vers [lindex $args 2]
			}
			set pkg_stack [linsert $pkg_stack 0 $name]
			
			set retCode [catch {uplevel 1 "::package-org $subcmd $args"} vers]
			set pkg_stack [lrange $pkg_stack 1 end]
			
			return -code $retCode $vers
		}
		default {}
	}
	
	return [uplevel 1 "::package-org $subcmd $args"]
}

rename source source-org
proc source {args} {
	global pkg pkg_stack
	
	set fname [lindex $args end]
	set pname [lindex $pkg_stack 0]
	# Skip pkgIndex.tcl file or if pkg name is null
	if {![string equal $pname ""] && 
		![string equal [file tail $fname] "pkgIndex.tcl"]} {
		lappend pkg($pname) [file normalize $fname]
	}
	
	return [uplevel 1 "::source-org $args"]
}

proc process-pkg-info {args} {
	global pkg_list
	
	# load all pkgs
	foreach elm [lsort [array names pkg_list]] {
		set name [lindex $elm 0]
		set vers [lindex $elm 1]
		#puts "$name $vers:"
		#puts $::auto_path
		# Load the package
		catch {package require $name} err
	}
}

proc print-pkg-info {args} {
	global pkg
	
	puts "+++++++++ Begin Pkg Info +++++++++++++++++++"
	foreach elm [lsort [array names pkg]] {
		set name [lindex $elm 0]
		set vers [lindex $elm 1]
		set files $pkg($elm)
		puts "$name $vers: $files"
	}
	puts "+++++++++ End Pkg Info +++++++++++++++++++"
}

proc main {argv} {
	global pkg pkg_stack
	set pkg_stack {} ;# initialize to null
	
	# try to load an unknown pkg, so that it discovers all packages
	catch {package-org require unknown-random-[clock seconds]}
	
	# Process pkg ifneeded bodies
	#process-pkg-info
	puts "DLTK:$::auto_path"

	# Print pkg names and corresponding src files
	#print-pkg-info
}
main $argv


# Exit needs to be called explicitly because some package may endup
# invoke Tk pkg which in turn may create a GUI window and wait forever.
::exit
