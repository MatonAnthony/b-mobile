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

  public static final String env;
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
        env = "debug";
        file = new FileInputStream("src/"+env+".properties");
      } else {
        env = "prod";
        file = new FileInputStream("src/"+env+".properties");
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
