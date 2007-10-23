###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
    class FeatureManager
        def initialize
            # Required
            @map = {
                # Required
                'language_supports_threads' => [0,        false], #get [0|1]
                'language_name'             => ['ruby',   false], #get {eg. PHP, Python, Perl}  
                'language_version'          => ['1.8',    false], #get {version string}
                'encoding'                  => ['UTF-8',  false], #get current encoding in use by the debugger session
                'protocol_version'          => [1,        false], #get {for now, always 1}  
                'supports_async'            => [1,        false], #get {for commands such as break}
                'data_encoding'             => ['base64', false], #get optional, allows to turn off the default base64 encoding of data. This should only be used for development and debugging of the debugger engines themselves, and not for general use. If implemented the value 'base64' must be supported to turn back on regular encoding. the value 'none' means no encoding is in use. all elements that use encoding must include an encoding attribute.
                'breakpoint_languages'      => ['',       false], #get some engines may support more than one language. This feature returns a string which is a comma separated list of supported languages. If the engine does not provide this feature, then it is assumed that the engine only supports the language defined in the feature language_name. One example of this is an XSLT debugger engine which supports XSLT, XML, HTML and XHTML. An IDE may need this information to to know what types of breakpoints an engine will accept.

                'multiple_sessions' => [0,        true], #get|set {0|1}
                'max_children'      => [10,       true], #get|set max number of array or object children to initially retrieve
                'max_data'          => [4096,     true], #get|set get|set   max amount of variable data to initially retrieve.
                'max_depth'         => [3,        true], #get|set maximum depth that the debugger engine may return when sending arrays, hashs or object structures to the IDE.

                # Additional
                'supports_postmortem' => [0, false], # get [0|1] This feature lets an IDE know that there is benefit to continuing interaction during the STOPPING state (sect. 7.1).
            
                'show_hidden' => [0, true], # get|set [0|1] This feature can get set by the IDE if it wants to have more detailed internal information on properties (eg. private members of classes, etc.) Zero means that hidden members are not shown to the IDE.
                'notify_ok'   => [0, true] # get|set [0|1] See section 8.5
           }
        end

        def supported?(name)
            @map.keys.include?(name)
        end

        def get(name)
            @map[name][0]
        end

        def set(name, value)
            if @map[name][1]
                @map[name][0] = value
            end
        end 
    end # class FeatureManager
end # module XoredDebugger