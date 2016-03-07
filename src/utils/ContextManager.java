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
