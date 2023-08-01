package org.valkanouski.controller.converter.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.valkanouski.controller.requests.role.RoleCreateRequest;
import org.valkanouski.domain.Role;
import org.valkanouski.domain.User;
import org.valkanouski.exception.EntityNotFoundException;
import org.valkanouski.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleCreateConverter extends RoleBaseConverter<RoleCreateRequest, Role> {

    private final UserRepository usersRepository;

    @Override
    public Role convert(RoleCreateRequest request) {

        Role role = new Role();

        role.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        role.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        Optional<User> user = usersRepository.findById(request.getUserId());
        role.setUser(user.orElseThrow(EntityNotFoundException::new));

        return doConvert(role, request);
    }
}
