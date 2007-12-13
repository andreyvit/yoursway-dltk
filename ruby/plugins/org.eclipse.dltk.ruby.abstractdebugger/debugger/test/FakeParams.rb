require 'common/Params'

module XoredDebugger
    class Params
        def host
            '127.0.0.1'
        end
        
        def port
            SimpleServer::PORT
        end
    end
end
