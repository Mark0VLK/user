package org.valkanouski.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.valkanouski.domain.User;
import org.valkanouski.repository.UserRepository;
import org.valkanouski.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> findAllByOrderByEmailAsc() {
        return repository.findAllByOrderByEmailAsc();
    }

    @Override
    public List<User> findAllByOrderByEmailAsc(Pageable pageable) {
        return repository.findAllByOrderByEmailAsc(pageable);
    }
}
