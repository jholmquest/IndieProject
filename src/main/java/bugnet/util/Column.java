package bugnet.util;

import lombok.Getter;

/**
 * Holds legal column names for get by property
 *
 * @author James Holmquest
 */
@Getter
public enum Column {
    ID("id"),
    USERNAME("username"),
    USERID("user_id"),
    USER("user");

    private final String name;

    Column(String name) {
        this.name = name;
    }
}
