package com.tietoevry.backend.user;

import com.tietoevry.backend.account.AccountService;
import com.tietoevry.backend.session.SessionService;
import com.tietoevry.backend.user.model.Role;
import com.tietoevry.backend.user.model.User;
import com.tietoevry.backend.user.model.UserCreationDto;
import com.tietoevry.backend.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SessionService sessionService;
    private final AccountService accountService;

    public List<UserDto> getUsers() {
        return userRepository
            .findAll()
            .stream()
            .map(UserMapper::toUserDto)
            .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserDto(long id) {
        return userRepository
            .findById(id)
            .map(UserMapper::toUserDto);
    }

    public User getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no such user with id " + id));
        return user.get();
    }

    public Optional<User> getLoggedInUser() {
        if(sessionService.getSession().getUser() == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not logged in ");

        Optional<User> user = userRepository.findByEmail(sessionService.getSession().getUser().getEmail());

        return user;
    }

    public UserDto createUser(UserCreationDto user) {
        return saveUser(user);
    }

    public UserDto updateUser(UserDto user) {
        return saveUser(user);
    }

    private UserDto saveUser(UserCreationDto user) {
        Optional<List<Role>> roleList = roleRepository.findAllByName("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = UserMapper.toUser(user, roleList);
        savedUser = userRepository.save(savedUser);
        accountService.createFirstAccountForUser(savedUser);
        return UserMapper.toUserDto(savedUser);
    }

    private UserDto saveUser(UserDto user) {
        Optional<List<Role>> roleList = roleRepository.findAllByName("USER");
        User savedUser = UserMapper.toUser(user, roleList);
        userRepository.save(savedUser);
        return UserMapper.toUserDto(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
