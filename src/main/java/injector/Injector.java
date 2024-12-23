package injector;

import annotation.AutoInjectable;
import reader.PropertiesReader;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    private final Properties properties = new Properties();

    public Injector() {
        PropertiesReader.read(properties);
    }

    public <T> T inject(T obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implName = properties.getProperty(interfaceName);

                if (implName != null) {
                    try {
                        Class<?> implClass = Class.forName(implName);
                        Object implInstance = implClass.getDeclaredConstructor().newInstance();

                        field.setAccessible(true);
                        field.set(obj, implInstance);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to inject dependency for field " + field.getName(), e);
                    }
                }
            }
        }

        return obj;
    }
}
