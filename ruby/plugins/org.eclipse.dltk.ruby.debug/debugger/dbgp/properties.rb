###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

#module XoredDebugger

    def has_children(obj)
        atomic_types = [Fixnum, String, Symbol]
        not atomic_types.include?(obj.class)
    end

    def get_string(obj)
        has_children(obj) ? 'Object' : obj.to_s
    end


    def prepare_object(name, obj)
        x = { 'name'         => name,
              'eval_name'    => name,
              'type'         => obj.class,
              'is_cosntant'  => false,
              'has_children' => true,
              'value'        => obj.to_s,
              'key'          => obj.object_id }

        x['children_props'] = obj.instance_variables.collect { |var|
            real_var = obj.instance_variable_get(var)

            { 'name'         => var,
              'eval_name'    => var,
              'type'         => real_var.class,
              'is_constant'  => false,
              'has_children' => has_children(real_var),
              'value'        => get_string(real_var),
              'key'          => real_var.object_id }
        }
        x
    end

    def prepare_array(name, array)
        x = { 'name'         => name,
              'eval_name'    => name,
              'type'         => array.class,
              'is_cosntant'  => false,
              'has_children' => true,
              'value'        => array.to_s,
              'key'          => array.object_id }
        
        index = 0
        x['children_props'] = array.collect { |value|
        n = sprintf('%s[%d]', name, index)
            index += 1
            { 'name'         => n,
              'eval_name'    => n,
              'type'         => value.class,
              'is_constant'  => false,
              'has_children' => has_children(value),
              'value'        => get_string(value),
              'key'          => value.object_id }
        }       
        x
    end

    def prepare_hash(name, hash)
        x = { 'name'         => name,
              'eval_name'    => name,
              'type'         => hash.class,
              'is_cosntant'  => false,
              'has_children' => true,
              'value'        => hash.to_s,
              'key'          => hash.object_id }

        x['children_props'] = hash.collect { |key, value|
            n = sprintf("%s['%s']", name, key.to_s)
            { 'name'         => n,
              'eval_name'    => n,
              'type'         => value.class,
              'is_constant'  => false,
              'has_children' => has_children(value),
              'value'        => get_string(value),
              'key'          => value.object_id }
        }
        x
    end

#end # module