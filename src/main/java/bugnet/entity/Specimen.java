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
}
