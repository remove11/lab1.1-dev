package kth.alex.demo.service;
import kth.alex.demo.entity.OtherPersonal;
import kth.alex.demo.entityDTO.OtherPersonalDTO;
import kth.alex.demo.repository.OtherPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OtherPersonalService {
    @Autowired
    private OtherPersonalRepository otherPersonalRepository;

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

    public List<OtherPersonalDTO> getBySocial(String socialNr) {
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

    public OtherPersonalDTO save(OtherPersonalDTO otherPersonalDTO){
        OtherPersonal otherPersonal = new OtherPersonal(otherPersonalDTO);
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

