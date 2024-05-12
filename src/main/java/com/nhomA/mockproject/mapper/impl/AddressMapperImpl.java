package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.AddressDTO;
import com.nhomA.mockproject.entity.Address;
import com.nhomA.mockproject.mapper.AddressMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDTO toDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setAddress(address.getAddress());
        return addressDTO;
    }

    @Override
    public List<AddressDTO> toDTOs(List<Address> addresses) {
        List<AddressDTO> addressDTOs = new ArrayList<>();
        for(Address a : addresses){
            addressDTOs.add(this.toDTO(a));
        }
        return addressDTOs;
    }

    @Override
    public Address toEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddress(addressDTO.getAddress());
        return address;
    }

    @Override
    public List<Address> toEntities(List<AddressDTO> addressDTOS) {
        List<Address> addresses = new ArrayList<>();
        for(AddressDTO a: addressDTOS){
            addresses.add(this.toEntity(a));
        }
        return addresses;
    }
}
