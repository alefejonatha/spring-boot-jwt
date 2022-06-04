package example.springbootjwt.mapper;

import example.springbootjwt.dto.UserPostRequestBody;
import example.springbootjwt.dto.UserPutRequestBody;
import example.springbootjwt.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);

    public abstract User toUser(UserPutRequestBody userPutRequestBody);
}
