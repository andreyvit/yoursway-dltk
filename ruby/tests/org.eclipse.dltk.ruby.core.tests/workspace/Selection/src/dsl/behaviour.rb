module Spec
  module DSL
  	# Evaluation module used to 
    class EvalModule < Module; end
		
		# Behaviour class stores and evaluates behaviour
    class Behaviour
      extend BehaviourCallbacks

      class << self
      	# Adds shared behaviour to the list of shared behaviours
      	# Raises ArgumentError if passed shared behaviours already on list
        def add_shared_behaviour(behaviour)
          raise ArgumentError.new("Shared Behaviour '#{behaviour.description}' already exists") if find_shared_behaviour(behaviour.description)
          shared_behaviours << behaviour
        end
				
				# Finds shared behaviour by description
        def find_shared_behaviour(behaviour_description)
          shared_behaviours.find { |b| b.description == behaviour_description }
        end
				
				# List of shared behaviours that were added 
        def shared_behaviours
          # TODO - this needs to be global, or at least accessible from
          # from subclasses of Behaviour in a centralized place. I'm not loving
          # this as a solution, but it works for now.
          $shared_behaviours ||= []
        end
      end
			
			# Initializes behaviour. Passed arguments are taken to Description constructor
			# Block passed to behaviour is evaluated
      def initialize(*args, &behaviour_block)
        init_description(*args)
        init_eval_module
        before_eval
        eval_behaviour(&behaviour_block)
      end
      
    private

      def init_description(*args)
        @description = Description.new(*args)
      end
      
      def init_eval_module
        @eval_module = EvalModule.new
        @eval_module.extend BehaviourEval::ModuleMethods
        @eval_module.include BehaviourEval::InstanceMethods
        @eval_module.behaviour = self
        @eval_module.description = @description
      end

      def eval_behaviour(&behaviour_block)
        @eval_module.class_eval(&behaviour_block)
      end
      
    protected
    
      def before_eval
      end
      
    public
			
			# Behaviour runner
			# Params are reporter that is used to build output, dry run (do not try to actually evaluate anything),
			# reversed order (false by default) and optional timeout for the run.
      def run(reporter, dry_run=false, reverse=false, timeout=nil)
        return if shared?                                # do not run shared behaviours twice
        reporter.add_behaviour(description)              # add behaviour to reporter
        prepare_execution_context_class                  #  
        errors = run_before_all(reporter, dry_run)       # list errors from before :all run

        specs = reverse ? examples.reverse : examples
        example_execution_context = nil
         
        if errors.empty?
          specs.each do |example|
            example_execution_context = execution_context(example)
            example_execution_context.copy_instance_variables_from(@before_and_after_all_context_instance) unless before_all_proc.nil?
            example.run(reporter, before_each_proc, after_each_proc, dry_run, example_execution_context, timeout)
          end
        end
        
        @before_and_after_all_context_instance.copy_instance_variables_from(example_execution_context) unless after_all_proc.nil?
        run_after_all(reporter, dry_run)
      end

      def number_of_examples
        examples.length
      end

      def matches?(specified_examples)
        matcher ||= ExampleMatcher.new(description)

        examples.each do |example|
          return true if example.matches?(matcher, specified_examples)
        end
        return false
      end

      def shared?
        @description[:shared]
      end

      def retain_examples_matching!(specified_examples)
        return if specified_examples.index(description)
        matcher = ExampleMatcher.new(description)
        examples.reject! do |example|
          !example.matches?(matcher, specified_examples)
        end
      end

      def methods
        my_methods = super
        my_methods |= @eval_module.methods
        my_methods
      end

    protected

      # Messages that this class does not understand
      # are passed directly to the @eval_module.
      def method_missing(sym, *args, &block)
        @eval_module.send(sym, *args, &block)
      end
			
			# Prepairs execution context and returns it's instance
			# It plugs mock framework (specified in configuration or default RSpec mocking framework) then 
			# weaves in included modules into execution context class
      def prepare_execution_context_class
        plugin_mock_framework
        weave_in_included_modules
        define_predicate_matchers #this is in behaviour_eval
        execution_context_class
      end

      def weave_in_included_modules
        mods = included_modules
        eval_module = @eval_module
        execution_context_class.class_eval do
          include eval_module
          Spec::Runner.configuration.included_modules.each { |mod| include mod }
          mods.each { |mod| include mod }
        end
      end

      def execution_context(example)
        execution_context_class.new(example)
      end

      def run_before_all(reporter, dry_run)
        errors = []
        unless dry_run
          begin
            @before_and_after_all_context_instance = execution_context(nil)
            @before_and_after_all_context_instance.instance_eval(&before_all_proc)
          rescue => e
            errors << e
            location = "before(:all)"
            reporter.example_finished(location, e, location) if reporter
          end
        end
        errors
      end
      
			# Executed when all contexts are executed
			# Takes reporter instance used for output as first argument. Second is whether we use dry run (executing nothing)
      def run_after_all(reporter, dry_run)
        unless dry_run
          begin 
            @before_and_after_all_context_instance ||= execution_context(nil) 
            @before_and_after_all_context_instance.instance_eval(&after_all_proc) 
          rescue => e
            location = "after(:all)"
            reporter.example_finished(location, e, location) if reporter # 
          end
        end
      end
      
			# Plugs in mock framework, either set in configuration or RSpec own default framework for mocks.
      def plugin_mock_framework
        case mock_framework = Spec::Runner.configuration.mock_framework
        when Module
          include mock_framework
        else
          require Spec::Runner.configuration.mock_framework
          include Spec::Plugins::MockFramework
        end
      end
      
			# Returns string representation of description
      def description
        @description.to_s
      end
      
			# Returns string representation of described type
      def described_type
        @description.described_type
      end

    end
  end
end
