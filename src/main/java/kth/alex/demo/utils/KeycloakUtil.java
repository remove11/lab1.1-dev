package kth.alex.demo.utils;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakUtil {
    private static Keycloak keycloak;

    @Value("${keycloak.utils.server-url}")
    private String serverUrl;

    @Value("${keycloak.utils.realm}")
    private String realm;

    @Value("${keycloak.utils.client-id}")
    private String clientId;

    @Value("${keycloak.utils.grant-type}")
    private String grantType;

    @Value("${keycloak.utils.username}")
    private String username;

    @Value("${keycloak.utils.password}")
    private String password;

    public Keycloak getInstance(){
        if(keycloak == null)
            keycloak = KeycloakBuilder
                    .builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .clientId(clientId)
                    .grantType(grantType)
                    .username(username)
                    .password(password)
                    .build();
        return keycloak;
    }
}
