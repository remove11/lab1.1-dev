package kth.alex.demo.configuration;

import jakarta.transaction.Transactional;
import kth.alex.demo.entity.*;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.EncounterRepository;
import kth.alex.demo.repository.MessageRepository;
import kth.alex.demo.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SeedingConfig {
    @Bean
    CommandLineRunner commandLineRunner(MessageRepository messageRepository, EncounterRepository encounterRepository, DoctorRepository doctorRepository, PatientRepository patientRepository){
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
            msg.setSender(patient);
            msg.setReceiver(doctor);

            patientRepository.save(patient);
            doctorRepository.save(doctor);
            messageRepository.save(msg);

            Encounter encounter = new Encounter();
            encounter.setDescription("Jag är encounter");
            encounter.setCreatedBy(doctor);
            encounter.setPatient(patient);

            encounterRepository.save(encounter);
        };
    }
}
