package org.valkanouski.controller.converter.user;


import org.springframework.core.convert.converter.Converter;
import org.valkanouski.controller.requests.user.UserCreateRequest;
import org.valkanouski.domain.AuthenticationInfo;
import org.valkanouski.domain.User;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public User doConvert(User userForUpdate, UserCreateRequest request) {

        userForUpdate.setSurname(request.getSurname());
        userForUpdate.setName(request.getName());
        userForUpdate.setPatronymic(request.getPatronymic());
        userForUpdate.setEmail(request.getEmail());

        AuthenticationInfo info = new AuthenticationInfo(request.getPassword());
        userForUpdate.setAuthenticationInfo(info);

        return userForUpdate;
    }
}
