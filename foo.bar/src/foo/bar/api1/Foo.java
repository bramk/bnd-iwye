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
