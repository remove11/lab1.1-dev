package kth.alex.demo.entityDTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EncounterDTO {
    private String id;
    private String patientSocialNr;
    private String patientName;
    private String doctorEmployeeId;
    private String doctorName;
    private String description;
    private LocalDateTime createdAt;
}
