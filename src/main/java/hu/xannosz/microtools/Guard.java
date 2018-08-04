package hu.xannosz.microtools;

import java.util.function.Supplier;

import hu.xannosz.microtools.logger.LogLevel;
import hu.xannosz.microtools.logger.Logger;

public class Guard {

	private static final Logger LOGGER = Logger.getLogger(Guard.class);

	public static <T> T objectCreateWithoutException(Supplier<T> supp) {
		return objectCreateWithoutException(supp, null);
	}

	public static <T> T objectCreateWithoutException(Supplier<T> supp, T defaulT) {
		return objectCreateWithoutException(supp, defaulT, LogLevel.TRACE, true);
	}

	public static <T> T objectCreateWithoutException(Supplier<T> supp, T defaulT, LogLevel level) {
		return objectCreateWithoutException(supp, defaulT, level, true);
	}

	public static <T> T objectCreateWithoutException(Supplier<T> supp, boolean enableLog) {
		return objectCreateWithoutException(supp, null, enableLog);
	}

	public static <T> T objectCreateWithoutException(Supplier<T> supp, T defaulT, boolean enableLog) {
		return objectCreateWithoutException(supp, defaulT, LogLevel.TRACE, enableLog);
	}

	public static <T> T objectCreateWithoutException(Supplier<T> supp, T defaulT, LogLevel level, boolean enableLog) {
		T result = defaulT;
		try {
			result = supp.get();
		} catch (Exception e) {
			if (enableLog) {
				LOGGER.log(level, "Creating failed: {} .\nReturn with {} value.", e, result);
			}
		}
		return result;
	}

	public static <T> String toStringWithoutNull(T s) {
		if (s == null) {
			return "";
		}
		return s.toString();
	}
}