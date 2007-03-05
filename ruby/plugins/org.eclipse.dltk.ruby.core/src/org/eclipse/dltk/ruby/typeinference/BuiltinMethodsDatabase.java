package org.eclipse.dltk.ruby.typeinference;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dltk.ast.Modifiers;

public class BuiltinMethodsDatabase {

	public static class MethodInfo {

		private final String name;

		private final int arity;

		private final int flags;

		public MethodInfo(String name, int arity, int flags) {
			this.name = name;
			this.arity = arity;
			this.flags = flags;
		}

		public int getArity() {
			return arity;
		}

		public String getName() {
			return name;
		}

		public int getFlags() {
			return flags;
		}

	}

	public static class Metaclass {

		private MethodInfo[] methods;

		private ModuleMetaclass[] includedModules;

		private String name;

		private ClassMetaclass metaClass;

		public Metaclass(String name) {
			this.name = name;
			if (metaClass instanceof SingletonMetaclass) {
				SingletonMetaclass metaclass = (SingletonMetaclass) metaClass;
				metaclass.set$instanceClass(this);
			}
		}

		public ModuleMetaclass[] getIncludedModules() {
			return includedModules;
		}

		public void setIncludedModules(ModuleMetaclass[] includedModules) {
			this.includedModules = includedModules;
		}

		public ClassMetaclass getMetaClass() {
			return metaClass;
		}

		public void setMetaClass(ClassMetaclass metaClass) {
			this.metaClass = metaClass;
		}

		public MethodInfo[] getMethods() {
			return methods;
		}

