package bugnet.entity;

import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
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
    private Date collectedDate;

    @Column(name = "notes")
    private String bugNotes;

    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "specimen_user_id_fk")
    )
    private User user;

    public Specimen() {
    }

    public Specimen(int id, String bugName, String collectedLocation,
                    Date collectedDate, String bugNotes, User user) {
        this.id = id;
        this.bugName = bugName;
        this.collectedLocation = collectedLocation;
        this.collectedDate = collectedDate;
        this.bugNotes = bugNotes;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBugName() {
        return bugName;
    }

    public void setBugName(String bugName) {
        this.bugName = bugName;
    }

    public String getCollectedLocation() {
        return collectedLocation;
    }

    public void setCollectedLocation(String collectedLocation) {
        this.collectedLocation = collectedLocation;
    }

    public Date getCollectedDate() {
        return collectedDate;
    }

    public void setCollectedDate(Date collectedDate) {
        this.collectedDate = collectedDate;
    }

    public String getBugNotes() {
        return bugNotes;
    }

    public void setBugNotes(String bugNotes) {
        this.bugNotes = bugNotes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
