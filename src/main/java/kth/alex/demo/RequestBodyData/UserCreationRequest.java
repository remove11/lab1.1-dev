package kth.alex.demo.requestBodyEntity;

import kth.alex.demo.entity.Person;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserCreationRequest {
    private String username;
    private String password;
    private String surename;
    private String email;
    private String lastname;
    private String adress;
    private String socialNr;
    private String phoneNr;
    private Person.Gender gender;
    private LocalDateTime createdAt;
    private String employeeId;
    String degreeId;
}
