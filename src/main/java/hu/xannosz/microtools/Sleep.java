package hu.xannosz.microtools;

import java.util.concurrent.TimeUnit;

import hu.xannosz.microtools.logger.Logger;

public class Sleep {

	private static final Logger LOGGER = Logger.getLogger(Sleep.class);

	public static void sleepMinutes(long minutes) {
		sleepMillis(TimeUnit.MINUTES.toMillis(minutes));
	}

	public static void sleepSeconds(long seconds) {
		sleepMillis(TimeUnit.SECONDS.toMillis(seconds));
	}

	public static void sleepMillis(long millis) {
		sleepMillis(millis, true);
	}

	public static void sleepMinutes(long minutes, boolean enableLog) {
		sleepMillis(TimeUnit.MINUTES.toMillis(minutes), enableLog);
	}

	public static void sleepSeconds(long seconds, boolean enableLog) {
		sleepMillis(TimeUnit.SECONDS.toMillis(seconds), enableLog);
	}

	public static void sleepMillis(long millis, boolean enableLog) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			if (enableLog) {
				LOGGER.trace("Sleep failed: {}", e);
			}
		}
	}
}