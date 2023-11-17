package kth.alex.demo.service;
import kth.alex.demo.Exeption.ClientErrorException;
import kth.alex.demo.Exeption.NotFoundException;
import kth.alex.demo.Exeption.ServerErrorException;
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

    public List<OtherPersonalDTO> getAll() throws ServerErrorException, NotFoundException {
        List<OtherPersonal> otherPersonals;

        try {
            otherPersonals = otherPersonalRepository.findAll();
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        if(otherPersonals == null)
            throw new NotFoundException("Other personal not found");

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
                    o.getEmployeeId(),
                    o.getKeycloakId(),
                    ""
            );
            otherPersonalDTOS.add(otherPersonalDTO);
        }
        return otherPersonalDTOS;
    }

    public OtherPersonalDTO getBySocial(String socialNr) throws ServerErrorException, NotFoundException {
        OtherPersonal o;
        UserRepresentation userR;

        try {
            o = otherPersonalRepository.findBySocialNr(socialNr);
            userR = keycloakRepository.getUserById(o.getKeycloakId()).orElseThrow(()->new NotFoundException("Keycloak user not found"));
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        if(o == null){
            throw new NotFoundException("OtherPersonal not found");
        }

            return new OtherPersonalDTO(
                    o.getSocialNr(),
                    o.getSurename(),
                    o.getLastname(),
                    o.getAdress(),
                    o.getPhoneNr(),
                    o.getGender(),
                    o.getCalenderId(),
                    o.getEmployeeId(),
                    o.getKeycloakId(),
                    userR.getEmail()
            );

    }

    public OtherPersonalDTO save(UserCreationRequest otherPersonalCreation) throws ClientErrorException, ServerErrorException, NotFoundException {
        OtherPersonal otherPersonal = new OtherPersonal(otherPersonalCreation);

        UserRepresentation user = keycloakRepository.createUser(otherPersonalCreation,"otherPersonal").orElseThrow();

        otherPersonal.setKeycloakId(user.getId());

        OtherPersonal o;
        try {
            o = otherPersonalRepository.save(otherPersonal);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }

        return new OtherPersonalDTO(
                o.getSocialNr(),
                o.getSurename(),
                o.getLastname(),
                o.getAdress(),
                o.getPhoneNr(),
                o.getGender(),
                o.getCalenderId(),
                o.getEmployeeId(),
                o.getKeycloakId(),
                user.getEmail()
        );
    }
}

