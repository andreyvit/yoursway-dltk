package org.eclipse.dltk.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class SimpleDLTKExtensionManager {
	private List extensions;

	private String extensionPoint = null;
	public SimpleDLTKExtensionManager( String extension ) {
		this.extensionPoint = extension;
	}
	
	public static class ElementInfo {
		public IConfigurationElement config;
		public Object object;

		protected ElementInfo(IConfigurationElement config) {
			this.config = config;
		}

		public IConfigurationElement getConfig() {
			return this.config;
		}

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((config == null) ? 0 : config.hashCode());
			result = prime * result
					+ ((object == null) ? 0 : object.hashCode());
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final ElementInfo other = (ElementInfo) obj;
			if (config == null) {
				if (other.config != null)
					return false;
			} else if (!config.equals(other.config))
				return false;
			if (object == null) {
				if (other.object != null)
					return false;
			} else if (!object.equals(other.object))
				return false;
			return true;
		}
		
	}
	private void initialize() {
		if (extensions != null) {
			return;
		}

		extensions = new ArrayList(5);
		IConfigurationElement[] cfg = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(this.extensionPoint);

		for (int i = 0; i < cfg.length; i++) {
			ElementInfo info = createInfo(cfg[i]);
			if( !this.extensions.contains(info) ) {
				extensions.add(info);
			}
		}
	}
	public ElementInfo[] getElementInfos() {
		initialize();
		return (ElementInfo[]) extensions.toArray(new ElementInfo[extensions.size()]);
	}
	protected ElementInfo createInfo(IConfigurationElement config) {
		return new ElementInfo(config);
	}
}
