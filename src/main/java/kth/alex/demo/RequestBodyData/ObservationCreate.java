package kth.alex.demo.RequestBodyData;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Encounter;
import kth.alex.demo.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ObservationCreate {
    private String id;
    private String patientSocialNr;
    private String doctorEmployeeId;
    private String encounterId;
    private String description;
}
