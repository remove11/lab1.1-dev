package kth.alex.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "otherPersonal")
public class OtherPersonal extends Employee{
    /*
    @OneToMany(mappedBy = "employee")
    private List<Message> messages;
    */
     @Column(name = "calendar")
     String calendar;

    public OtherPersonal() {
        super();
    }


/*
    public void addObservation(Patient patient, String description) {
        Observation observation = new Observation();
        observation.setPatient(patient);
        observation.setDescription(description);
        observation.setEmployee(this);
        //TODO gör anrop till DB
        System.out.println("ADDED TO DB: Patient = " + patient.toString() + ", Observation = " + description + ", set by = " + this.getSurename());
    }

    public void addEncounter(Patient patient, String description) {
        Encounter encounter = new Encounter();
        encounter.setPatient(patient);
        encounter.setDescription(description);
        encounter.setEmployee(this);
        //TODO gör anrop till DB
        System.out.println("ADDED TO DB: Patient = " + patient.toString() + ", Observation = " + description + ", set by = " + this.getSurename());
    }
    public void addMedicalCondition(Patient patient, String description) {
        MedicalCondition medicalCondition = new MedicalCondition();
        medicalCondition.setPatient(patient);
        medicalCondition.setDescription(description);
        medicalCondition.setEmployee(this);
        //TODO gör anrop till DB
        System.out.println("ADDED TO DB: Patient = " + patient.toString() + ", Observation = " + description + ", set by = " + this.getSurename());
    }

    public void viewMessages() {
        List<Message> messages = getMessages();
        for (Message message : messages) {
            System.out.println(message.getContent());
        }
    }

    public void replyToMessage(Message message, String replyContent) {
        Message reply = new Message();
        reply.setEmployee(this);
        reply.setPatient(message.getPatient()); //Kan va getEmp här
        reply.setContent(replyContent);
        // TODO: Save reply to DB
        System.out.println("REPLY SAVED TO DB: " + reply.toString());
    }

    */

}
