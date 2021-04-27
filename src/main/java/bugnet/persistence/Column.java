package bugnet.persistence;

import lombok.Getter;

/**
 * Holds legal column names
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
