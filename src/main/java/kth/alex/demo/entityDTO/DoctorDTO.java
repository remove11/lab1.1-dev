package kth.alex.demo.entityDTO;
import kth.alex.demo.entity.Person;

public record DoctorDTO (
        String socialNr,
        String surename,
        String lastname,
        String adress,
        String phoneNr,
        Person.Gender gender,
        String degreeId,
        String employeeId
){
}
