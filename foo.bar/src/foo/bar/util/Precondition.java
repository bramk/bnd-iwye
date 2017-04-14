package foo.bar.util;

public class Precondition {

	public static final <T> T assertNotNull(T x) {
		if (x == null) {
			throw new IllegalArgumentException("Argument cannot be null!");
		}
		return x;
	}

	public static final <T extends CharSequence> T assertNotEmpty(T x) {
		if (x == null || "".equals(x)) {
			throw new IllegalArgumentException("Argument cannot be empty!");
		}
		return x;
	}
}
