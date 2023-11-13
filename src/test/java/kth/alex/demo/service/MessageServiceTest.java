package kth.alex.demo.service;

import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.MessageCreate;
import kth.alex.demo.entity.Doctor;
import kth.alex.demo.entity.Message;
import kth.alex.demo.entity.Patient;
import kth.alex.demo.entity.Person;
import kth.alex.demo.repository.DoctorRepository;
import kth.alex.demo.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MessageServiceTest {
    @Autowired
    MessageService messageService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Test
    void create() throws NotFoundException, ServerErrorException {

        Patient patient = new Patient();
        patient.setAdress("address1");
        patient.setLastname("sender");
        patient.setGender(Person.Gender.MAN);
        patient.setPhoneNr("123");
        patient.setSurename("surename");
        patient.setSocialNr("112211");
        patientRepository.save(patient);

        Doctor doctor = new Doctor();
        doctor.setAdress("addres2");
        doctor.setLastname("reciever");
        doctor.setGender(Person.Gender.WOMAN);
        doctor.setPhoneNr("12322");
        doctor.setSurename("surename1");
        doctor.setSocialNr("6662");
        doctor.setEmployeeId("321");
        doctorRepository.save(doctor);

        MessageCreate messageCreate = new MessageCreate();
        messageCreate.setDescription("Du har tid idag!! GLöm inte igen");
        messageCreate.setSenderSocialNr(patient.getSocialNr());
        messageCreate.setReceiverSocialNr(doctor.getSocialNr());

        messageService.create(messageCreate);
    }
}