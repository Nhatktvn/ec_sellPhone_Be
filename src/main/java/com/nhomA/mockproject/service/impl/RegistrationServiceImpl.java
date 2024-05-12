package com.nhomA.mockproject.service.impl;
import com.nhomA.mockproject.dto.RegistrationDTO;
import com.nhomA.mockproject.entity.Cart;
import com.nhomA.mockproject.entity.Identification;
import com.nhomA.mockproject.entity.Role;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.RoleNotFoundException;
import com.nhomA.mockproject.exception.UserNameExistedException;
import com.nhomA.mockproject.repository.RoleRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.ActiveAccountService;
import com.nhomA.mockproject.service.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ActiveAccountService activeAccountService;
    public RegistrationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ActiveAccountService activeAccountService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.activeAccountService = activeAccountService;
    }
    @Transactional
    @Override
    public void registration(RegistrationDTO registrationDTO) {
        //check if username register before -> throw Exception
        Optional<User> user = userRepository.findByUsername(registrationDTO.getUsername());
        if (user.isPresent()) {
            throw new UserNameExistedException("Username existed!");
        }
        //check if role existed in database -> throw Exception
        Optional<Role> role = roleRepository.findByName("USER");
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found");
        }
        //Map DTO -> Entity
        Cart cart = new Cart();
        cart.setCreatedDate(LocalDate.now());
        Identification identification = new Identification();
        identification.setFullName("");
        identification.setEmail("");
        identification.setPhone("");

        User mappedUser = new User();
        mappedUser.setStatus(false);
        mappedUser.setIdentification(identification);
        mappedUser.setUsername(registrationDTO.getUsername());
        mappedUser.setRoles(Collections.singleton(role.get()));
        mappedUser.setCart(cart);
        mappedUser.setCreatedDate(ZonedDateTime.now());
        cart.setUser(mappedUser);
        //Encode password using bcrypt
        mappedUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        //Save into database
        userRepository.save(mappedUser);
//        activeAccountService.sendCodeActiveToEmail(registrationDTO.getUsername());
    }
}
