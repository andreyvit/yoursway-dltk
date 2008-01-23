#!/bin/sh
# path.tcl \
exec tclsh "$0" ${1+"$@"}

rename package package-org
proc package {subcmd args} {
    global pkg_list pkg_stack
	
	switch -exact -- $subcmd {
		"ifneeded" {
			set name [lindex $args 0]
			set vers [lindex $args 1]
			set body [lindex $args 2]
			set path [file dirname [info script]]
			set pkg_list([list $name $vers]) $path
			#set pkg_list([list $name $vers]) {}
			return [uplevel 1 "::package-org $subcmd $args"]
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
	global tcl_version
	global pkg_files pkg_stack
	
	set fname [lindex $args end]
	set pname [lindex $pkg_stack 0]
	# Skip pkgIndex.tcl file or if pkg name is null
	if {![string equal $pname ""] && 
		![string equal [file tail $fname] "pkgIndex.tcl"]} {
		# [file normalize] cmd was introduced in tcl8.4 version
		if {$tcl_version >= 8.4} {
			set fname [file normalize $fname]
		}
		lappend pkg_files($pname) $fname
	}
	
	return [uplevel 1 "::source-org $args"]
}

# Some pkgs rename exit proc when it is loaded or they may explicitly
# terminate this script by explicitly calling exit. We prevent this
# by renaming exit cmd as per below.
rename exit exit-org
proc exit args {}

proc process-pkg-info {args} {
	global pkg_list pkg_loaded
	
	# load all pkgs
	foreach elm [array names pkg_list] {
		set name [lindex $elm 0]
		set vers [lindex $elm 1]
		set path $pkg_list($elm)
		#puts "$name $vers:"
		#puts $::auto_path
		# Load the package
		if {![catch {package require $name} res]} {
                    set pkg_loaded([list $name $res]) $path
                }
	}
}
proc pkg-add-path {path} {
	global pkg_paths

	# Get the directory name for the specified path
	if {![file isdirectory $path]} {
		set path [file dirname $path]
	}
	set pkg_paths($path) 1
}
proc print-pkg-info {args} {
	global pkg_loaded pkg_files pkg_paths
	
	puts "DLTK-BEGIN"
	#puts "+++++++++ Begin Pkg Info +++++++++++++++++++"
	foreach elm [array names pkg_loaded] {
		set name [lindex $elm 0]
		set vers [lindex $elm 1]
		set path $pkg_loaded($elm)
                if {[info exists pkg_files($name)]} {
		    set files $pkg_files($name)
                }
		
                puts "+ $path"
                puts "    - [set name]($vers)"
		# add unical paths to roots
		foreach f $files {
			#pkg-add-path $f
                        puts "        . $f"
		}
	}
	puts "DLTK-END"
	#puts "+++++++++ End Pkg Info +++++++++++++++++++"
        foreach elm [array names pkg_paths] {
#            puts "$elm"
        }
	#puts "Number of pkgs names found: [llength [array names pkg_files]]"
	#puts "Number of pkgs paths found: [llength [array names pkg_paths]]"
}

proc main {argv} {
	global pkg_stack
	set pkg_stack {} ;# initialize to null

	# We could use tcl's time cmd below but we don't need
	# microsecond resolution
	set start_time [clock seconds]

	# try to load an unknown pkg, so that it discovers all packages
	catch {::package-org require unknown-random-[clock seconds]}
	# Process pkg ifneeded bodies
	process-pkg-info
	# Print pkg root folders
	print-pkg-info

	set stop_time [clock seconds]

	#puts "Run time = [expr {$stop_time-$start_time}] secs"
}
main $argv


# Exit needs to be called explicitly because some package may endup
# invoking Tk pkg which in turn may create a GUI window and wait forever.
::exit-org
