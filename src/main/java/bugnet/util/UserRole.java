package bugnet.util;

import lombok.Getter;

/**
 * Defines the legal role names
 *
 * @author James Holmquest
 */
@Getter
public enum UserRole {
    ADMIN("administrator"),
    USER("user");

    private final String roleName;

    /**
     * Standard constructor
     * @param roleName name of role
     */
    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
