###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################
require 'set'

class String
	def javacase
		repl = self.gsub('::', '_')
		"#{repl[0..0].downcase}#{repl[1..-1]}"
	end
	
	def to_java_string
		'"' + self + '"'
	end
end

[].each do |klass|
	sup = klass.superclass
	puts "#{klass.name}"
	klass.instance_methods(false).each { |m| puts "    #{m}" }
	puts "#{klass.name}.class"
	klass.methods(false).each { |m| puts "    #{m}" }
end

def put_methods!(metaclass)
	i = metaclass.included_modules
	
	ms = metaclass.public_instance_methods(false)
	for t in i do; ms -= t.public_instance_methods(true); end
	
	$data << "\t\npublic\n"
	for i in ms do
		put_rdoc(metaclass.name + "." + i.to_s)
		$data << <<-"END"		
	def #{i.to_s} (#{generateArgs(metaclass.instance_method(i).arity)})
	end
	
		END
	end
	
	ms = metaclass.protected_instance_methods(false)
	for t in i do; 
		if t.respond_to?(:protected_instance_methods) then 
			ms -= t.protected_instance_methods(true)
		end  
	end
	
	$data << "\t\nprotected\n"
	for i in ms do
		put_rdoc(metaclass.name + "." + i.to_s)
		$data << <<-"END"		
	def #{i.to_s} (#{generateArgs(metaclass.instance_method(i).arity)})
	end
	
		END
	end
	
	
	ms = metaclass.private_instance_methods(false)
	for t in i do; 
		if t.respond_to?(:private_instance_methods) then 
			ms -= t.private_instance_methods(true)
		end 		 
	end
	
	$data << "\t\nprivate\n"
	for i in ms do
		put_rdoc(metaclass.name + "." + i.to_s)
		$data << <<-"END"		
	def #{i.to_s} (#{generateArgs(metaclass.instance_method(i).arity)})
	end
	
		END
	end			
	
end

def generateArgs(arity)
	if arity < 0 then
		return "*args"
	end
	result = ""
	for i in 1..arity do 
		result <<  "arg" + i.to_s
		if i != arity then 
			result << ", "
		end
	end
	result
end

def put_singleton_methods!(metaclass)
	if (metaclass.is_a?(Class))
		$data << <<-"END2"
		class << self
		END2
	else
#		$data << "\t\tprivate\n"
	end
#	ms = (metaclass.public_methods(false) - Class.instance_methods(false)).each { |m|
	ms = (metaclass.singleton_methods(false)).each { |m|
	$data << <<-"END2"
		def #{m.to_s} (#{generateArgs(metaclass.method(m).arity)})
		end
	
	END2
	}
	if (metaclass.is_a?(Class))
		$data << <<-"END2"
		end
		END2
	end
end

def put_included_modules!(metaclass)

	ms = (metaclass.included_modules - 
			((metaclass.is_a?(Class) &&
			metaclass.superclass &&
				metaclass.superclass.included_modules) || []))
				
	for in_mod in ms do
		$data << <<-"END"
		
	include #{in_mod.name}
	
		END
	end
	
end

def put_singleton_included_modules!(metaclass)
	$data << <<-"END2"
	class << ::#{metaclass.name}
	END2

	sup = (class << metaclass; superclass; end)
#	ms = ((class << metaclass; included_modules; end) -
#			((sup && sup.included_modules) || []))
	ms = (class << metaclass; included_modules; end)
	
	
	for in_mod in ms do
		$data << <<-"END"
		
		include #{in_mod.name}
	
		END
	end
	
	$data << <<-"END2"
	end
	END2

end

def put_superclass!(metaclass)
	return if metaclass.superclass.nil?
	$data << <<-"END"
		#{const_for(metaclass)}.setSuperClass(#{const_for(metaclass.superclass)});
	END
end

#def put_singleton_superclass!(metaclass)
#	$data << <<-"END"
#		#{const_for_singleton(metaclass)}.setSuperClass(#{const_for(metaclass.class)});
#	END
#end



def dumpClass(klass)
	name = klass.name
	if name == "NameError::message" || name == "fatal" then
		return
	end
	sc = klass.superclass
	if sc then scname = " < ::" + sc.name else scname = "" end
#	methods = klass.instance_methods(false)
	put_rdoc(name)
	$data << <<-"END"
	
class #{name} #{scname}
	END
	put_included_modules!(klass)
	put_singleton_included_modules!(klass)
	put_methods!(klass)
	put_singleton_methods!(klass)
	$data << <<-"END"	
end

	END
end

def put_rdoc(name)
	return
	print "Getting doc for " + name + "\n"
	rdoc = `d:\\instantrails\\ruby\\bin\\ri.bat \"#{name}\" -f html`	
	rdoc.to_a.each { |line|
		$data << "#" + line
	}
end

def dumpModule(klass)
	name = klass.name
	#	methods = klass.instance_methods(false)
	put_rdoc(name)
	$data << <<-"END"
		
module #{name}
	END
	put_included_modules!(klass)
	put_methods!(klass)
	put_singleton_methods!(klass)
	$data << <<-"END"
end

	END
end

def dumpVariables
	$data << "# Global variables\n"
	global_variables.each { |var|
		$data << <<-"END"
#{var.to_s} = #{var.to_s}
		END
	}
end


def process_all
	classes = Set.new
	known_modules = Set.new #[Enumerable, Comparable]
	ObjectSpace.each_object do |o|
		classes << o if o.is_a?(Class)
		known_modules << o if o.is_a?(Module)
	end
	known_modules = (known_modules - classes).to_a
	classes = classes.to_a.sort { |a,b| a.name <=> b.name }
	
	classes.each { |c|		
		$data = "" 
		dumpClass(c) 
		filename = c.name
		pos = filename.index(':')
		if pos then 
			filename = filename.slice(0..pos-1)
			#puts "sliced " + filename  
		end		
		file = filename + ".rb"		
		puts "#### DLTK RUBY BUILTINS ####" + file + "\n"
		puts $data 
		#File.open(file, 'a') {|f| f.write $data}
	}
	known_modules.each { |c|
		$data = "" 
		dumpModule(c) 
		filename = c.name
		pos = filename.index(':')
		if pos then 
			filename = filename.slice(0..pos-1)
			#puts "sliced " + filename 
		end		
		file = filename + ".rb"
		puts "#### DLTK RUBY BUILTINS ####" + file + "\n"
		puts $data 
		#File.open(file, 'a') {|f| f.write $data}		
	}
	
	ccc = Module.constants.to_a
	known_modules.each { |x|
		ccc.delete(x.to_s)
	}
	classes.each { |x|
		ccc.delete(x.to_s)
	}
	
	puts "#### DLTK RUBY BUILTINS ####constants.rb\n\n\n"
	ccc.each { |bar|
		puts "#{bar} = #{Module.const_get(bar).class.to_s}.new"
	}
	
end

process_all

