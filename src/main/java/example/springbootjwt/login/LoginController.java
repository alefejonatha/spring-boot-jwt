package example.springbootjwt.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


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
    public ResponseEntity<SessionDTO> logar(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(loginService.login(loginDTO), HttpStatus.OK);
    }

}
