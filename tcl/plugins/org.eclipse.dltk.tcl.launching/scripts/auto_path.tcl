###############################################################################
#!/bin/sh
# find-pkg-src.tcl \
exec tclsh "$0" ${1+"$@"}

catch {::package require unknown-random-[clock seconds]}
puts "DLTK:$auto_path"
::exit
