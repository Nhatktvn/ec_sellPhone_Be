package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAllAddress (String username);
    AddressDTO createAddress (String username, AddressDTO addressDTO);
    AddressDTO updateAddressById (String username, Long id, AddressDTO addressDTO);
    boolean deleteAddressById (String username,Long id);
}
