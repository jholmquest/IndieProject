package bugnet.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

/**
 * Holds values for the role table
 *
 * @author James Holmquest
 */
@Getter
@Setter
@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "role_id", strategy = "native")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "role_user_id_fk")
    )
    private User user;

    /**
     * basic no argument constructor
     */
    public Role() {
    }

    /**
     * constructs a Role object with preset values
     * @param roleName name of role
     * @param userName user with role
     * @param user reference to user for hibernate relational mapping
     */
    public Role(String roleName, String userName, User user) {
        this.roleName = roleName;
        this.username = userName;
        this.user = user;
    }

    /**
     * Checks if two role objects are equivalent
     * @param o role object being passed in
     * @return whether or not the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(roleName, role.roleName) && Objects.equals(username, role.username);
    }

    /**
     * gives the hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, username);
    }
}
