module T
  def t t1
    puts t1
  end

  module W
    def w w4
      puts w4+'53'
    end
  end
end

module Kkk
  include Kkk
  def k
    include Kkk
    include T
    q=34
    q+=q*q
    w.
  end
end

include Kkk

fds = k
