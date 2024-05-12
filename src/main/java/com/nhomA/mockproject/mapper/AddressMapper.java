package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.AddressDTO;
import com.nhomA.mockproject.entity.Address;

import java.util.List;

public interface AddressMapper {
    AddressDTO toDTO (Address address);

    List<AddressDTO> toDTOs (List<Address> addresses);
    Address toEntity(AddressDTO addressDTO);
    List<Address> toEntities (List<AddressDTO> addressDTOS);
}
