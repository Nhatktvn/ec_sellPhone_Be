package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.AddressDTO;
import com.nhomA.mockproject.entity.Address;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.AddressNotFoundException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.mapper.AddressMapper;
import com.nhomA.mockproject.repository.AddressRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(UserRepository userRepository, AddressMapper addressMapper, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }
    @Transactional
    @Override
    public List<AddressDTO> getAllAddress(String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("Address not found!");
        }
        List<Address> addresses = existedUser.get().getAddresses();
        return addressMapper.toDTOs(addresses);
    }
    @Transactional
    @Override
    public AddressDTO createAddress(String username, AddressDTO addressDTO) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("Address not found!");
        }
        User user = existedUser.get();
        Address address = new Address();
        address.setAddress(addressDTO.getAddress());
        user.getAddresses().add(address);
        address.setUser(user);
        User saveUser = userRepository.save(user);
        return addressMapper.toDTO(address);
    }
    @Transactional
    @Override
    public AddressDTO updateAddressById(String username, Long id, AddressDTO addressDTO) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("Address not found!");
        }
        Long userId = existedUser.get().getId();
        Optional<Address> existedAddress = addressRepository.findByIdAndUserId(id,userId);
        if(existedAddress.isEmpty()){
            throw new AddressNotFoundException("Address not found!");
        }
        Address address = existedAddress.get();
        address.setAddress(addressDTO.getAddress());
        Address saveAddress = addressRepository.save(address);
        return addressMapper.toDTO(address);
    }
    @Transactional
    @Override
    public boolean deleteAddressById(String username, Long id) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("Address not found!");
        }
        Long userId = existedUser.get().getId();
        Optional<Address> existedAddress = addressRepository.findByIdAndUserId(id,userId);
        if(existedAddress.isEmpty()){
            throw new AddressNotFoundException("Address not found!");
        }
        addressRepository.deleteById(id);
        return true;
    }
}
