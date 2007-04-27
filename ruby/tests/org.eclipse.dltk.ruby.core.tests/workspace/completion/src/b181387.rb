def identity(x) 
        x
end

class Test
        def lenta(x)
                @temp = identity(x)
                @temp2 = identity(@temp)
                @temp3 = identity(@temp2)
                identity(@temp3)                                
        end

        def lenta2(x)
                @var = lenta(x)
                @x = lenta(@var)
                lenta(@x)                               
        end

        def lenta3
                puts 'xxxxxx'
        end

        def Test.xxx
                t = Test.new
                t = t.lenta(t).lenta2(t)                
        end     
end


t = identity(identity(identity(identity(Test.xxx))))
t.


