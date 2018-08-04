package hu.xannosz.microtools.retry;

import java.util.function.Supplier;

import hu.xannosz.microtools.Sleep;
import hu.xannosz.microtools.logger.Logger;

public class ExtendedThreadWaitForTime<T> extends Thread {

	private static final Logger LOGGER = Logger.getLogger(ExtendedThreadWaitForTime.class);

	public T result = null;
	private long waitBetweenRepeats;
	private Supplier<T> supp;

	private final boolean enableLog;

	public ExtendedThreadWaitForTime(Supplier<T> supp, long waitBetweenRepeats, boolean enableLog) {
		this.supp = supp;
		this.waitBetweenRepeats = waitBetweenRepeats;
		this.enableLog = enableLog;
	};

	@Override
	public void run() {
		for (;;) {
			try {
				result = supp.get();
			} catch (Exception e) {
				if (enableLog) {
					LOGGER.trace("Execution failed: {}, wait {} seconds and retry.", e, waitBetweenRepeats);
				}
				Sleep.sleepSeconds(waitBetweenRepeats);
			}
		}
	}
}