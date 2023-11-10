package kth.alex.demo.utils;

import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeycloakUtilTest {
    @Autowired
    KeycloakUtil keycloakUtil;

    @Test
    public void getInstanceTest(){
        Keycloak keycloak = keycloakUtil.getInstance();
    }
}