package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.SpecificationDTO;
import com.nhomA.mockproject.entity.Specification;
import com.nhomA.mockproject.mapper.SpecificationMapper;
import org.springframework.stereotype.Component;

@Component
public class SpecificationMapperImpl implements SpecificationMapper {
    @Override
    public Specification toEntity(SpecificationDTO specificationDTO) {
        Specification specification = new Specification();
        specification.setSizeScreen(specificationDTO.getSizeScreen());
        specification.setScreenTechnology(specificationDTO.getScreenTechnology());
        specification.setCameraRear(specificationDTO.getCameraRear());
        specification.setCameraFront(specificationDTO.getCameraFront());
        specification.setChipset(specificationDTO.getChipset());
        specification.setRam(specificationDTO.getRam());
        specification.setRom(specificationDTO.getRom());
        specification.setBattery(specificationDTO.getBattery());
        specification.setScreenResolution(specificationDTO.getScreenResolution());
        specification.setOperaSystem(specificationDTO.getOperaSystem());
        return specification;
    }

    @Override
    public SpecificationDTO toDTO(Specification specification) {
        SpecificationDTO specificationDTO = new SpecificationDTO();
        specificationDTO.setSizeScreen(specification.getSizeScreen());
        specificationDTO.setScreenTechnology(specification.getScreenTechnology());
        specificationDTO.setCameraRear(specification.getCameraRear());
        specificationDTO.setCameraFront(specification.getCameraFront());
        specificationDTO.setChipset(specification.getChipset());
        specificationDTO.setRam(specification.getRam());
        specificationDTO.setRom(specification.getRom());
        specificationDTO.setBattery(specification.getBattery());
        specificationDTO.setScreenResolution(specification.getScreenResolution());
        specificationDTO.setOperaSystem(specification.getOperaSystem());
        return specificationDTO;
    }
}
