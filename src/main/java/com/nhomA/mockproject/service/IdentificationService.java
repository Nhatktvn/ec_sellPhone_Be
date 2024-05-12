package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.IdentificationDTO;

public interface IdentificationService {
    IdentificationDTO getIdentification (String username);
    IdentificationDTO updateIdentification (String username, IdentificationDTO identificationDTO);
}
