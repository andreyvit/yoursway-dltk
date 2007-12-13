class BreakpointTypeNotSupportedError < RuntimeError
end

class BreakpointCouldNotBeSetError < RuntimeError
end

class InvalidContextError < RuntimeError
end

class NoSuchBreakpointError < RuntimeError
end

class OperationNotAvailableError < RuntimeError
end
