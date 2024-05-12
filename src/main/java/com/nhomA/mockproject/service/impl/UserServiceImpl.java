package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.UpdateUserRequestDTO;
import com.nhomA.mockproject.dto.UserDTO;
import com.nhomA.mockproject.entity.Identification;
import com.nhomA.mockproject.entity.PasswordReset;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.CodeForgotIncorrectException;
import com.nhomA.mockproject.exception.PasswordIncorrectException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.mapper.UserMapper;
import com.nhomA.mockproject.repository.IdentificationRepository;
import com.nhomA.mockproject.repository.PasswordResetRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.SendEmailService;
import com.nhomA.mockproject.service.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final IdentificationRepository identificationRepository;
    private final SendEmailService sendEmailService;

    public UserServiceImpl(UserRepository userRepository, PasswordResetRepository passwordResetRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, IdentificationRepository identificationRepository, JavaMailSender javaMailSender, SendEmailService sendEmailService) {
        this.userRepository = userRepository;
        this.passwordResetRepository = passwordResetRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.identificationRepository = identificationRepository;
        this.sendEmailService = sendEmailService;
    }

    @Transactional
    @Override
    public boolean changePassword(String username, String checkPassword, String newPassword) {
        Optional<User> emptyUser = userRepository.findByUsername(username);
        User user = emptyUser.get();
        boolean check = passwordEncoder.matches(checkPassword,user.getPassword());
        if(check == false){
            throw new PasswordIncorrectException("Password incorrect!");
        }
        String encodeNewPass = passwordEncoder.encode(newPassword);
        user.setPassword(encodeNewPass);
        userRepository.save(user);
        return true;
    }
    @Transactional
    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = userMapper.toDTOs(users);
        return userDTOS;
    }
    @Transactional
    @Override
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }
    @Transactional
    @Override
    public boolean resetPassword(Long id, String newPassword) {
        Optional<User> emptyUser = userRepository.findById(id);
        if(emptyUser.isEmpty())
        {
            throw new UserNotFoundException("User not found!");
        }
        User user = emptyUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }
    @Transactional
    @Override
    public UserDTO updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
        Optional<User> existedUser = userRepository.findById(id);
        if(existedUser.isEmpty()){
            throw  new UserNotFoundException("User not found!");
        }
        User user = existedUser.get();
        user.setPassword(updateUserRequestDTO.getPassword());
        user.setUsername(updateUserRequestDTO.getUsername());
        Identification identification = user.getIdentification();
        identification.setFullName(updateUserRequestDTO.getFullName());
        identification.setEmail(updateUserRequestDTO.getEmail());
        identification.setPhone(updateUserRequestDTO.getPhone());
        user.setIdentification(identification);
        User saveUser = userRepository.save(user);
        return userMapper.toDTO(saveUser);
    }
    @Transactional
    @Override
    public String sendForgotPassword(String email) {
        Optional<User> existedUser = userRepository.findByUsername(email);
//        Optional<Identification> existedIdentification = identificationRepository.findByEmail(email);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        User user = existedUser.get();
        Random random = new Random();
        int codeReset = random.nextInt(900000) + 100000;
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setUser(user);
        passwordReset.setCodeReset((long) codeReset);
        passwordReset.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        user.setPasswordReset(passwordReset);
        userRepository.save(user);
        sendEmailService.sendEmail(email, "Password Reset", "Bạn đã yêu cầu lấy lại mật khẩu. Mã có hiệu lực trong 5 phút, vui lòng không chia sẻ với bất kì ai. Mã của bạn là: " + codeReset);
        return "send mail success";
    }

    @Override
    public boolean updatePassword(String codeReset, String email, String password) {
        Optional<User> existedUser = userRepository.findByUsername(email);
        User user = existedUser.get();
        if(user == null) {
            throw new UserNotFoundException("User not found!");
        }
        if(!codeReset.equals(user.getPasswordReset().getCodeReset().toString())){
            throw new CodeForgotIncorrectException("Invalid authentication code!");
        }
        if(user.getPasswordReset().getExpiryDate().isBefore(LocalDateTime.now())){
            throw new CodeForgotIncorrectException("The authentication code has expired!");
        }
        PasswordReset passwordReset = user.getPasswordReset();
        user.setPassword(passwordEncoder.encode(password));
        user.setPasswordReset(null);
        passwordResetRepository.delete(passwordReset);
        userRepository.save(user);
        return true;
    }
}
