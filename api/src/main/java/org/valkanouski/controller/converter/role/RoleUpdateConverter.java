package org.valkanouski.controller.converter.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.valkanouski.controller.requests.role.RoleUpdateRequest;
import org.valkanouski.domain.Role;
import org.valkanouski.exception.EntityNotFoundException;
import org.valkanouski.repository.RoleRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleUpdateConverter extends RoleBaseConverter<RoleUpdateRequest, Role> {

    private final RoleRepository repository;

    @Override
    public Role convert(RoleUpdateRequest request) {

        Optional<Role> role = repository.findById(request.getId());
        role.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(role.orElseThrow(EntityNotFoundException::new), request);
    }
}
