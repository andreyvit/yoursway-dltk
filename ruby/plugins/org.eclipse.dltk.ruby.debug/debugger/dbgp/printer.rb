###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'socket'
require 'base64'

module XoredDebugger

    class Printer

    private
        def escape(text)
            text.gsub(/&/,"&#38;").gsub(/"/,"&#34;").gsub(/</,"&#60;").gsub(/>/,"&#62;")
        end

        def escape_data(data)
            if data.class  == String
                escape(data)
            elsif data.class == Array
                data.collect! { |v|
                    escape_data(v)
                }
            elsif data.class == Hash
                data.each { |k, v|
                    data[k] = escape_data(v)    
                }
            else
                data
            end
        end

        def bool_to_bit(value)
            return value ? '1' : '0'
        end


        def wrap(xml)
            '<?xml version="1.0" encoding="UTF-8"?>' + xml
        end

        def std_data(command, m)
            sprintf('<stream type="%s">%s</stream>',
                command, Base64.encode64(m['data']))
        end

        def print_property(m)
            value = ''
            children = m['children_props']
            unless children.nil?
                value = children.collect { |p| print_property(p) }.join("\n")
            end
            
            sprintf('<property name="%s" fullname="%s" type="%s" constant="%d" children="%d" encoding="base64" key="%s">%s</property>',
                    m['name'], 
                    m['eval_name'], 
                    m['type'].to_s,
                    bool_to_bit(m['is_cosntant']),
                    bool_to_bit(m['has_children']), 
                    m['key'].to_s,
                    value.empty? ? Base64.encode64(m['value']) : value)
        end

        def print_continuation(command, m)
            sprintf('<response command="%s" status="%s" reason="%s" transaction_id="%d"/>',
                command, m['status'], m['reason'], m['id'])
        end

    public
        def print(command, data)
            #puts '#### Data ####'
            #p data

            escape_data(data)
            
            #puts '#### Escaped data ####'
            #p data

            self.send('print_' + command, data)
        end

        # Init command
        def print_init(m)
            sprintf('<init appid="%s" idekey="%s" session="%s"  thread="%s"  parent="%s"  language="ruby"  protocol_version="1.0" fileuri="%s"/>',
                m['app_id'], m['ide_key'], m['session'], m['thread'], m['parent'], m['file_uri'])
        end

        # Status command
        def print_status(m)
            print_continuation('status', m)
        end 


        # Feature commands
        def print_feature_get(m)
            sprintf('<response command="feature_get" feature_name="%s" supported="%d" transaction_id="%d">%s</response>',
                m['name'], m['supported'], m['id'], Base64.encode64(m['value']))
        end

        def print_feature_set(m)
            sprintf('<response command="feature_set" feature="%s" success="%d" transaction_id="%d"/>', 
                m['name'], m['success'], m['id'])
        end

        # Continuation commands
        def print_run(m)
            print_continuation('run', m)
        end

        def print_step_into(m)
            print_continuation('step_into', m)
        end

        def print_step_over(m)
            print_continuation('step_over', m)
        end

        def print_step_out(m)
            print_continuation('step_out', m)
        end

        def print_stop(m)
            print_continuation('stop', m)
        end
        
        def print_detach(m)
            print_continuation('detach', m)
        end

        def print_break(m)
            sprintf('<response command="break" transaction_id="%d" success="%s"/>',
                m['id'], m['success'] ? '1' : '0')
        end

        # Properties
        def print_property_get(m)
            sprintf('<response command="property_get" transaction_id="%d">%s</response>',
                m['id'], print_property(m['property']))
        end

        def print_property_set(m)
            sprintf('<response command="property_set" transaction_id="%d" success="%d"/>',
                m['id'], m['success'] ? '1' : '0')
        end

        def print_property_value(m)
            sprintf('<response command="property_value" size="%d" encoding="%s" transaction_id="%d">%s</response>',
                m['size'], m['encoding'], m['id'], Base64.encode64(m['value']));
        end

        # Contexts
        def print_context_names(m)
            ctxs = ''
            m['contexts'].each { |s|
                ctxs += sprintf('<context name="%s" id="%d"/>', s['name'], s['id'])
            }
            sprintf('<response command="context_names" transaction_id="%d">%s</response>',
                m['id'], ctxs)
        end

        def print_context_get(m)

            props = m['properties'].collect { |p| print_property(p) }.join("\n")

            sprintf('<response command="context_get" context="%d"  transaction_id="%d">%s</response>',
                m['context_id'], m['id'], props)
        end

        # Stream commands
        def print_std(command, m)
            sprintf('<response command="%s" success="%d" transaction_id="%d"/>',
                command, m['success'], m['id'])
        end

        def print_stdout(m)
            print_std('stdout', m)
        end

        def print_stderr(m)
            print_std('stderr', m)
        end

        def print_stdin(m)
            print_std('stdin', m)
        end

        # Data
        def print_stdout_data(m)
            std_data('stdout', m)
        end

        def print_stderr_data(m)
            std_data('stderr', m)
        end

        # Stack commands
        def print_stack_depth(m)
            sprintf('<response command="stack_depth" depth="%d" transaction_id="%d"/>',
                m['depth'], m['id'])
        end
                              
        def print_stack_get(m)
            levels = ''
            m['levels'].each { |s|
                levels += sprintf('<stack level="%d" type="%s" filename="%s" lineno="%d" cmdbegin="%s" cmdend="%s" where="%s"/>',
                    s['level'], s['type'], s['filename'], s['lineno'], s['cmdbegin'], s['cmdend'], s['where'])
            }

            sprintf('<response command="stack_get" transaction_id="%d">%s</response>',
                m['id'], levels)
        end

        # Breakpoint commands
        def print_breakpoint_set(m)
            sprintf('<response command="breakpoint_set" state="%s" id="%d" transaction_id="%d"/>',
                m['state'] == true ? 'enabled' : 'disabled', m['breakpoint_id'], m['id'])
        end

        def print_breakpoint_get(m)
            # TODO:
        end

        def print_breakpoint_update(m)
            sprintf('<response command="breakpoint_update" transaction_id="%d"/>',
                m['id'])
        end

        def print_breakpoint_remove(m)
            sprintf('<response command="breakpoint_remove" transaction_id="%d"/>',
                m['id'])
        end

        def print_breakpoint_list(m)
            # TODO:
        end
    end # class Printer

end # module