package kth.alex.demo.RequestBodyData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class MedicalConditionCreate {
    private String patientSocialNr;
    private String doctorEmployeeId;
    private String diagnos;
}
