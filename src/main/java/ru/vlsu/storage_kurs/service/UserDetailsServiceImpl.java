package ru.vlsu.storage_kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vlsu.storage_kurs.config.UserDetailsImpl;
import ru.vlsu.storage_kurs.entity.Role;
import ru.vlsu.storage_kurs.entity.User;
import ru.vlsu.storage_kurs.repo.RoleRepository;
import ru.vlsu.storage_kurs.repo.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserDetailsImpl(user);
    }

    public void registerUser(User user) {
        // Шифруем пароль перед сохранением в базу данных
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        checkAndAddRole("USER");
        checkAndAddRole("ADMIN");
        // Проверяем наличие ролей в базе данных и добавляем их, если не существуют
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    private void checkAndAddRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}