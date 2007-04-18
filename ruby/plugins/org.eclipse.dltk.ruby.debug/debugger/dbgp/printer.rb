require 'socket'
require "base64"

class Printer

private
	def escape(text)
		text.sub!(/&/,"&#38;")
		text.sub!(/"/,"&#34;")
		text.sub!(/</,"&#60;")
		text.sub!(/>/,"&#62;")
		text
	end

	def escape_map(m)
		m.each { |k, v|
			if v.kind_of?(String)
				m[k] = escape(v)
			end
		}			
	end


	def wrap(xml)
		'<?xml version="1.0" encoding="UTF-8"?>' + xml
	end

	def std_data(command, m)
		sprintf('<stream type="%s">%s</stream>',
			command, Base64.encode64(m['data']))
	end

	def print_property(m)
		escape_map(m)
		#sprintf('<property name="%s" fullname="%s" type="%s" classname="%s" constant="%d" children="%d" size="%d"
		#	          page="%d" pagesize="%d" address="%d"  key="%s"  encoding="base64" numchildren="%d">%s</property>',
		#		m['short_name'], m['long_name'], m['data_type'], m['class_name'], m['cosntant'], m['children'], m['size'],
		#		m['page'], m['page_size'], m['address'], m['key'],  m['numchildren'], Base64.encode64(m['value']))
		sprintf('<property name="%s" fullname="%s" type="%s" classname="%s" constant="%d" children="%d" encoding="base64">%s</property>',
				m['short_name'], m['long_name'], m['data_type'], m['class_name'], m['cosntant'], m['children'], Base64.encode64(m['value']))
	end

	def print_continuation(command, m)
		sprintf('<response command="%s" status="%s" reason="%s" transaction_id="%d"/>',
			command, m['status'], m['reason'], m['id'])
	end

public
	def print(command, data)
	    escape_map(data)
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

	# Properties
	def print_property_get(m)
		sprintf('<response command="property_get" transaction_id="%d">%s</response>',
			m['id'], print_property(m['property']))
	end

	def print_property_set(m)
		sprintf('<response command="property_set" success="%d" transaction_id="%d"/>',
			m['success'], m['id'])
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
		props = ''
		m['properties'].each { |s|
			props += print_property(s)
		}

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
			levels += sprintf('<stack level="%d" type="%s" filename="%s" lineno="%d" cmdbegin="%s" cmdend="%s"/>',
				s['level'], s['type'], s['filename'], s['lineno'], s['cmdbegin'], s['cmdend'])
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
		puts "!!! NOT IMPLEMENTED !!!"
	end

	def print_breakpoint_update(m)
		sprintf('<response command="breakpoint_update" transaction_id="%d"/>',
			m['id'])
	end

	def print_breakpoint_remove(m)
		sprintf('<response command="breakpoint_remove", transaction_id="%d"/>',
			m['id'])
	end

	def print_breakpoint_list(m)
		puts "!!! NOT IMPLEMENTED !!!"
	end
end

