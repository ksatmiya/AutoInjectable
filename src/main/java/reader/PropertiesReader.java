package reader;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final String FILE_PATH_PROPERTIES = "src/main/resources/config/config.properties";

    public static void read(Properties properties) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH_PROPERTIES)) {
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }
}
