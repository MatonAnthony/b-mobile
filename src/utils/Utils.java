package utils;

/**
 * Static methods used to avoid code duplication.
 */
public abstract class Utils {
  /**
   * Check if the object is of the correct type.
   * 
   * @param object The object to check.
   * @param ObjectType The type to compare.
   */
  public static void checkObjects(Object object, Class objectType) {
    if (object.getClass() != objectType) {
      throw new IllegalArgumentException();
    }

  }
}
