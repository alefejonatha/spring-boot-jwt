package example.springbootjwt.controller;

import example.springbootjwt.dto.UserPostRequestBody;
import example.springbootjwt.entity.User;
import example.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String welcome() {
        return "Bem-vindo a API Spring Boot JWT";
    }

    @GetMapping(path = "/user")
    public String users() {
        return "Usuário autorizado";
    }

    @GetMapping(path = "/admin")
    public String admin() {
        return "Administrador autorizado";
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserPostRequestBody userPostRequestBody) { //TODO RETORNAR USUÁRIO CUSTOMIZADO
        userService.save(userPostRequestBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
