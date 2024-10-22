package br.com.pierre.despeze.modules.user.services;

import br.com.pierre.despeze.modules.user.dto.UserAuthDTO;
import br.com.pierre.despeze.modules.user.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class UserAuthService {

    @Value("${security.token.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getAuthToken(UserAuthDTO userAuthDTO) throws AuthenticationException {

        var user = this.userRepository.findByEmail(userAuthDTO.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado"));

        var passwordMatches = this.passwordEncoder.matches(userAuthDTO.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("Senha/e-mail incorreto");
        }

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create().withIssuer("despeze")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getId().toString())
                .sign(algorithm);
    }
}
