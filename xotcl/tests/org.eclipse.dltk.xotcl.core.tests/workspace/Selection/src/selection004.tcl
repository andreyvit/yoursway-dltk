Object obj
obj set a 0

Class C1
Class C2
Class create C3 -superclass {C1 C2}
Class create C4 -superclass C1

C1 create c1_inst
C1 instproc foo {} {return 0}
c1_inst foo

C1 proc c1_proc {} {return 0}
C1 c1_proc

obj proc obj_proc {} {return 0}
obj obj_proc
