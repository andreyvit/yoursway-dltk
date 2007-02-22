package org.eclipse.dltk.ruby.typeinference.internal;

public abstract class TypeInferenceMode {
	
	private TypeInferenceMode() {
	}

	public static final TypeInferenceMode EXTERNAL_SCOPES_CONSTRUCTION = new TypeInferenceMode() {
		
	};
	
	public static final TypeInferenceMode BACKGROUND_INFERENCING = new TypeInferenceMode() {
		
	};
	
	public static final TypeInferenceMode FULL_INFERENCING = new TypeInferenceMode() {
		
	};
	
	public static final TypeInferenceMode ACTIVATING_SCOPES = new TypeInferenceMode() {
		
	};
	
}
