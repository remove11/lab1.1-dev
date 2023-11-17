package kth.alex.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kth.alex.demo.repository.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
@SecurityRequirement(name="Keycloak")
public class TestController {
    @Autowired
    IdentityRepository identityRepository;


    @GetMapping("/headers")
    public Map<String, String> getHeaders(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println(key+" : "+value);
        });
        return headers;
    }
    @GetMapping("/jwt")
    public String getAuthenticationPrinciple() {
        final String user =  (String)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();


        System.out.println(user);
        return user;
    }
    @GetMapping("/claimss")
    public Map<String, Object> getClaims(){
        final Jwt user = (Jwt) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Map<String, Object> claims = user.getClaims();
        return claims;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public String admin(){
        return "Hej, ADMIN";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('user')")
    public String user(){
        return "Hej, USER";
    }

    @GetMapping("/service/id")
    public String getSub(){
        return identityRepository.getUserId().orElse("id not found");
    }

    @GetMapping("/service/hasrole/{role}")
    public Boolean hasrole(@PathVariable String role){
        return identityRepository.hasRole(role);
    }
}
