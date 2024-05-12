package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.ActiveAccountRequestDTO;
import com.nhomA.mockproject.entity.ActiveAccount;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.CodeActiveIncorrectException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.repository.ActiveAccountRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.ActiveAccountService;
import com.nhomA.mockproject.service.SendEmailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class ActiveAccountServiceImpl implements ActiveAccountService {
    private final SendEmailService sendEmailService;
    private final UserRepository userRepository;
    private final ActiveAccountRepository activeAccountRepository;

    public ActiveAccountServiceImpl(SendEmailService sendEmailService, UserRepository userRepository, ActiveAccountRepository activeAccountRepository) {
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
        this.activeAccountRepository = activeAccountRepository;
    }

    @Transactional
    @Override
    public boolean sendCodeActiveToEmail(String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if (existedUser.isEmpty()){
            throw new UserNotFoundException("Account does not exist!");
        }
        Random random = new Random();
        int codeReset = random.nextInt(900000) + 100000;
        ActiveAccount activeAccount = new ActiveAccount();
        activeAccount.setCodeActive(String.valueOf(codeReset));
        User user = existedUser.get();
        user.setActiveAccount(activeAccount);
        userRepository.save(user);
        sendEmailService.sendEmail(username, "Active account", "Mã kích hoạt tài khoản của bạn là: " + codeReset + ". Mã có thời hạn trong 5 phút");
        return true;
    }

    @Transactional
    @Override
    public boolean activeAccount(ActiveAccountRequestDTO activeAccountRequestDTO) {
        Optional<User> existedUser = userRepository.findByUsername(activeAccountRequestDTO.getEmail());
        if (existedUser.isEmpty()){
            throw new UserNotFoundException("Account does not exist!");
        }
        User user = existedUser.get();
        ActiveAccount activeAccount = user.getActiveAccount();
        if (!activeAccountRequestDTO.getCodeActive().equals(activeAccount.getCodeActive())){
            throw new CodeActiveIncorrectException("Code active incorrect!");
        }
        user.setStatus(true);
        user.setActiveAccount(null);
        userRepository.save(user);
        activeAccountRepository.delete(activeAccount);
        return true;
    }
}
