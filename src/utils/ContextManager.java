package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ContextManager {

  private static Properties props;

  static {
    props = new Properties();

    try {
      FileInputStream file = new FileInputStream("src/prod.properties");
      props.load(file);
      file.close();
    } catch (Throwable exc) {
      throw new RuntimeException(exc);
    }
  }

  public static String getProperty(String property) {
    return props.getProperty(property);
  }


}
