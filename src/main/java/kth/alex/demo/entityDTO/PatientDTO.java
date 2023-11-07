package kth.alex.demo.entityDTO;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import kth.alex.demo.entity.MedicalCondition;
import kth.alex.demo.entity.Message;
import kth.alex.demo.entity.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record PatientDTO (
    String socialNr,
    String surename,
    String lastname,
    String adress,
    String phoneNr,
    Person.Gender gender,
    LocalDateTime createdAt
){}
