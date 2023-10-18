package ru.baydak.pizza.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.baydak.pizza.domain.dto.UserRegisterDTO;
import ru.baydak.pizza.domain.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-19T01:11:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User registerDTOToUser(UserRegisterDTO userRegisterDTO) {
        if ( userRegisterDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userRegisterDTO.getUsername() );

        user.setPassword( passwordEncoder.encode(userRegisterDTO.getPassword()) );
        user.setRole( "ROLE_USER" );

        return user;
    }
}
