require 'dbgp/XmlElement'

module XoredDebugger    
    class ErrorElement < XmlElement
        attr_reader :code
        
        def initialize(code)
           super('error')
           @code = code
           add_attribute('code', code)
           
           message = ErrorMessages.get_message(code)
           if (!message.nil?)
               set_data('<message>' + ErrorMessages.get_message(code) + '</message>')
           end 
        end       
    end 
end
