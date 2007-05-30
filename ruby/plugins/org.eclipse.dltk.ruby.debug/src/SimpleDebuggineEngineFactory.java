import org.eclipse.dltk.debug.core.IDebuggingEngineRunner;
import org.eclipse.dltk.debug.core.IDebuggingEngineRunnerFactory;


public class SimpleDebuggineEngineFactory implements
		IDebuggingEngineRunnerFactory {

	public SimpleDebuggineEngineFactory() {
	}

	public IDebuggingEngineRunner createDebuggingEngineRunner() {
		return new SimpleDebuggingEngineRunner();
	}

}
