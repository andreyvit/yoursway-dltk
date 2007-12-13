###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'base64'
require 'cgi'
require 'dbgp/ErrorMessages.rb'

module XoredDebugger

    class XmlElement
        def initialize(name)
            @name = name
            @attributes = {}
            @data = nil
            @encode_data = true
            nil
        end
        
        def add_attribute(name, value)
            @attributes[name.to_s] = value.to_s
            nil
        end
        
        def get_attribute(name)
            @attributes[name.to_s]
        end

        def remove_attribute(name)
            @attributes.delete(name.to_s) 
        end
        
        def set_data(data, encode = false)
            @data = data
            @encode_data = encode
            nil
        end
        
        def get_data()
            @data
        end                                      
        
        def to_xml
            if (@data.nil?)
                sprintf(PACKET_WITHOUT_DATA_TEMPLATE, @name, attributes_xml) 
            else                    
                sprintf(PACKET_WITH_DATA_TEMPLATE, @name, attributes_xml, 
                    prepare_data(@data), @name)                                
            end               
        end

        alias to_s :to_xml   
                    
    protected    
	    PACKET_WITH_DATA_TEMPLATE = '<%s%s>%s</%s>'
	    PACKET_WITHOUT_DATA_TEMPLATE = '<%s%s/>'

        def transaction_id_only_xml
            if (@transation_id.nil?)
                ''
            else
                sprintf(' transaction_id="%s"', @transation_id)
            end
        end
        
        def attributes_xml
            result = transaction_id_only_xml
            @attributes.each { |name, value| result += sprintf(' %s="%s"', name, CGI::escapeHTML(value)) }
            result
        end
        
        def prepare_data(data)
            if (@encode_data)
                sprintf('<![CDATA[%s]]>', Base64.encode64(data.to_s))
            else 
                data
            end
        end        
    end    
end # module
