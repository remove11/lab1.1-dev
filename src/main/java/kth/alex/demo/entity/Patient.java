package kth.alex.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends Person{

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMsg;
    @OneToMany(mappedBy = "receiver")
    private List<Message> receiverMsg;
    @OneToMany(mappedBy = "patient")
    private List<MedicalCondition> medicalConditions;

    @OneToMany(mappedBy = "patient")
    private List<Encounter> encounter;

    private LocalDate createdAt;

    public Patient() {
        super();
    }


    /*
    public String viewSelfInfo() {
        StringBuilder info = new StringBuilder();

        info.append("Patient Information:\n");
        info.append("Name: ").append(getSurename()).append(" ").append(getLastname()).append("\n");
        info.append("Address: ").append(getAdress()).append("\n");
        info.append("Phone Number: ").append(getPhoneNr()).append("\n");
        info.append("Gender: ").append(getGender()).append("\n");
        info.append("Date Created: ").append(createdAt).append("\n");

        info.append("Observations: ").append(listToString(observations)).append("\n");
        info.append("Encounters: ").append(listToString(encounters)).append("\n");
        info.append("Medical Conditions: ").append(listToString(medicalConditions)).append("\n");
        info.append("Messages: ").append(listToString(messages)).append("\n");

        return info.toString();
    }

    public void sendMessage(Person recipient, String content) {
        Message message = new Message();
        message.setPatient(this);
        message.setEmployee(recipient);
        message.setContent(content);
        //TODO gör anrop till DB för att spara meddelandet
        System.out.println("Message sent: " + content);
    }


    */

}
