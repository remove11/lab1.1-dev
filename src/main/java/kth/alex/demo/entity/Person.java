package kth.alex.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@ToString
public abstract class Person {

    public enum Gender {
        MAN, WOMAN, OTHER;
    }

    @Column(name = "surename")
    private String surename;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "keycloakId")
    private String keycloakId;

    @Column(name = "adress")
    private String adress;

    @Column(name="id", unique = true)
    private String userCredential;
    @Id
    @Column(name = "socialNr")
    private String socialNr;

    @Column(name = "phoneNr")
    private String phoneNr;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMsg;
    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMsg;


    public Person() {
    }

    public Person(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender, String keycloakId) {
        this.surename = surename;
        this.lastname = lastname;
        this.adress = adress;
        this.socialNr = socialNr;
        this.phoneNr = phoneNr;
        this.gender = gender;
        this.keycloakId = keycloakId;
    }

    public Person(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender) {
        this.surename = surename;
        this.lastname = lastname;
        this.adress = adress;
        this.socialNr = socialNr;
        this.phoneNr = phoneNr;
        this.gender = gender;
    }
}
