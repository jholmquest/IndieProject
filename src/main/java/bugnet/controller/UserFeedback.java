package bugnet.controller;

import lombok.Getter;

/**
 * Contains messages that give users feedback on various operations
 *
 * @author James Holmquest
 */
@Getter
public enum UserFeedback {
    COORDINATE_SUCCESS(""),
    COORDINATE_FAILURE("");

    private final String message;

    UserFeedback(String message) {
        this.message = message;
    }
}
