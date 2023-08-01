package org.valkanouski.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
import org.valkanouski.controller.requests.role.RoleCreateRequest;
import org.valkanouski.controller.requests.role.RoleUpdateRequest;
import org.valkanouski.domain.Role;
import org.valkanouski.repository.RoleRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable Long id) {

        Optional<Role> role = roleRepository.findById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<Object> saveRole(@Valid @RequestBody RoleCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Role role = conversionService.convert(request, Role.class);

        role = roleRepository.save(role);

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PutMapping
    public ResponseEntity<Object> updateRole(@Valid @RequestBody RoleUpdateRequest request) {

        Role role = conversionService.convert(request, Role.class);

        role = roleRepository.save(role);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {

        Optional<Role> role = roleRepository.findById(id);

        roleRepository.deleteById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
