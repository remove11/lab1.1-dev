package kth.alex.demo.RequestBodyData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ObservationCreate {
    private String patientSocialNr;
    private String doctorEmployeeId;
    private String encounterId;
    private String description;

}
