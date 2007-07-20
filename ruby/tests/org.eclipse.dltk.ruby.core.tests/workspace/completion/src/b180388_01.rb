module TemplatesConverter
  class BaseTemplateParser
    
  	attr_reader :templates
  	
  	def initialize
  		@templates = []
  		@filled = false			
  	end
		
		def parse(file)
			raise Exception, "Override in subclass"
		end
		
		def output(file)
		  raise Exception, "Override in subclass"
		end
		
		# Whether data parsed and ready to output
		def filled?
			@filled
		end
		  	
  end
end
