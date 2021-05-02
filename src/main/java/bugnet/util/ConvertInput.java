package bugnet.util;

/**
 * Contains methods with basic logic for making form input usable
 *
 * @author James Holmquest
 */
public interface ConvertInput {

    /**
     * Checks if any text was entered from the form, and if
     * any text was it converts it to a double
     *
     * @param coordinateText text form of coordinate being parsed
     * @return null if no text, otherwise the converted double
     */
    default Double setCoordinate(String coordinateText) {

        if (coordinateText.isEmpty()) {
            return null;
        } else {
            return Double.valueOf(coordinateText);
        }
    }
}
