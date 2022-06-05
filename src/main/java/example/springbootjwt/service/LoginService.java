package example.springbootjwt.service;

import example.springbootjwt.dto.LoginDTO;
import example.springbootjwt.dto.SessionDTO;
import example.springbootjwt.entity.User;
import example.springbootjwt.repository.UserRepository;
import example.springbootjwt.security.JWTCreator;
import example.springbootjwt.security.JWTObject;
import example.springbootjwt.security.PasswordEncodingConfig;
import example.springbootjwt.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserRepository userRepository;

    private final PasswordEncodingConfig encoder;


    public SessionDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user != null) {
            boolean passwordOk = encoder.passwordEncoder().matches(loginDTO.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha ou Usuário inválido: " + loginDTO.getUsername()); //TODO RETIRAR ESSA MENSAGEM!!!!!
            }

            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessionDTO.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessionDTO;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}


