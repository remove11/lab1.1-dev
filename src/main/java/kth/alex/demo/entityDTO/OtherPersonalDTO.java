package kth.alex.demo.entityDTO;
import kth.alex.demo.entity.Person;
import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OtherPersonalDTO{
        String socialNr;
        String surename;
        String lastname;
        String adress;
        String phoneNr;
        Person.Gender gender;
        String calenderId;
        String employeeId;
        String keycloakId;
        String email;
}
