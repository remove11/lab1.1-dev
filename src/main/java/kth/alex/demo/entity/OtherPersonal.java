package kth.alex.demo.entity;

import jakarta.persistence.*;
import kth.alex.demo.entityDTO.OtherPersonalDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "otherPersonal")
@Getter
@Setter
@ToString
public class OtherPersonal extends Employee{

    @Column(name = "calenderId")
    String calenderId;

    public OtherPersonal() {
        this.calenderId = UUID.randomUUID().toString();
    }

    public OtherPersonal(String calenderId) {
        this.calenderId = calenderId;
    }

    public OtherPersonal(OtherPersonalDTO otherPersonalDTO){
        super(
                otherPersonalDTO.getSurename(),
                otherPersonalDTO.getLastname(),
                otherPersonalDTO.getAdress(),
                otherPersonalDTO.getSocialNr(),
                otherPersonalDTO.getPhoneNr(),
                otherPersonalDTO.getGender(),
                otherPersonalDTO.getEmployeeId()
        );
        this.calenderId=otherPersonalDTO.getCalenderId();
    }
}
