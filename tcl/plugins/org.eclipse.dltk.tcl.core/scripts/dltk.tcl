#!/bin/sh
## Begin dltk.tcl \
exec tclsh "$0" ${1+"$@"}

# We are not using namespace for this package so that it should
# work with tcl versions older than 8.0 (not tested yet).

# This script relies on few proc renames, so instruct the tclchecker
# to ignore proc renames.
#checker -scope global exclude warnRedefine

## Begin renaming of core tcl cmds
rename package package-org
proc package {subcmd args} {
    global pkg_stack pkg_reqs_tmp
    
    switch -exact -- $subcmd {
        "require" {
            set exact 0
            set name [lindex $args 0]
            set vers [lrange $args 1 end]
            if {[string equal $name "-exact"]} {
                set exact 1
                set name [lindex $args 1]
                set vers [lrange $args 2 end]
            }
            # Track package require dependencies but
            # skip if pkg name is null 
            set pname [lindex $pkg_stack 0]
            if {![string equal $pname ""]} {
                lappend pkg_reqs_tmp($pname) [list $name $vers $exact]
            }
            set pkg_stack [linsert $pkg_stack 0 $name]
            set retCode [catch {uplevel 1 "::package-org $subcmd $args"} vers]
            set pkg_stack [lrange $pkg_stack 1 end]
            if {$retCode} {
                # Unsuccessful pkg load
                add-pkg-srcs-info $name ""
            } else {
                # Successful pkg load
                add-pkg-srcs-info $name $vers
            }
            return -code $retCode $vers
        }
        "ifneeded" {
            set name [lindex $args 0]
            set vers [lindex $args 1]
            #set body [lindex $args 2]
            upvar 1 use_path lcl_use_path
            upvar 1 dir lcl_dir
            if {[info exists lcl_use_path]} {
                set path [lindex $lcl_use_path end]
                add-pkg-info $name $vers $path $lcl_dir
            }
            return [uplevel 1 "::package-org $subcmd $args"]
        }
        default {}
    }
    
    return [uplevel 1 "::package-org $subcmd $args"]
} ;# End of proc package

rename source source-org
proc source {args} {
    global pkg_srcs_tmp pkg_stack
    global file_srcs_tmp
    global auto_index
    
    log::debug "source $args"
    # Ignore "-rsrc*" option when its optional filename
    # which should be present as 3rd argument is not specified
    if {[llength $args] != "2"} {
        # If the file is already sourced, don't source it again
        set fname [get-canonical-path [lindex $args end]]
        if {[info exists file_srcs_tmp($fname)]} {
            return
        }
        set file_srcs_tmp($fname) 1
        log::debug "source file: $fname"

        # Skip pkgIndex.tcl or tclIndex file or if pkg name is null
        set pname [lindex $pkg_stack 0]
        set ftail [file tail $fname]
        if {![string equal $pname ""] && [file exists $fname] &&
            ![string equal $ftail "pkgIndex.tcl"] &&
            ![string equal $ftail "tclIndex"]} {
            lappend pkg_srcs_tmp($pname) $fname
        } ;# End of if stmt
    } ;# End of if stmt
    
    return [uplevel 1 "::source-org $args"]
} ;# End of proc source

rename load load-org
proc load {args} {
    global pkg_load_tmp pkg_stack
    global file_load_tmp

    log::debug "load $args"
    # If the file is already loaded, don't load it again
    set fname [get-canonical-path [lindex $args 0]]
    if {[info exists file_load_tmp($fname)]} {
        return
    }
    set file_load_tmp($fname) 1
    log::debug "load file: $fname"

    # Skip if pkg name is null
    set pname [lindex $pkg_stack 0]
    if {![string equal $pname ""] && [file exists $fname]} {
        lappend pkg_load_tmp($pname) $fname
    }
    
    return [uplevel 1 "::load-org $args"]
} ;# End of proc load

# Some pkgs rename exit proc when it is loaded or they may explicitly
# terminate this script by explicitly calling exit. We prevent this
# by renaming exit cmd as per below.
rename exit exit-org
proc exit args {}
## End renaming of core tcl cmds

## Log routines: can be easily replaced with tcllib's logger pkg
##
namespace eval ::log {
    variable level warn
}
proc ::log::noop {args} {}
proc ::log::print {lvl msg} {
    # Print log level and msg
    puts stdout "$lvl - $msg"
} ;# End of proc ::log::print

