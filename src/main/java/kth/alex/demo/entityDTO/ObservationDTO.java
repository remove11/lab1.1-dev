package kth.alex.demo.entityDTO;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ObservationDTO {
    private String id;
    private String patientName;
    private String patientSocialNr;
    private String doctorName;
    private String doctorEmployeeId;
    private String description;
    private String encounterId;
    private LocalDateTime createdAt;
}
