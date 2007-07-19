module TemplatesConverter
  class BaseTemplateParser

    def initialize
        @filled = false
    end

    def parse(filename)                 
        File.class
        raise Exception, "File #{filename} does not exist" unless File.exists?(filename)
    end
  end
end
