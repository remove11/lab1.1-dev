package kth.alex.demo.entity;

import jakarta.persistence.*;

import java.util.List;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Person {

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

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMsg;
    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMsg;

    public void setSocialNr(String socialNr) {
        this.socialNr = socialNr;
    }

    public void setSentMsg(List<Message> sentMsg) {
        this.sentMsg = sentMsg;
    }

    public void setReceivedMsg(List<Message> receivedMsg) {
        this.receivedMsg = receivedMsg;
    }

    public List<Message> getSentMsg() {
        return sentMsg;
    }

    public List<Message> getReceivedMsg() {
        return receivedMsg;
    }

    public Person() {
    }

    public Person(String surename, String lastname, String adress, String socialNr, String phoneNr, Gender gender) {
        this.surename = surename;
        this.lastname = lastname;
        this.adress = adress;
        this.socialNr = socialNr;
        this.phoneNr = phoneNr;
        this.gender = gender;
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
                ", socialNr='" + socialNr + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                ", gender=" + gender +
                '}';
    }
}
