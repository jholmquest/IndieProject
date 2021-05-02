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
    SIGNUP_SUCCESS("User Created with an id of "),
    PASSWORDS_DIFFERENT("The passwords must match"),
    DUPLICATE_USER("This user already exists"),
    GENERIC_FAILURE("Something went wrong");

    private final String message;

    UserFeedback(String message) {
        this.message = message;
    }
}
