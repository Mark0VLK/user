package org.valkanouski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.valkanouski.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
