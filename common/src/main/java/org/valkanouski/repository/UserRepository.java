package org.valkanouski.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.valkanouski.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByEmailAsc();
    List<User> findAllByOrderByEmailAsc(Pageable pageable);
}
