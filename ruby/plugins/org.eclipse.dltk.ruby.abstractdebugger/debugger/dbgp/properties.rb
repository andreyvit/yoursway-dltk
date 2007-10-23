###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################


def has_children(obj)
    atomic_types = [NilClass, TrueClass, FalseClass, Fixnum, String, Symbol]
    not atomic_types.include?(obj.class)
end

def get_string(obj)
    obj.nil? ? 'nil' : (has_children(obj) ? '' : obj.to_s)
end

def prepare_object(name, obj)
    x = { :name         => name,
          :eval_name    => name,
          :_type        => obj.class,
          :is_cosntant  => false,
          :has_children => has_children(obj),
          :_value       => get_string(obj),
          :key          => obj.object_id }

    if x[:has_children]
        children = obj.instance_variables.collect { |var|
            real_var = obj.instance_variable_get(var)

            { :name         => var,
              :eval_name    => sprintf("%s.instance_eval('%s')", name, var),
              :_type        => real_var.class,
              :is_constant  => false,
              :has_children => has_children(real_var),
              :_value       => get_string(real_var),
              :key          => real_var.object_id }
        }
       
        x[:num_children] = children.length
        x[:children_props] = children
    end

    x
end

def prepare_array(name, array)
    x = { :name         => name,
          :eval_name    => name,
          :_type         => array.class,
          :is_cosntant  => false,
          :has_children => true,
          :_value       => '[...]',
          :key          => array.object_id }
    
    index = -1
    children = array.collect { |value|
        index += 1
                    
        { :name         => sprintf('[%d]', index),
          :eval_name    => sprintf('%s[%d]', name, index),
          :_type         => value.class,
          :is_constant  => false,
          :has_children => has_children(value),
          :_value       => get_string(value),
          :key          => value.object_id }            
    }

    x[:num_children] = children.length
    x[:children_props] = children       
    x
end

def prepare_hash(name, hash)
    x = { :name         => name,
          :eval_name    => name,
          :_type         => hash.class,
          :is_cosntant  => false,
          :has_children => true,
          :_value       => '{...}',
          :key          => hash.object_id }

    children = hash.collect { |key, value|
        { :name         => sprintf("[%s]", key.inspect),
          :eval_name    => sprintf("%s[%s]", name, key.inspect),
          :_type         => value.class,
          :is_constant  => false,
          :has_children => has_children(value),
          :_value       => get_string(value),
          :key          => value.object_id }
    }

    x[:num_children] = children.length
    x[:children_props] = children
    x
end

def make_property(name, obj)
    type = obj.class

    if type == Hash
        prepare_hash(name, obj)
    elsif type == Array
        prepare_array(name, obj)
    else
        prepare_object(name, obj)
    end
end
