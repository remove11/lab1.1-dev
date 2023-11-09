package kth.alex.demo.entityDTO;
import kth.alex.demo.entity.Person;

import java.util.UUID;

public record OtherPersonalDTO(
        String socialNr,
        String surename,
        String lastname,
        String adress,
        String phoneNr,
        Person.Gender gender,
        String calenderId,
        String employeeId

){
}
