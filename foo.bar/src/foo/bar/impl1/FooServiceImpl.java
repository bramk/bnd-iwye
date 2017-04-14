package foo.bar.impl1;

import foo.bar.api1.Foo;
import foo.bar.api1.FooService;
import foo.bar.util.Precondition;

public class FooServiceImpl implements FooService {

	@Override
	public void doWith(Foo foo) {
		Precondition.assertNotNull(foo);
		System.out.printf("HELO Foo[%s]%n", foo.getName());
	}
}
