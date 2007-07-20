require "rexml/document"

module TemplatesConverter
	class IdeaTemplateConverter < BaseTemplateParser
		include REXML
		
		def parse(file)			
			@template = Document.new(file)	
			@templates = [1, 2]		
			
		end		
  end
end
