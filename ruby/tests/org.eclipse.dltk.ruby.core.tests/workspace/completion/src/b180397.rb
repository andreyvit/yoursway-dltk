class BaseTemplate
  attr_accessor :variable_template

  def initialize(name, abbrev, body, description)
    @name = name
    @shortcut, @body, @description = shortcut, body, description

    # Template variable
    @variable_template = /\$\{\w+\}/ # ${variable}
  end                   

  def variable_gsub_expession
    "${\\1}"
  end

  # Parses template body
  def parse_body(variable_template)
    gsub_entities_in_body!
	@body.i
  end                   

  protected

  def gsub_entities_in_body!
    subs = {
      /&quot;/ => '"',
      /&#10;/ => "\n"
    }

    subs.each { |k, v| @body.gsub!(k, v) }                        
  end
end
