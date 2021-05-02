package bugnet.controller;

import lombok.Getter;

/**
 * Contains messages that give users feedback on various operations
 *
 * @author James Holmquest
 */
@Getter
public enum UserFeedback {
    COORDINATE_SUCCESS("Coordinates Found"),
    COORDINATE_FAILURE("No Coordinates Found"),
    SIGNUP_SUCCESS("User Created"),
    PASSWORDS_DIFFERENT("The passwords must match"),
    GENERIC_FAILURE("Something went wrong");

    private final String message;

    UserFeedback(String message) {
        this.message = message;
    }
}
