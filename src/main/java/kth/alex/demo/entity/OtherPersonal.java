package kth.alex.demo.entity;

import jakarta.persistence.*;
import kth.alex.demo.entityDTO.OtherPersonalDTO;

import java.util.List;

@Entity
@Table(name = "otherPersonal")
public class OtherPersonal extends Employee{

    @OneToMany(mappedBy = "patient")
    private List<MedicalCondition> writenMedicalConditions;

    @Column(name = "calenderId")
    String calenderId;

    public OtherPersonal() {
        super();
    }

    public OtherPersonal(OtherPersonalDTO otherPersonalDTO){
        super(
                otherPersonalDTO.surename(),
                otherPersonalDTO.lastname(),
                otherPersonalDTO.adress(),
                otherPersonalDTO.socialNr(),
                otherPersonalDTO.phoneNr(),
                otherPersonalDTO.gender(),
                otherPersonalDTO.employeeId()
        );
        this.calenderId=otherPersonalDTO.calenderId();
    }

    public List<MedicalCondition> getWritenMedicalConditions() {
        return writenMedicalConditions;
    }

    public void setWritenMedicalConditions(List<MedicalCondition> writenMedicalConditions) {
        this.writenMedicalConditions = writenMedicalConditions;
    }

    public String getCalenderId() {
        return calenderId;
    }

    public void setCalenderId(String calenderId) {
        this.calenderId = calenderId;
    }
}
