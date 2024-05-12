package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.IdentificationDTO;
import com.nhomA.mockproject.entity.Identification;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.mapper.IdentificationMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IdentificationMapperImpl implements IdentificationMapper {

    @Override
    public Identification toEntity(IdentificationDTO identificationDTO) {
        Identification identification = new Identification();
        identification.setFullName(identificationDTO.getFullName());
        identification.setBirthDate(identificationDTO.getBirthDate());
        identification.setPhone(identificationDTO.getPhone());
        identification.setEmail(identificationDTO.getEmail());
        identification.setUrlAvatar(identificationDTO.getUrlAvatar());
        return identification;
    }

    @Override
    public IdentificationDTO toDTO(Identification identification) {
        IdentificationDTO identificationDTO = new IdentificationDTO();
        identificationDTO.setFullName(identification.getFullName());
        identificationDTO.setBirthDate(identification.getBirthDate());
        identificationDTO.setPhone(identification.getPhone());
        identificationDTO.setEmail(identification.getEmail());
        identificationDTO.setUrlAvatar(identification.getUrlAvatar());
        return identificationDTO;
    }

    @Override
    public List<IdentificationDTO> toDTOs(List<Identification> identifications) {
        List<IdentificationDTO> identificationDTOS = new ArrayList<>();
        for(Identification id : identifications){
            identificationDTOS.add(this.toDTO(id));
        }
        return identificationDTOS;
    }
}
