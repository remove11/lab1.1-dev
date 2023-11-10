package kth.alex.demo.entityDTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MedicalConditionDTO {
    private String id;
    private String patientSocialNr;
    private String patientName;
    private String doctorEmployeeId;
    private String doctorName;
    private String diagnos;
    private LocalDateTime createdAt;
}
