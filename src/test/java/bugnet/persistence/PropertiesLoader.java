package bugnet.persistence;

import java.io.IOException;
import java.util.Properties;

public interface PropertiesLoader {

    /** load the properties file containing the driver, connection url, userid and pwd.
     */
    default Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream(filePath));
        } catch (IOException ioe) {
            System.out.println("Database.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Database.loadProperties()..." + e);
            e.printStackTrace();
        }
        return properties;
    }
}
