require 'dbgp/XmlElement'

module XoredDebugger
    class PropertyElement < XmlElement
        attr_reader :xml 
        def initialize(object, name, fullname = nil, add_children = true)
            super('property')
            
            if (fullname.nil?)
                fullname = name
            end
            
            children = get_children(fullname, object)
            
            add_attribute('name', name)
            add_attribute('fullname', fullname)
            add_attribute('type', object.class.to_s)
            add_attribute('constant', 0)
            add_attribute('key', object.object_id)
            add_attribute('encoding','base64')
            add_attribute('children', children.empty? ? 0:1) 
            add_attribute('numchildren', children.size)            
            
            value = get_value(object, !children.empty?)
            data = prepare_data(value)
            if (add_children)                
                children.collect{ |name, fullname, child|
                    data += PropertyElement.new(child, name, fullname, false).to_s
                }
            end
            set_data(data)            
        end
        
        def get_value(object, has_children)
           type = object.class
           value = if (type == Array)
               '[...]'
           elsif (type == Hash)
               '{...}'
           elsif (type == MatchData)
               '[...]'                
           else
               object.nil? ? 'nil' : (has_children ? '' : object.to_s)
           end
           return value
       end
        
        
        def get_children(name, object)
            type = object.class
            children = if (type == Array)
                get_array_children(name, object)
            elsif (type == Hash)
                get_hash_children(name, object)
            elsif (type == MatchData)
                get_matchdata_children(name, object)                
	        else
	            get_object_children(name, object)                     
            end
            return children
        end
        
        
        def get_array_children(name, array)
            index = -1
            children = array.collect { |value|
                 index += 1                             
                 [ sprintf('[%d]', index), sprintf('%s[%d]', name, index), value ]
            }    
            return children        
        end
        
        def get_hash_children(name, hash)
            children = hash.collect { |key, value|
                [ sprintf("[%s]", key.inspect), sprintf("%s[%s]", name, key.inspect), value ]
            }
            return children        
        end
        
        def get_matchdata_children(name, matchdata)
            index = -1
            children = matchdata.to_a.collect { |value|
                 index += 1                             
                 [ sprintf('[%d]', index), sprintf('%s[%d]', name, index), value ]
            }    
            return children        
        end        
        
        def get_object_children(name, object)         
            children = object.instance_variables.collect { |var|
                real_var = object.instance_variable_get(var)
                [ var, sprintf("%s.instance_eval('%s')", name, var), real_var ]
            }
            return children        
        end
	end
end
