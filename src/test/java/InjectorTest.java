import bean.SomeBean;
import injector.Injector;
import interfaces.impl.SODoer;
import interfaces.impl.SomeImpl;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class InjectorTest {

    @Test
    void testInjection() {
        Properties properties = new Properties();
        properties.setProperty("interfaces.Some", "interfaces.impl.SomeImpl");
        properties.setProperty("interfaces.SomeOther", "interfaces.impl.SODoer");

        try (FileOutputStream fos = new FileOutputStream("src/test/resources/config/test-config.properties")) {
            properties.store(fos, null);
        } catch (Exception e) {
            fail("Failed to create config.properties: " + e.getMessage());
        }

        SomeBean bean = new Injector().inject(new SomeBean());

        assertNotNull(bean.getField1());
        assertNotNull(bean.getField2());

        assertInstanceOf(SomeImpl.class, bean.getField1());
        assertInstanceOf(SODoer.class, bean.getField2());
    }
}
