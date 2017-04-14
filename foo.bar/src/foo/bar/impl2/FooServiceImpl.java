package foo.bar.impl2;

import foo.bar.api2.Foo;
import foo.bar.api2.FooService;
import foo.bar.util.Precondition;

public class FooServiceImpl implements FooService {

	@Override
	public void doWith(Foo foo) {
		Precondition.assertNotNull(foo);
		System.out.printf("HELO Foo[%s]%n", foo.getName());
	}
}
