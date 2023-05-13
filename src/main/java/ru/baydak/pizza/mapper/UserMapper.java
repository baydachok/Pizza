package ru.baydak.pizza.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.baydak.pizza.domain.dto.UserRegisterDTO;
import ru.baydak.pizza.domain.model.User;

@Component
@Mapper(componentModel = "spring" )
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password",  expression = "java(passwordEncoder.encode(userRegisterDTO.getPassword()))")
    @Mapping(target = "role", constant = "ROLE_USER")
//    @Mapping(target = "products", ignore = true)
    public abstract User registerDTOToUser(UserRegisterDTO userRegisterDTO);

}