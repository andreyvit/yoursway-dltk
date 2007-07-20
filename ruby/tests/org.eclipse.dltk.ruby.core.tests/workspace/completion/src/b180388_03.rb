require "lib/converter"

include TemplatesConverter

context "IDEA templates parser" do
	
	setup do
		@template = File.open(File.join("spec", "fixtures", "idea", "rails.xml"))
		File.exists?(@template).should be_true
		
		@parser = IdeaTemplateConverter.new
	end
	
	teardown do
		@template.close
	end
	
	specify "should have no templates after creation" do
		@parser = IdeaTemplateConverter.new; @parser.should have(:no).templates
	end	
	
	
	specify "should parse template file correctly" do
		lambda {
			@parser.parse(@template)
		}.should_not raise_error
		
		@parser.should have_at_least(2).templates
	end
	
end
