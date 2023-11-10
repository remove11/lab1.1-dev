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
    private String doctorName;
    private String description;
    private LocalDateTime createdAt;
}
