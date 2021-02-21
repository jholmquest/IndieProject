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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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

    /**
     * gets user's id
     * @return users id
     */
    public int getId() {
        return id;
    }

    /**
     * sets user's id
     * @param id user's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets specimens collected by user
     * @return specimens collected by user
     */
    public Set<Specimen> getSpecimens() {
        return specimens;
    }

    /**
     * sets specimens collected by user
     * @param specimens specimens collected by user
     */
    public void setSpecimens(Set<Specimen> specimens) {
        this.specimens = specimens;
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
}
