package hu.xannosz.microtools.logger;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import hu.xannosz.microtools.ExternalToString;
import hu.xannosz.microtools.Guard;

public class Logger {

	private static final String LOGGER_JSON = "logger.json";
	private static final String LOGGER_JSON_NOT_FOUND = "The logger.json file not found.";
	private static final String NONAME = "NoName";
	private static List<ObjectOutputStream> out = new ArrayList<>();
	private static List<Pair<String, Integer>> output = new ArrayList<>();
	private static String program = NONAME;

	private Class<?> clazz;
	private static JSONObject obj;
	private static JSONArray arr;
	private static JSONObject arrField;
	private static boolean jsonReaded = false;

	private Logger() {
	}

	public static Logger getLogger(Class<?> clazz) {
		if (!jsonReaded) {
			jsonReaded = true;
			readJson();
		}
		Logger logger = new Logger();
		logger.clazz = clazz;
		return logger;
	}

	public static void addServer(String host, int port) {
		output.add(new Pair<>(host, port));
	}

	public static void addProgramName(String name) {
		if (program.equals(NONAME)) {
			program = name;
		}
	}

	private static void createConnections() {
		for (int i = 0; i < output.size(); i++) {
			Pair<String, Integer> p = output.get(i);
			try {
				@SuppressWarnings({ "resource" })
				Socket socket = new Socket();
				int tries = 0;
				while (!socket.isConnected() && tries < 10) {
					try {
						tries += 1;
						socket.connect(new InetSocketAddress(p.getValue0(), p.getValue1()));

						out.add(new ObjectOutputStream(socket.getOutputStream()));
					} catch (IOException exp) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}
					}
				}
			} finally {
				output.remove(i);
			}
		}
	}

	private static void readJson() {
		obj = null;
		try {
			List<String> file = Files.readAllLines(Paths.get(LOGGER_JSON));
			StringBuffer content = new StringBuffer();
			for (String line : file) {
				content.append(line);
			}
			obj = new JSONObject(content.toString());
		} catch (IOException e) {
			System.out.println(LOGGER_JSON_NOT_FOUND);
		}

		if (program.equals(NONAME)) {
			String name = Guard.objectCreateWithoutException(() -> {
				return obj.getString("programName");
			}, false);
			if (name != null && !name.isEmpty()) {
				program = name;
			}
		}

		arr = Guard.objectCreateWithoutException(() -> {
			return obj.getJSONArray("servers");
		}, false);

		if (arr != null) {
			for (int i = 0; i < arr.length(); i++) {
				arrField = arr.getJSONObject(i);
				output.add(Guard.objectCreateWithoutException(() -> {
					return new Pair<>(arrField.getString("server"), arrField.getInt("port"));
				}, false));
			}
		} else if (output.isEmpty()) {
			output.add(new Pair<>("127.0.0.1", 1776));
		}
	}

	private static void log(LogLevel level, String message, Class<?> clazz) {
		Log log = new Log(level, Calendar.getInstance().getTime(), message, program, clazz.getSimpleName());
		createConnections();
		for (int i = 0; i < out.size(); i++) {
			ObjectOutputStream o = out.get(i);
			try {
				o.writeObject(log);
				o.flush();
			} catch (IOException exp) {
				out.remove(i);
			}
		}
	}

	public void log(LogLevel level, String message, Object... objects) {
		if (objects.length == 0) {
			log(level, Guard.toStringWithoutNull(message), clazz);
		} else if (objects.length == 1 && message == null) {
			log(level, ExternalToString.objectToString(objects[0]), clazz);
		} else {
			log(level, createObjectLog(Guard.toStringWithoutNull(message), objects), clazz);
		}
	}

	private String createObjectLog(String format, Object[] objects) {
		String result = format;
		for (Object o : objects) {
			result = result.replaceFirst("\\{\\}", ExternalToString.objectToString(o));
		}
		return result;
	}

	public void error(String s) {
		log(LogLevel.ERROR, s);
	}

	public void error(Object object) {
		log(LogLevel.ERROR, null, object);
	}

	public void error(String s, Object... objects) {
		log(LogLevel.ERROR, s, objects);
	}

	public void warning(String s) {
		log(LogLevel.WARNING, s);
	}

	public void warning(Object object) {
		log(LogLevel.WARNING, null, object);
	}

	public void warning(String s, Object... objects) {
		log(LogLevel.WARNING, s, objects);
	}

	public void info(String s) {
		log(LogLevel.INFO, s);
	}

	public void info(Object object) {
		log(LogLevel.INFO, null, object);
	}

	public void info(String s, Object... objects) {
		log(LogLevel.INFO, s, objects);
	}

	public void debug(String s) {
		log(LogLevel.DEBUG, s);
	}

	public void debug(Object object) {
		log(LogLevel.DEBUG, null, object);
	}

	public void debug(String s, Object... objects) {
		log(LogLevel.DEBUG, s, objects);
	}

	public void trace(String s) {
		log(LogLevel.TRACE, s);
	}

	public void trace(Object object) {
		log(LogLevel.TRACE, null, object);
	}

	public void trace(String s, Object... objects) {
		log(LogLevel.TRACE, s, objects);
	}
}