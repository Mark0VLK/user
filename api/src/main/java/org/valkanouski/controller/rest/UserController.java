package org.valkanouski.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.valkanouski.controller.exceptions.IllegalRequestException;
import org.valkanouski.controller.requests.user.UserCreateRequest;
import org.valkanouski.controller.requests.user.UserUpdateRequest;
import org.valkanouski.domain.User;
import org.valkanouski.repository.UserRepository;
import org.valkanouski.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final ConversionService conversionService;


    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.findAllByOrderByEmailAsc();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                userService.findAllByOrderByEmailAsc(PageRequest.of(page, 10))), HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        User user = conversionService.convert(request, User.class);

        user = userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PutMapping
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserUpdateRequest request) {

        User user = conversionService.convert(request, User.class);

        user = userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);

        userRepository.deleteById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
