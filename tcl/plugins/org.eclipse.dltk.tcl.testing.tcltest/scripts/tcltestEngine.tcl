#puts "TCLTESTENGINE:START"
#puts "args: $argv"
set myvars $argv
set script [lindex $argv 0]
set argv [lrange $argv 1 [llength $argv]]
set argv0 $script
package require tcltest 2
namespace import -force ::tcltest::*
::tcltest::verbose { pass skip body }
source $script
#puts "TCLTESTENGINE:END"
