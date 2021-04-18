package bugnet.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream(filePath));
        } catch (IOException ioe) {
            logger.error("Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            logger.error("PropertyLoader" + e);
            e.printStackTrace();
        }
        return properties;
    }
}
