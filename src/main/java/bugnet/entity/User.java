package bugnet.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

/**
 * class representing a user
 *
 * @author James Holmquest
 */
@Getter
@Setter
@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Specimen> specimens = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    /**
     * Instantiate a new user
     */
    public User() {
    }

    /**
     * Instantiates a user with parameters
     * @param username user username
     * @param password user password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * adds specimen to list of specimens
     * @param specimen new specimen
     */
    public void addSpecimen(Specimen specimen) {
        specimens.add(specimen);
        specimen.setUser(this);
    }

    /**
     * removes a specific specimen
     * @param specimen specimen to be removed
     */
    public void removeSpecimen(Specimen specimen) {
        specimens.remove(specimen);
        specimen.setUser(null);
    }

    /**
     * adds a specific role
     * @param role user role
     */
    public void addRole(Role role) {
        roles.add(role);
        role.setUser(this);
    }

    /**
     * removes a specific role
     * @param role user role
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.setUser(null);
    }
    /**
     * compares entities
     * @param o another object for comparison
     * @return whether the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    /**
     * gets a hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
