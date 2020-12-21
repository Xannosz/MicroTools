package hu.xannosz.microtools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

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
}
