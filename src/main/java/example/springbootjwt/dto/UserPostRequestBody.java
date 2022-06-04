package example.springbootjwt.dto;

import example.springbootjwt.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostRequestBody {

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;

    @NotNull
    @Max(120)
    private int age;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;
}
