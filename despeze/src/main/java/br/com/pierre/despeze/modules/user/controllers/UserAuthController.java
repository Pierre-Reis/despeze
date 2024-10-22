package br.com.pierre.despeze.modules.user.controllers;

import br.com.pierre.despeze.modules.user.dto.UserAuthDTO;
import br.com.pierre.despeze.modules.user.services.UserAuthService;
import br.com.pierre.despeze.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/user")
    public ResponseEntity<Object> authenticate(@RequestBody UserAuthDTO userAuthDTO){
        try {
            var result = this.userAuthService.getAuthToken(userAuthDTO);

            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}