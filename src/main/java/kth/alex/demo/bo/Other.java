package kth.alex.demo.bo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Other")
public class Other extends Person{

}
