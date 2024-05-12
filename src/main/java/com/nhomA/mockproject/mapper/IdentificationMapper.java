package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.IdentificationDTO;
import com.nhomA.mockproject.entity.Identification;
import com.nhomA.mockproject.entity.User;

import java.util.List;

public interface IdentificationMapper {
    Identification toEntity (IdentificationDTO identificationDTO);
    IdentificationDTO toDTO (Identification identification);
    List<IdentificationDTO> toDTOs (List<Identification> identifications);
}
