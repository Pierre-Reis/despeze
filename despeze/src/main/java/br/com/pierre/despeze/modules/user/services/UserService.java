package br.com.pierre.despeze.modules.user.services;

import br.com.pierre.despeze.modules.user.entities.User;
import br.com.pierre.despeze.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(User user){

        userRepository
                .findByEmail(user.getEmail())
                .ifPresent(
                        (userExists) -> {
                            throw new RuntimeException("E-mail já cadastrado");
                        });

        userRepository.save(user);
    }
}