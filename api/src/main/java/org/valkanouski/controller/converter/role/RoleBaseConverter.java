package org.valkanouski.controller.converter.role;

import org.springframework.core.convert.converter.Converter;
import org.valkanouski.controller.requests.role.RoleCreateRequest;
import org.valkanouski.domain.Role;

public abstract class RoleBaseConverter<S, T> implements Converter<S, T> {

    public Role doConvert(Role roleForUpdate, RoleCreateRequest request) {

        roleForUpdate.setRoleName(request.getRoleName());

        return roleForUpdate;
    }
}
