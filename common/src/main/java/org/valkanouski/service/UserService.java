package org.valkanouski.service;

import org.springframework.data.domain.Pageable;
import org.valkanouski.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAllByOrderByEmailAsc();

    List<User> findAllByOrderByEmailAsc(Pageable pageable);
}
