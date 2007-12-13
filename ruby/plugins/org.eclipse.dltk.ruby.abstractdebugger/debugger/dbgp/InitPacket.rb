require 'dbgp/XmlElement'

module XoredDebugger
    class InitPacket < XmlElement
	    def initialize(ide_key, thread, file_uri)
	        super('init')            
	        add_attribute('appid', 'xored_debugger')
            add_attribute('idekey', ide_key)
            add_attribute('thread', thread)
		    add_attribute('parent', '')
            add_attribute('language', 'ruby')
            add_attribute('fileuri', file_uri)
            add_attribute('protocol_version', '1.0')
	        nil        
	    end
	end
end
