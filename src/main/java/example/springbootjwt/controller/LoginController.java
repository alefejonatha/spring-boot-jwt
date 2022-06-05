package example.springbootjwt.controller;

import example.springbootjwt.dto.LoginDTO;
import example.springbootjwt.dto.SessionDTO;
import example.springbootjwt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<SessionDTO> logar(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(loginService.login(loginDTO), HttpStatus.OK);
    }

}
