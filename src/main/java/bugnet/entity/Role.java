package bugnet.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_level")
    private int roleLevel;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    private List<User> users = new ArrayList<User>();
}
