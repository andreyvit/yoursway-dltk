module Kds
        def Kds.xxx1
                puts "xxx1"
        end     

        def Kds.xxx2
                puts "xxx2"
        end

        def xxx3
                puts "xxx3"
        end     
end     

class Test
        include Kds
end

t = Test.
