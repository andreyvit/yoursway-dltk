###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

module XoredDebugger
    class ThreadManager
        def add_thread(thread)
            wrapper = @threads[thread] = @creator.call(thread)
            wrapper.created
        end
        private :add_thread
        
        def remove_thread(thread)
            wrapper = @threads.delete(thread)
            wrapper.terminated
        end
        private :remove_thread
		
		def get_threads(&filter)		
			list = Thread.list
			filter.nil? ? list : list.delete_if(&filter)
		end
		private :get_threads
    
        def initialize(&creator)
            @creator = creator
            @threads = {}
        end
		
		def main_thread
			@threads[Thread.main]
		end
		
		def all_threads
			@threads.values
		end

        def synchronize(&filter)
			ths = get_threads(&filter)
			
			# Adding
			added = ths - @threads.keys
			added.each { |t| 
				add_thread(t) 
			}
			
			# Removing
			removed = @threads.keys - ths
			removed.each { |t| 
				remove_thread(t) 
			}               
			
			@threads[Thread.current]
        end     
    end # class ThreadManager
end # module XoredDebugger