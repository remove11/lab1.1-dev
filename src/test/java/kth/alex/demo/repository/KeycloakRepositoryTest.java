package kth.alex.demo.repository;

import kth.alex.demo.Exeption.ClientErrorException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.entity.Person;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class KeycloakRepositoryTest {
    @Autowired
    KeycloakRepository keycloakRepository;

    @Test
    void getUsers() throws ServerErrorException {
        List<UserRepresentation> users = keycloakRepository.getUsers();
        users.forEach(user -> {
            System.out.println(user.getEmail());
            System.out.println(user.getId());
            System.out.println(user.getUsername());
        });
    }

    @Test
    void getUserById() throws ServerErrorException {
        UserRepresentation user = keycloakRepository.getUserById("c2a3ab26-50b5-4c15-8da0-76692da5dbfb").orElse(null);
        System.out.println(user);
        System.out.println(user.getEmail());
        System.out.println("--------");
        UserRepresentation user1 = keycloakRepository.getUserById("c2a3ab26-50b5-4c15-8da0-76692da5dbf+").orElse(null);
        if(user1 != null){
            System.out.println(user1);
            System.out.println(user1.getEmail());
        }
    }

    @Test
    void userCreationUser() throws ClientErrorException, ServerErrorException {
        UserCreationRequest newUser = UserCreationRequest.builder()
                .username("newuser") .password("newuser")
                .surename("my") .lastname("name")
                .email("email@kth.se") .adress("home")
                .socialNr("123") .phoneNr("1112")
                .gender(Person.Gender.OTHER) .createdAt(LocalDateTime.now())
                .employeeId("332") .degreeId("2223")
                .build();
        UserRepresentation user = keycloakRepository.createUser(newUser, "PATIENT").orElse(null);
        assert user != null;
    }

    @Test
    void userDeletionUser() throws ClientErrorException, ServerErrorException {
        boolean res = keycloakRepository.deleteUser("175edad6-da71-4300-bf97-2051d1d69a48");
        assert res;
    }

    @Test
    void getUserByEmail() throws ServerErrorException {
        UserRepresentation res = keycloakRepository.getUserByEmail("admin@kth.se").orElse(null);

        assert res != null;

        System.out.println(res.getUsername());
    }
}