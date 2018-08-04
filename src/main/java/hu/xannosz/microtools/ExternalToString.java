package hu.xannosz.microtools;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExternalToString {
	public static String objectToString(Object o) {
		if (o instanceof Throwable) {
			return throwableToString((Throwable) o);
		} else {
			return Guard.toStringWithoutNull(o);
		}
	}

	public static String throwableToString(Throwable t) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		t.printStackTrace(ps);
		ps.close();
		return baos.toString();
	}
}