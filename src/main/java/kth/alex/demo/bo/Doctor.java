package kth.alex.demo.bo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Doctor")
public class Doctor extends Person{
    public Doctor() {
        super();
    }

}
