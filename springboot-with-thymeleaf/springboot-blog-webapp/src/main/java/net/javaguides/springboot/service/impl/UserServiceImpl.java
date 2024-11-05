package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.dto.RegistrationDto;
import net.javaguides.springboot.entity.Role;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());

        Role role = roleRepository.findByName("GUEST");
        user.setRoles(Arrays.asList(role));

        // TODO: use spring security to encrypt the password
        user.setPassword(registrationDto.getPassword());

        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
