package example.springbootjwt.user.controller;

import example.springbootjwt.user.dto.UserPostRequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface UserControllerDocs {

    //TODO implementar as outras requisições
    @Operation(summary = "User creation operation", description = "add description",
            tags = {"user"}) //pode ocultar a descrição e as tags
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success user creation"),
            @ApiResponse(responseCode = "400", description = "When anime does not exist in the database")    })
    ResponseEntity<Void> createUser(UserPostRequestBody userPostRequestBody);

}
