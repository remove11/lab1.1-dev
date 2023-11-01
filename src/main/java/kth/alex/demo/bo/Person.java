package kth.alex.demo.bo;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "person_type",
        discriminatorType = DiscriminatorType.STRING
)
public class Person {

    public enum Gender {
        MAN, WOMAN, OTHER;
    }

    @Column(name = "surename")
    private String surename;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "adress")
    private String adress;

    @Id
    @Column(name = "socialNr")
    private String socialNr;

    @Column(name = "phoneNr")
    private String phoneNr;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    public Person() {
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getSocialNr() {
        return socialNr;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surename='" + surename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +
                ", socialNr=" + socialNr +
                ", phoneNr=" + phoneNr +
                ", gender=" + gender +
                '}';
    }
}
