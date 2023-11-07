package kth.alex.demo.configuration;

import kth.alex.demo.entity.*;
import kth.alex.demo.repository.EncounterRepository;
import kth.alex.demo.repository.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SeedingConfig {
    @Bean
    CommandLineRunner commandLineRunner(MessageRepository messageRepository, EncounterRepository encounterRepository){
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
            doctor.setSocialNr("3333334");

            Message msg = new Message();
            msg.setContent("Du har tid idag");
            msg.setSender(doctor);
            msg.setReceiver(patient);

            //messageRepository.save(msg);

            Encounter encounter = new Encounter();
            encounter.setDescription("Jag är encounter");
            System.out.println("** Pat info is = " + patient.getSocialNr());
            System.out.println("** doc info is = " + doctor.getSocialNr());
            encounter.setPatient(patient);
            encounter.setCreatedBy(doctor);

            encounterRepository.save(encounter);
        };
    }
}
