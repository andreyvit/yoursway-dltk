namespace eval mynamespace {
	proc a1 { aa1 } {
		return $aa1
	}

	proc a2 { aa21 aa22 aa23 } {
		return $aa23
	}
}

namespace eval mynamespace2 {
	proc a1 { aa1 } {
		return $aa1
	}

	proc a2 { aa21 aa22 aa23 } {
		return $aa23
	}
}	

namespace eval mynamespace3 {
	proc a1 { aa1 } {
		return $aa1
	}

	proc a2 { aa21 aa22 aa23 } {
		return $aa23
	}
}

namespace eval mynamespace2 {
	proc a3 { aa1 } {
		return $aa1
	}

	proc a4 { aa21 aa22 aa23 } {
		return $aa23
	}
}