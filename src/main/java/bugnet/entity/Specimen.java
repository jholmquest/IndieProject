package bugnet.entity;

import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;

/**
 * Object representing the specimen table
 */
@Entity(name = "Specimen")
@Table(name = "specimen")
public class Specimen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "determination")
    private String bugName;

    @Column(name = "location")
    private String collectedLocation;

    @Column(name = "date")
    private LocalDate collectedDate;

    @Column(name = "notes")
    private String bugNotes;

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "specimen_user_id_fk")
    )
    private User user;

    /**
     * Standard no arg constructor
     */
    public Specimen() {
    }

    /**
     * creates a specimen with specified parameters
     * @param bugName name of insect
     * @param collectedLocation where insect was collected
     * @param collectedDate when insect was collected
     * @param bugNotes notes about insect
     * @param user user who collected insect
     */
    public Specimen(String bugName, String collectedLocation,
                    LocalDate collectedDate, String bugNotes, User user) {
        this.bugName = bugName;
        this.collectedLocation = collectedLocation;
        this.collectedDate = collectedDate;
        this.bugNotes = bugNotes;
        this.user = user;
    }

    /**
     * gets the id
     * @return id of insect
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id
     * @param id id of insect
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the name of the insect
     * @return name of insect
     */
    public String getBugName() {
        return bugName;
    }

    /**
     * sets the name of the insect
     * @param bugName name of insect
     */
    public void setBugName(String bugName) {
        this.bugName = bugName;
    }

    /**
     * gets where insect was collected
     * @return where insect was collected
     */
    public String getCollectedLocation() {
        return collectedLocation;
    }

    /**
     * sets where insect was collected
     * @param collectedLocation where insect was collected
     */
    public void setCollectedLocation(String collectedLocation) {
        this.collectedLocation = collectedLocation;
    }

    /**
     * gets date insect collected
     * @return date insect collected
     */
    public LocalDate getCollectedDate() {
        return collectedDate;
    }

    /**
     * sets date insect collected
     * @param collectedDate
     */
    public void setCollectedDate(LocalDate collectedDate) {
        this.collectedDate = collectedDate;
    }

    /**
     * gets notes on insect
     * @return notes on insect
     */
    public String getBugNotes() {
        return bugNotes;
    }

    /**
     * sets notes on insect
     * @param bugNotes notes on insect
     */
    public void setBugNotes(String bugNotes) {
        this.bugNotes = bugNotes;
    }

    /**
     * gets user that collected insect
     * @return collector
     */
    public User getUser() {
        return user;
    }

    /**
     * sets user that collected insect
     * @param user collector
     */
    public void setUser(User user) {
        this.user = user;
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
        Specimen specimen = (Specimen) o;
        return id == specimen.id && Objects.equals(bugName, specimen.bugName) && Objects.equals(collectedLocation, specimen.collectedLocation) && Objects.equals(collectedDate, specimen.collectedDate) && Objects.equals(bugNotes, specimen.bugNotes) && Objects.equals(user, specimen.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bugName, collectedLocation, collectedDate, bugNotes, user);
    }
}
