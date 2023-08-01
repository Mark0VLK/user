package org.valkanouski.controller.converter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.valkanouski.controller.requests.user.UserUpdateRequest;
import org.valkanouski.domain.User;
import org.valkanouski.exception.EntityNotFoundException;
import org.valkanouski.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter extends UserBaseConverter<UserUpdateRequest, User> {

    private final UserRepository repository;

    @Override
    public User convert(UserUpdateRequest request) {

        Optional<User> user = repository.findById(request.getId());
        user.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(user.orElseThrow(EntityNotFoundException::new), request);
    }
}
