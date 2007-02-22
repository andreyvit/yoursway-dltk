proc foo0 { a b c d } {
	proc qwe {} {}
}
namespace eval namespace1 {
	proc a {} {}
	namespace eval inner1 {
		proc b {} {}
	}
}

namespace eval namespace2 {
	proc c {} {}
	namespace eval inner2 {
		proc d {} {}
		namespace eval inner4 {
			proc e {} {}
		}
	}
}