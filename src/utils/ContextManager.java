package utils;

import java.io.FileInputStream;
import java.util.Properties;


/**
 * ContextManager pour la gestion des properties.
 * 
 * @author Martin
 * @since 7 mars 2016
 */
public class ContextManager {

  private static Properties props;
  private static Properties buildConfig;

  static {
    props = new Properties();
    buildConfig = new Properties();

    try {
      FileInputStream build = new FileInputStream("src/build.properties");
      buildConfig.load(build);
      build.close();
      FileInputStream file;
      if (buildConfig.containsKey("status") && buildConfig.containsValue("DEBUG")) {
        file = new FileInputStream("src/debug.properties");
      } else {
        file = new FileInputStream("src/prod.properties");
      }
      props.load(file);
      file.close();
    } catch (Throwable exc) {
      throw new RuntimeException(exc);
    }
  }

  /**
   * Permet de récuperer une propriete stockee dans le fichier de proprietes.
   * 
   * @param property Le nom de la propriete a trouver.
   * @return La valeur stockee dans la propriete.
   */
  public static String getProperty(String property) {
    return props.getProperty(property);
  }


}
