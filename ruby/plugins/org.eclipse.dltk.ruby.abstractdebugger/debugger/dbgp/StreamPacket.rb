require 'dbgp/XmlElement'

module XoredDebugger
    class StreamPacket < XmlElement
        def initialize(stream, message)
            super('stream')
            add_attribute('type', stream)
            set_data(message, true)
        end
    end
end
