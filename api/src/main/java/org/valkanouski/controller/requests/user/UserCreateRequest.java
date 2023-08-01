package org.valkanouski.controller.requests.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class UserCreateRequest {

    @NotNull
    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String surname;

    @NotNull
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;

    @NotNull
    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String patronymic;

    @NotNull
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @NotNull
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
    private String password;
}
