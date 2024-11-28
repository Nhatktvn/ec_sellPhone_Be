package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.IdentificationDTO;
import com.nhomA.mockproject.entity.Identification;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.mapper.IdentificationMapper;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.IdentificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IdentificationServiceImpl implements IdentificationService {
    private final UserRepository userRepository;
    private final IdentificationMapper identificationMapper ;
    public IdentificationServiceImpl(UserRepository userRepository, IdentificationMapper identificationMapper) {
        this.userRepository = userRepository;
        this.identificationMapper = identificationMapper;
    }
    @Transactional
    @Override
    public IdentificationDTO getIdentification(String username) {
        Optional<User> emptyUser  = userRepository.findByUsername(username);
        User user = emptyUser.get();
        Identification identification = user.getIdentification();
        identification.setEmail(user.getUsername());
        IdentificationDTO identificationDTO = identificationMapper.toDTO(identification);
        identificationDTO.setId(user.getId());
        return identificationDTO;
    }
    @Transactional
    @Override
    public IdentificationDTO updateIdentification(String username, IdentificationDTO identificationDTO) {
        Optional<User> emptyUser  = userRepository.findByUsername(username);
        User user = emptyUser.get();
        Identification identification = user.getIdentification();
        identification.setFullName(identificationDTO.getFullName());
        identification.setBirthDate(identificationDTO.getBirthDate());
        identification.setPhone(identificationDTO.getPhone());
        identification.setEmail(identificationDTO.getEmail());
        if(identificationDTO.getUrlAvatar() != ""){
            identification.setUrlAvatar(identificationDTO.getUrlAvatar());
        }
        user.setIdentification(identification);
        userRepository.save(user);
        return identificationDTO;
    }
}
