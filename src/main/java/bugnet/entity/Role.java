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

    public Role() {
    }

    public Role(String roleName, String userName, User user) {
        this.roleName = roleName;
        this.username = userName;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(roleName, role.roleName) && Objects.equals(username, role.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, username);
    }
}
