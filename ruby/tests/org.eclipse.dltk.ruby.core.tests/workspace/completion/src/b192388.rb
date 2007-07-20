class Parent
        def meth
                puts 'meth'
        end
end

der = Parent.new

class << der
        def methder
                self.m
                puts 'mder'
        end
end

der.methder
