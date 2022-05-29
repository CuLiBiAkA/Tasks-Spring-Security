package web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import web.dto.UserDto;
import web.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto dto);

    UserDto toDto(User entity);
}
