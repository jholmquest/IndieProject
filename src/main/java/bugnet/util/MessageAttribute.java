package bugnet.util;

import lombok.Getter;

/**
 * Attributes for various feedback messages
 * The messages pop up on user interaction
 *
 * @author James Holmquest
 */
@Getter
public enum MessageAttribute {
    INSERT("insertMessage"),
    SPECIMEN("specimenMessage"),
    SIGNUP("signupMessage");

    private final String attribute;

    /**
     * standard enum constructor
     * @param attribute name of attribute for storage in session
     */
    MessageAttribute(String attribute) {
        this.attribute = attribute;
    }
}
