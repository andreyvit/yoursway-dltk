package org.eclipse.dltk.ruby.typeinference;

public interface BuiltinMetaTypeMethods {

	public String[] objectMethods = {
			"methods",
			"instance_eval",
			"dup",
			"instance_variables",
			"include?",
			"private_instance_methods",
			"instance_of?",
			"protected_method_defined?",
			"extend",
			"const_defined?",
			"eql?",
			"name",
			"public_class_method",
			"autoload",
			"method_dump",
			"new",
			"hash",
			"id",
			"singleton_methods",
			"instance_method",
			"taint",
			"constants",
			"frozen?",
			"instance_variable_get",
			"kind_of?",
			"ancestors",
			"to_a",
			"private_class_method",
			"const_missing",
			"type",
			"instance_methods",
			"protected_methods",
			"superclass",
			"method_defined?",
			"instance_variable_set",
			"const_get",
			"is_a?",
			"autoload?",
			"respond_to?",
			"to_s",
			"module_eval",
			"class_variables",
			"allocate",
			"class",
			"<=>",
			"<",
			"method",
			"tainted?",
			"private_methods",
			"==",
			"public_instance_methods",
			"__id__",
			"===",
			"public_method_defined?",
			">",
			"included_modules",
			"nil?",
			"untaint",
			"const_set",
			">=",
			"<=",
			"send",
			"display",
			"inspect",
			"class_eval",
			"clone",
			"=~",
			"protected_instance_methods",
			"public_methods",
			"private_method_defined?",
			"__send__",
			"equal?",
			"freeze",
			"object_id"
	};
	
	public String[] stringMethods = {
			
	};
	
	public String[] regexpMethods = {
			"quote",
			"escape",
			"union",
			"last_match",
			"compile"	
	};
	
	public String[] fixnumMethods = {
			"induced_from"	
	};
	
	public String[] floatMethods = {
			"induced_from"	
	};
	
	public String[] arrayMethods = {
			"[]"	
	};
	
}
