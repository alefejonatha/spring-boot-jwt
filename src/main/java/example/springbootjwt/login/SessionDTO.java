package example.springbootjwt.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDTO {
    private String login;
    private String token;
}
