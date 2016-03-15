package utils;

/*
 * Static methods used to avoid code duplication.
 */
public abstract class Utils {

    public static void checkObjects(Object object, Class ObjectType) {
        if (object.getClass() != ObjectType) {
            throw new IllegalArgumentException();
        }
    }
}
