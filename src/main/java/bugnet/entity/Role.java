package bugnet.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

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
    private String userName;

    /*
    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> users = new HashSet<User>();
    */
    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "role_user_id_fk")
    )
    private User user;

    public Role() {
    }

    public Role(String roleName, String userName, User user) {
        this.roleName = roleName;
        this.userName = userName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(roleName, role.roleName) && Objects.equals(userName, role.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, userName);
    }
}
