package hu.xannosz.microtools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

@UtilityClass
public class Json {
    public static <T> T readData(Path path, Class<T> clazz) throws IOException {
        path.toFile().getParentFile().mkdirs();
        path.toFile().createNewFile();
        JsonElement dataObject = JsonParser.parseString(FileUtils.readFileToString(path.toFile()));
        return new Gson().fromJson(dataObject, clazz);
    }

    public static <T> void writeData(Path path, T data) throws IOException {
        path.toFile().getParentFile().mkdirs();
        path.toFile().createNewFile();
        FileUtils.writeStringToFile(path.toFile(), new GsonBuilder().setPrettyPrinting().create().toJson(data));
    }
}
