package kth.alex.demo.bo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Patient")
public class Patient extends Person{
    @OneToMany(mappedBy = "patient")
    private List<Observation> observations;
    @OneToMany(mappedBy = "patient")
    private List<Encounter> encounters;
    @OneToMany(mappedBy = "patient")
    private List<MedicalCondition> medicalConditions;
    @OneToMany(mappedBy = "patient")
    private List<Message> messages;

    public Patient() {
        super();
    }


    public <T> String listToString(List<T> list) {
        return list.toString();
    }




}
