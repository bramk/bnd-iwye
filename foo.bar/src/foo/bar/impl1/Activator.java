package foo.bar.impl1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import foo.bar.api1.FooService;

public class Activator implements BundleActivator {

	private ServiceRegistration<?> reg;

	@Override
	public void start(BundleContext context) throws Exception {
    	System.err.println("Impl loads FooService from classloader: " + FooService.class.getClassLoader().toString());
		reg = context.registerService(FooService.class, new FooServiceImpl(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		reg.unregister();
	}
}
