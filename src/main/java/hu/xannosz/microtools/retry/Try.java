package hu.xannosz.microtools.retry;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import hu.xannosz.microtools.logger.Logger;

public class Try {

	private static final Logger LOGGER = Logger.getLogger(Try.class);

	public static <T> T linearWaitUntilNonNull(Supplier<T> supp, long waitBetweenRepeats, long timeOut) {
		return linearWaitUntilNonNull(supp, waitBetweenRepeats, timeOut, true);
	}

	public static <T> T linearWaitUntilTimeOut(Supplier<T> supp, long waitBetweenRepeats, long timeOut) {
		return linearWaitUntilTimeOut(supp, waitBetweenRepeats, timeOut, true);
	}

	public static <T> T linearWaitUntilNonNull(Supplier<T> supp, long waitBetweenRepeats, long timeOut,
			boolean enableLog) {
		ExtendedThreadWaitForNonNull<T> task = new ExtendedThreadWaitForNonNull<>(supp, waitBetweenRepeats, enableLog);
		execute(task, timeOut, enableLog);
		return task.result;
	}

	public static <T> T linearWaitUntilTimeOut(Supplier<T> supp, long waitBetweenRepeats, long timeOut,
			boolean enableLog) {
		ExtendedThreadWaitForTime<T> task = new ExtendedThreadWaitForTime<>(supp, waitBetweenRepeats, enableLog);
		execute(task, timeOut, enableLog);
		return task.result;
	}

	private static void execute(Thread task, long timeout, boolean enableLog) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(task);

		try {
			future.get(timeout, TimeUnit.MINUTES);
		} catch (TimeoutException e) {
			future.cancel(true);
			if (enableLog) {
				LOGGER.trace("No response until {} minutes.Time is out. Return null.", timeout);
			}
		} catch (InterruptedException |ExecutionException e) {
			if (enableLog) {
				LOGGER.trace("We have a problem: ", e);
			}
		}

		executor.shutdownNow();
	}
}