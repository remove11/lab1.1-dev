package kth.alex.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IdentityService {

    public Optional<String> getUserId(SecurityContext context){
        Jwt jwt;

        try{
            jwt = (Jwt) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        }catch (Exception e){
            return Optional.empty();
        }

        String id = jwt.getClaim("sub");
        if(id == null || id.length() == 0)
            return Optional.empty();

        return Optional.of(id);
    }

    public Boolean hasRole(String role){
        Jwt jwt;

        try{
            jwt = (Jwt) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        }catch (Exception e){
            return false;
        }

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        ObjectMapper mapper = new ObjectMapper();
        List<String> keycloakRoles = mapper.convertValue(realmAccess.get("roles"), new TypeReference<List<String>>(){});

        return keycloakRoles.contains(role);
    }
}
