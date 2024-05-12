package com.nhomA.mockproject.service;

import com.nhomA.mockproject.entity.*;
import com.nhomA.mockproject.repository.RoleRepository;
import com.nhomA.mockproject.repository.StatusOrderRepository;
import com.nhomA.mockproject.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    private final RoleRepository roleRepository;
    private final StatusOrderRepository statusOrderRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataService(RoleRepository roleRepository, StatusOrderRepository statusOrderRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.statusOrderRepository = statusOrderRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Optional<Role> existedRoleUser = roleRepository.findById(1L);
        if(existedRoleUser.isEmpty()){
            Role roleUser = new Role();
            roleUser.setName("USER");
            roleUser.setDescription("text");
            roleRepository.save(roleUser);
        }
        Optional<Role> existedRoleAdmin = roleRepository.findById(2L);
        if(existedRoleAdmin.isEmpty()){
            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            roleAdmin.setDescription("text");
            roleRepository.save(roleAdmin);

        }

        List<StatusOrder> existedStatusOrder = statusOrderRepository.findAll();
        if(existedStatusOrder.isEmpty()){
            StatusOrder statusOrder1 = new StatusOrder();
            statusOrder1.setName("Đang xác nhận");
            statusOrderRepository.save(statusOrder1);

            StatusOrder statusOrder2 = new StatusOrder();
            statusOrder2.setName("Đang tiến hàng đóng gói");
            statusOrderRepository.save(statusOrder2);

            StatusOrder statusOrder3 = new StatusOrder();
            statusOrder3.setName("Đang chờ vận chuyển");
            statusOrderRepository.save(statusOrder3);

            StatusOrder statusOrder4 = new StatusOrder();
            statusOrder4.setName("Đang vận chuyển");
            statusOrderRepository.save(statusOrder4);

            StatusOrder statusOrder5 = new StatusOrder();
            statusOrder5.setName("Đã nhận được hàng");
            statusOrderRepository.save(statusOrder5);

            StatusOrder statusOrder6 = new StatusOrder();
            statusOrder6.setName("Đã hủy");
            statusOrderRepository.save(statusOrder6);
        }

        Optional<User> emptyUser = userRepository.findByUsername("admin@gmail.com");
        if(emptyUser.isEmpty()){
            Optional<Role> role = roleRepository.findByName("ADMIN");
            Cart cart = new Cart();
            cart.setCreatedDate(LocalDate.now());
            Identification identification = new Identification();
            identification.setFullName("Nguyen Minh Nhat");
            identification.setEmail("minhnhat24112001@gmail.com");
            identification.setPhone("0378025713");
            identification.setBirthDate(LocalDate.parse("2001-11-24"));

            User user = new User();
            user.setIdentification(identification);
            user.setUsername("admin@gmail.com");
            user.setRoles(Collections.singleton(role.get()));
            user.setCart(cart);
            user.setStatus(true);
            user.setActiveAccount(null);
            user.setCreatedDate(ZonedDateTime.now());
            cart.setUser(user);
            //Encode password using bcrypt
            user.setPassword(passwordEncoder.encode("admin123"));
            //Save into database
            userRepository.save(user);
        }

    }
}
