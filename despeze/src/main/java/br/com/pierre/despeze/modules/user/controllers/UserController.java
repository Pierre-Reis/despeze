package br.com.pierre.despeze.modules.user.controllers;

import br.com.pierre.despeze.modules.user.entities.User;
import br.com.pierre.despeze.modules.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid User user){
        try {
            var result = this.userService.create(user);
            return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
