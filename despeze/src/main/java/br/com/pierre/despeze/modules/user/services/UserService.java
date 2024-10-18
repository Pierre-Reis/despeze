package br.com.pierre.despeze.modules.user.services;

import br.com.pierre.despeze.exceptions.runtime.UserFoundException;
import br.com.pierre.despeze.modules.user.entities.User;
import br.com.pierre.despeze.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(User user){

        this.userRepository
                .findByEmail(user.getEmail())
                .ifPresent(
                        (userExists) -> {
                            throw new UserFoundException();
                        });

        var encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return this.userRepository.save(user);
    }
}