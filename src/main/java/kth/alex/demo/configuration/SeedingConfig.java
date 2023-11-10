package kth.alex.demo.configuration;

import jakarta.transaction.Transactional;
import kth.alex.demo.entity.*;
import kth.alex.demo.entityDTO.ObservationDTO;
import kth.alex.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


@Configuration
public class SeedingConfig {
    @Bean
    CommandLineRunner commandLineRunner(MessageRepository messageRepository, EncounterRepository encounterRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, OtherPersonalRepository otherPersonalRepository, MedicalConditionRepository medicalConditionRepository, ObservationRepository observationRepository){
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
            doctor.setEmployeeId("32");


            OtherPersonal otherPersonal = new OtherPersonal();
            otherPersonal.setAdress("address33");
            otherPersonal.setLastname("lastname33");
            otherPersonal.setGender(Person.Gender.WOMAN);
            otherPersonal.setPhoneNr("333111");
            otherPersonal.setSurename("surename3");
            otherPersonal.setSocialNr("444444");
            otherPersonal.setEmployeeId(UUID.randomUUID().toString());
            System.out.println("EmpId= " + otherPersonal.getEmployeeId());


            Message msg = new Message();
            msg.setContent("Du har tid idag");
            msg.setSender(patient);
            msg.setReceiver(doctor);



            System.out.println("YOYOYOY1");
            otherPersonalRepository.save(otherPersonal);
            System.out.println("YOYOYOY2");
            patientRepository.save(patient);
            System.out.println("YOYOYOY3");
            doctorRepository.save(doctor);
            System.out.println("YOYOYOY4");
            messageRepository.save(msg);
            System.out.println("YOYOYOY5");

            Encounter encounter = new Encounter();
            encounter.setDescription("Jag är encounter");
            encounter.setCreatedBy(doctor);
            encounter.setPatient(patient);

            encounterRepository.save(encounter);

            MedicalCondition medicalCondition = new MedicalCondition();
            medicalCondition.setDoctor(doctor);
            medicalCondition.setPatient(patient);
            medicalCondition.setDiagnos("Väldigt sjukt tyvärr");

            System.out.println("YOYOYOY6");
            medicalConditionRepository.save(medicalCondition);


            System.out.println("YOYOYOY7");

            Observation observation = new Observation();
            observation.setCreatedBy(doctor);
            observation.setPatient(patient);
            observation.setEncounter(encounter);
            observation.setDescription("Lite galen");

            observationRepository.save(observation);
            System.out.println("YOYOYOY8");


        };
    }
}
