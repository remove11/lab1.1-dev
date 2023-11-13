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

            Doctor doctor2 = new Doctor();
            doctor2.setAdress("address2");
            doctor2.setLastname("lastname3");
            doctor2.setGender(Person.Gender.WOMAN);
            doctor2.setPhoneNr("222");
            doctor2.setSurename("surename2");
            doctor2.setSocialNr("2222221");
            doctor2.setEmployeeId("22");

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

            Message msg1 = new Message();
            msg1.setContent("Du har tid idag2");
            msg1.setSender(patient);
            msg1.setReceiver(doctor);

            Message msg2 = new Message();
            msg2.setContent("Du har tid idag3");
            msg2.setSender(patient);
            msg2.setReceiver(doctor2);


            System.out.println("YOYOYOY1");
            otherPersonalRepository.save(otherPersonal);
            System.out.println("YOYOYOY2");
            patientRepository.save(patient);
            System.out.println("YOYOYOY3");
            doctorRepository.save(doctor);
            doctorRepository.save(doctor2);
            System.out.println("YOYOYOY4");
            messageRepository.save(msg);
            messageRepository.save(msg1);
            messageRepository.save(msg2);
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
