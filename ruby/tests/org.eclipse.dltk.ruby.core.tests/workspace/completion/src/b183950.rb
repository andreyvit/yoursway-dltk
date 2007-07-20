class Leto
        def test
        end
end

t = Leto.new

class <<t
        def xxx
                puts 'xxx'
        end
end

t.
