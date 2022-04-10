package com.tietoevry.backend.user;

import com.tietoevry.backend.user.model.UserCreationDto;
import com.tietoevry.backend.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(path = "/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserDto(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by id " + id));
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserCreationDto user) {
        return userService.createUser(user);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping
    public UserDto updateUser(@Valid @RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
