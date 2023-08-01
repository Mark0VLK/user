package org.valkanouski.controller.converter.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.valkanouski.controller.requests.user.UserCreateRequest;
import org.valkanouski.domain.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, User> {

    @Override
    public User convert(UserCreateRequest request) {

        User user = new User();

        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(user, request);
    }
}
