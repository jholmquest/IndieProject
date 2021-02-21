package bugnet.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

/**
 * class representing a user
 *
 * @author James Holmquest
 */
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Specimen> specimens = new HashSet<>();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Specimen> getSpecimens() {
        return specimens;
    }

    public void setSpecimens(Set<Specimen> specimens) {
        this.specimens = specimens;
    }

    public void addSpecimen(Specimen specimen) {
        specimens.add(specimen);
        specimen.setUser(this);
    }

    public void removeSpecimen(Specimen specimen) {
        specimens.remove(specimen);
        specimen.setUser(null);
    }
}
