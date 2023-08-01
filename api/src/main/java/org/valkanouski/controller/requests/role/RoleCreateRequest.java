package org.valkanouski.controller.requests.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.valkanouski.domain.system.SystemRoles;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class RoleCreateRequest {
    @NotNull
    private SystemRoles roleName;

    @NotNull
    private Long userId;
}
