package kth.alex.demo.service;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.entity.OtherPersonal;
import kth.alex.demo.entityDTO.OtherPersonalDTO;
import kth.alex.demo.repository.KeycloakRepository;
import kth.alex.demo.repository.OtherPersonalRepository;
import org.apache.catalina.User;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OtherPersonalService {
    @Autowired
    private OtherPersonalRepository otherPersonalRepository;

    @Autowired
    private KeycloakRepository keycloakRepository;

    public List<OtherPersonalDTO> getAll() {
        List<OtherPersonal> otherPersonals = otherPersonalRepository.findAll();
        List<OtherPersonalDTO> otherPersonalDTOS = new ArrayList<>();
        for (OtherPersonal o : otherPersonals) {
            OtherPersonalDTO otherPersonalDTO = new OtherPersonalDTO(
                    o.getSocialNr(),
                    o.getSurename(),
                    o.getLastname(),
                    o.getAdress(),
                    o.getPhoneNr(),
                    o.getGender(),
                    o.getCalenderId(),
                    o.getEmployeeId()
            );
            otherPersonalDTOS.add(otherPersonalDTO);
        }
        return otherPersonalDTOS;
    }

    public OtherPersonalDTO getBySocial(String socialNr) {
        OtherPersonal o = otherPersonalRepository.findBySocialNr(socialNr);
            return new OtherPersonalDTO(
                    o.getSocialNr(),
                    o.getSurename(),
                    o.getLastname(),
                    o.getAdress(),
                    o.getPhoneNr(),
                    o.getGender(),
                    o.getCalenderId(),
                    o.getEmployeeId()
            );

    }

    public OtherPersonalDTO save(UserCreationRequest otherPersonalCreation){
        OtherPersonal otherPersonal = new OtherPersonal(otherPersonalCreation);

        UserRepresentation user = keycloakRepository.createUser(otherPersonalCreation).orElseThrow();

        otherPersonal.setKeycloakId(user.getId());
        OtherPersonal o = otherPersonalRepository.save(otherPersonal);

        return new OtherPersonalDTO(
                o.getSocialNr(),
                o.getSurename(),
                o.getLastname(),
                o.getAdress(),
                o.getPhoneNr(),
                o.getGender(),
                o.getCalenderId(),
                o.getEmployeeId()
        );
    }
}

