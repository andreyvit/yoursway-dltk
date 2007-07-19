class Test
        def initialize(index)
                @data = {1 => 'one', 2 => 'two'}
                @index = index                          
        end     

        def print
                puts @data[@i]
        end
end

t = Test.new(1)
t.print