proc ::log::levellist {} {
    return [list error warn notice info debug]
} ;# End of proc ::log::levellist
proc ::log::getlevel {} {
    variable level
    return $level
} ;# End of proc ::log::getlevel

proc ::log::setlevel {lvl} {
    variable level

    # Validate specified level
    if {[lsearch [log::levellist] $lvl] == "-1"} {
        log::warn "Invalid log level: $lvl ... ignoring"
        return
    } ;# End of switch stmt

    set log_alias ::log::print
    foreach elm [log::levellist] {
        interp alias {} ::log::$elm {} $log_alias [string toupper $elm]
        if {[string equal $lvl $elm]} {
            set log_alias ::log::noop
        } ;# End of if stmt 
    } ;# End of foreach loop

    return $lvl
} ;# End of proc ::log::setlevel

## Local procs below
##
proc get-canonical-path {path} {
    global tcl_version

    set retVal $path
    # [file normalize] cmd was introduced in tcl8.4 version
    if {$tcl_version >= 8.4} {
        set retVal [file normalize $path]
    } elseif {[string equal [file pathtype $path] "relative"]} {
        set retVal [file join [pwd] $path]
    } ;# End of if stmt

    return $retVal
} ;# End of proc get-canonical-path

proc trace-auto_index {var idx op} {
    global auto_index

    # Return immediately if it is not a write operation on
    # auto_index array element
    if {![string equal $var "auto_index"] || ![string equal $op "w"]} {
        return
    } ;# End of if stmt

    # If the auto_index element is updated by tclIndex file
    # of a package, the corresponding file should be sourced
    if {[info exists auto_index($idx)]} {
        catch {uplevel #0 $auto_index($idx)} err
    } ;# End of if stmt

    return
} ;# End of proc trace-auto_index

proc process-pkg-info {find_level find_pkgs} {
    global auto_index

    # Do not go any further it find level is negative
    if {$find_level < 0} {
        return
    } ;# End of if stmt

    # Set a variable trace on auto_index so that it also
    # finds auto-loaded tcl soruce files for a pkg
    trace variable ::auto_index w trace-auto_index

    # Also source files in existing auto_index array to
    # avoid mixup of existing entries with new pkg
    foreach {elm cmd} [array get auto_index] {
        catch {uplevel #0 $cmd} err
    } ;# End of foreach {elm cmd} loop

    # load all specified pkgs
    set pkgs $find_pkgs
    if {[string equal $pkgs ""]} {
        set pkgs [package names]
    } ;# End of if stmt
    foreach name $pkgs {
        log::notice "%DLTK-PROCESS-PKG% $name"
        if {![get-pkg-info exists $name]} {
            # This package does not exist, so skip
            continue
        } ;# End of if stmt

        # Load the package
        if {$find_level && ![catch {package require $name} vers]} {
            # If the package was loaded successfully, do nothing
            # since its info will be added by package require
        } else {
            # If the package could not be loaded, register all
            # known versions of the pkg
            foreach nvers [get-pkg-info vers $name] {
                add-pkg-srcs-info $name $nvers
            } ;# End of foreach loop
        } ;# End of if catch stmt
    } ;# End of foreach name loop

    return
} ;# End of proc process-pkg-info

proc add-pkg-srcs-info {name vers} {
    global pkg_path_names
    global pkg_dir_names
    global pkg_reqs pkg_srcs pkg_load
    global pkg_reqs_tmp pkg_srcs_tmp pkg_load_tmp
    global pkg_names_tmp

    set path [get-pkg-info path $name $vers]
    if {[string equal $path ""]} {
        # If the package is not known to the interpreter,
        # its shouldn't be recorded, so return immediately.
        return
    } ;# End of if stmt

    set elm [list $name $vers]
    set dir  [get-pkg-info dir $name $vers]
    # Skip if pkg name has been previously recorded
    if {[info exists pkg_names_tmp($elm)]} {
        return
    } ;# End of if stmt
    set pkg_names_tmp($elm) $vers

    # Store the files sourced and its package require
    # dependencies in a global variable.
    set pkg_srcs($elm) [list]
    set pkg_load($elm) [list]
    set pkg_reqs($elm) [list]
    if {[info exists pkg_srcs_tmp($name)]} {
        set pkg_srcs($elm) $pkg_srcs_tmp($name)
        unset pkg_srcs_tmp($name)
    } ;# End of if stmt
    if {[info exists pkg_load_tmp($name)]} {
        set pkg_load($elm) $pkg_load_tmp($name)
        unset pkg_load_tmp($name)
    } ;# End of if stmt
    if {[info exists pkg_reqs_tmp($name)]} {
        set pkg_reqs($elm) $pkg_reqs_tmp($name)
        unset pkg_reqs_tmp($name)
    } ;# End of if stmt
    lappend  pkg_path_names($path) $elm
    lappend  pkg_dir_names($dir) $elm
    log::debug "PKG-PATH $elm $path"

    return
} ;# End of proc add-pkg-srcs-info

proc add-pkg-info {name vers path {dir ""}} {
    global pkg_names_vers
    global pkg_names_path
    global pkg_names_dir

    # Don't go any further if:
    #    1. the specified pkg vers has been recorded previously, or
    #    2. the vers is null and any pkg vers has been previously recorded
    set elm [list $name $vers]
    if {[info exists pkg_names_path($elm)] ||
        ([string equal $vers ""] && [info exists pkg_names_vers($name)])} {
        return
    } ;# End of if stmt

    if {![info exists pkg_names_vers($name)]} {
        set pkg_names_vers($name) [list]
    } ;# End of if stmt
    if {![string equal $vers ""]} {
        lappend pkg_names_vers($name) $vers
    } ;# End of if stmt

    # Save path and dir for pkg version.
    set pkg_names_path($elm) $path
    set pkg_names_dir($elm)  $dir

    log::debug "PATH-PKG $name $vers $path $dir"
    return
} ;# End of proc add-pkg-info

proc get-pkg-info {what name {vers ""}} {
    global pkg_names_vers
    global pkg_names_path
    global pkg_names_dir

    # Parse $what 
    set retVal {}
    set elm [list $name $vers]
    switch -exact -- $what {
        "path" {
            if {[info exists pkg_names_path($elm)]} {
                set retVal $pkg_names_path($elm)
            } ;# End of if stmt
        }
        "dir"  {
            if {[info exists pkg_names_dir($elm)]} {
                set retVal $pkg_names_dir($elm)
            } ;# End of if stmt
        }
        "vers" {
            # Returns list of known pkg versions
            if {[info exists pkg_names_vers($name)]} {
                set retVal $pkg_names_vers($name)
            } ;# End of if stmt
        }
        "exists" {
            # Returns 1 if the pkg version is known
            # otherwise it returns 0
            set retVal 0
            if {[info exists pkg_names_vers($name)]} {
                if {[string equal $vers ""] ||
                    [lsearch $pkg_names_vers($name) $vers] != "-1"} {
                    set retVal 1
                } ;# End of if stmt
            } ;# End of if stmt
        }
        default {
            # should never reach here
            return
        }
    } ;# End of switch stmt

    return $retVal
} ;# End of proc get-pkg-info

proc get-path-info {what {path ""}} {
    global pkg_path_names

    # Parse $what 
    set retVal {}
    switch -exact -- $what {
        "list" {
             set retVal [array names pkg_path_names]
        }
        "pkgs" {
            # Returns list of known pkgs for the given path
            if {[info exists pkg_path_names($path)]} {
                set retVal $pkg_path_names($path)
            } ;# End of if stmt
        }
        default {
            # should never reach here
            return
        }
    } ;# End of switch stmt

    return $retVal
} ;# End of proc get-path-info

proc process-pkg-paths {find_paths} {
    global env auto_path
    global pkg_path_type
    global pkg_path_names

    #set pkg_path_type() unknown
    # First save paths found in auto_path variable
    # and set the flag to 0
    set paths $find_paths
    if {[string equal $paths ""]} {
        set paths $auto_path
    } ;# End of if stmt
    foreach path $paths {
        set path [get-canonical-path $path]
        set pkg_path_names($path) [list]
        set pkg_path_type($path) "auto_path"
    } ;# End of foreach elm loop

    # Next look for paths found in TCLLIBPATH variable
    # and set the flag to 1
    if {[info exists env(TCLLIBPATH)]} {
        foreach path $::env(TCLLIBPATH) {
            set path [get-canonical-path $path]
            set pkg_path_names($path) [list]
            set pkg_path_type($path) "tcllibpath"
        } ;# End of foreach elm loop
    } ;# End of if TCLLIBPATH exists sttm

    return
} ;# End of proc process-pkg-paths

proc print-pkg-info {output} {
    global pkg_srcs pkg_load pkg_reqs pkg_path_type
    
    set tabspc "    "
    set path_count 0
    set pkgs_count 0
    set reqs_count 0
    set srcs_count 0
    set load_count 0

    set dltk_size  0
    set path_data {}
    foreach path [lsort [get-path-info list]] {
        # Skip null paths
        if {[string equal $path ""]} {
            continue
        } ;# End of if stmt

        # Indentation
        set indent "$tabspc"
        incr path_count

        set path_size 0
        set pkg_data {}
        log::info "path: $path"

        foreach elm [lsort [get-path-info pkgs $path]] {
            # Double indentation
            set indent "$tabspc$tabspc"
            incr pkgs_count
            set pkg_size 0
            set data {}

            set name [lindex $elm 0]
            set vers [lindex $elm 1]
            set srcs $pkg_srcs($elm)
            set load $pkg_load($elm)
            set reqs $pkg_reqs($elm)
            log::info "  pkg: $name $vers"

            foreach r $reqs {
                incr reqs_count
                set rname  [lindex $r 0]
                set rvers  [lindex $r 1]
                set rexact [lindex $r 2]
                set rattr name=\"$rname\"
                if {![string equal $rvers ""]} {
                    append rattr " " version=\"$rvers\"
                } ;# End of if stmt
                if {$rexact} {
                    append rattr " " exact=\"1\"
                } ;# End of if stmt
                log::info "    require: $rname $rvers"
                append data $indent $tabspc "<require $rattr/>" \n
            } ;# End of foreach rname loop

            foreach sname $srcs {
                incr srcs_count
                set sattr name=\"$sname\"
                if {[file exists $sname]} {
                    set ssize [file size $sname]
                    if {$ssize} {
                        append sattr " " size=\"$ssize\"
                        incr pkg_size $ssize
                    } ;# End of if stmt
                } else {
                    log::warn "Source file not found: $sname"
                } ;# End of ifelse stmt
                log::info "    source: $sname"
                append data $indent $tabspc "<source $sattr/>" \n
            } ;# End of foreach sname loop

            foreach lname $load {
                incr load_count
                set lattr name=\"$lname\"
                if {[file exists $lname]} {
                    set lsize [file size $lname]
                    if {$lsize} {
                        append lattr " " size=\"$lsize\"
                        incr pkg_size $lsize
                    } ;# End of if stmt
                } else {
                    log::warn "Load file not found: $lname"
                } ;# End of ifelse stmt
                log::info "    load: $lname"
                append data $indent $tabspc "<load $lattr/>" \n
            } ;# End of foreach lname loop

            # Create the pkg xml structure
            set pattr name=\"$name\"
            if {![string equal $vers ""]} {
                append pattr " " version=\"$vers\"
            } ;# End of if stmt
# Disable printing of directory name for the pkg for now
#            set pkg_dir [get-pkg-info dir $name $vers]
#            if {![string equal $pkg_dir ""]} {
#                append pattr " " dir=\"$pkg_dir\"
#            } ;# End of if stmt
            if {$pkg_size} {
                append pattr " " size=\"$pkg_size\"
            } ;# End of if stmt
            append pkg_data $indent "<package $pattr>" \n
            append pkg_data $data
            append pkg_data $indent  "</package>" \n
            incr path_size $pkg_size
        } ;# End of foreach loop

        # Create the path xml structure
        set indent "$tabspc"
        set type $pkg_path_type($path)
        set pattr "name=\"$path\" type=\"$type\""
        if {$path_size} {
            append pattr " " size=\"$path_size\"
        }
        append path_data $indent  "<path $pattr>" \n
        append path_data $pkg_data
        append path_data $indent  "</path>" \n
        incr dltk_size $path_size
    } ;# End of foreach loop

    # Create the dltk xml structure
    set dltk_data {}
    append dltk_data "<DLTK size=\"$dltk_size\">" \n
    append dltk_data $path_data
    append dltk_data </DLTK>

    # Print dltk data to stdout or output file
    set fname [get-canonical-path $output]
    if {[string equal $fname ""]} {
        puts stdout $dltk_data
    } else {
        # Ensure that parent dir exists
        set fdir [file dirname $fname]
        file mkdir $fdir
        set fh [open $fname w]
        puts $fh $dltk_data
        close $fh
    } ;# End of if else stmt

    log::notice "Output file:                 $fname"
    log::notice "Number of pkg paths found:   $path_count"
    log::notice "Number of pkgs found:        $pkgs_count"
    log::notice "Number of files sourced:     $srcs_count"
    log::notice "Number of libs loaded:       $load_count"
    log::notice "Number of pkg dependencies:  $reqs_count"

    return
} ;# End of proc print-pkg-info

proc help {{usermsg ""}} {
    set msg "usermsg"
    append msg {
USAGE:  dltk.tcl [subcmd] [options] ?-output <file>?

    This script scans all the directories relevant to the tcl
    interpreter and returns an xml structure consisting of library
    paths, various packages available for loading alongwith its
    corresponding version number, package dependencies and files
    sourced or loaded by the package. The -output option can be
    specified to save the xml structure to a file, otherwise it
    print it to stdout.


  > dltk.tcl get-paths ?-paths "<p1> .."? ?-output <file>? \
                       ?-loglevel error|warn|notice|info|debug?
        Print all paths known to tcl interpeter including $auto_path 
        and TCLLIBPATH values. Optionally restrict the paths printed
        using -paths option.

  > dltk.tcl get-pkgs ?-paths "<p1> .."? ?-pkgs "<pkg1> .."? ?-output <file>? \
                      ?-loglevel error|warn|notice|info|debug?
        Print pkg paths and associated package names. Specify -paths and/or
        -pkgs option to display limited number of paths and pkgs.

  > dltk.tcl get-srcs ?-paths "<p1> .."? ?-pkgs "<pkg1> .."? ?-output <file>? \
                      ?-loglevel error|warn|notice|info|debug?
        Print pkg paths, pkg names/versions and corresponding library 
        source files. Limit output to specified packages or paths using
        -pkgs or -paths option.

  > dltk.tcl
  > dltk.tcl help
        Print this help msg
}

    puts stderr $msg

    return
} ;# End of proc help

proc main {argv} {
    global auto_path
    global pkg_stack
    set pkg_stack {} ;# initialize to null
    set subcmd [lindex $argv 0]

    # We could use tcl's time cmd below but we don't need
    # microsecond resolution
    set start_time [clock seconds]

    # Initialize log level
    ::log::setlevel notice

    set find_level 1
    switch -exact -- $subcmd {
        "get-paths" {
            set find_level -1
        }
        "get-pkgs" {
            set find_level 0
        }
        "get-srcs" {
            set find_level 1
        }
        "help"  -
        default {
            # Print help msg and quit
            help
            return
        }
    } ;# End of switch stmt

    # Parse the options
    set argc [llength $argv]
    set idx 1 
    set paths  [list]
    set pkgs   [list]
    set output [list]
    while {$idx < $argc} {
        set opt [lindex $argv $idx]
        incr idx
        switch -exact -- $opt {
            "-path"  -
            "-paths" {
                set paths [lindex $argv $idx]
            }
            "-pkg"  -
            "-pkgs" {
                set pkgs [lindex $argv $idx]
            }
            "-output" {
                set output [get-canonical-path [lindex $argv $idx]]
            }
            "-loglevel" {
                ::log::setlevel [lindex $argv $idx]
            }
            default {
                # Invalid option, print error
                help "Invalid option: $opt\n\n"
                return
            }
        } ;# End of switch stmt
        incr idx
    } ;# End of while loop

    # try to load an unknown pkg, so that it discovers all packages
    catch {::package-org require unknown-random-[clock seconds]}
    log::notice "%DLTK-NUM-PATHS% [llength $auto_path]"
    log::notice "%DLTK-NUM-PKGS% [llength [package names]]"

    # Process pkg paths in auto_path and TCLLIBPATH
    process-pkg-paths $paths

    # Process pkg ifneeded bodies by invoking [pkg req]
    process-pkg-info $find_level $pkgs

    # Print pkg root folders
    print-pkg-info $output

    set stop_time [clock seconds]

    log::notice "Run time = [expr {$stop_time-$start_time}] secs"

    return
} ;# End of proc main 
main $argv

# Exit needs to be called explicitly because some package may endup
# invoking Tk pkg which in turn may create a GUI window and wait forever.
::exit-org

## End dltk.tcl
