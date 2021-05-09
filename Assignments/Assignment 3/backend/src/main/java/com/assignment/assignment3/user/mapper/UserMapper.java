package com.assignment.assignment3.user.mapper;

import com.assignment.assignment3.user.dto.UserDTO;
import com.assignment.assignment3.user.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(
            target = "role",
            source = "role.name"
    )
    UserDTO userToUserDTO(User user);

    @InheritInverseConfiguration
    User userDTOToUser(UserDTO userDTO);
}
