package example.springbootjwt.user.service;

import example.springbootjwt.user.dto.UserPostRequestBody;
import example.springbootjwt.user.dto.UserPutRequestBody;
import example.springbootjwt.user.entity.User;
import example.springbootjwt.user.mapper.UserMapper;
import example.springbootjwt.user.repository.UserRepository;
import example.springbootjwt.security.PasswordEncodingConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncodingConfig encoder;

    @Transactional
    public User save(UserPostRequestBody userPostRequestBody) {
        verifyIfExists(userPostRequestBody.getEmail(), userPostRequestBody.getUsername());

        userPostRequestBody.setPassword(encoder.passwordEncoder().encode(userPostRequestBody.getPassword()));

        return userRepository.save(UserMapper.INSTANCE.toUser(userPostRequestBody));
    }

    public void replace(UserPutRequestBody userPutRequestBody) {
        User savedUser = findById(userPutRequestBody.getId());

        userPutRequestBody.setId(savedUser.getId());
        User userUpdate = UserMapper.INSTANCE.toUser(userPutRequestBody);
        userUpdate.setPassword(encoder.passwordEncoder().encode(userPutRequestBody.getPassword()));
        userRepository.save(userUpdate);
    }

    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void verifyIfExists(String email, String username) {
        Optional<User> foundUser = userRepository.findByEmailOrUsername(email, username);
        if (foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists");
        }
    }
}
