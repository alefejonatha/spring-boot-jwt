package example.springbootjwt.service;

import example.springbootjwt.dto.UserPostRequestBody;
import example.springbootjwt.entity.User;
import example.springbootjwt.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import example.springbootjwt.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    //    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Transactional
    public User save(UserPostRequestBody userPostRequestBody) {
        verifyIfExists(userPostRequestBody.getEmail(), userPostRequestBody.getUsername());
//        userPostRequestBody.setPassword(passwordEncoder.encode(userPostRequestBody.getPassword()));

        return userRepository.save(UserMapper.INSTANCE.toUser(userPostRequestBody));
    }

    private void verifyIfExists(String email, String username) {
        Optional<User> foundUser = userRepository.findByEmailOrUsername(email, username);
        if (foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists");
        }
    }
}
