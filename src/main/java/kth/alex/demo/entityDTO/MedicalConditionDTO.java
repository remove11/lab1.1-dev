package kth.alex.demo.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class MedicalConditionDTO {
    private Long id;
    private String patientSocialNr;
    private String patientName;
    private String doctorEmployeeId;
    private String doctorName;
    private String diagnos;
    private LocalDateTime createdAt;
}
