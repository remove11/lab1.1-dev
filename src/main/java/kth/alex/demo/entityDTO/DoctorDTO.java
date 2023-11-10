package kth.alex.demo.entityDTO;
import kth.alex.demo.entity.Person;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DoctorDTO {
        private String socialNr;
        private String surename;
        private String lastname;
        private String adress;
        private String phoneNr;
        private Person.Gender gender;
        private String degreeId;
        private String employeeId;
}
