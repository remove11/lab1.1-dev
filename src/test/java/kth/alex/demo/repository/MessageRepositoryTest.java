package kth.alex.demo.repository;
import kth.alex.demo.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
class MessageRepositoryTest {
    @Autowired
    MessageRepository messageRepository;

    @Test
    public void testRepo (){
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

        OtherPersonal otherPersonal = new OtherPersonal();
        otherPersonal.setAdress("address2");
        otherPersonal.setLastname("lastname2");
        otherPersonal.setGender(Person.Gender.OTHER);
        otherPersonal.setPhoneNr("222222");
        otherPersonal.setSurename("surename2");
        otherPersonal.setSocialNr("444444");

        Message msg = new Message();
        msg.setContent("Du har tid idag");
        msg.setSender(doctor);
        msg.setReceiver(patient);

        messageRepository.save(msg);

        List<Message> messageList = messageRepository.findAll();

        System.out.println(messageList);

    }

}