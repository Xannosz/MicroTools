package hu.xannosz.microtools;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class FileResourcesUtils {
    public static File streamToTempFile(InputStream in) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("resourceFile", UUID.randomUUID().toString()
                    .replace("-", ""));
            tempFile.deleteOnExit();
            FileUtils.copyInputStreamToFile(in, tempFile);
        } catch (IOException e) {
            //Empty
        }
        return tempFile;
    }

    public static String streamToString(InputStream in) {
        return new BufferedReader(
                new InputStreamReader(in))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public static File getFileFromResourceAsFile(String fileName) {
        return streamToTempFile(getFileFromResourceAsStream(fileName));
    }

    public static String getFileFromResourceAsString(String fileName) {
        return streamToString(getFileFromResourceAsStream(fileName));
    }
}
