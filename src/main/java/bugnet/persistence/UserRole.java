package bugnet.persistence;

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

    String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
