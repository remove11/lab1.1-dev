package kth.alex.demo.repository;

import jakarta.ws.rs.core.Response;
import kth.alex.demo.Exeption.ClientErrorException;
import kth.alex.demo.Exeption.ServerErrorException;
import kth.alex.demo.RequestBodyData.UserCreationRequest;
import kth.alex.demo.utils.KeycloakUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
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

    public List<UserRepresentation> getUsers() throws ServerErrorException {
        Keycloak keycloak = keycloakUtil.getInstance();
        try {
            List<UserRepresentation> users = keycloak.realm(realm).users().list();
            return users;
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }
    }

    public Optional<UserRepresentation> getUserById(String id) throws ServerErrorException {
        Keycloak keycloak = keycloakUtil.getInstance();
        try{
            UserRepresentation user = keycloak.realm(realm).users().get(id).toRepresentation();
            if(user == null)
                return Optional.empty();
            return Optional.of(user);
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }
    }

    public Optional<UserRepresentation> getUserByEmail(String email) throws ServerErrorException {
        Keycloak keycloak = keycloakUtil.getInstance();
        try {
            List<UserRepresentation> users = keycloak.realm(realm).users().searchByEmail(email, true);
            if(users.size() == 0)
                return Optional.empty();

            return Optional.of(users.get(0));
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }
    }

    public Optional<UserRepresentation> getUserByUsername(String username) throws ServerErrorException {
        Keycloak keycloak = keycloakUtil.getInstance();
        try {
            List<UserRepresentation> users = keycloak.realm(realm).users().searchByUsername(username, true);
            if(users.size() == 0)
                return Optional.empty();

            return Optional.of(users.get(0));
        }catch (Exception ex){
            throw new ServerErrorException(ex.getMessage());
        }
    }

    public Optional<UserRepresentation> createUser(UserCreationRequest newUser, String role) throws ServerErrorException, ClientErrorException {
        Keycloak keycloak = keycloakUtil.getInstance();
        UserRepresentation userRep = toUserRep(newUser, role);

        Response response = keycloak.realm(realm).users().create(userRep);
        response.close();

        int statusCode = response.getStatus();
        if(statusCode < 300 && statusCode >= 200){
            return Optional.of(userRep);
        }
        if(statusCode < 500 && statusCode >= 400){
            throw new ClientErrorException("Can not create server(bad request)");
        }
        if(statusCode < 600 && statusCode >= 500){
            throw new ServerErrorException("Can not create server(server error)");
        }
        throw new ServerErrorException("Something gone wrong");
    }

    public boolean deleteUser(String id) throws ClientErrorException, ServerErrorException {
        Keycloak keycloak = keycloakUtil.getInstance();
        Response response = keycloak.realm(realm).users().delete(id);
        response.close();

        int statusCode = response.getStatus();
        if(statusCode >= 200 && statusCode < 300){
            return true;
        }
        if(statusCode < 500 && statusCode >= 400){
            throw new ClientErrorException("Can not create server(bad request)");
        }
        if(statusCode < 600 && statusCode >= 500){
            throw new ServerErrorException("Can not create server(server error)");
        }
        throw new ServerErrorException("Something gone wrong");
    }

    private UserRepresentation toUserRep(UserCreationRequest user, String role){
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

        // Set user roles
        List<String> roles = new ArrayList<>();
        roles.add(role);
        userRep.setRealmRoles(roles);

        credentialRepresentations.add(credentialRepresentation);
        userRep.setCredentials(credentialRepresentations);

        return userRep;
    }


}
