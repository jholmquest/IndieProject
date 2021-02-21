package bugnet.entity;

import org.hibernate.annotations.GenericGenerator;

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
}
