package kth.alex.demo.configuration;

import kth.alex.demo.entity.*;
import kth.alex.demo.repository.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SeedingConfig {
    @Bean
    CommandLineRunner commandLineRunner(MessageRepository messageRepository){
        return arg -> {
            Patient patient = new Patient();
            patient.setAdress("address");
            patient.setLastname("lastname");
            patient.setGender(Person.Gender.MAN);
            patient.setPhoneNr("123");
            patient.setSurename("surename");
            patient.setSocialNr("122233");

            Doctor doctor = new Doctor();
            doctor.setAdress("address1");
            doctor.setLastname("lastname1");
            doctor.setGender(Person.Gender.WOMAN);
            doctor.setPhoneNr("12322");
            doctor.setSurename("surename1");
            doctor.setSocialNr("333333");

            Message msg = new Message();
            msg.setContent("Du har tid idag");
            msg.setSender(doctor);
            msg.setReceiver(patient);

            messageRepository.save(msg);
        };
    }
}
