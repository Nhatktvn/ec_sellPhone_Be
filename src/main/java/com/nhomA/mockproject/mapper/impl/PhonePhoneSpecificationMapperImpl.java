package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.PhoneSpecificationDTO;
import com.nhomA.mockproject.entity.PhoneSpecification;
import com.nhomA.mockproject.mapper.PhoneSpecificationMapper;
import org.springframework.stereotype.Component;

@Component
public class PhonePhoneSpecificationMapperImpl implements PhoneSpecificationMapper {
    @Override
    public PhoneSpecification toEntity(PhoneSpecificationDTO phoneSpecificationDTO) {
        PhoneSpecification phoneSpecification = new PhoneSpecification();
        phoneSpecification.setSizeScreen(phoneSpecificationDTO.getSizeScreen());
        phoneSpecification.setScreenTechnology(phoneSpecificationDTO.getScreenTechnology());
        phoneSpecification.setCameraRear(phoneSpecificationDTO.getCameraRear());
        phoneSpecification.setCameraFront(phoneSpecificationDTO.getCameraFront());
        phoneSpecification.setChipset(phoneSpecificationDTO.getChipset());
        phoneSpecification.setRam(phoneSpecificationDTO.getRam());
        phoneSpecification.setRom(phoneSpecificationDTO.getRom());
        phoneSpecification.setBattery(phoneSpecificationDTO.getBattery());
        phoneSpecification.setScreenResolution(phoneSpecificationDTO.getScreenResolution());
        phoneSpecification.setOperaSystem(phoneSpecificationDTO.getOperaSystem());
        return phoneSpecification;
    }

    @Override
    public PhoneSpecificationDTO toDTO(PhoneSpecification phoneSpecification) {
        PhoneSpecificationDTO phoneSpecificationDTO = new PhoneSpecificationDTO();
        phoneSpecificationDTO.setSizeScreen(phoneSpecification.getSizeScreen());
        phoneSpecificationDTO.setScreenTechnology(phoneSpecification.getScreenTechnology());
        phoneSpecificationDTO.setCameraRear(phoneSpecification.getCameraRear());
        phoneSpecificationDTO.setCameraFront(phoneSpecification.getCameraFront());
        phoneSpecificationDTO.setChipset(phoneSpecification.getChipset());
        phoneSpecificationDTO.setRam(phoneSpecification.getRam());
        phoneSpecificationDTO.setRom(phoneSpecification.getRom());
        phoneSpecificationDTO.setBattery(phoneSpecification.getBattery());
        phoneSpecificationDTO.setScreenResolution(phoneSpecification.getScreenResolution());
        phoneSpecificationDTO.setOperaSystem(phoneSpecification.getOperaSystem());
        return phoneSpecificationDTO;
    }
}
