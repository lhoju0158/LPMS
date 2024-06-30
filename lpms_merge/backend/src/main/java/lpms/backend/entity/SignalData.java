package lpms.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
<<<<<<< Updated upstream

import static jakarta.persistence.FetchType.LAZY;
=======
import lpms.backend.enums.EventType;
>>>>>>> Stashed changes

/**
 * Entity class representing the signal data.
 */
@Entity
@Data
@Table(name = "signal_data", indexes = {
        @Index(name = "idx_file_name", columnList = "fileName")
})
public class SignalData {

    @Id
    @GeneratedValue
    @Column(name = "signal_data_id")
    private Long id; // Unique identifier for the signal data

<<<<<<< Updated upstream
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "binary_data_id")
    private BinaryData binaryData;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "graph_data_id")
    private GraphData graphData;
}



=======
    private String filePath; // File path where the signal data is stored
    private String fileName; // Name of the file containing the signal data
    private String time; // Timestamp of the signal data

    private Boolean status; // Status of the signal data

    /*public short channel; // Channel number of the signal data
    @Enumerated(EnumType.STRING)
    private EventType Type; // Type of event associated with the signal data*/

    private Boolean aiResult; // AI result associated with the signal data
    private Float aiProbability; // Probability of the AI result
}


>>>>>>> Stashed changes
