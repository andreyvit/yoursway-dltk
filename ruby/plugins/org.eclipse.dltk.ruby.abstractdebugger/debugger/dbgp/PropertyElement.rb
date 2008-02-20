require 'dbgp/XmlElement'
require 'common/Logger'

module XoredDebugger
    class PropertyElement < XmlElement
        include Logger
        
        attr_reader :xml       
        def initialize(object, name, pagesize = nil, page = nil, fullname = nil, add_children = true)
            super('property')
            
            if (fullname.nil?)
                fullname = name
            end
            
            @pagesize = pagesize
            
            num_children = num_children(object)
            
            add_attribute('name', name)
            add_attribute('fullname', fullname)
            add_attribute('type', object.class.to_s)
            add_attribute('constant', 0)
            add_attribute('key', object.object_id)
            add_attribute('encoding','base64')
            add_attribute('children', num_children > 0 ? '1': '0') 
            add_attribute('numchildren', num_children)            
            
            unless page.nil? || num_children == 0
                add_attribute('page', page)                
                add_attribute('pagesize', pagesize)
            end
                        
            value = get_value(object, num_children > 0)
            data = prepare_data(value)
            if (add_children && num_children > 0)
                data += get_children_xml(fullname, object, page, pagesize)                
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
        
        def num_children(object)
           type = object.class
           if (type == Array)
               return object.size
           elsif (type == Hash)
               return object.size
           elsif (type == MatchData)
               return object.to_a.size                              
           else
               return object.instance_variables.size
           end
        end
        
        def get_children_xml(name, object, page, pagesize)
            log('Get children xml')
			start = 0
			finish = num_children(object) - 1
            unless (page.nil? || pagesize.nil?)
                start = page*pagesize
            	new_finish = (page + 1)*pagesize - 1
            	            	
            	if (finish > new_finish)
            	    finish = new_finish
            	end                
            end
            log('Gathering ' + name + ' children form ' + start.to_s + ' to ' + finish.to_s)

        	type = object.class
            if (type == Array)
            	return get_array_children_xml(name, object, start, finish)
            elsif (type == Hash)
            	return get_hash_children_xml(name, object, start, finish)   
            elsif (type == MatchData)             
            	return get_array_children_xml(name, object.to_a, start, finish)   
            else
            	return get_object_children_xml(name, object, start, finish)                     
            end
        end
        
        
        def get_array_children_xml(name, array, start, finish)
            children_xml = ''
            for index in start..finish
                child_name = sprintf('[%d]', index)
                child_fullname = sprintf('%s[%d]', name, index) 
                children_xml += PropertyElement.new(array[index], child_name, @pagesize, nil, child_fullname, false).to_xml
            end    
            return children_xml        
        end
        
        def get_object_children_xml(name, object, start, finish)         
            children_xml = ''
            vars = object.instance_variables          
        	for index in start..finish
                child_name = vars[index]
                child = object.instance_variable_get(child_name)
                child_fullname = sprintf("%s.instance_eval('%s')", name, child_name) 
                children_xml += PropertyElement.new(child, child_name, @pagesize, nil, child_fullname, false).to_xml
            end    
            return children_xml
        end
        
        def get_hash_children_xml(name, hash, start, finish)
            keys = hash.keys[start..finish]
            children_xml = ''
            keys.each { |key|
                value = hash[key]
                child_name = sprintf("[%s]", key.inspect)
                child_fullname = sprintf("%s[%s]", name, key.inspect) 
                children_xml += PropertyElement.new(value, child_name, @pagesize, nil, child_fullname, false).to_xml                
            }
            return children_xml        
        end     
 	end
end
