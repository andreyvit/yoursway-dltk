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

def put_constant!(metaclass)
	$data << <<-"END"
		#{type_for(metaclass)} #{const_for(metaclass)} = (#{type_for(metaclass)}) add(new #{type_for(metaclass)}(#{metaclass.name.to_java_string}));
		SingletonMetaclass #{const_for_singleton(metaclass)} = new SingletonMetaclass(#{metaclass.name.to_java_string});
	END
end

def put_methods!(metaclass)
	ms = metaclass.instance_methods(false).collect { |m|
		"new MethodInfo(#{m.to_java_string}, #{metaclass.instance_method(m).arity}, 0)"
	}.join(",\n");
	
	$data << <<-"END"
		#{const_for(metaclass)}.setMethods(new MethodInfo[] {
			#{ms}
		});
	END
end

def put_singleton_methods!(metaclass)
	ms = metaclass.methods(false).collect { |m|
		"new MethodInfo(#{m.to_java_string}, #{metaclass.method(m).arity}, Modifiers.AccStatic)"
	}.join(",\n");
	
	$data << <<-"END"
		#{const_for_singleton(metaclass)}.setMethods(new MethodInfo[] {
			#{ms}
		});
	END
end

def put_included_modules!(metaclass)
	ms = (metaclass.included_modules - 
			((metaclass.is_a?(Class) &&
			metaclass.superclass &&
				metaclass.superclass.included_modules) || [])).collect { |m|
		const_for(m)
	}.join(",");
	
	$data << <<-"END"
		#{const_for(metaclass)}.setIncludedModules(new ModuleMetaclass[] {
			#{ms}
		});
	END
end

def put_singleton_included_modules!(metaclass)
	sup = (class << metaclass; superclass; end)
	ms = ((class << metaclass; included_modules; end) -
			((sup && sup.included_modules) || [])).collect { |m|
		const_for(m)
	}.join(",");
	
	$data << <<-"END"
		#{const_for_singleton(metaclass)}.setIncludedModules(new ModuleMetaclass[] {
			#{ms}
		});
	END
end

def put_superclass!(metaclass)
	return if metaclass.superclass.nil?
	$data << <<-"END"
		#{const_for(metaclass)}.setSuperClass(#{const_for(metaclass.superclass)});
	END
end

def put_singleton_superclass!(metaclass)
	$data << <<-"END"
		#{const_for_singleton(metaclass)}.setSuperClass(#{const_for(metaclass.class)});
	END
end

def put_metaclass!(metaclass)
	$data << <<-"END"
		#{const_for(metaclass)}.setMetaClass(#{const_for_singleton(metaclass)});
	END
end

def type_for(metaclass) 
	case metaclass
	when Class then "ClassMetaclass"
	when Module then "ModuleMetaclass"
	else "UnknownMetaclass@$%"
	end
end

def postfix_for(metaclass) 
	case metaclass
	when Class then "Metaclass"
	when Module then "ModuleMetaclass"
	else "UnknownMetaclass"
	end
end

def const_for(metaclass)
	"#{metaclass.name.javacase}#{postfix_for(metaclass)}"
end

def postfix_for_singleton(metaclass) 
	case metaclass
	when Class then "ClassSingletonMetaclass"
	when Module then "ModuleSingletonMetaclass"
	else "UnknownSingletonMetaclass"
	end
end

def const_for_singleton(metaclass)
	"#{metaclass.name.javacase}#{postfix_for_singleton(metaclass)}"
end

def process_all
	classes = Set.new
	known_modules = Set.new #[Enumerable, Comparable]
	ObjectSpace.each_object do |o|
		classes << o.class
		classes << o if o.is_a?(Class)
		known_modules << o if o.is_a?(Module)
	end
	known_modules = (known_modules - classes).to_a
	classes = classes.to_a.sort { |a,b| a.name <=> b.name }
	
	(known_modules + classes).each { |c| put_constant!(c) }
	(known_modules + classes).each { |c| put_methods!(c) }
	(known_modules + classes).each { |c| put_included_modules!(c) }
	classes.each { |c| put_superclass!(c) if c.is_a?(Class) }
	(known_modules + classes).each { |c| put_metaclass!(c) }
	(known_modules + classes).each { |c| put_singleton_methods!(c) }
	(known_modules + classes).each { |c| put_singleton_superclass!(c) }
	(known_modules + classes).each { |c| put_singleton_included_modules!(c) }
end

$data = ""
process_all

javafile = 'BuiltinMethodsDatabase.java'

data = File.open(javafile) {|f| f.read}

data.sub! %r!(// start generated code).*(// end generated code)!m do puts "Source modified."; "#{$1}\n#{$data}\n#{$2}" end

File.open(javafile, 'w') {|f| f.write data}

