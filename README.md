# Testing potential bnd issue

This project demonstrates an issue where BND fails to automatically add an import statement for an exported api. Looks like a bug unless we are doing something bad.. 

## Setup

The `foo.bar` project generate two bundles, foo.bar.impl1.jar and `foo.bar.impl2.jar`. Both have their own exported api package and a corresponding private implementation package. Furthermore, they share the `foo.bar.util` package which both inline as a private package.

The notable difference between that `foo.bar.api1` uses the `Precondition` class from the private `foo.bar.util` package whereas `foo.bar.api2` does not. The assumption is that this is legal as the `foo.bar.util` package does not appear on any `foo.bar.api1 `signature. It's *just* an implementation detail and BND also does not give a reference error on it.
```
package foo.bar.api1;

import foo.bar.util.Precondition;

public class Foo {

	private final String m_name;

	public Foo(String name) {
		Precondition.assertNotEmpty(name);
		m_name = name;
	}

	public String getName() {
		return m_name;
	}
}
```

## Problem

Although both bundles are generated without warnings or errors and work in a simple deployment... it turns out that for `foo.bar.impl1.jar` the import statement for the `foo.bar.api1` is missing. It therefore violates the "import what you export" rule and can run into problems..
```
Manifest-Version: 1.0
Bnd-LastModified: 1492168735547
Bundle-Activator: foo.bar.impl1.Activator
Bundle-ManifestVersion: 2
Bundle-Name: foo.bar.impl1
Bundle-SymbolicName: foo.bar.impl1
Bundle-Version: 0
Created-By: 1.8.0_111 (Oracle Corporation)
Export-Package: foo.bar.api1;version="1.0.0"
Import-Package: org.osgi.framework;version="[1.8,2)"
Private-Package: foo.bar.impl1,foo.bar.util
Require-Capability: osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.8))"
Tool: Bnd-3.3.0.201609221906
```

```
Manifest-Version: 1.0
Bnd-LastModified: 1492168735599
Bundle-Activator: foo.bar.impl2.Activator
Bundle-ManifestVersion: 2
Bundle-Name: foo.bar.impl2
Bundle-SymbolicName: foo.bar.impl2
Bundle-Version: 0
Created-By: 1.8.0_111 (Oracle Corporation)
Export-Package: foo.bar.api2;version="1.0.0"
Import-Package: foo.bar.api2;version="[1.0,2)",org.osgi.framework;vers
 ion="[1.8,2)"
Private-Package: foo.bar.impl2,foo.bar.util
Require-Capability: osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.8))"
Tool: Bnd-3.3.0.201609221906
```
