class Exception
    yaml_as "tag:ruby.yaml.org,2002:exception"
    def Exception.yaml_new( klass, tag, val )
        o = YAML.object_maker( klass, { 'mesg' => val.delete( 'message' ) } )
        val.each_pair do |k,v|
            o.instance_variable_set("@#{k}", v)
        end
    end
end