		public void setMethods(MethodInfo[] methods) {
			this.methods = methods;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class ModuleMetaclass extends Metaclass {

		public ModuleMetaclass(String name) {
			super(name);
		}

	}

	public static class ClassMetaclass extends Metaclass {

		private ClassMetaclass superClass;

		public ClassMetaclass(String name) {
			super(name);
		}

		public ClassMetaclass getSuperClass() {
			return superClass;
		}

		public void setSuperClass(ClassMetaclass superClass) {
			this.superClass = superClass;
		}

	}

	public static class SingletonMetaclass extends ClassMetaclass {

		private Metaclass instanceClass;

		public SingletonMetaclass(String name) {
			super(name);
		}

		void set$instanceClass(Metaclass instanceClass) {
			this.instanceClass = instanceClass;
		}

		public Metaclass getInstanceClass() {
			return instanceClass;
		}

	}

	private final static Map metaclasses = new HashMap();

	private static Metaclass add(Metaclass metaclass) {
		metaclasses.put(metaclass.getName(), metaclass);
		return metaclass;
	}

	public static Metaclass get(String name) {
		return (Metaclass) metaclasses.get(name);
	}

	static {
		// start generated code
		ModuleMetaclass kernelModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass("Kernel"));
		SingletonMetaclass kernelModuleSingletonMetaclass = new SingletonMetaclass("Kernel");
		ModuleMetaclass fileTestModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"FileTest"));
		SingletonMetaclass fileTestModuleSingletonMetaclass = new SingletonMetaclass("FileTest");
		ModuleMetaclass marshalModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Marshal"));
		SingletonMetaclass marshalModuleSingletonMetaclass = new SingletonMetaclass("Marshal");
		ModuleMetaclass signalModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass("Signal"));
		SingletonMetaclass signalModuleSingletonMetaclass = new SingletonMetaclass("Signal");
		ModuleMetaclass process_GIDModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Process::GID"));
		SingletonMetaclass process_GIDModuleSingletonMetaclass = new SingletonMetaclass(
				"Process::GID");
		ModuleMetaclass file_ConstantsModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"File::Constants"));
		SingletonMetaclass file_ConstantsModuleSingletonMetaclass = new SingletonMetaclass(
				"File::Constants");
		ModuleMetaclass objectSpaceModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"ObjectSpace"));
		SingletonMetaclass objectSpaceModuleSingletonMetaclass = new SingletonMetaclass(
				"ObjectSpace");
		ModuleMetaclass precisionModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Precision"));
		SingletonMetaclass precisionModuleSingletonMetaclass = new SingletonMetaclass("Precision");
		ModuleMetaclass enumerableModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Enumerable"));
		SingletonMetaclass enumerableModuleSingletonMetaclass = new SingletonMetaclass("Enumerable");
		ModuleMetaclass errnoModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass("Errno"));
		SingletonMetaclass errnoModuleSingletonMetaclass = new SingletonMetaclass("Errno");
		ModuleMetaclass process_SysModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Process::Sys"));
		SingletonMetaclass process_SysModuleSingletonMetaclass = new SingletonMetaclass(
				"Process::Sys");
		ModuleMetaclass process_UIDModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Process::UID"));
		SingletonMetaclass process_UIDModuleSingletonMetaclass = new SingletonMetaclass(
				"Process::UID");
		ModuleMetaclass mathModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass("Math"));
		SingletonMetaclass mathModuleSingletonMetaclass = new SingletonMetaclass("Math");
		ModuleMetaclass gCModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass("GC"));
		SingletonMetaclass gCModuleSingletonMetaclass = new SingletonMetaclass("GC");
		ModuleMetaclass processModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Process"));
		SingletonMetaclass processModuleSingletonMetaclass = new SingletonMetaclass("Process");
		ModuleMetaclass comparableModuleMetaclass = (ModuleMetaclass) add(new ModuleMetaclass(
				"Comparable"));
		SingletonMetaclass comparableModuleSingletonMetaclass = new SingletonMetaclass("Comparable");
		ClassMetaclass argumentErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"ArgumentError"));
		SingletonMetaclass argumentErrorClassSingletonMetaclass = new SingletonMetaclass(
				"ArgumentError");
		ClassMetaclass arrayMetaclass = (ClassMetaclass) add(new ClassMetaclass("Array"));
		SingletonMetaclass arrayClassSingletonMetaclass = new SingletonMetaclass("Array");
		ClassMetaclass bignumMetaclass = (ClassMetaclass) add(new ClassMetaclass("Bignum"));
		SingletonMetaclass bignumClassSingletonMetaclass = new SingletonMetaclass("Bignum");
		ClassMetaclass bindingMetaclass = (ClassMetaclass) add(new ClassMetaclass("Binding"));
		SingletonMetaclass bindingClassSingletonMetaclass = new SingletonMetaclass("Binding");
		ClassMetaclass classMetaclass = (ClassMetaclass) add(new ClassMetaclass("Class"));
		SingletonMetaclass classClassSingletonMetaclass = new SingletonMetaclass("Class");
		ClassMetaclass continuationMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Continuation"));
		SingletonMetaclass continuationClassSingletonMetaclass = new SingletonMetaclass(
				"Continuation");
		ClassMetaclass dataMetaclass = (ClassMetaclass) add(new ClassMetaclass("Data"));
		SingletonMetaclass dataClassSingletonMetaclass = new SingletonMetaclass("Data");
		ClassMetaclass dirMetaclass = (ClassMetaclass) add(new ClassMetaclass("Dir"));
		SingletonMetaclass dirClassSingletonMetaclass = new SingletonMetaclass("Dir");
		ClassMetaclass eOFErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("EOFError"));
		SingletonMetaclass eOFErrorClassSingletonMetaclass = new SingletonMetaclass("EOFError");
		ClassMetaclass errno_E2BIGMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::E2BIG"));
		SingletonMetaclass errno_E2BIGClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::E2BIG");
		ClassMetaclass errno_EACCESMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EACCES"));
		SingletonMetaclass errno_EACCESClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EACCES");
		ClassMetaclass errno_EADDRINUSEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EADDRINUSE"));
		SingletonMetaclass errno_EADDRINUSEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EADDRINUSE");
		ClassMetaclass errno_EADDRNOTAVAILMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EADDRNOTAVAIL"));
		SingletonMetaclass errno_EADDRNOTAVAILClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EADDRNOTAVAIL");
		ClassMetaclass errno_EAFNOSUPPORTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EAFNOSUPPORT"));
		SingletonMetaclass errno_EAFNOSUPPORTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EAFNOSUPPORT");
		ClassMetaclass errno_EAGAINMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EAGAIN"));
		SingletonMetaclass errno_EAGAINClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EAGAIN");
		ClassMetaclass errno_EALREADYMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EALREADY"));
		SingletonMetaclass errno_EALREADYClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EALREADY");
		ClassMetaclass errno_EBADFMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EBADF"));
		SingletonMetaclass errno_EBADFClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EBADF");
		ClassMetaclass errno_EBADMSGMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EBADMSG"));
		SingletonMetaclass errno_EBADMSGClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EBADMSG");
		ClassMetaclass errno_EBUSYMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EBUSY"));
		SingletonMetaclass errno_EBUSYClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EBUSY");
		ClassMetaclass errno_ECHILDMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ECHILD"));
		SingletonMetaclass errno_ECHILDClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ECHILD");
		ClassMetaclass errno_ECONNABORTEDMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ECONNABORTED"));
		SingletonMetaclass errno_ECONNABORTEDClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ECONNABORTED");
		ClassMetaclass errno_ECONNREFUSEDMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ECONNREFUSED"));
		SingletonMetaclass errno_ECONNREFUSEDClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ECONNREFUSED");
		ClassMetaclass errno_ECONNRESETMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ECONNRESET"));
		SingletonMetaclass errno_ECONNRESETClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ECONNRESET");
		ClassMetaclass errno_EDEADLKMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EDEADLK"));
		SingletonMetaclass errno_EDEADLKClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EDEADLK");
		ClassMetaclass errno_EDESTADDRREQMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EDESTADDRREQ"));
		SingletonMetaclass errno_EDESTADDRREQClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EDESTADDRREQ");
		ClassMetaclass errno_EDOMMetaclass = (ClassMetaclass) add(new ClassMetaclass("Errno::EDOM"));
		SingletonMetaclass errno_EDOMClassSingletonMetaclass = new SingletonMetaclass("Errno::EDOM");
		ClassMetaclass errno_EDQUOTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EDQUOT"));
		SingletonMetaclass errno_EDQUOTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EDQUOT");
		ClassMetaclass errno_EEXISTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EEXIST"));
		SingletonMetaclass errno_EEXISTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EEXIST");
		ClassMetaclass errno_EFAULTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EFAULT"));
		SingletonMetaclass errno_EFAULTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EFAULT");
		ClassMetaclass errno_EFBIGMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EFBIG"));
		SingletonMetaclass errno_EFBIGClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EFBIG");
		ClassMetaclass errno_EHOSTDOWNMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EHOSTDOWN"));
		SingletonMetaclass errno_EHOSTDOWNClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EHOSTDOWN");
		ClassMetaclass errno_EHOSTUNREACHMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EHOSTUNREACH"));
		SingletonMetaclass errno_EHOSTUNREACHClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EHOSTUNREACH");
		ClassMetaclass errno_EIDRMMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EIDRM"));
		SingletonMetaclass errno_EIDRMClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EIDRM");
		ClassMetaclass errno_EILSEQMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EILSEQ"));
		SingletonMetaclass errno_EILSEQClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EILSEQ");
		ClassMetaclass errno_EINPROGRESSMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EINPROGRESS"));
		SingletonMetaclass errno_EINPROGRESSClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EINPROGRESS");
		ClassMetaclass errno_EINTRMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EINTR"));
		SingletonMetaclass errno_EINTRClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EINTR");
		ClassMetaclass errno_EINVALMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EINVAL"));
		SingletonMetaclass errno_EINVALClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EINVAL");
		ClassMetaclass errno_EIOMetaclass = (ClassMetaclass) add(new ClassMetaclass("Errno::EIO"));
		SingletonMetaclass errno_EIOClassSingletonMetaclass = new SingletonMetaclass("Errno::EIO");
		ClassMetaclass errno_EISCONNMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EISCONN"));
		SingletonMetaclass errno_EISCONNClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EISCONN");
		ClassMetaclass errno_EISDIRMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EISDIR"));
		SingletonMetaclass errno_EISDIRClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EISDIR");
		ClassMetaclass errno_ELOOPMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ELOOP"));
		SingletonMetaclass errno_ELOOPClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ELOOP");
		ClassMetaclass errno_EMFILEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EMFILE"));
		SingletonMetaclass errno_EMFILEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EMFILE");
		ClassMetaclass errno_EMLINKMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EMLINK"));
		SingletonMetaclass errno_EMLINKClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EMLINK");
		ClassMetaclass errno_EMSGSIZEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EMSGSIZE"));
		SingletonMetaclass errno_EMSGSIZEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EMSGSIZE");
		ClassMetaclass errno_EMULTIHOPMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EMULTIHOP"));
		SingletonMetaclass errno_EMULTIHOPClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EMULTIHOP");
		ClassMetaclass errno_ENAMETOOLONGMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENAMETOOLONG"));
		SingletonMetaclass errno_ENAMETOOLONGClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENAMETOOLONG");
		ClassMetaclass errno_ENETDOWNMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENETDOWN"));
		SingletonMetaclass errno_ENETDOWNClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENETDOWN");
		ClassMetaclass errno_ENETRESETMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENETRESET"));
		SingletonMetaclass errno_ENETRESETClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENETRESET");
		ClassMetaclass errno_ENETUNREACHMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENETUNREACH"));
		SingletonMetaclass errno_ENETUNREACHClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENETUNREACH");
		ClassMetaclass errno_ENFILEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENFILE"));
		SingletonMetaclass errno_ENFILEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENFILE");
		ClassMetaclass errno_ENOBUFSMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOBUFS"));
		SingletonMetaclass errno_ENOBUFSClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOBUFS");
		ClassMetaclass errno_ENODATAMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENODATA"));
		SingletonMetaclass errno_ENODATAClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENODATA");
		ClassMetaclass errno_ENODEVMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENODEV"));
		SingletonMetaclass errno_ENODEVClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENODEV");
		ClassMetaclass errno_ENOENTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOENT"));
		SingletonMetaclass errno_ENOENTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOENT");
		ClassMetaclass errno_ENOEXECMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOEXEC"));
		SingletonMetaclass errno_ENOEXECClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOEXEC");
		ClassMetaclass errno_ENOLCKMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOLCK"));
		SingletonMetaclass errno_ENOLCKClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOLCK");
		ClassMetaclass errno_ENOLINKMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOLINK"));
		SingletonMetaclass errno_ENOLINKClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOLINK");
		ClassMetaclass errno_ENOMEMMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOMEM"));
		SingletonMetaclass errno_ENOMEMClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOMEM");
		ClassMetaclass errno_ENOMSGMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOMSG"));
		SingletonMetaclass errno_ENOMSGClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOMSG");
		ClassMetaclass errno_ENOPROTOOPTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOPROTOOPT"));
		SingletonMetaclass errno_ENOPROTOOPTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOPROTOOPT");
		ClassMetaclass errno_ENOSPCMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOSPC"));
		SingletonMetaclass errno_ENOSPCClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOSPC");
		ClassMetaclass errno_ENOSRMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOSR"));
		SingletonMetaclass errno_ENOSRClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOSR");
		ClassMetaclass errno_ENOSTRMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOSTR"));
		SingletonMetaclass errno_ENOSTRClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOSTR");
		ClassMetaclass errno_ENOSYSMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOSYS"));
		SingletonMetaclass errno_ENOSYSClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOSYS");
		ClassMetaclass errno_ENOTBLKMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOTBLK"));
		SingletonMetaclass errno_ENOTBLKClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOTBLK");
		ClassMetaclass errno_ENOTCONNMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOTCONN"));
		SingletonMetaclass errno_ENOTCONNClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOTCONN");
		ClassMetaclass errno_ENOTDIRMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOTDIR"));
		SingletonMetaclass errno_ENOTDIRClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOTDIR");
		ClassMetaclass errno_ENOTEMPTYMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOTEMPTY"));
		SingletonMetaclass errno_ENOTEMPTYClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOTEMPTY");
		ClassMetaclass errno_ENOTSOCKMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOTSOCK"));
		SingletonMetaclass errno_ENOTSOCKClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOTSOCK");
		ClassMetaclass errno_ENOTTYMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENOTTY"));
		SingletonMetaclass errno_ENOTTYClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENOTTY");
		ClassMetaclass errno_ENXIOMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ENXIO"));
		SingletonMetaclass errno_ENXIOClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ENXIO");
		ClassMetaclass errno_EOPNOTSUPPMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EOPNOTSUPP"));
		SingletonMetaclass errno_EOPNOTSUPPClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EOPNOTSUPP");
		ClassMetaclass errno_EOVERFLOWMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EOVERFLOW"));
		SingletonMetaclass errno_EOVERFLOWClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EOVERFLOW");
		ClassMetaclass errno_EPERMMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EPERM"));
		SingletonMetaclass errno_EPERMClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EPERM");
		ClassMetaclass errno_EPFNOSUPPORTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EPFNOSUPPORT"));
		SingletonMetaclass errno_EPFNOSUPPORTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EPFNOSUPPORT");
		ClassMetaclass errno_EPIPEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EPIPE"));
		SingletonMetaclass errno_EPIPEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EPIPE");
		ClassMetaclass errno_EPROTOMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EPROTO"));
		SingletonMetaclass errno_EPROTOClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EPROTO");
		ClassMetaclass errno_EPROTONOSUPPORTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EPROTONOSUPPORT"));
		SingletonMetaclass errno_EPROTONOSUPPORTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EPROTONOSUPPORT");
		ClassMetaclass errno_EPROTOTYPEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EPROTOTYPE"));
		SingletonMetaclass errno_EPROTOTYPEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EPROTOTYPE");
		ClassMetaclass errno_ERANGEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ERANGE"));
		SingletonMetaclass errno_ERANGEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ERANGE");
		ClassMetaclass errno_EREMOTEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EREMOTE"));
		SingletonMetaclass errno_EREMOTEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EREMOTE");
		ClassMetaclass errno_EROFSMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EROFS"));
		SingletonMetaclass errno_EROFSClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EROFS");
		ClassMetaclass errno_ESHUTDOWNMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ESHUTDOWN"));
		SingletonMetaclass errno_ESHUTDOWNClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ESHUTDOWN");
		ClassMetaclass errno_ESOCKTNOSUPPORTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ESOCKTNOSUPPORT"));
		SingletonMetaclass errno_ESOCKTNOSUPPORTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ESOCKTNOSUPPORT");
		ClassMetaclass errno_ESPIPEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ESPIPE"));
		SingletonMetaclass errno_ESPIPEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ESPIPE");
		ClassMetaclass errno_ESRCHMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ESRCH"));
		SingletonMetaclass errno_ESRCHClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ESRCH");
		ClassMetaclass errno_ESTALEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ESTALE"));
		SingletonMetaclass errno_ESTALEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ESTALE");
		ClassMetaclass errno_ETIMEMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ETIME"));
		SingletonMetaclass errno_ETIMEClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ETIME");
		ClassMetaclass errno_ETIMEDOUTMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ETIMEDOUT"));
		SingletonMetaclass errno_ETIMEDOUTClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ETIMEDOUT");
		ClassMetaclass errno_ETOOMANYREFSMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ETOOMANYREFS"));
		SingletonMetaclass errno_ETOOMANYREFSClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ETOOMANYREFS");
		ClassMetaclass errno_ETXTBSYMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::ETXTBSY"));
		SingletonMetaclass errno_ETXTBSYClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::ETXTBSY");
		ClassMetaclass errno_EUSERSMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EUSERS"));
		SingletonMetaclass errno_EUSERSClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EUSERS");
		ClassMetaclass errno_EXDEVMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Errno::EXDEV"));
		SingletonMetaclass errno_EXDEVClassSingletonMetaclass = new SingletonMetaclass(
				"Errno::EXDEV");
		ClassMetaclass exceptionMetaclass = (ClassMetaclass) add(new ClassMetaclass("Exception"));
		SingletonMetaclass exceptionClassSingletonMetaclass = new SingletonMetaclass("Exception");
		ClassMetaclass falseClassMetaclass = (ClassMetaclass) add(new ClassMetaclass("FalseClass"));
		SingletonMetaclass falseClassClassSingletonMetaclass = new SingletonMetaclass("FalseClass");
		ClassMetaclass fileMetaclass = (ClassMetaclass) add(new ClassMetaclass("File"));
		SingletonMetaclass fileClassSingletonMetaclass = new SingletonMetaclass("File");
		ClassMetaclass file_StatMetaclass = (ClassMetaclass) add(new ClassMetaclass("File::Stat"));
		SingletonMetaclass file_StatClassSingletonMetaclass = new SingletonMetaclass("File::Stat");
		ClassMetaclass fixnumMetaclass = (ClassMetaclass) add(new ClassMetaclass("Fixnum"));
		SingletonMetaclass fixnumClassSingletonMetaclass = new SingletonMetaclass("Fixnum");
		ClassMetaclass floatMetaclass = (ClassMetaclass) add(new ClassMetaclass("Float"));
		SingletonMetaclass floatClassSingletonMetaclass = new SingletonMetaclass("Float");
		ClassMetaclass floatDomainErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"FloatDomainError"));
		SingletonMetaclass floatDomainErrorClassSingletonMetaclass = new SingletonMetaclass(
				"FloatDomainError");
		ClassMetaclass hashMetaclass = (ClassMetaclass) add(new ClassMetaclass("Hash"));
		SingletonMetaclass hashClassSingletonMetaclass = new SingletonMetaclass("Hash");
		ClassMetaclass iOMetaclass = (ClassMetaclass) add(new ClassMetaclass("IO"));
		SingletonMetaclass iOClassSingletonMetaclass = new SingletonMetaclass("IO");
		ClassMetaclass iOErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("IOError"));
		SingletonMetaclass iOErrorClassSingletonMetaclass = new SingletonMetaclass("IOError");
		ClassMetaclass indexErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("IndexError"));
		SingletonMetaclass indexErrorClassSingletonMetaclass = new SingletonMetaclass("IndexError");
		ClassMetaclass integerMetaclass = (ClassMetaclass) add(new ClassMetaclass("Integer"));
		SingletonMetaclass integerClassSingletonMetaclass = new SingletonMetaclass("Integer");
		ClassMetaclass interruptMetaclass = (ClassMetaclass) add(new ClassMetaclass("Interrupt"));
		SingletonMetaclass interruptClassSingletonMetaclass = new SingletonMetaclass("Interrupt");
		ClassMetaclass loadErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("LoadError"));
		SingletonMetaclass loadErrorClassSingletonMetaclass = new SingletonMetaclass("LoadError");
		ClassMetaclass localJumpErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"LocalJumpError"));
		SingletonMetaclass localJumpErrorClassSingletonMetaclass = new SingletonMetaclass(
				"LocalJumpError");
		ClassMetaclass matchDataMetaclass = (ClassMetaclass) add(new ClassMetaclass("MatchData"));
		SingletonMetaclass matchDataClassSingletonMetaclass = new SingletonMetaclass("MatchData");
		ClassMetaclass methodMetaclass = (ClassMetaclass) add(new ClassMetaclass("Method"));
		SingletonMetaclass methodClassSingletonMetaclass = new SingletonMetaclass("Method");
		ClassMetaclass moduleMetaclass = (ClassMetaclass) add(new ClassMetaclass("Module"));
		SingletonMetaclass moduleClassSingletonMetaclass = new SingletonMetaclass("Module");
		ClassMetaclass nameErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("NameError"));
		SingletonMetaclass nameErrorClassSingletonMetaclass = new SingletonMetaclass("NameError");
		ClassMetaclass nameError_messageMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"NameError::message"));
		SingletonMetaclass nameError_messageClassSingletonMetaclass = new SingletonMetaclass(
				"NameError::message");
		ClassMetaclass nilClassMetaclass = (ClassMetaclass) add(new ClassMetaclass("NilClass"));
		SingletonMetaclass nilClassClassSingletonMetaclass = new SingletonMetaclass("NilClass");
		ClassMetaclass noMemoryErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"NoMemoryError"));
		SingletonMetaclass noMemoryErrorClassSingletonMetaclass = new SingletonMetaclass(
				"NoMemoryError");
		ClassMetaclass noMethodErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"NoMethodError"));
		SingletonMetaclass noMethodErrorClassSingletonMetaclass = new SingletonMetaclass(
				"NoMethodError");
		ClassMetaclass notImplementedErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"NotImplementedError"));
		SingletonMetaclass notImplementedErrorClassSingletonMetaclass = new SingletonMetaclass(
				"NotImplementedError");
		ClassMetaclass numericMetaclass = (ClassMetaclass) add(new ClassMetaclass("Numeric"));
		SingletonMetaclass numericClassSingletonMetaclass = new SingletonMetaclass("Numeric");
		ClassMetaclass objectMetaclass = (ClassMetaclass) add(new ClassMetaclass("Object"));
		SingletonMetaclass objectClassSingletonMetaclass = new SingletonMetaclass("Object");
		ClassMetaclass procMetaclass = (ClassMetaclass) add(new ClassMetaclass("Proc"));
		SingletonMetaclass procClassSingletonMetaclass = new SingletonMetaclass("Proc");
		ClassMetaclass process_StatusMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"Process::Status"));
		SingletonMetaclass process_StatusClassSingletonMetaclass = new SingletonMetaclass(
				"Process::Status");
		ClassMetaclass rangeMetaclass = (ClassMetaclass) add(new ClassMetaclass("Range"));
		SingletonMetaclass rangeClassSingletonMetaclass = new SingletonMetaclass("Range");
		ClassMetaclass rangeErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("RangeError"));
		SingletonMetaclass rangeErrorClassSingletonMetaclass = new SingletonMetaclass("RangeError");
		ClassMetaclass regexpMetaclass = (ClassMetaclass) add(new ClassMetaclass("Regexp"));
		SingletonMetaclass regexpClassSingletonMetaclass = new SingletonMetaclass("Regexp");
		ClassMetaclass regexpErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("RegexpError"));
		SingletonMetaclass regexpErrorClassSingletonMetaclass = new SingletonMetaclass(
				"RegexpError");
		ClassMetaclass runtimeErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"RuntimeError"));
		SingletonMetaclass runtimeErrorClassSingletonMetaclass = new SingletonMetaclass(
				"RuntimeError");
		ClassMetaclass scriptErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("ScriptError"));
		SingletonMetaclass scriptErrorClassSingletonMetaclass = new SingletonMetaclass(
				"ScriptError");
		ClassMetaclass securityErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"SecurityError"));
		SingletonMetaclass securityErrorClassSingletonMetaclass = new SingletonMetaclass(
				"SecurityError");
		ClassMetaclass setMetaclass = (ClassMetaclass) add(new ClassMetaclass("Set"));
		SingletonMetaclass setClassSingletonMetaclass = new SingletonMetaclass("Set");
		ClassMetaclass signalExceptionMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"SignalException"));
		SingletonMetaclass signalExceptionClassSingletonMetaclass = new SingletonMetaclass(
				"SignalException");
		ClassMetaclass sortedSetMetaclass = (ClassMetaclass) add(new ClassMetaclass("SortedSet"));
		SingletonMetaclass sortedSetClassSingletonMetaclass = new SingletonMetaclass("SortedSet");
		ClassMetaclass standardErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"StandardError"));
		SingletonMetaclass standardErrorClassSingletonMetaclass = new SingletonMetaclass(
				"StandardError");
		ClassMetaclass stringMetaclass = (ClassMetaclass) add(new ClassMetaclass("String"));
		SingletonMetaclass stringClassSingletonMetaclass = new SingletonMetaclass("String");
		ClassMetaclass structMetaclass = (ClassMetaclass) add(new ClassMetaclass("Struct"));
		SingletonMetaclass structClassSingletonMetaclass = new SingletonMetaclass("Struct");
		ClassMetaclass struct_TmsMetaclass = (ClassMetaclass) add(new ClassMetaclass("Struct::Tms"));
		SingletonMetaclass struct_TmsClassSingletonMetaclass = new SingletonMetaclass("Struct::Tms");
		ClassMetaclass symbolMetaclass = (ClassMetaclass) add(new ClassMetaclass("Symbol"));
		SingletonMetaclass symbolClassSingletonMetaclass = new SingletonMetaclass("Symbol");
		ClassMetaclass syntaxErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("SyntaxError"));
		SingletonMetaclass syntaxErrorClassSingletonMetaclass = new SingletonMetaclass(
				"SyntaxError");
		ClassMetaclass systemCallErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"SystemCallError"));
		SingletonMetaclass systemCallErrorClassSingletonMetaclass = new SingletonMetaclass(
				"SystemCallError");
		ClassMetaclass systemExitMetaclass = (ClassMetaclass) add(new ClassMetaclass("SystemExit"));
		SingletonMetaclass systemExitClassSingletonMetaclass = new SingletonMetaclass("SystemExit");
		ClassMetaclass systemStackErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"SystemStackError"));
		SingletonMetaclass systemStackErrorClassSingletonMetaclass = new SingletonMetaclass(
				"SystemStackError");
		ClassMetaclass threadMetaclass = (ClassMetaclass) add(new ClassMetaclass("Thread"));
		SingletonMetaclass threadClassSingletonMetaclass = new SingletonMetaclass("Thread");
		ClassMetaclass threadErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("ThreadError"));
		SingletonMetaclass threadErrorClassSingletonMetaclass = new SingletonMetaclass(
				"ThreadError");
		ClassMetaclass threadGroupMetaclass = (ClassMetaclass) add(new ClassMetaclass("ThreadGroup"));
		SingletonMetaclass threadGroupClassSingletonMetaclass = new SingletonMetaclass(
				"ThreadGroup");
		ClassMetaclass timeMetaclass = (ClassMetaclass) add(new ClassMetaclass("Time"));
		SingletonMetaclass timeClassSingletonMetaclass = new SingletonMetaclass("Time");
		ClassMetaclass trueClassMetaclass = (ClassMetaclass) add(new ClassMetaclass("TrueClass"));
		SingletonMetaclass trueClassClassSingletonMetaclass = new SingletonMetaclass("TrueClass");
		ClassMetaclass typeErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass("TypeError"));
		SingletonMetaclass typeErrorClassSingletonMetaclass = new SingletonMetaclass("TypeError");
		ClassMetaclass unboundMethodMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"UnboundMethod"));
		SingletonMetaclass unboundMethodClassSingletonMetaclass = new SingletonMetaclass(
				"UnboundMethod");
		ClassMetaclass zeroDivisionErrorMetaclass = (ClassMetaclass) add(new ClassMetaclass(
				"ZeroDivisionError"));
		SingletonMetaclass zeroDivisionErrorClassSingletonMetaclass = new SingletonMetaclass(
				"ZeroDivisionError");
		ClassMetaclass fatalMetaclass = (ClassMetaclass) add(new ClassMetaclass("fatal"));
		SingletonMetaclass fatalClassSingletonMetaclass = new SingletonMetaclass("fatal");
		kernelModuleMetaclass.setMethods(new MethodInfo[] { new MethodInfo("dup", 0, 0),
				new MethodInfo("hash", 0, 0), new MethodInfo("private_methods", -1, 0),
				new MethodInfo("extend", -1, 0), new MethodInfo("nil?", 0, 0),
				new MethodInfo("display", -1, 0), new MethodInfo("__send__", -1, 0),
				new MethodInfo("instance_eval", -1, 0), new MethodInfo("tainted?", 0, 0),
				new MethodInfo("class", 0, 0), new MethodInfo("singleton_methods", -1, 0),
				new MethodInfo("=~", 1, 0), new MethodInfo("untaint", 0, 0),
				new MethodInfo("kind_of?", 1, 0), new MethodInfo("object_id", 0, 0),
				new MethodInfo("instance_variable_get", 1, 0),
				new MethodInfo("respond_to?", -1, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("frozen?", 0, 0), new MethodInfo("taint", 0, 0),
				new MethodInfo("id", 0, 0), new MethodInfo("public_methods", -1, 0),
				new MethodInfo("to_a", 0, 0), new MethodInfo("equal?", 1, 0),
				new MethodInfo("clone", 0, 0), new MethodInfo("protected_methods", -1, 0),
				new MethodInfo("send", -1, 0), new MethodInfo("freeze", 0, 0),
				new MethodInfo("instance_variable_set", 2, 0), new MethodInfo("type", 0, 0),
				new MethodInfo("is_a?", 1, 0), new MethodInfo("methods", -1, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("instance_of?", 1, 0),
				new MethodInfo("===", 1, 0), new MethodInfo("instance_variables", 0, 0),
				new MethodInfo("__id__", 0, 0), new MethodInfo("eql?", 1, 0),
				new MethodInfo("to_s", 0, 0), new MethodInfo("method", 1, 0) });
		fileTestModuleMetaclass.setMethods(new MethodInfo[] {

		});
		marshalModuleMetaclass.setMethods(new MethodInfo[] {

		});
		signalModuleMetaclass.setMethods(new MethodInfo[] {

		});
		process_GIDModuleMetaclass.setMethods(new MethodInfo[] {

		});
		file_ConstantsModuleMetaclass.setMethods(new MethodInfo[] {

		});
		objectSpaceModuleMetaclass.setMethods(new MethodInfo[] {

		});
		precisionModuleMetaclass.setMethods(new MethodInfo[] { new MethodInfo("prec_f", 0, 0),
				new MethodInfo("prec_i", 0, 0), new MethodInfo("prec", 1, 0) });
		enumerableModuleMetaclass.setMethods(new MethodInfo[] { new MethodInfo("find_all", 0, 0),
				new MethodInfo("sort_by", 0, 0), new MethodInfo("collect", 0, 0),
				new MethodInfo("detect", -1, 0), new MethodInfo("max", 0, 0),
				new MethodInfo("sort", 0, 0), new MethodInfo("partition", 0, 0),
				new MethodInfo("any?", 0, 0), new MethodInfo("reject", 0, 0),
				new MethodInfo("zip", -1, 0), new MethodInfo("to_set", -1, 0),
				new MethodInfo("find", -1, 0), new MethodInfo("min", 0, 0),
				new MethodInfo("member?", 1, 0), new MethodInfo("entries", 0, 0),
				new MethodInfo("inject", -1, 0), new MethodInfo("all?", 0, 0),
				new MethodInfo("select", 0, 0), new MethodInfo("each_with_index", 0, 0),
				new MethodInfo("grep", 1, 0), new MethodInfo("to_a", 0, 0),
				new MethodInfo("map", 0, 0), new MethodInfo("include?", 1, 0) });
		errnoModuleMetaclass.setMethods(new MethodInfo[] {

		});
		process_SysModuleMetaclass.setMethods(new MethodInfo[] {

		});
		process_UIDModuleMetaclass.setMethods(new MethodInfo[] {

		});
		mathModuleMetaclass.setMethods(new MethodInfo[] {

		});
		gCModuleMetaclass.setMethods(new MethodInfo[] { new MethodInfo("garbage_collect", 0, 0) });
		processModuleMetaclass.setMethods(new MethodInfo[] {

		});
		comparableModuleMetaclass.setMethods(new MethodInfo[] { new MethodInfo("==", 1, 0),
				new MethodInfo(">=", 1, 0), new MethodInfo("<", 1, 0), new MethodInfo("<=", 1, 0),
				new MethodInfo(">", 1, 0), new MethodInfo("between?", 2, 0) });
		argumentErrorMetaclass.setMethods(new MethodInfo[] {

		});
		arrayMetaclass.setMethods(new MethodInfo[] { new MethodInfo("last", -1, 0),
				new MethodInfo("assoc", 1, 0), new MethodInfo("&", 1, 0),
				new MethodInfo("slice!", -1, 0), new MethodInfo("hash", 0, 0),
				new MethodInfo("values_at", -1, 0), new MethodInfo("length", 0, 0),
				new MethodInfo("sort!", 0, 0), new MethodInfo("reject", 0, 0),
				new MethodInfo("each_index", 0, 0), new MethodInfo("delete", 1, 0),
				new MethodInfo("sort", 0, 0), new MethodInfo("fetch", -1, 0),
				new MethodInfo("each", 0, 0), new MethodInfo("clear", 0, 0),
				new MethodInfo("*", 1, 0), new MethodInfo("join", -1, 0),
				new MethodInfo("empty?", 0, 0), new MethodInfo("shift", 0, 0),
				new MethodInfo("+", 1, 0), new MethodInfo("rindex", 1, 0),
				new MethodInfo("flatten!", 0, 0), new MethodInfo("to_ary", 0, 0),
				new MethodInfo("slice", -1, 0), new MethodInfo("reverse!", 0, 0),
				new MethodInfo("indices", -1, 0), new MethodInfo("nitems", 0, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("-", 1, 0),
				new MethodInfo("compact!", 0, 0), new MethodInfo("frozen?", 0, 0),
				new MethodInfo("concat", 1, 0), new MethodInfo("push", -1, 0),
				new MethodInfo("rassoc", 1, 0), new MethodInfo("[]", -1, 0),
				new MethodInfo("size", 0, 0), new MethodInfo("[]=", -1, 0),
				new MethodInfo("delete_at", 1, 0), new MethodInfo("flatten", 0, 0),
				new MethodInfo("to_a", 0, 0), new MethodInfo("collect!", 0, 0),
				new MethodInfo("<<", 1, 0), new MethodInfo("|", 1, 0),
				new MethodInfo("collect", 0, 0), new MethodInfo("reverse_each", 0, 0),
				new MethodInfo("include?", 1, 0), new MethodInfo("fill", -1, 0),
				new MethodInfo("uniq!", 0, 0), new MethodInfo("reverse", 0, 0),
				new MethodInfo("first", -1, 0), new MethodInfo("insert", -1, 0),
				new MethodInfo("reject!", 0, 0), new MethodInfo("pack", 1, 0),
				new MethodInfo("select", 0, 0), new MethodInfo("unshift", -1, 0),
				new MethodInfo("compact", 0, 0), new MethodInfo("replace", 1, 0),
				new MethodInfo("at", 1, 0), new MethodInfo("zip", -1, 0),
				new MethodInfo("transpose", 0, 0), new MethodInfo("<=>", 1, 0),
				new MethodInfo("uniq", 0, 0), new MethodInfo("index", 1, 0),
				new MethodInfo("pop", 0, 0), new MethodInfo("==", 1, 0),
				new MethodInfo("delete_if", 0, 0), new MethodInfo("map!", 0, 0),
				new MethodInfo("eql?", 1, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("indexes", -1, 0), new MethodInfo("map", 0, 0) });
		bignumMetaclass.setMethods(new MethodInfo[] { new MethodInfo("quo", 1, 0),
				new MethodInfo("<=>", 1, 0), new MethodInfo("coerce", 1, 0),
				new MethodInfo("-", 1, 0), new MethodInfo("[]", 1, 0), new MethodInfo("==", 1, 0),
				new MethodInfo("modulo", 1, 0), new MethodInfo("/", 1, 0),
				new MethodInfo("|", 1, 0), new MethodInfo("<<", 1, 0),
				new MethodInfo("eql?", 1, 0), new MethodInfo("%", 1, 0),
				new MethodInfo(">>", 1, 0), new MethodInfo("divmod", 1, 0),
				new MethodInfo("&", 1, 0), new MethodInfo("~", 0, 0), new MethodInfo("hash", 0, 0),
				new MethodInfo("^", 1, 0), new MethodInfo("to_s", -1, 0),
				new MethodInfo("abs", 0, 0), new MethodInfo("div", 1, 0),
				new MethodInfo("size", 0, 0), new MethodInfo("-@", 0, 0),
				new MethodInfo("*", 1, 0), new MethodInfo("to_f", 0, 0), new MethodInfo("+", 1, 0),
				new MethodInfo("remainder", 1, 0), new MethodInfo("**", 1, 0) });
		bindingMetaclass.setMethods(new MethodInfo[] { new MethodInfo("clone", 0, 0) });
		classMetaclass.setMethods(new MethodInfo[] { new MethodInfo("new", -1, 0),
				new MethodInfo("superclass", 0, 0), new MethodInfo("allocate", 0, 0) });
		continuationMetaclass.setMethods(new MethodInfo[] { new MethodInfo("call", -1, 0),
				new MethodInfo("[]", -1, 0) });
		dataMetaclass.setMethods(new MethodInfo[] {

		});
		dirMetaclass.setMethods(new MethodInfo[] { new MethodInfo("pos", 0, 0),
				new MethodInfo("each", 0, 0), new MethodInfo("close", 0, 0),
				new MethodInfo("rewind", 0, 0), new MethodInfo("pos=", 1, 0),
				new MethodInfo("seek", 1, 0), new MethodInfo("tell", 0, 0),
				new MethodInfo("read", 0, 0), new MethodInfo("path", 0, 0) });
		eOFErrorMetaclass.setMethods(new MethodInfo[] {

		});
		errno_E2BIGMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EACCESMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EADDRINUSEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EADDRNOTAVAILMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EAFNOSUPPORTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EAGAINMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EALREADYMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EBADFMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EBADMSGMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EBUSYMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECHILDMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECONNABORTEDMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECONNREFUSEDMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECONNRESETMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDEADLKMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDESTADDRREQMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDOMMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDQUOTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EEXISTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EFAULTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EFBIGMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EHOSTDOWNMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EHOSTUNREACHMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EIDRMMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EILSEQMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EINPROGRESSMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EINTRMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EINVALMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EIOMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EISCONNMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EISDIRMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ELOOPMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMFILEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMLINKMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMSGSIZEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMULTIHOPMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENAMETOOLONGMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENETDOWNMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENETRESETMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENETUNREACHMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENFILEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOBUFSMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENODATAMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENODEVMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOENTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOEXECMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOLCKMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOLINKMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOMEMMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOMSGMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOPROTOOPTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSPCMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSRMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSTRMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSYSMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTBLKMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTCONNMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTDIRMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTEMPTYMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTSOCKMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTTYMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENXIOMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EOPNOTSUPPMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EOVERFLOWMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPERMMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPFNOSUPPORTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPIPEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPROTOMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPROTONOSUPPORTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPROTOTYPEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ERANGEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EREMOTEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EROFSMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESHUTDOWNMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESOCKTNOSUPPORTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESPIPEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESRCHMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESTALEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETIMEMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETIMEDOUTMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETOOMANYREFSMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETXTBSYMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EUSERSMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EXDEVMetaclass.setMethods(new MethodInfo[] {

		});
		exceptionMetaclass.setMethods(new MethodInfo[] { new MethodInfo("message", 0, 0),
				new MethodInfo("exception", -1, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("set_backtrace", 1, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("to_str", 0, 0), new MethodInfo("backtrace", 0, 0) });
		falseClassMetaclass
				.setMethods(new MethodInfo[] { new MethodInfo("|", 1, 0),
						new MethodInfo("&", 1, 0), new MethodInfo("^", 1, 0),
						new MethodInfo("to_s", 0, 0) });
		fileMetaclass.setMethods(new MethodInfo[] { new MethodInfo("chmod", 1, 0),
				new MethodInfo("atime", 0, 0), new MethodInfo("flock", 1, 0),
				new MethodInfo("ctime", 0, 0), new MethodInfo("lstat", 0, 0),
				new MethodInfo("truncate", 1, 0), new MethodInfo("chown", 2, 0),
				new MethodInfo("mtime", 0, 0), new MethodInfo("path", 0, 0) });
		file_StatMetaclass.setMethods(new MethodInfo[] { new MethodInfo("<=>", 1, 0),
				new MethodInfo("dev", 0, 0), new MethodInfo("blksize", 0, 0),
				new MethodInfo("pipe?", 0, 0), new MethodInfo("gid", 0, 0),
				new MethodInfo("file?", 0, 0), new MethodInfo("sticky?", 0, 0),
				new MethodInfo("ino", 0, 0), new MethodInfo("atime", 0, 0),
				new MethodInfo("writable?", 0, 0), new MethodInfo("blockdev?", 0, 0),
				new MethodInfo("rdev_minor", 0, 0), new MethodInfo("grpowned?", 0, 0),
				new MethodInfo("uid", 0, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("executable_real?", 0, 0), new MethodInfo("setgid?", 0, 0),
				new MethodInfo("dev_minor", 0, 0), new MethodInfo("ftype", 0, 0),
				new MethodInfo("readable_real?", 0, 0), new MethodInfo("socket?", 0, 0),
				new MethodInfo("rdev_major", 0, 0), new MethodInfo("directory?", 0, 0),
				new MethodInfo("owned?", 0, 0), new MethodInfo("nlink", 0, 0),
				new MethodInfo("ctime", 0, 0), new MethodInfo("executable?", 0, 0),
				new MethodInfo("zero?", 0, 0), new MethodInfo("setuid?", 0, 0),
				new MethodInfo("dev_major", 0, 0), new MethodInfo("size", 0, 0),
				new MethodInfo("blocks", 0, 0), new MethodInfo("readable?", 0, 0),
				new MethodInfo("symlink?", 0, 0), new MethodInfo("rdev", 0, 0),
				new MethodInfo("size?", 0, 0), new MethodInfo("mode", 0, 0),
				new MethodInfo("mtime", 0, 0), new MethodInfo("writable_real?", 0, 0),
				new MethodInfo("chardev?", 0, 0) });
		fixnumMetaclass.setMethods(new MethodInfo[] { new MethodInfo("quo", 1, 0),
				new MethodInfo("<=>", 1, 0), new MethodInfo("-", 1, 0), new MethodInfo("==", 1, 0),
				new MethodInfo("[]", 1, 0), new MethodInfo("id2name", 0, 0),
				new MethodInfo("modulo", 1, 0), new MethodInfo("/", 1, 0),
				new MethodInfo("|", 1, 0), new MethodInfo("<<", 1, 0), new MethodInfo("%", 1, 0),
				new MethodInfo(">=", 1, 0), new MethodInfo(">>", 1, 0),
				new MethodInfo("divmod", 1, 0), new MethodInfo("<", 1, 0),
				new MethodInfo("<=", 1, 0), new MethodInfo("~", 0, 0), new MethodInfo("&", 1, 0),
				new MethodInfo("^", 1, 0), new MethodInfo("to_s", -1, 0),
				new MethodInfo("abs", 0, 0), new MethodInfo(">", 1, 0),
				new MethodInfo("zero?", 0, 0), new MethodInfo("div", 1, 0),
				new MethodInfo("size", 0, 0), new MethodInfo("-@", 0, 0),
				new MethodInfo("*", 1, 0), new MethodInfo("to_f", 0, 0),
				new MethodInfo("to_sym", 0, 0), new MethodInfo("+", 1, 0),
				new MethodInfo("**", 1, 0) });
		floatMetaclass.setMethods(new MethodInfo[] { new MethodInfo("<=>", 1, 0),
				new MethodInfo("round", 0, 0), new MethodInfo("coerce", 1, 0),
				new MethodInfo("-", 1, 0), new MethodInfo("==", 1, 0),
				new MethodInfo("to_i", 0, 0), new MethodInfo("finite?", 0, 0),
				new MethodInfo("modulo", 1, 0), new MethodInfo("/", 1, 0),
				new MethodInfo("eql?", 1, 0), new MethodInfo("ceil", 0, 0),
				new MethodInfo("%", 1, 0), new MethodInfo(">=", 1, 0),
				new MethodInfo("infinite?", 0, 0), new MethodInfo("divmod", 1, 0),
				new MethodInfo("<", 1, 0), new MethodInfo("<=", 1, 0),
				new MethodInfo("hash", 0, 0), new MethodInfo("to_int", 0, 0),
				new MethodInfo("floor", 0, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo(">", 1, 0), new MethodInfo("abs", 0, 0),
				new MethodInfo("zero?", 0, 0), new MethodInfo("nan?", 0, 0),
				new MethodInfo("truncate", 0, 0), new MethodInfo("-@", 0, 0),
				new MethodInfo("*", 1, 0), new MethodInfo("to_f", 0, 0), new MethodInfo("+", 1, 0),
				new MethodInfo("**", 1, 0) });
		floatDomainErrorMetaclass.setMethods(new MethodInfo[] {

		});
		hashMetaclass.setMethods(new MethodInfo[] { new MethodInfo("to_hash", 0, 0),
				new MethodInfo("length", 0, 0), new MethodInfo("empty?", 0, 0),
				new MethodInfo("replace", 1, 0), new MethodInfo("value?", 1, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("[]", 1, 0),
				new MethodInfo("default=", 1, 0), new MethodInfo("clear", 0, 0),
				new MethodInfo("invert", 0, 0), new MethodInfo("merge!", 1, 0),
				new MethodInfo("[]=", 2, 0), new MethodInfo("each", 0, 0),
				new MethodInfo("each_value", 0, 0), new MethodInfo("reject!", 0, 0),
				new MethodInfo("rehash", 0, 0), new MethodInfo("fetch", -1, 0),
				new MethodInfo("merge", 1, 0), new MethodInfo("has_value?", 1, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("sort", 0, 0),
				new MethodInfo("values", 0, 0), new MethodInfo("delete", 1, 0),
				new MethodInfo("key?", 1, 0), new MethodInfo("default_proc", 0, 0),
				new MethodInfo("values_at", -1, 0), new MethodInfo("reject", 0, 0),
				new MethodInfo("index", 1, 0), new MethodInfo("indices", -1, 0),
				new MethodInfo("keys", 0, 0), new MethodInfo("member?", 1, 0),
				new MethodInfo("has_key?", 1, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("each_pair", 0, 0), new MethodInfo("store", 2, 0),
				new MethodInfo("size", 0, 0), new MethodInfo("select", -1, 0),
				new MethodInfo("default", -1, 0), new MethodInfo("indexes", -1, 0),
				new MethodInfo("update", 1, 0), new MethodInfo("to_a", 0, 0),
				new MethodInfo("each_key", 0, 0), new MethodInfo("shift", 0, 0),
				new MethodInfo("delete_if", 0, 0), new MethodInfo("include?", 1, 0) });
		iOMetaclass.setMethods(new MethodInfo[] { new MethodInfo("lineno", 0, 0),
				new MethodInfo("readlines", -1, 0), new MethodInfo("write", 1, 0),
				new MethodInfo("pos", 0, 0), new MethodInfo("eof?", 0, 0),
				new MethodInfo("ioctl", -1, 0), new MethodInfo("stat", 0, 0),
				new MethodInfo("putc", 1, 0), new MethodInfo("fileno", 0, 0),
				new MethodInfo("to_i", 0, 0), new MethodInfo("isatty", 0, 0),
				new MethodInfo("each", -1, 0), new MethodInfo("each_byte", 0, 0),
				new MethodInfo("ungetc", 1, 0), new MethodInfo("close", 0, 0),
				new MethodInfo("sync", 0, 0), new MethodInfo("lineno=", 1, 0),
				new MethodInfo("readline", -1, 0), new MethodInfo("<<", 1, 0),
				new MethodInfo("rewind", 0, 0), new MethodInfo("pos=", 1, 0),
				new MethodInfo("sysseek", -1, 0), new MethodInfo("print", -1, 0),
				new MethodInfo("sysread", -1, 0), new MethodInfo("seek", -1, 0),
				new MethodInfo("close_write", 0, 0), new MethodInfo("tty?", 0, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("each_line", -1, 0),
				new MethodInfo("getc", 0, 0), new MethodInfo("readchar", 0, 0),
				new MethodInfo("closed?", 0, 0), new MethodInfo("pid", 0, 0),
				new MethodInfo("fsync", 0, 0), new MethodInfo("sync=", 1, 0),
				new MethodInfo("gets", -1, 0), new MethodInfo("binmode", 0, 0),
				new MethodInfo("printf", -1, 0), new MethodInfo("syswrite", 1, 0),
				new MethodInfo("tell", 0, 0), new MethodInfo("close_read", 0, 0),
				new MethodInfo("readpartial", -1, 0), new MethodInfo("read", -1, 0),
				new MethodInfo("eof", 0, 0), new MethodInfo("fcntl", -1, 0),
				new MethodInfo("puts", -1, 0), new MethodInfo("to_io", 0, 0),
				new MethodInfo("reopen", -1, 0), new MethodInfo("flush", 0, 0) });
		iOErrorMetaclass.setMethods(new MethodInfo[] {

		});
		indexErrorMetaclass.setMethods(new MethodInfo[] {

		});
		integerMetaclass.setMethods(new MethodInfo[] { new MethodInfo("round", 0, 0),
				new MethodInfo("to_i", 0, 0), new MethodInfo("downto", 1, 0),
				new MethodInfo("ceil", 0, 0), new MethodInfo("next", 0, 0),
				new MethodInfo("to_int", 0, 0), new MethodInfo("floor", 0, 0),
				new MethodInfo("chr", 0, 0), new MethodInfo("truncate", 0, 0),
				new MethodInfo("upto", 1, 0), new MethodInfo("integer?", 0, 0),
				new MethodInfo("times", 0, 0), new MethodInfo("succ", 0, 0) });
		interruptMetaclass.setMethods(new MethodInfo[] {

		});
		loadErrorMetaclass.setMethods(new MethodInfo[] {

		});
		localJumpErrorMetaclass.setMethods(new MethodInfo[] { new MethodInfo("reason", 0, 0),
				new MethodInfo("exit_value", 0, 0) });
		matchDataMetaclass.setMethods(new MethodInfo[] { new MethodInfo("length", 0, 0),
				new MethodInfo("captures", 0, 0), new MethodInfo("[]", -1, 0),
				new MethodInfo("string", 0, 0), new MethodInfo("end", 1, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("values_at", -1, 0),
				new MethodInfo("post_match", 0, 0), new MethodInfo("begin", 1, 0),
				new MethodInfo("to_s", 0, 0), new MethodInfo("size", 0, 0),
				new MethodInfo("select", -1, 0), new MethodInfo("pre_match", 0, 0),
				new MethodInfo("offset", 1, 0), new MethodInfo("to_a", 0, 0) });
		methodMetaclass.setMethods(new MethodInfo[] { new MethodInfo("call", -1, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("[]", -1, 0),
				new MethodInfo("arity", 0, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("unbind", 0, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("clone", 0, 0), new MethodInfo("to_proc", 0, 0) });
		moduleMetaclass.setMethods(new MethodInfo[] { new MethodInfo("<=>", 1, 0),
				new MethodInfo("const_missing", 1, 0), new MethodInfo("method_defined?", 1, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("name", 0, 0),
				new MethodInfo("const_get", 1, 0), new MethodInfo("const_defined?", 1, 0),
				new MethodInfo("private_class_method", -1, 0), new MethodInfo("===", 1, 0),
				new MethodInfo("public_instance_methods", -1, 0),
				new MethodInfo("protected_method_defined?", 1, 0), new MethodInfo(">=", 1, 0),
				new MethodInfo("constants", 0, 0), new MethodInfo("public_class_method", -1, 0),
				new MethodInfo("instance_method", 1, 0), new MethodInfo("freeze", 0, 0),
				new MethodInfo("<", 1, 0), new MethodInfo("<=", 1, 0),
				new MethodInfo("instance_methods", -1, 0),
				new MethodInfo("private_method_defined?", 1, 0),
				new MethodInfo("class_eval", -1, 0), new MethodInfo("autoload", 2, 0),
				new MethodInfo(">", 1, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("included_modules", 0, 0),
				new MethodInfo("private_instance_methods", -1, 0),
				new MethodInfo("class_variables", 0, 0),
				new MethodInfo("public_method_defined?", 1, 0), new MethodInfo("ancestors", 0, 0),
				new MethodInfo("const_set", 2, 0), new MethodInfo("module_eval", -1, 0),
				new MethodInfo("autoload?", 1, 0), new MethodInfo("include?", 1, 0),
				new MethodInfo("protected_instance_methods", -1, 0) });
		nameErrorMetaclass.setMethods(new MethodInfo[] { new MethodInfo("name", 0, 0),
				new MethodInfo("to_s", 0, 0) });
		nameError_messageMetaclass.setMethods(new MethodInfo[] { new MethodInfo("to_str", 0, 0),
				new MethodInfo("_dump", 1, 0) });
		nilClassMetaclass.setMethods(new MethodInfo[] { new MethodInfo("to_i", 0, 0),
				new MethodInfo("|", 1, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("&", 1, 0), new MethodInfo("^", 1, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("to_f", 0, 0), new MethodInfo("nil?", 0, 0),
				new MethodInfo("to_a", 0, 0) });
		noMemoryErrorMetaclass.setMethods(new MethodInfo[] {

		});
		noMethodErrorMetaclass.setMethods(new MethodInfo[] { new MethodInfo("args", 0, 0) });
		notImplementedErrorMetaclass.setMethods(new MethodInfo[] {

		});
		numericMetaclass.setMethods(new MethodInfo[] { new MethodInfo("<=>", 1, 0),
				new MethodInfo("quo", 1, 0), new MethodInfo("round", 0, 0),
				new MethodInfo("coerce", 1, 0), new MethodInfo("modulo", 1, 0),
				new MethodInfo("eql?", 1, 0), new MethodInfo("ceil", 0, 0),
				new MethodInfo("nonzero?", 0, 0), new MethodInfo("divmod", 1, 0),
				new MethodInfo("to_int", 0, 0), new MethodInfo("step", -1, 0),
				new MethodInfo("floor", 0, 0), new MethodInfo("abs", 0, 0),
				new MethodInfo("zero?", 0, 0), new MethodInfo("+@", 0, 0),
				new MethodInfo("div", 1, 0), new MethodInfo("truncate", 0, 0),
				new MethodInfo("singleton_method_added", 1, 0), new MethodInfo("-@", 0, 0),
				new MethodInfo("remainder", 1, 0), new MethodInfo("integer?", 0, 0) });
		objectMetaclass.setMethods(new MethodInfo[] {

		});
		procMetaclass.setMethods(new MethodInfo[] { new MethodInfo("call", -1, 0),
				new MethodInfo("arity", 0, 0), new MethodInfo("[]", -1, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("dup", 0, 0),
				new MethodInfo("binding", 0, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("clone", 0, 0), new MethodInfo("to_proc", 0, 0) });
		process_StatusMetaclass.setMethods(new MethodInfo[] { new MethodInfo("stopped?", 0, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("to_i", 0, 0),
				new MethodInfo("exitstatus", 0, 0), new MethodInfo("stopsig", 0, 0),
				new MethodInfo("success?", 0, 0), new MethodInfo(">>", 1, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("coredump?", 0, 0),
				new MethodInfo("&", 1, 0), new MethodInfo("to_int", 0, 0),
				new MethodInfo("pid", 0, 0), new MethodInfo("signaled?", 0, 0),
				new MethodInfo("to_s", 0, 0), new MethodInfo("termsig", 0, 0),
				new MethodInfo("exited?", 0, 0) });
		rangeMetaclass.setMethods(new MethodInfo[] { new MethodInfo("first", 0, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("===", 1, 0),
				new MethodInfo("each", 0, 0), new MethodInfo("eql?", 1, 0),
				new MethodInfo("end", 0, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("hash", 0, 0), new MethodInfo("step", -1, 0),
				new MethodInfo("begin", 0, 0), new MethodInfo("member?", 1, 0),
				new MethodInfo("to_s", 0, 0), new MethodInfo("exclude_end?", 0, 0),
				new MethodInfo("last", 0, 0), new MethodInfo("include?", 1, 0) });
		rangeErrorMetaclass.setMethods(new MethodInfo[] {

		});
		regexpMetaclass.setMethods(new MethodInfo[] { new MethodInfo("==", 1, 0),
				new MethodInfo("===", 1, 0), new MethodInfo("match", 1, 0),
				new MethodInfo("eql?", 1, 0), new MethodInfo("source", 0, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("hash", 0, 0),
				new MethodInfo("~", 0, 0), new MethodInfo("kcode", 0, 0),
				new MethodInfo("casefold?", 0, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("=~", 1, 0), new MethodInfo("options", 0, 0) });
		regexpErrorMetaclass.setMethods(new MethodInfo[] {

		});
		runtimeErrorMetaclass.setMethods(new MethodInfo[] {

		});
		scriptErrorMetaclass.setMethods(new MethodInfo[] {

		});
		securityErrorMetaclass.setMethods(new MethodInfo[] {

		});
		setMetaclass.setMethods(new MethodInfo[] { new MethodInfo("length", 0, 0),
				new MethodInfo("empty?", 0, 0), new MethodInfo("replace", 1, 0),
				new MethodInfo("add?", 1, 0), new MethodInfo("clear", 0, 0),
				new MethodInfo("superset?", 1, 0), new MethodInfo("-", 1, 0),
				new MethodInfo("intersection", 1, 0), new MethodInfo("==", 1, 0),
				new MethodInfo("flatten!", 0, 0), new MethodInfo("each", 0, 0),
				new MethodInfo("reject!", 0, 0), new MethodInfo("pretty_print", 1, 0),
				new MethodInfo("proper_subset?", 1, 0), new MethodInfo("<<", 1, 0),
				new MethodInfo("map!", 0, 0), new MethodInfo("merge", 1, 0),
				new MethodInfo("|", 1, 0), new MethodInfo("eql?", 1, 0),
				new MethodInfo("delete", 1, 0), new MethodInfo("difference", 1, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("&", 1, 0),
				new MethodInfo("hash", 0, 0), new MethodInfo("member?", 1, 0),
				new MethodInfo("subset?", 1, 0), new MethodInfo("collect!", 0, 0),
				new MethodInfo("^", 1, 0), new MethodInfo("add", 1, 0),
				new MethodInfo("subtract", 1, 0), new MethodInfo("size", 0, 0),
				new MethodInfo("delete?", 1, 0), new MethodInfo("divide", 0, 0),
				new MethodInfo("proper_superset?", 1, 0), new MethodInfo("union", 1, 0),
				new MethodInfo("classify", 0, 0), new MethodInfo("pretty_print_cycle", 1, 0),
				new MethodInfo("to_a", 0, 0), new MethodInfo("flatten_merge", -2, 0),
				new MethodInfo("flatten", 0, 0), new MethodInfo("include?", 1, 0),
				new MethodInfo("delete_if", 0, 0), new MethodInfo("+", 1, 0) });
		signalExceptionMetaclass.setMethods(new MethodInfo[] {

		});
		sortedSetMetaclass.setMethods(new MethodInfo[] {

		});
		standardErrorMetaclass.setMethods(new MethodInfo[] {

		});
		stringMetaclass.setMethods(new MethodInfo[] { new MethodInfo("slice!", -1, 0),
				new MethodInfo("strip", 0, 0), new MethodInfo("each_line", -1, 0),
				new MethodInfo("hash", 0, 0), new MethodInfo("to_i", -1, 0),
				new MethodInfo("gsub!", -1, 0), new MethodInfo("length", 0, 0),
				new MethodInfo("tr_s!", 2, 0), new MethodInfo("to_str", 0, 0),
				new MethodInfo("unpack", 1, 0), new MethodInfo("ljust", -1, 0),
				new MethodInfo("delete", -1, 0), new MethodInfo("to_java_string", 0, 0),
				new MethodInfo("rstrip!", 0, 0), new MethodInfo("split", -1, 0),
				new MethodInfo("each", -1, 0), new MethodInfo("*", 1, 0),
				new MethodInfo("swapcase!", 0, 0), new MethodInfo("=~", 1, 0),
				new MethodInfo("casecmp", 1, 0), new MethodInfo("swapcase", 0, 0),
				new MethodInfo("chop", 0, 0), new MethodInfo("empty?", 0, 0),
				new MethodInfo("tr", 2, 0), new MethodInfo("+", 1, 0),
				new MethodInfo("javacase", 0, 0), new MethodInfo("rindex", -1, 0),
				new MethodInfo("intern", 0, 0), new MethodInfo("slice", -1, 0),
				new MethodInfo("next!", 0, 0), new MethodInfo("reverse!", 0, 0),
				new MethodInfo("strip!", 0, 0), new MethodInfo("match", 1, 0),
				new MethodInfo("hex", 0, 0), new MethodInfo("downcase", 0, 0),
				new MethodInfo("sub", -1, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("downcase!", 0, 0), new MethodInfo("to_sym", 0, 0),
				new MethodInfo("upto", 1, 0), new MethodInfo("concat", 1, 0),
				new MethodInfo("[]", -1, 0), new MethodInfo("to_f", 0, 0),
				new MethodInfo("chop!", 0, 0), new MethodInfo("size", 0, 0),
				new MethodInfo("lstrip", 0, 0), new MethodInfo("each_byte", 0, 0),
				new MethodInfo("[]=", -1, 0), new MethodInfo("succ!", 0, 0),
				new MethodInfo("delete!", -1, 0), new MethodInfo("dump", 0, 0),
				new MethodInfo("<<", 1, 0), new MethodInfo("rjust", -1, 0),
				new MethodInfo("squeeze", -1, 0), new MethodInfo("include?", 1, 0),
				new MethodInfo("next", 0, 0), new MethodInfo("reverse", 0, 0),
				new MethodInfo("chomp", -1, 0), new MethodInfo("sub!", -1, 0),
				new MethodInfo("insert", 2, 0), new MethodInfo("scan", 1, 0),
				new MethodInfo("tr_s", 2, 0), new MethodInfo("tr!", 2, 0),
				new MethodInfo("replace", 1, 0), new MethodInfo("oct", 0, 0),
				new MethodInfo("succ", 0, 0), new MethodInfo("lstrip!", 0, 0),
				new MethodInfo("<=>", 1, 0), new MethodInfo("capitalize!", 0, 0),
				new MethodInfo("gsub", -1, 0), new MethodInfo("capitalize", 0, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("crypt", 1, 0),
				new MethodInfo("index", -1, 0), new MethodInfo("rstrip", 0, 0),
				new MethodInfo("chomp!", -1, 0), new MethodInfo("sum", -1, 0),
				new MethodInfo("center", -1, 0), new MethodInfo("upcase", 0, 0),
				new MethodInfo("eql?", 1, 0), new MethodInfo("%", 1, 0),
				new MethodInfo("upcase!", 0, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("count", -1, 0), new MethodInfo("squeeze!", -1, 0) });
		structMetaclass.setMethods(new MethodInfo[] { new MethodInfo("length", 0, 0),
				new MethodInfo("==", 1, 0), new MethodInfo("[]", 1, 0),
				new MethodInfo("each", 0, 0), new MethodInfo("[]=", 2, 0),
				new MethodInfo("eql?", 1, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("values", 0, 0), new MethodInfo("hash", 0, 0),
				new MethodInfo("values_at", -1, 0), new MethodInfo("to_s", 0, 0),
				new MethodInfo("each_pair", 0, 0), new MethodInfo("members", 0, 0),
				new MethodInfo("size", 0, 0), new MethodInfo("select", -1, 0),
				new MethodInfo("to_a", 0, 0) });
		struct_TmsMetaclass.setMethods(new MethodInfo[] { new MethodInfo("stime", 0, 0),
				new MethodInfo("cutime=", 1, 0), new MethodInfo("utime", 0, 0),
				new MethodInfo("stime=", 1, 0), new MethodInfo("utime=", 1, 0),
				new MethodInfo("cstime", 0, 0), new MethodInfo("cutime", 0, 0),
				new MethodInfo("cstime=", 1, 0) });
		symbolMetaclass.setMethods(new MethodInfo[] { new MethodInfo("to_i", 0, 0),
				new MethodInfo("id2name", 0, 0), new MethodInfo("===", 1, 0),
				new MethodInfo("inspect", 0, 0), new MethodInfo("to_int", 0, 0),
				new MethodInfo("to_s", 0, 0), new MethodInfo("to_sym", 0, 0) });
		syntaxErrorMetaclass.setMethods(new MethodInfo[] {

		});
		systemCallErrorMetaclass.setMethods(new MethodInfo[] { new MethodInfo("errno", 0, 0) });
		systemExitMetaclass.setMethods(new MethodInfo[] { new MethodInfo("success?", 0, 0),
				new MethodInfo("status", 0, 0) });
		systemStackErrorMetaclass.setMethods(new MethodInfo[] {

		});
		threadMetaclass.setMethods(new MethodInfo[] { new MethodInfo("terminate", 0, 0),
				new MethodInfo("raise", -1, 0), new MethodInfo("abort_on_exception=", 1, 0),
				new MethodInfo("group", 0, 0), new MethodInfo("[]", 1, 0),
				new MethodInfo("[]=", 2, 0), new MethodInfo("wakeup", 0, 0),
				new MethodInfo("exit", 0, 0), new MethodInfo("safe_level", 0, 0),
				new MethodInfo("key?", 1, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("kill", 0, 0), new MethodInfo("join", -1, 0),
				new MethodInfo("stop?", 0, 0), new MethodInfo("run", 0, 0),
				new MethodInfo("keys", 0, 0), new MethodInfo("priority", 0, 0),
				new MethodInfo("value", 0, 0), new MethodInfo("alive?", 0, 0),
				new MethodInfo("abort_on_exception", 0, 0), new MethodInfo("status", 0, 0),
				new MethodInfo("priority=", 1, 0) });
		threadErrorMetaclass.setMethods(new MethodInfo[] {

		});
		threadGroupMetaclass.setMethods(new MethodInfo[] { new MethodInfo("enclose", 0, 0),
				new MethodInfo("list", 0, 0), new MethodInfo("enclosed?", 0, 0),
				new MethodInfo("add", 1, 0) });
		timeMetaclass.setMethods(new MethodInfo[] { new MethodInfo("<=>", 1, 0),
				new MethodInfo("gmtime", 0, 0), new MethodInfo("year", 0, 0),
				new MethodInfo("tv_sec", 0, 0), new MethodInfo("to_i", 0, 0),
				new MethodInfo("-", 1, 0), new MethodInfo("mday", 0, 0),
				new MethodInfo("gmt_offset", 0, 0), new MethodInfo("utc?", 0, 0),
				new MethodInfo("getutc", 0, 0), new MethodInfo("isdst", 0, 0),
				new MethodInfo("strftime", 1, 0), new MethodInfo("eql?", 1, 0),
				new MethodInfo("localtime", 0, 0), new MethodInfo("month", 0, 0),
				new MethodInfo("utc", 0, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("hour", 0, 0), new MethodInfo("gmtoff", 0, 0),
				new MethodInfo("hash", 0, 0), new MethodInfo("getgm", 0, 0),
				new MethodInfo("yday", 0, 0), new MethodInfo("dst?", 0, 0),
				new MethodInfo("usec", 0, 0), new MethodInfo("min", 0, 0),
				new MethodInfo("mon", 0, 0), new MethodInfo("ctime", 0, 0),
				new MethodInfo("to_s", 0, 0), new MethodInfo("sec", 0, 0),
				new MethodInfo("zone", 0, 0), new MethodInfo("getlocal", 0, 0),
				new MethodInfo("wday", 0, 0), new MethodInfo("tv_usec", 0, 0),
				new MethodInfo("to_f", 0, 0), new MethodInfo("day", 0, 0),
				new MethodInfo("utc_offset", 0, 0), new MethodInfo("gmt?", 0, 0),
				new MethodInfo("asctime", 0, 0), new MethodInfo("to_a", 0, 0),
				new MethodInfo("+", 1, 0), new MethodInfo("succ", 0, 0),
				new MethodInfo("_dump", -1, 0) });
		trueClassMetaclass
				.setMethods(new MethodInfo[] { new MethodInfo("|", 1, 0),
						new MethodInfo("&", 1, 0), new MethodInfo("^", 1, 0),
						new MethodInfo("to_s", 0, 0) });
		typeErrorMetaclass.setMethods(new MethodInfo[] {

		});
		unboundMethodMetaclass.setMethods(new MethodInfo[] { new MethodInfo("==", 1, 0),
				new MethodInfo("arity", 0, 0), new MethodInfo("inspect", 0, 0),
				new MethodInfo("to_s", 0, 0), new MethodInfo("bind", 1, 0),
				new MethodInfo("clone", 0, 0) });
		zeroDivisionErrorMetaclass.setMethods(new MethodInfo[] {

		});
		fatalMetaclass.setMethods(new MethodInfo[] {

		});
		kernelModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		fileTestModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		marshalModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		signalModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_GIDModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		file_ConstantsModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		objectSpaceModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		precisionModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		enumerableModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errnoModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_SysModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_UIDModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		mathModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		gCModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		processModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		comparableModuleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		argumentErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		arrayMetaclass.setIncludedModules(new ModuleMetaclass[] { enumerableModuleMetaclass });
		bignumMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		bindingMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		classMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		continuationMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		dataMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		dirMetaclass.setIncludedModules(new ModuleMetaclass[] { enumerableModuleMetaclass });
		eOFErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_E2BIGMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EACCESMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EADDRINUSEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EADDRNOTAVAILMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EAFNOSUPPORTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EAGAINMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EALREADYMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EBADFMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EBADMSGMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EBUSYMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECHILDMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECONNABORTEDMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECONNREFUSEDMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECONNRESETMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDEADLKMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDESTADDRREQMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDOMMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDQUOTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EEXISTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EFAULTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EFBIGMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EHOSTDOWNMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EHOSTUNREACHMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EIDRMMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EILSEQMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EINPROGRESSMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EINTRMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EINVALMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EIOMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EISCONNMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EISDIRMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ELOOPMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMFILEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMLINKMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMSGSIZEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMULTIHOPMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENAMETOOLONGMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENETDOWNMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENETRESETMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENETUNREACHMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENFILEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOBUFSMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENODATAMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENODEVMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOENTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOEXECMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOLCKMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOLINKMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOMEMMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOMSGMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOPROTOOPTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSPCMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSRMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSTRMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSYSMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTBLKMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTCONNMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTDIRMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTEMPTYMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTSOCKMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTTYMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENXIOMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EOPNOTSUPPMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EOVERFLOWMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPERMMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPFNOSUPPORTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPIPEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPROTOMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPROTONOSUPPORTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPROTOTYPEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ERANGEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EREMOTEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EROFSMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESHUTDOWNMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESOCKTNOSUPPORTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESPIPEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESRCHMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESTALEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETIMEMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETIMEDOUTMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETOOMANYREFSMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETXTBSYMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EUSERSMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EXDEVMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		exceptionMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		falseClassMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		fileMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		file_StatMetaclass.setIncludedModules(new ModuleMetaclass[] { comparableModuleMetaclass });
		fixnumMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		floatMetaclass.setIncludedModules(new ModuleMetaclass[] { precisionModuleMetaclass });
		floatDomainErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		hashMetaclass.setIncludedModules(new ModuleMetaclass[] { enumerableModuleMetaclass });
		iOMetaclass.setIncludedModules(new ModuleMetaclass[] { file_ConstantsModuleMetaclass,
				enumerableModuleMetaclass });
		iOErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		indexErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		integerMetaclass.setIncludedModules(new ModuleMetaclass[] { precisionModuleMetaclass });
		interruptMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		loadErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		localJumpErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		matchDataMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		methodMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		moduleMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		nameErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		nameError_messageMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		nilClassMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		noMemoryErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		noMethodErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		notImplementedErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		numericMetaclass.setIncludedModules(new ModuleMetaclass[] { comparableModuleMetaclass });
		objectMetaclass.setIncludedModules(new ModuleMetaclass[] { kernelModuleMetaclass });
		procMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_StatusMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		rangeMetaclass.setIncludedModules(new ModuleMetaclass[] { enumerableModuleMetaclass });
		rangeErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		regexpMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		regexpErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		runtimeErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		scriptErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		securityErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		setMetaclass.setIncludedModules(new ModuleMetaclass[] { enumerableModuleMetaclass });
		signalExceptionMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		sortedSetMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		standardErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		stringMetaclass.setIncludedModules(new ModuleMetaclass[] { enumerableModuleMetaclass,
				comparableModuleMetaclass });
		structMetaclass.setIncludedModules(new ModuleMetaclass[] { enumerableModuleMetaclass });
		struct_TmsMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		symbolMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		syntaxErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		systemCallErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		systemExitMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		systemStackErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		threadMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		threadErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		threadGroupMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		timeMetaclass.setIncludedModules(new ModuleMetaclass[] { comparableModuleMetaclass });
		trueClassMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		typeErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		unboundMethodMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		zeroDivisionErrorMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		fatalMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		argumentErrorMetaclass.setSuperClass(standardErrorMetaclass);
		arrayMetaclass.setSuperClass(objectMetaclass);
		bignumMetaclass.setSuperClass(integerMetaclass);
		bindingMetaclass.setSuperClass(objectMetaclass);
		classMetaclass.setSuperClass(moduleMetaclass);
		continuationMetaclass.setSuperClass(objectMetaclass);
		dataMetaclass.setSuperClass(objectMetaclass);
		dirMetaclass.setSuperClass(objectMetaclass);
		eOFErrorMetaclass.setSuperClass(iOErrorMetaclass);
		errno_E2BIGMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EACCESMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EADDRINUSEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EADDRNOTAVAILMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EAFNOSUPPORTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EAGAINMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EALREADYMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EBADFMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EBADMSGMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EBUSYMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ECHILDMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ECONNABORTEDMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ECONNREFUSEDMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ECONNRESETMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EDEADLKMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EDESTADDRREQMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EDOMMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EDQUOTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EEXISTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EFAULTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EFBIGMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EHOSTDOWNMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EHOSTUNREACHMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EIDRMMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EILSEQMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EINPROGRESSMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EINTRMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EINVALMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EIOMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EISCONNMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EISDIRMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ELOOPMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EMFILEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EMLINKMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EMSGSIZEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EMULTIHOPMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENAMETOOLONGMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENETDOWNMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENETRESETMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENETUNREACHMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENFILEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOBUFSMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENODATAMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENODEVMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOENTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOEXECMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOLCKMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOLINKMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOMEMMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOMSGMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOPROTOOPTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOSPCMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOSRMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOSTRMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOSYSMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOTBLKMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOTCONNMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOTDIRMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOTEMPTYMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOTSOCKMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENOTTYMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ENXIOMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EOPNOTSUPPMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EOVERFLOWMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EPERMMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EPFNOSUPPORTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EPIPEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EPROTOMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EPROTONOSUPPORTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EPROTOTYPEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ERANGEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EREMOTEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EROFSMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ESHUTDOWNMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ESOCKTNOSUPPORTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ESPIPEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ESRCHMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ESTALEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ETIMEMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ETIMEDOUTMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ETOOMANYREFSMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_ETXTBSYMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EUSERSMetaclass.setSuperClass(systemCallErrorMetaclass);
		errno_EXDEVMetaclass.setSuperClass(systemCallErrorMetaclass);
		exceptionMetaclass.setSuperClass(objectMetaclass);
		falseClassMetaclass.setSuperClass(objectMetaclass);
		fileMetaclass.setSuperClass(iOMetaclass);
		file_StatMetaclass.setSuperClass(objectMetaclass);
		fixnumMetaclass.setSuperClass(integerMetaclass);
		floatMetaclass.setSuperClass(numericMetaclass);
		floatDomainErrorMetaclass.setSuperClass(rangeErrorMetaclass);
		hashMetaclass.setSuperClass(objectMetaclass);
		iOMetaclass.setSuperClass(objectMetaclass);
		iOErrorMetaclass.setSuperClass(standardErrorMetaclass);
		indexErrorMetaclass.setSuperClass(standardErrorMetaclass);
		integerMetaclass.setSuperClass(numericMetaclass);
		interruptMetaclass.setSuperClass(signalExceptionMetaclass);
		loadErrorMetaclass.setSuperClass(scriptErrorMetaclass);
		localJumpErrorMetaclass.setSuperClass(standardErrorMetaclass);
		matchDataMetaclass.setSuperClass(objectMetaclass);
		methodMetaclass.setSuperClass(objectMetaclass);
		moduleMetaclass.setSuperClass(objectMetaclass);
		nameErrorMetaclass.setSuperClass(standardErrorMetaclass);
		nameError_messageMetaclass.setSuperClass(dataMetaclass);
		nilClassMetaclass.setSuperClass(objectMetaclass);
		noMemoryErrorMetaclass.setSuperClass(exceptionMetaclass);
		noMethodErrorMetaclass.setSuperClass(nameErrorMetaclass);
		notImplementedErrorMetaclass.setSuperClass(scriptErrorMetaclass);
		numericMetaclass.setSuperClass(objectMetaclass);
		procMetaclass.setSuperClass(objectMetaclass);
		process_StatusMetaclass.setSuperClass(objectMetaclass);
		rangeMetaclass.setSuperClass(objectMetaclass);
		rangeErrorMetaclass.setSuperClass(standardErrorMetaclass);
		regexpMetaclass.setSuperClass(objectMetaclass);
		regexpErrorMetaclass.setSuperClass(standardErrorMetaclass);
		runtimeErrorMetaclass.setSuperClass(standardErrorMetaclass);
		scriptErrorMetaclass.setSuperClass(exceptionMetaclass);
		securityErrorMetaclass.setSuperClass(standardErrorMetaclass);
		setMetaclass.setSuperClass(objectMetaclass);
		signalExceptionMetaclass.setSuperClass(exceptionMetaclass);
		sortedSetMetaclass.setSuperClass(setMetaclass);
		standardErrorMetaclass.setSuperClass(exceptionMetaclass);
		stringMetaclass.setSuperClass(objectMetaclass);
		structMetaclass.setSuperClass(objectMetaclass);
		struct_TmsMetaclass.setSuperClass(structMetaclass);
		symbolMetaclass.setSuperClass(objectMetaclass);
		syntaxErrorMetaclass.setSuperClass(scriptErrorMetaclass);
		systemCallErrorMetaclass.setSuperClass(standardErrorMetaclass);
		systemExitMetaclass.setSuperClass(exceptionMetaclass);
		systemStackErrorMetaclass.setSuperClass(standardErrorMetaclass);
		threadMetaclass.setSuperClass(objectMetaclass);
		threadErrorMetaclass.setSuperClass(standardErrorMetaclass);
		threadGroupMetaclass.setSuperClass(objectMetaclass);
		timeMetaclass.setSuperClass(objectMetaclass);
		trueClassMetaclass.setSuperClass(objectMetaclass);
		typeErrorMetaclass.setSuperClass(standardErrorMetaclass);
		unboundMethodMetaclass.setSuperClass(objectMetaclass);
		zeroDivisionErrorMetaclass.setSuperClass(standardErrorMetaclass);
		fatalMetaclass.setSuperClass(exceptionMetaclass);
		kernelModuleMetaclass.setMetaClass(kernelModuleSingletonMetaclass);
		fileTestModuleMetaclass.setMetaClass(fileTestModuleSingletonMetaclass);
		marshalModuleMetaclass.setMetaClass(marshalModuleSingletonMetaclass);
		signalModuleMetaclass.setMetaClass(signalModuleSingletonMetaclass);
		process_GIDModuleMetaclass.setMetaClass(process_GIDModuleSingletonMetaclass);
		file_ConstantsModuleMetaclass.setMetaClass(file_ConstantsModuleSingletonMetaclass);
		objectSpaceModuleMetaclass.setMetaClass(objectSpaceModuleSingletonMetaclass);
		precisionModuleMetaclass.setMetaClass(precisionModuleSingletonMetaclass);
		enumerableModuleMetaclass.setMetaClass(enumerableModuleSingletonMetaclass);
		errnoModuleMetaclass.setMetaClass(errnoModuleSingletonMetaclass);
		process_SysModuleMetaclass.setMetaClass(process_SysModuleSingletonMetaclass);
		process_UIDModuleMetaclass.setMetaClass(process_UIDModuleSingletonMetaclass);
		mathModuleMetaclass.setMetaClass(mathModuleSingletonMetaclass);
		gCModuleMetaclass.setMetaClass(gCModuleSingletonMetaclass);
		processModuleMetaclass.setMetaClass(processModuleSingletonMetaclass);
		comparableModuleMetaclass.setMetaClass(comparableModuleSingletonMetaclass);
		argumentErrorMetaclass.setMetaClass(argumentErrorClassSingletonMetaclass);
		arrayMetaclass.setMetaClass(arrayClassSingletonMetaclass);
		bignumMetaclass.setMetaClass(bignumClassSingletonMetaclass);
		bindingMetaclass.setMetaClass(bindingClassSingletonMetaclass);
		classMetaclass.setMetaClass(classClassSingletonMetaclass);
		continuationMetaclass.setMetaClass(continuationClassSingletonMetaclass);
		dataMetaclass.setMetaClass(dataClassSingletonMetaclass);
		dirMetaclass.setMetaClass(dirClassSingletonMetaclass);
		eOFErrorMetaclass.setMetaClass(eOFErrorClassSingletonMetaclass);
		errno_E2BIGMetaclass.setMetaClass(errno_E2BIGClassSingletonMetaclass);
		errno_EACCESMetaclass.setMetaClass(errno_EACCESClassSingletonMetaclass);
		errno_EADDRINUSEMetaclass.setMetaClass(errno_EADDRINUSEClassSingletonMetaclass);
		errno_EADDRNOTAVAILMetaclass.setMetaClass(errno_EADDRNOTAVAILClassSingletonMetaclass);
		errno_EAFNOSUPPORTMetaclass.setMetaClass(errno_EAFNOSUPPORTClassSingletonMetaclass);
		errno_EAGAINMetaclass.setMetaClass(errno_EAGAINClassSingletonMetaclass);
		errno_EALREADYMetaclass.setMetaClass(errno_EALREADYClassSingletonMetaclass);
		errno_EBADFMetaclass.setMetaClass(errno_EBADFClassSingletonMetaclass);
		errno_EBADMSGMetaclass.setMetaClass(errno_EBADMSGClassSingletonMetaclass);
		errno_EBUSYMetaclass.setMetaClass(errno_EBUSYClassSingletonMetaclass);
		errno_ECHILDMetaclass.setMetaClass(errno_ECHILDClassSingletonMetaclass);
		errno_ECONNABORTEDMetaclass.setMetaClass(errno_ECONNABORTEDClassSingletonMetaclass);
		errno_ECONNREFUSEDMetaclass.setMetaClass(errno_ECONNREFUSEDClassSingletonMetaclass);
		errno_ECONNRESETMetaclass.setMetaClass(errno_ECONNRESETClassSingletonMetaclass);
		errno_EDEADLKMetaclass.setMetaClass(errno_EDEADLKClassSingletonMetaclass);
		errno_EDESTADDRREQMetaclass.setMetaClass(errno_EDESTADDRREQClassSingletonMetaclass);
		errno_EDOMMetaclass.setMetaClass(errno_EDOMClassSingletonMetaclass);
		errno_EDQUOTMetaclass.setMetaClass(errno_EDQUOTClassSingletonMetaclass);
		errno_EEXISTMetaclass.setMetaClass(errno_EEXISTClassSingletonMetaclass);
		errno_EFAULTMetaclass.setMetaClass(errno_EFAULTClassSingletonMetaclass);
		errno_EFBIGMetaclass.setMetaClass(errno_EFBIGClassSingletonMetaclass);
		errno_EHOSTDOWNMetaclass.setMetaClass(errno_EHOSTDOWNClassSingletonMetaclass);
		errno_EHOSTUNREACHMetaclass.setMetaClass(errno_EHOSTUNREACHClassSingletonMetaclass);
		errno_EIDRMMetaclass.setMetaClass(errno_EIDRMClassSingletonMetaclass);
		errno_EILSEQMetaclass.setMetaClass(errno_EILSEQClassSingletonMetaclass);
		errno_EINPROGRESSMetaclass.setMetaClass(errno_EINPROGRESSClassSingletonMetaclass);
		errno_EINTRMetaclass.setMetaClass(errno_EINTRClassSingletonMetaclass);
		errno_EINVALMetaclass.setMetaClass(errno_EINVALClassSingletonMetaclass);
		errno_EIOMetaclass.setMetaClass(errno_EIOClassSingletonMetaclass);
		errno_EISCONNMetaclass.setMetaClass(errno_EISCONNClassSingletonMetaclass);
		errno_EISDIRMetaclass.setMetaClass(errno_EISDIRClassSingletonMetaclass);
		errno_ELOOPMetaclass.setMetaClass(errno_ELOOPClassSingletonMetaclass);
		errno_EMFILEMetaclass.setMetaClass(errno_EMFILEClassSingletonMetaclass);
		errno_EMLINKMetaclass.setMetaClass(errno_EMLINKClassSingletonMetaclass);
		errno_EMSGSIZEMetaclass.setMetaClass(errno_EMSGSIZEClassSingletonMetaclass);
		errno_EMULTIHOPMetaclass.setMetaClass(errno_EMULTIHOPClassSingletonMetaclass);
		errno_ENAMETOOLONGMetaclass.setMetaClass(errno_ENAMETOOLONGClassSingletonMetaclass);
		errno_ENETDOWNMetaclass.setMetaClass(errno_ENETDOWNClassSingletonMetaclass);
		errno_ENETRESETMetaclass.setMetaClass(errno_ENETRESETClassSingletonMetaclass);
		errno_ENETUNREACHMetaclass.setMetaClass(errno_ENETUNREACHClassSingletonMetaclass);
		errno_ENFILEMetaclass.setMetaClass(errno_ENFILEClassSingletonMetaclass);
		errno_ENOBUFSMetaclass.setMetaClass(errno_ENOBUFSClassSingletonMetaclass);
		errno_ENODATAMetaclass.setMetaClass(errno_ENODATAClassSingletonMetaclass);
		errno_ENODEVMetaclass.setMetaClass(errno_ENODEVClassSingletonMetaclass);
		errno_ENOENTMetaclass.setMetaClass(errno_ENOENTClassSingletonMetaclass);
		errno_ENOEXECMetaclass.setMetaClass(errno_ENOEXECClassSingletonMetaclass);
		errno_ENOLCKMetaclass.setMetaClass(errno_ENOLCKClassSingletonMetaclass);
		errno_ENOLINKMetaclass.setMetaClass(errno_ENOLINKClassSingletonMetaclass);
		errno_ENOMEMMetaclass.setMetaClass(errno_ENOMEMClassSingletonMetaclass);
		errno_ENOMSGMetaclass.setMetaClass(errno_ENOMSGClassSingletonMetaclass);
		errno_ENOPROTOOPTMetaclass.setMetaClass(errno_ENOPROTOOPTClassSingletonMetaclass);
		errno_ENOSPCMetaclass.setMetaClass(errno_ENOSPCClassSingletonMetaclass);
		errno_ENOSRMetaclass.setMetaClass(errno_ENOSRClassSingletonMetaclass);
		errno_ENOSTRMetaclass.setMetaClass(errno_ENOSTRClassSingletonMetaclass);
		errno_ENOSYSMetaclass.setMetaClass(errno_ENOSYSClassSingletonMetaclass);
		errno_ENOTBLKMetaclass.setMetaClass(errno_ENOTBLKClassSingletonMetaclass);
		errno_ENOTCONNMetaclass.setMetaClass(errno_ENOTCONNClassSingletonMetaclass);
		errno_ENOTDIRMetaclass.setMetaClass(errno_ENOTDIRClassSingletonMetaclass);
		errno_ENOTEMPTYMetaclass.setMetaClass(errno_ENOTEMPTYClassSingletonMetaclass);
		errno_ENOTSOCKMetaclass.setMetaClass(errno_ENOTSOCKClassSingletonMetaclass);
		errno_ENOTTYMetaclass.setMetaClass(errno_ENOTTYClassSingletonMetaclass);
		errno_ENXIOMetaclass.setMetaClass(errno_ENXIOClassSingletonMetaclass);
		errno_EOPNOTSUPPMetaclass.setMetaClass(errno_EOPNOTSUPPClassSingletonMetaclass);
		errno_EOVERFLOWMetaclass.setMetaClass(errno_EOVERFLOWClassSingletonMetaclass);
		errno_EPERMMetaclass.setMetaClass(errno_EPERMClassSingletonMetaclass);
		errno_EPFNOSUPPORTMetaclass.setMetaClass(errno_EPFNOSUPPORTClassSingletonMetaclass);
		errno_EPIPEMetaclass.setMetaClass(errno_EPIPEClassSingletonMetaclass);
		errno_EPROTOMetaclass.setMetaClass(errno_EPROTOClassSingletonMetaclass);
		errno_EPROTONOSUPPORTMetaclass.setMetaClass(errno_EPROTONOSUPPORTClassSingletonMetaclass);
		errno_EPROTOTYPEMetaclass.setMetaClass(errno_EPROTOTYPEClassSingletonMetaclass);
		errno_ERANGEMetaclass.setMetaClass(errno_ERANGEClassSingletonMetaclass);
		errno_EREMOTEMetaclass.setMetaClass(errno_EREMOTEClassSingletonMetaclass);
		errno_EROFSMetaclass.setMetaClass(errno_EROFSClassSingletonMetaclass);
		errno_ESHUTDOWNMetaclass.setMetaClass(errno_ESHUTDOWNClassSingletonMetaclass);
		errno_ESOCKTNOSUPPORTMetaclass.setMetaClass(errno_ESOCKTNOSUPPORTClassSingletonMetaclass);
		errno_ESPIPEMetaclass.setMetaClass(errno_ESPIPEClassSingletonMetaclass);
		errno_ESRCHMetaclass.setMetaClass(errno_ESRCHClassSingletonMetaclass);
		errno_ESTALEMetaclass.setMetaClass(errno_ESTALEClassSingletonMetaclass);
		errno_ETIMEMetaclass.setMetaClass(errno_ETIMEClassSingletonMetaclass);
		errno_ETIMEDOUTMetaclass.setMetaClass(errno_ETIMEDOUTClassSingletonMetaclass);
		errno_ETOOMANYREFSMetaclass.setMetaClass(errno_ETOOMANYREFSClassSingletonMetaclass);
		errno_ETXTBSYMetaclass.setMetaClass(errno_ETXTBSYClassSingletonMetaclass);
		errno_EUSERSMetaclass.setMetaClass(errno_EUSERSClassSingletonMetaclass);
		errno_EXDEVMetaclass.setMetaClass(errno_EXDEVClassSingletonMetaclass);
		exceptionMetaclass.setMetaClass(exceptionClassSingletonMetaclass);
		falseClassMetaclass.setMetaClass(falseClassClassSingletonMetaclass);
		fileMetaclass.setMetaClass(fileClassSingletonMetaclass);
		file_StatMetaclass.setMetaClass(file_StatClassSingletonMetaclass);
		fixnumMetaclass.setMetaClass(fixnumClassSingletonMetaclass);
		floatMetaclass.setMetaClass(floatClassSingletonMetaclass);
		floatDomainErrorMetaclass.setMetaClass(floatDomainErrorClassSingletonMetaclass);
		hashMetaclass.setMetaClass(hashClassSingletonMetaclass);
		iOMetaclass.setMetaClass(iOClassSingletonMetaclass);
		iOErrorMetaclass.setMetaClass(iOErrorClassSingletonMetaclass);
		indexErrorMetaclass.setMetaClass(indexErrorClassSingletonMetaclass);
		integerMetaclass.setMetaClass(integerClassSingletonMetaclass);
		interruptMetaclass.setMetaClass(interruptClassSingletonMetaclass);
		loadErrorMetaclass.setMetaClass(loadErrorClassSingletonMetaclass);
		localJumpErrorMetaclass.setMetaClass(localJumpErrorClassSingletonMetaclass);
		matchDataMetaclass.setMetaClass(matchDataClassSingletonMetaclass);
		methodMetaclass.setMetaClass(methodClassSingletonMetaclass);
		moduleMetaclass.setMetaClass(moduleClassSingletonMetaclass);
		nameErrorMetaclass.setMetaClass(nameErrorClassSingletonMetaclass);
		nameError_messageMetaclass.setMetaClass(nameError_messageClassSingletonMetaclass);
		nilClassMetaclass.setMetaClass(nilClassClassSingletonMetaclass);
		noMemoryErrorMetaclass.setMetaClass(noMemoryErrorClassSingletonMetaclass);
		noMethodErrorMetaclass.setMetaClass(noMethodErrorClassSingletonMetaclass);
		notImplementedErrorMetaclass.setMetaClass(notImplementedErrorClassSingletonMetaclass);
		numericMetaclass.setMetaClass(numericClassSingletonMetaclass);
		objectMetaclass.setMetaClass(objectClassSingletonMetaclass);
		procMetaclass.setMetaClass(procClassSingletonMetaclass);
		process_StatusMetaclass.setMetaClass(process_StatusClassSingletonMetaclass);
		rangeMetaclass.setMetaClass(rangeClassSingletonMetaclass);
		rangeErrorMetaclass.setMetaClass(rangeErrorClassSingletonMetaclass);
		regexpMetaclass.setMetaClass(regexpClassSingletonMetaclass);
		regexpErrorMetaclass.setMetaClass(regexpErrorClassSingletonMetaclass);
		runtimeErrorMetaclass.setMetaClass(runtimeErrorClassSingletonMetaclass);
		scriptErrorMetaclass.setMetaClass(scriptErrorClassSingletonMetaclass);
		securityErrorMetaclass.setMetaClass(securityErrorClassSingletonMetaclass);
		setMetaclass.setMetaClass(setClassSingletonMetaclass);
		signalExceptionMetaclass.setMetaClass(signalExceptionClassSingletonMetaclass);
		sortedSetMetaclass.setMetaClass(sortedSetClassSingletonMetaclass);
		standardErrorMetaclass.setMetaClass(standardErrorClassSingletonMetaclass);
		stringMetaclass.setMetaClass(stringClassSingletonMetaclass);
		structMetaclass.setMetaClass(structClassSingletonMetaclass);
		struct_TmsMetaclass.setMetaClass(struct_TmsClassSingletonMetaclass);
		symbolMetaclass.setMetaClass(symbolClassSingletonMetaclass);
		syntaxErrorMetaclass.setMetaClass(syntaxErrorClassSingletonMetaclass);
		systemCallErrorMetaclass.setMetaClass(systemCallErrorClassSingletonMetaclass);
		systemExitMetaclass.setMetaClass(systemExitClassSingletonMetaclass);
		systemStackErrorMetaclass.setMetaClass(systemStackErrorClassSingletonMetaclass);
		threadMetaclass.setMetaClass(threadClassSingletonMetaclass);
		threadErrorMetaclass.setMetaClass(threadErrorClassSingletonMetaclass);
		threadGroupMetaclass.setMetaClass(threadGroupClassSingletonMetaclass);
		timeMetaclass.setMetaClass(timeClassSingletonMetaclass);
		trueClassMetaclass.setMetaClass(trueClassClassSingletonMetaclass);
		typeErrorMetaclass.setMetaClass(typeErrorClassSingletonMetaclass);
		unboundMethodMetaclass.setMetaClass(unboundMethodClassSingletonMetaclass);
		zeroDivisionErrorMetaclass.setMetaClass(zeroDivisionErrorClassSingletonMetaclass);
		fatalMetaclass.setMetaClass(fatalClassSingletonMetaclass);
		kernelModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("sprintf", -1, Modifiers.AccStatic),
				new MethodInfo("Array", 1, Modifiers.AccStatic),
				new MethodInfo("abort", -1, Modifiers.AccStatic),
				new MethodInfo("readlines", -1, Modifiers.AccStatic),
				new MethodInfo("fork", 0, Modifiers.AccStatic),
				new MethodInfo("lambda", 0, Modifiers.AccStatic),
				new MethodInfo("raise", -1, Modifiers.AccStatic),
				new MethodInfo("set_trace_func", 1, Modifiers.AccStatic),
				new MethodInfo("gsub!", -1, Modifiers.AccStatic),
				new MethodInfo("warn", 1, Modifiers.AccStatic),
				new MethodInfo("putc", 1, Modifiers.AccStatic),
				new MethodInfo("trap", -1, Modifiers.AccStatic),
				new MethodInfo("load", -1, Modifiers.AccStatic),
				new MethodInfo("throw", -1, Modifiers.AccStatic),
				new MethodInfo("chop", 0, Modifiers.AccStatic),
				new MethodInfo("syscall", -1, Modifiers.AccStatic),
				new MethodInfo("String", 1, Modifiers.AccStatic),
				new MethodInfo("exit", -1, Modifiers.AccStatic),
				new MethodInfo("readline", -1, Modifiers.AccStatic),
				new MethodInfo("exec", -1, Modifiers.AccStatic),
				new MethodInfo("proc", 0, Modifiers.AccStatic),
				new MethodInfo("untrace_var", -1, Modifiers.AccStatic),
				new MethodInfo("sub!", -1, Modifiers.AccStatic),
				new MethodInfo("print", -1, Modifiers.AccStatic),
				new MethodInfo("eval", -1, Modifiers.AccStatic),
				new MethodInfo("block_given?", 0, Modifiers.AccStatic),
				new MethodInfo("catch", 1, Modifiers.AccStatic),
				new MethodInfo("gsub", -1, Modifiers.AccStatic),
				new MethodInfo("split", -1, Modifiers.AccStatic),
				new MethodInfo("getc", 0, Modifiers.AccStatic),
				new MethodInfo("sleep", -1, Modifiers.AccStatic),
				new MethodInfo("Float", 1, Modifiers.AccStatic),
				new MethodInfo("method_missing", -1, Modifiers.AccStatic),
				new MethodInfo("caller", -1, Modifiers.AccStatic),
				new MethodInfo("chomp!", -1, Modifiers.AccStatic),
				new MethodInfo("gets", -1, Modifiers.AccStatic),
				new MethodInfo("test", -1, Modifiers.AccStatic),
				new MethodInfo("exit!", -1, Modifiers.AccStatic),
				new MethodInfo("autoload", 2, Modifiers.AccStatic),
				new MethodInfo("binding", 0, Modifiers.AccStatic),
				new MethodInfo("loop", 0, Modifiers.AccStatic),
				new MethodInfo("local_variables", 0, Modifiers.AccStatic),
				new MethodInfo("trace_var", -1, Modifiers.AccStatic),
				new MethodInfo("printf", -1, Modifiers.AccStatic),
				new MethodInfo("rand", -1, Modifiers.AccStatic),
				new MethodInfo("format", -1, Modifiers.AccStatic),
				new MethodInfo("iterator?", 0, Modifiers.AccStatic),
				new MethodInfo("at_exit", 0, Modifiers.AccStatic),
				new MethodInfo("sub", -1, Modifiers.AccStatic),
				new MethodInfo("callcc", 0, Modifiers.AccStatic),
				new MethodInfo("select", -1, Modifiers.AccStatic),
				new MethodInfo("`", 1, Modifiers.AccStatic),
				new MethodInfo("p", -1, Modifiers.AccStatic),
				new MethodInfo("system", -1, Modifiers.AccStatic),
				new MethodInfo("Integer", 1, Modifiers.AccStatic),
				new MethodInfo("fail", -1, Modifiers.AccStatic),
				new MethodInfo("chop!", 0, Modifiers.AccStatic),
				new MethodInfo("scan", 1, Modifiers.AccStatic),
				new MethodInfo("puts", -1, Modifiers.AccStatic),
				new MethodInfo("require", 1, Modifiers.AccStatic),
				new MethodInfo("autoload?", 1, Modifiers.AccStatic),
				new MethodInfo("global_variables", 0, Modifiers.AccStatic),
				new MethodInfo("chomp", -1, Modifiers.AccStatic),
				new MethodInfo("open", -1, Modifiers.AccStatic),
				new MethodInfo("srand", -1, Modifiers.AccStatic) });
		fileTestModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("exists?", 1, Modifiers.AccStatic),
				new MethodInfo("pipe?", 1, Modifiers.AccStatic),
				new MethodInfo("file?", 1, Modifiers.AccStatic),
				new MethodInfo("sticky?", 1, Modifiers.AccStatic),
				new MethodInfo("writable?", 1, Modifiers.AccStatic),
				new MethodInfo("blockdev?", 1, Modifiers.AccStatic),
				new MethodInfo("exist?", 1, Modifiers.AccStatic),
				new MethodInfo("grpowned?", 1, Modifiers.AccStatic),
				new MethodInfo("executable_real?", 1, Modifiers.AccStatic),
				new MethodInfo("setgid?", 1, Modifiers.AccStatic),
				new MethodInfo("readable_real?", 1, Modifiers.AccStatic),
				new MethodInfo("socket?", 1, Modifiers.AccStatic),
				new MethodInfo("directory?", 1, Modifiers.AccStatic),
				new MethodInfo("owned?", 1, Modifiers.AccStatic),
				new MethodInfo("executable?", 1, Modifiers.AccStatic),
				new MethodInfo("zero?", 1, Modifiers.AccStatic),
				new MethodInfo("setuid?", 1, Modifiers.AccStatic),
				new MethodInfo("readable?", 1, Modifiers.AccStatic),
				new MethodInfo("size", 1, Modifiers.AccStatic),
				new MethodInfo("symlink?", 1, Modifiers.AccStatic),
				new MethodInfo("size?", 1, Modifiers.AccStatic),
				new MethodInfo("identical?", 2, Modifiers.AccStatic),
				new MethodInfo("writable_real?", 1, Modifiers.AccStatic),
				new MethodInfo("chardev?", 1, Modifiers.AccStatic) });
		marshalModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("load", -1, Modifiers.AccStatic),
				new MethodInfo("dump", -1, Modifiers.AccStatic),
				new MethodInfo("restore", -1, Modifiers.AccStatic) });
		signalModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("trap", -1, Modifiers.AccStatic),
				new MethodInfo("list", 0, Modifiers.AccStatic) });
		process_GIDModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("rid", 0, Modifiers.AccStatic),
				new MethodInfo("switch", 0, Modifiers.AccStatic),
				new MethodInfo("grant_privilege", 1, Modifiers.AccStatic),
				new MethodInfo("re_exchangeable?", 0, Modifiers.AccStatic),
				new MethodInfo("change_privilege", 1, Modifiers.AccStatic),
				new MethodInfo("eid", 0, Modifiers.AccStatic),
				new MethodInfo("re_exchange", 0, Modifiers.AccStatic),
				new MethodInfo("sid_available?", 0, Modifiers.AccStatic) });
		file_ConstantsModuleSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		objectSpaceModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("undefine_finalizer", 1, Modifiers.AccStatic),
				new MethodInfo("remove_finalizer", 1, Modifiers.AccStatic),
				new MethodInfo("garbage_collect", 0, Modifiers.AccStatic),
				new MethodInfo("define_finalizer", -1, Modifiers.AccStatic),
				new MethodInfo("add_finalizer", 1, Modifiers.AccStatic),
				new MethodInfo("call_finalizer", 1, Modifiers.AccStatic),
				new MethodInfo("each_object", -1, Modifiers.AccStatic),
				new MethodInfo("_id2ref", 1, Modifiers.AccStatic),
				new MethodInfo("finalizers", 0, Modifiers.AccStatic) });
		precisionModuleSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("included",
				1, Modifiers.AccStatic) });
		enumerableModuleSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errnoModuleSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		process_SysModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("getgid", 0, Modifiers.AccStatic),
				new MethodInfo("setresgid", 3, Modifiers.AccStatic),
				new MethodInfo("setegid", 1, Modifiers.AccStatic),
				new MethodInfo("setgid", 1, Modifiers.AccStatic),
				new MethodInfo("geteuid", 0, Modifiers.AccStatic),
				new MethodInfo("setresuid", 3, Modifiers.AccStatic),
				new MethodInfo("seteuid", 1, Modifiers.AccStatic),
				new MethodInfo("setuid", 1, Modifiers.AccStatic),
				new MethodInfo("getuid", 0, Modifiers.AccStatic),
				new MethodInfo("setregid", 2, Modifiers.AccStatic),
				new MethodInfo("setrgid", 1, Modifiers.AccStatic),
				new MethodInfo("getegid", 0, Modifiers.AccStatic),
				new MethodInfo("issetugid", 0, Modifiers.AccStatic),
				new MethodInfo("setreuid", 2, Modifiers.AccStatic),
				new MethodInfo("setruid", 1, Modifiers.AccStatic) });
		process_UIDModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("rid", 0, Modifiers.AccStatic),
				new MethodInfo("switch", 0, Modifiers.AccStatic),
				new MethodInfo("grant_privilege", 1, Modifiers.AccStatic),
				new MethodInfo("re_exchangeable?", 0, Modifiers.AccStatic),
				new MethodInfo("change_privilege", 1, Modifiers.AccStatic),
				new MethodInfo("eid", 0, Modifiers.AccStatic),
				new MethodInfo("re_exchange", 0, Modifiers.AccStatic),
				new MethodInfo("sid_available?", 0, Modifiers.AccStatic) });
		mathModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("atan2", 2, Modifiers.AccStatic),
				new MethodInfo("asinh", 1, Modifiers.AccStatic),
				new MethodInfo("cosh", 1, Modifiers.AccStatic),
				new MethodInfo("ldexp", 2, Modifiers.AccStatic),
				new MethodInfo("tan", 1, Modifiers.AccStatic),
				new MethodInfo("log", 1, Modifiers.AccStatic),
				new MethodInfo("acosh", 1, Modifiers.AccStatic),
				new MethodInfo("erfc", 1, Modifiers.AccStatic),
				new MethodInfo("atan", 1, Modifiers.AccStatic),
				new MethodInfo("frexp", 1, Modifiers.AccStatic),
				new MethodInfo("sin", 1, Modifiers.AccStatic),
				new MethodInfo("exp", 1, Modifiers.AccStatic),
				new MethodInfo("tanh", 1, Modifiers.AccStatic),
				new MethodInfo("erf", 1, Modifiers.AccStatic),
				new MethodInfo("asin", 1, Modifiers.AccStatic),
				new MethodInfo("sqrt", 1, Modifiers.AccStatic),
				new MethodInfo("cos", 1, Modifiers.AccStatic),
				new MethodInfo("atanh", 1, Modifiers.AccStatic),
				new MethodInfo("sinh", 1, Modifiers.AccStatic),
				new MethodInfo("hypot", 2, Modifiers.AccStatic),
				new MethodInfo("acos", 1, Modifiers.AccStatic),
				new MethodInfo("log10", 1, Modifiers.AccStatic) });
		gCModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("start", 0, Modifiers.AccStatic),
				new MethodInfo("disable", 0, Modifiers.AccStatic),
				new MethodInfo("enable", 0, Modifiers.AccStatic) });
		processModuleSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("fork", 0, Modifiers.AccStatic),
				new MethodInfo("abort", -1, Modifiers.AccStatic),
				new MethodInfo("detach", 1, Modifiers.AccStatic),
				new MethodInfo("setpgrp", 0, Modifiers.AccStatic),
				new MethodInfo("initgroups", 2, Modifiers.AccStatic),
				new MethodInfo("groups=", 1, Modifiers.AccStatic),
				new MethodInfo("wait2", -1, Modifiers.AccStatic),
				new MethodInfo("gid", 0, Modifiers.AccStatic),
				new MethodInfo("setsid", 0, Modifiers.AccStatic),
				new MethodInfo("exit", -1, Modifiers.AccStatic),
				new MethodInfo("waitall", 0, Modifiers.AccStatic),
				new MethodInfo("getpgrp", 0, Modifiers.AccStatic),
				new MethodInfo("egid", 0, Modifiers.AccStatic),
				new MethodInfo("wait", -1, Modifiers.AccStatic),
				new MethodInfo("uid", 0, Modifiers.AccStatic),
				new MethodInfo("gid=", 1, Modifiers.AccStatic),
				new MethodInfo("kill", -1, Modifiers.AccStatic),
				new MethodInfo("pid", 0, Modifiers.AccStatic),
				new MethodInfo("setpgid", 2, Modifiers.AccStatic),
				new MethodInfo("maxgroups", 0, Modifiers.AccStatic),
				new MethodInfo("exit!", -1, Modifiers.AccStatic),
				new MethodInfo("waitpid2", -1, Modifiers.AccStatic),
				new MethodInfo("ppid", 0, Modifiers.AccStatic),
				new MethodInfo("euid", 0, Modifiers.AccStatic),
				new MethodInfo("egid=", 1, Modifiers.AccStatic),
				new MethodInfo("setpriority", 3, Modifiers.AccStatic),
				new MethodInfo("uid=", 1, Modifiers.AccStatic),
				new MethodInfo("getpgid", 1, Modifiers.AccStatic),
				new MethodInfo("groups", 0, Modifiers.AccStatic),
				new MethodInfo("maxgroups=", 1, Modifiers.AccStatic),
				new MethodInfo("waitpid", -1, Modifiers.AccStatic),
				new MethodInfo("euid=", 1, Modifiers.AccStatic),
				new MethodInfo("getpriority", 2, Modifiers.AccStatic),
				new MethodInfo("times", 0, Modifiers.AccStatic) });
		comparableModuleSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		argumentErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		arrayClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("[]", -1,
				Modifiers.AccStatic) });
		bignumClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		bindingClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		classClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		continuationClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		dataClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		dirClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("foreach", 1, Modifiers.AccStatic),
				new MethodInfo("rmdir", 1, Modifiers.AccStatic),
				new MethodInfo("[]", 1, Modifiers.AccStatic),
				new MethodInfo("getwd", 0, Modifiers.AccStatic),
				new MethodInfo("unlink", 1, Modifiers.AccStatic),
				new MethodInfo("mkdir", -1, Modifiers.AccStatic),
				new MethodInfo("delete", 1, Modifiers.AccStatic),
				new MethodInfo("chdir", -1, Modifiers.AccStatic),
				new MethodInfo("entries", 1, Modifiers.AccStatic),
				new MethodInfo("chroot", 1, Modifiers.AccStatic),
				new MethodInfo("glob", -1, Modifiers.AccStatic),
				new MethodInfo("open", 1, Modifiers.AccStatic),
				new MethodInfo("pwd", 0, Modifiers.AccStatic) });
		eOFErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_E2BIGClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EACCESClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EADDRINUSEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EADDRNOTAVAILClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EAFNOSUPPORTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EAGAINClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EALREADYClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EBADFClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EBADMSGClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EBUSYClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECHILDClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECONNABORTEDClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECONNREFUSEDClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ECONNRESETClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDEADLKClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDESTADDRREQClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDOMClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EDQUOTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EEXISTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EFAULTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EFBIGClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EHOSTDOWNClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EHOSTUNREACHClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EIDRMClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EILSEQClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EINPROGRESSClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EINTRClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EINVALClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EIOClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EISCONNClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EISDIRClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ELOOPClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMFILEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMLINKClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMSGSIZEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EMULTIHOPClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENAMETOOLONGClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENETDOWNClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENETRESETClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENETUNREACHClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENFILEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOBUFSClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENODATAClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENODEVClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOENTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOEXECClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOLCKClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOLINKClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOMEMClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOMSGClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOPROTOOPTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSPCClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSRClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSTRClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOSYSClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTBLKClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTCONNClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTDIRClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTEMPTYClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTSOCKClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENOTTYClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ENXIOClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EOPNOTSUPPClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EOVERFLOWClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPERMClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPFNOSUPPORTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPIPEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPROTOClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPROTONOSUPPORTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EPROTOTYPEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ERANGEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EREMOTEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EROFSClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESHUTDOWNClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESOCKTNOSUPPORTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESPIPEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESRCHClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ESTALEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETIMEClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETIMEDOUTClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETOOMANYREFSClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_ETXTBSYClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EUSERSClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		errno_EXDEVClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		exceptionClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("exception",
				-1, Modifiers.AccStatic) });
		falseClassClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		fileClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("exists?", 1, Modifiers.AccStatic),
				new MethodInfo("pipe?", 1, Modifiers.AccStatic),
				new MethodInfo("stat", 1, Modifiers.AccStatic),
				new MethodInfo("link", 2, Modifiers.AccStatic),
				new MethodInfo("file?", 1, Modifiers.AccStatic),
				new MethodInfo("sticky?", 1, Modifiers.AccStatic),
				new MethodInfo("chmod", -1, Modifiers.AccStatic),
				new MethodInfo("basename", -1, Modifiers.AccStatic),
				new MethodInfo("writable?", 1, Modifiers.AccStatic),
				new MethodInfo("blockdev?", 1, Modifiers.AccStatic),
				new MethodInfo("atime", 1, Modifiers.AccStatic),
				new MethodInfo("unlink", -1, Modifiers.AccStatic),
				new MethodInfo("exist?", 1, Modifiers.AccStatic),
				new MethodInfo("grpowned?", 1, Modifiers.AccStatic),
				new MethodInfo("lchown", -1, Modifiers.AccStatic),
				new MethodInfo("executable_real?", 1, Modifiers.AccStatic),
				new MethodInfo("setgid?", 1, Modifiers.AccStatic),
				new MethodInfo("utime", -1, Modifiers.AccStatic),
				new MethodInfo("delete", -1, Modifiers.AccStatic),
				new MethodInfo("expand_path", -1, Modifiers.AccStatic),
				new MethodInfo("readable_real?", 1, Modifiers.AccStatic),
				new MethodInfo("socket?", 1, Modifiers.AccStatic),
				new MethodInfo("ftype", 1, Modifiers.AccStatic),
				new MethodInfo("readlink", 1, Modifiers.AccStatic),
				new MethodInfo("split", 1, Modifiers.AccStatic),
				new MethodInfo("join", -1, Modifiers.AccStatic),
				new MethodInfo("directory?", 1, Modifiers.AccStatic),
				new MethodInfo("owned?", 1, Modifiers.AccStatic),
				new MethodInfo("lchmod", -1, Modifiers.AccStatic),
				new MethodInfo("extname", 1, Modifiers.AccStatic),
				new MethodInfo("fnmatch", -1, Modifiers.AccStatic),
				new MethodInfo("executable?", 1, Modifiers.AccStatic),
				new MethodInfo("zero?", 1, Modifiers.AccStatic),
				new MethodInfo("setuid?", 1, Modifiers.AccStatic),
				new MethodInfo("ctime", 1, Modifiers.AccStatic),
				new MethodInfo("umask", -1, Modifiers.AccStatic),
				new MethodInfo("readable?", 1, Modifiers.AccStatic),
				new MethodInfo("size", 1, Modifiers.AccStatic),
				new MethodInfo("symlink?", 1, Modifiers.AccStatic),
				new MethodInfo("lstat", 1, Modifiers.AccStatic),
				new MethodInfo("symlink", 2, Modifiers.AccStatic),
				new MethodInfo("truncate", 2, Modifiers.AccStatic),
				new MethodInfo("size?", 1, Modifiers.AccStatic),
				new MethodInfo("identical?", 2, Modifiers.AccStatic),
				new MethodInfo("chown", -1, Modifiers.AccStatic),
				new MethodInfo("dirname", 1, Modifiers.AccStatic),
				new MethodInfo("fnmatch?", -1, Modifiers.AccStatic),
				new MethodInfo("writable_real?", 1, Modifiers.AccStatic),
				new MethodInfo("chardev?", 1, Modifiers.AccStatic),
				new MethodInfo("mtime", 1, Modifiers.AccStatic),
				new MethodInfo("rename", 2, Modifiers.AccStatic) });
		file_StatClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		fixnumClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("induced_from",
				1, Modifiers.AccStatic) });
		floatClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("induced_from",
				1, Modifiers.AccStatic) });
		floatDomainErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		hashClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("[]", -1,
				Modifiers.AccStatic) });
		iOClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("readlines", -1, Modifiers.AccStatic),
				new MethodInfo("foreach", -1, Modifiers.AccStatic),
				new MethodInfo("popen", -1, Modifiers.AccStatic),
				new MethodInfo("new", -1, Modifiers.AccStatic),
				new MethodInfo("for_fd", -1, Modifiers.AccStatic),
				new MethodInfo("read", -1, Modifiers.AccStatic),
				new MethodInfo("select", -1, Modifiers.AccStatic),
				new MethodInfo("pipe", 0, Modifiers.AccStatic),
				new MethodInfo("open", -1, Modifiers.AccStatic),
				new MethodInfo("sysopen", -1, Modifiers.AccStatic) });
		iOErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		indexErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		integerClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("induced_from",
				1, Modifiers.AccStatic) });
		interruptClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		loadErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		localJumpErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		matchDataClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		methodClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		moduleClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("nesting", 0, Modifiers.AccStatic),
				new MethodInfo("constants", 0, Modifiers.AccStatic) });
		nameErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		nameError_messageClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("!", 3, Modifiers.AccStatic),
				new MethodInfo("_load", 1, Modifiers.AccStatic) });
		nilClassClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		noMemoryErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		noMethodErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		notImplementedErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		numericClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		objectClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		procClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("new", -1,
				Modifiers.AccStatic) });
		process_StatusClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		rangeClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		rangeErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		regexpClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("escape", -1, Modifiers.AccStatic),
				new MethodInfo("quote", -1, Modifiers.AccStatic),
				new MethodInfo("last_match", -1, Modifiers.AccStatic),
				new MethodInfo("compile", -1, Modifiers.AccStatic),
				new MethodInfo("union", -1, Modifiers.AccStatic) });
		regexpErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		runtimeErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		scriptErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		securityErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		setClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("[]", -1,
				Modifiers.AccStatic) });
		signalExceptionClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		sortedSetClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("setup", 0, Modifiers.AccStatic),
				new MethodInfo("[]", -1, Modifiers.AccStatic) });
		standardErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		stringClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		structClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("new", -1,
				Modifiers.AccStatic) });
		struct_TmsClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("[]", -1, Modifiers.AccStatic),
				new MethodInfo("new", -1, Modifiers.AccStatic),
				new MethodInfo("members", 0, Modifiers.AccStatic) });
		symbolClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("all_symbols",
				0, Modifiers.AccStatic) });
		syntaxErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		systemCallErrorClassSingletonMetaclass.setMethods(new MethodInfo[] { new MethodInfo("===",
				1, Modifiers.AccStatic) });
		systemExitClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		systemStackErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		threadClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("fork", -1, Modifiers.AccStatic),
				new MethodInfo("critical", 0, Modifiers.AccStatic),
				new MethodInfo("abort_on_exception=", 1, Modifiers.AccStatic),
				new MethodInfo("pass", 0, Modifiers.AccStatic),
				new MethodInfo("start", -1, Modifiers.AccStatic),
				new MethodInfo("exit", 0, Modifiers.AccStatic),
				new MethodInfo("list", 0, Modifiers.AccStatic),
				new MethodInfo("critical=", 1, Modifiers.AccStatic),
				new MethodInfo("kill", 1, Modifiers.AccStatic),
				new MethodInfo("new", -1, Modifiers.AccStatic),
				new MethodInfo("main", 0, Modifiers.AccStatic),
				new MethodInfo("stop", 0, Modifiers.AccStatic),
				new MethodInfo("abort_on_exception", 0, Modifiers.AccStatic),
				new MethodInfo("current", 0, Modifiers.AccStatic) });
		threadErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		threadGroupClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		timeClassSingletonMetaclass.setMethods(new MethodInfo[] {
				new MethodInfo("gm", -1, Modifiers.AccStatic),
				new MethodInfo("utc", -1, Modifiers.AccStatic),
				new MethodInfo("at", -1, Modifiers.AccStatic),
				new MethodInfo("mktime", -1, Modifiers.AccStatic),
				new MethodInfo("now", -1, Modifiers.AccStatic),
				new MethodInfo("_load", 1, Modifiers.AccStatic),
				new MethodInfo("local", -1, Modifiers.AccStatic),
				new MethodInfo("times", 0, Modifiers.AccStatic) });
		trueClassClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		typeErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		unboundMethodClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		zeroDivisionErrorClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		fatalClassSingletonMetaclass.setMethods(new MethodInfo[] {

		});
		kernelModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		fileTestModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		marshalModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		signalModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		process_GIDModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		file_ConstantsModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		objectSpaceModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		precisionModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		enumerableModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		errnoModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		process_SysModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		process_UIDModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		mathModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		gCModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		processModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		comparableModuleSingletonMetaclass.setSuperClass(moduleMetaclass);
		argumentErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		arrayClassSingletonMetaclass.setSuperClass(classMetaclass);
		bignumClassSingletonMetaclass.setSuperClass(classMetaclass);
		bindingClassSingletonMetaclass.setSuperClass(classMetaclass);
		classClassSingletonMetaclass.setSuperClass(classMetaclass);
		continuationClassSingletonMetaclass.setSuperClass(classMetaclass);
		dataClassSingletonMetaclass.setSuperClass(classMetaclass);
		dirClassSingletonMetaclass.setSuperClass(classMetaclass);
		eOFErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_E2BIGClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EACCESClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EADDRINUSEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EADDRNOTAVAILClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EAFNOSUPPORTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EAGAINClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EALREADYClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EBADFClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EBADMSGClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EBUSYClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ECHILDClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ECONNABORTEDClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ECONNREFUSEDClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ECONNRESETClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EDEADLKClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EDESTADDRREQClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EDOMClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EDQUOTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EEXISTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EFAULTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EFBIGClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EHOSTDOWNClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EHOSTUNREACHClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EIDRMClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EILSEQClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EINPROGRESSClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EINTRClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EINVALClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EIOClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EISCONNClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EISDIRClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ELOOPClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EMFILEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EMLINKClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EMSGSIZEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EMULTIHOPClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENAMETOOLONGClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENETDOWNClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENETRESETClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENETUNREACHClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENFILEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOBUFSClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENODATAClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENODEVClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOENTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOEXECClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOLCKClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOLINKClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOMEMClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOMSGClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOPROTOOPTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOSPCClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOSRClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOSTRClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOSYSClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOTBLKClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOTCONNClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOTDIRClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOTEMPTYClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOTSOCKClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENOTTYClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ENXIOClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EOPNOTSUPPClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EOVERFLOWClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EPERMClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EPFNOSUPPORTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EPIPEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EPROTOClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EPROTONOSUPPORTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EPROTOTYPEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ERANGEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EREMOTEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EROFSClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ESHUTDOWNClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ESOCKTNOSUPPORTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ESPIPEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ESRCHClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ESTALEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ETIMEClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ETIMEDOUTClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ETOOMANYREFSClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_ETXTBSYClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EUSERSClassSingletonMetaclass.setSuperClass(classMetaclass);
		errno_EXDEVClassSingletonMetaclass.setSuperClass(classMetaclass);
		exceptionClassSingletonMetaclass.setSuperClass(classMetaclass);
		falseClassClassSingletonMetaclass.setSuperClass(classMetaclass);
		fileClassSingletonMetaclass.setSuperClass(classMetaclass);
		file_StatClassSingletonMetaclass.setSuperClass(classMetaclass);
		fixnumClassSingletonMetaclass.setSuperClass(classMetaclass);
		floatClassSingletonMetaclass.setSuperClass(classMetaclass);
		floatDomainErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		hashClassSingletonMetaclass.setSuperClass(classMetaclass);
		iOClassSingletonMetaclass.setSuperClass(classMetaclass);
		iOErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		indexErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		integerClassSingletonMetaclass.setSuperClass(classMetaclass);
		interruptClassSingletonMetaclass.setSuperClass(classMetaclass);
		loadErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		localJumpErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		matchDataClassSingletonMetaclass.setSuperClass(classMetaclass);
		methodClassSingletonMetaclass.setSuperClass(classMetaclass);
		moduleClassSingletonMetaclass.setSuperClass(classMetaclass);
		nameErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		nameError_messageClassSingletonMetaclass.setSuperClass(classMetaclass);
		nilClassClassSingletonMetaclass.setSuperClass(classMetaclass);
		noMemoryErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		noMethodErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		notImplementedErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		numericClassSingletonMetaclass.setSuperClass(classMetaclass);
		objectClassSingletonMetaclass.setSuperClass(classMetaclass);
		procClassSingletonMetaclass.setSuperClass(classMetaclass);
		process_StatusClassSingletonMetaclass.setSuperClass(classMetaclass);
		rangeClassSingletonMetaclass.setSuperClass(classMetaclass);
		rangeErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		regexpClassSingletonMetaclass.setSuperClass(classMetaclass);
		regexpErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		runtimeErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		scriptErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		securityErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		setClassSingletonMetaclass.setSuperClass(classMetaclass);
		signalExceptionClassSingletonMetaclass.setSuperClass(classMetaclass);
		sortedSetClassSingletonMetaclass.setSuperClass(classMetaclass);
		standardErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		stringClassSingletonMetaclass.setSuperClass(classMetaclass);
		structClassSingletonMetaclass.setSuperClass(classMetaclass);
		struct_TmsClassSingletonMetaclass.setSuperClass(classMetaclass);
		symbolClassSingletonMetaclass.setSuperClass(classMetaclass);
		syntaxErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		systemCallErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		systemExitClassSingletonMetaclass.setSuperClass(classMetaclass);
		systemStackErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		threadClassSingletonMetaclass.setSuperClass(classMetaclass);
		threadErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		threadGroupClassSingletonMetaclass.setSuperClass(classMetaclass);
		timeClassSingletonMetaclass.setSuperClass(classMetaclass);
		trueClassClassSingletonMetaclass.setSuperClass(classMetaclass);
		typeErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		unboundMethodClassSingletonMetaclass.setSuperClass(classMetaclass);
		zeroDivisionErrorClassSingletonMetaclass.setSuperClass(classMetaclass);
		fatalClassSingletonMetaclass.setSuperClass(classMetaclass);
		kernelModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		fileTestModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		marshalModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		signalModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_GIDModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		file_ConstantsModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		objectSpaceModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		precisionModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		enumerableModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errnoModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_SysModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_UIDModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		mathModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		gCModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		processModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		comparableModuleSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		argumentErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		arrayClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		bignumClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		bindingClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		classClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		continuationClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		dataClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		dirClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		eOFErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_E2BIGClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EACCESClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EADDRINUSEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EADDRNOTAVAILClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EAFNOSUPPORTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EAGAINClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EALREADYClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EBADFClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EBADMSGClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EBUSYClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECHILDClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECONNABORTEDClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECONNREFUSEDClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ECONNRESETClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDEADLKClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDESTADDRREQClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDOMClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EDQUOTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EEXISTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EFAULTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EFBIGClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EHOSTDOWNClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EHOSTUNREACHClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EIDRMClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EILSEQClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EINPROGRESSClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EINTRClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EINVALClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EIOClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EISCONNClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EISDIRClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ELOOPClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMFILEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMLINKClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMSGSIZEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EMULTIHOPClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENAMETOOLONGClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENETDOWNClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENETRESETClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENETUNREACHClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENFILEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOBUFSClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENODATAClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENODEVClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOENTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOEXECClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOLCKClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOLINKClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOMEMClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOMSGClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOPROTOOPTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSPCClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSRClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSTRClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOSYSClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTBLKClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTCONNClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTDIRClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTEMPTYClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTSOCKClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENOTTYClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ENXIOClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EOPNOTSUPPClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EOVERFLOWClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPERMClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPFNOSUPPORTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPIPEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPROTOClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPROTONOSUPPORTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EPROTOTYPEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ERANGEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EREMOTEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EROFSClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESHUTDOWNClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESOCKTNOSUPPORTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESPIPEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESRCHClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ESTALEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETIMEClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETIMEDOUTClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETOOMANYREFSClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_ETXTBSYClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EUSERSClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		errno_EXDEVClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		exceptionClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		falseClassClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		fileClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		file_StatClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		fixnumClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		floatClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		floatDomainErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		hashClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		iOClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		iOErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		indexErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		integerClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		interruptClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		loadErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		localJumpErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		matchDataClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		methodClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		moduleClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		nameErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		nameError_messageClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		nilClassClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		noMemoryErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		noMethodErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		notImplementedErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		numericClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		objectClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		procClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		process_StatusClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		rangeClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		rangeErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		regexpClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		regexpErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		runtimeErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		scriptErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		securityErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		setClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		signalExceptionClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		sortedSetClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		standardErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		stringClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		structClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		struct_TmsClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		symbolClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		syntaxErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		systemCallErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		systemExitClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		systemStackErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		threadClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		threadErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		threadGroupClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		timeClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		trueClassClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		typeErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		unboundMethodClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		zeroDivisionErrorClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});
		fatalClassSingletonMetaclass.setIncludedModules(new ModuleMetaclass[] {

		});

		// end generated code
	}

	public static String[] objectMethods = { "methods", "instance_eval", "dup",
			"instance_variables", "include?", "private_instance_methods", "instance_of?",
			"protected_method_defined?", "ext	end", "const_defined?", "eql?", "name",
			"public_class_method", "autoload", "method_dump", "new", "hash", "id",
			"singleton_methods", "instance_method", "taint", "constants", "frozen?",
			"instance_variable_get", "kind_of?", "ancestors", "to_a", "private_class_method",
			"const_missing", "type", "instance_methods", "protected_methods", "superclass",
			"method_defined?", "instance_variable_set", "const_get", "is_a?", "autoload?",
			"respond_to?", "to_s", "module_eval", "class_variables", "allocate", "class", "<=>",
			"<", "method", "tainted?", "private_methods", "==", "public_instance_methods",
			"__id__", "===", "public_method_defined?", ">", "included_modules", "nil?", "untaint",
			"const_set", ">=", "<=", "send", "display", "inspect", "class_eval", "clone", "=~",
			"protected_instance_methods", "public_methods", "private_method_defined?", "__send__",
			"equal?", "freeze", "object_id" };

	public static String[] stringMethods = {

	};

	public static String[] regexpMethods = { "quote", "escape", "union", "last_match", "compile" };

	public static String[] fixnumMethods = { "induced_from" };

	public static String[] floatMethods = { "induced_from" };

	public static String[] arrayMethods = { "[]" };

}
