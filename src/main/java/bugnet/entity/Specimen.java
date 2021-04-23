package bugnet.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;

/**
 * Object representing the specimen table
 */
@Getter
@Setter
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

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

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
