package kth.alex.demo.repository;

import jakarta.ws.rs.core.Response;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.utils.KeycloakUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class KeycloakRepository {
    @Autowired
    private KeycloakUtil keycloakUtil;

    @Value("${keycloak.utils.realm}")
    private String realm;

    public List<UserRepresentation> getUsers(){
        Keycloak keycloak = keycloakUtil.getInstance();
        try {
            List<UserRepresentation> users = keycloak.realm(realm).users().list();
            return users;
        }catch (Exception ex){
            return new ArrayList<>();
        }
    }

    public Optional<UserRepresentation> getUserById(String id){
        Keycloak keycloak = keycloakUtil.getInstance();
        try{
            UserRepresentation user = keycloak.realm(realm).users().get(id).toRepresentation();
            return Optional.of(user);
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    public Optional<UserRepresentation> getUserByEmail(String email){
        Keycloak keycloak = keycloakUtil.getInstance();
        try {
            List<UserRepresentation> users = keycloak.realm(realm).users().searchByEmail(email, true);
            if(users.size() == 0)
                return Optional.empty();

            return Optional.of(users.get(0));
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    public Optional<UserRepresentation> getUserByUsername(String username){
        Keycloak keycloak = keycloakUtil.getInstance();
        try {
            List<UserRepresentation> users = keycloak.realm(realm).users().searchByUsername(username, true);
            if(users.size() == 0)
                return Optional.empty();

            return Optional.of(users.get(0));
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    public Optional<UserRepresentation> createUser(UserCreationRequest newUser){
        try{
            Keycloak keycloak = keycloakUtil.getInstance();
            UserRepresentation userRep = toUserRep(newUser);

            Response response = keycloak.realm(realm).users().create(userRep);
            response.close();

            int statusCode = response.getStatus();
            if(statusCode < 300 && statusCode >= 200){
                return Optional.of(userRep);
            }

            return Optional.empty();
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    public boolean deleteUser(String id){
        try{
            Keycloak keycloak = keycloakUtil.getInstance();
            Response response = keycloak.realm(realm).users().delete(id);
            response.close();

            int statusCode = response.getStatus();
            if(statusCode >= 200 && statusCode < 300)
                return true;

            return false;
        }catch (Exception ex){
            return false;
        }

    }

    private UserRepresentation toUserRep(UserCreationRequest user){
        UserRepresentation userRep = new UserRepresentation();
        userRep.setUsername(user.getUsername());
        userRep.setFirstName(user.getSurename());
        userRep.setLastName(user.getLastname());
        userRep.setEmail(user.getEmail());
        userRep.setEnabled(true);
        userRep.setEmailVerified(true);

        List<CredentialRepresentation> credentialRepresentations = new ArrayList<>();
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentation.setTemporary(false);

        credentialRepresentations.add(credentialRepresentation);
        userRep.setCredentials(credentialRepresentations);

        return userRep;
    }


}
