package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.LaptopSpecificationDTO;
import com.nhomA.mockproject.entity.LaptopSpecification;
import org.springframework.stereotype.Component;
import com.nhomA.mockproject.mapper.LaptopSpecificationMapper;
@Component
public class LaptopSpecificationMapperImpl implements LaptopSpecificationMapper{
    @Override
    public LaptopSpecification toEntity(LaptopSpecificationDTO laptopSpecificationDTO) {
        LaptopSpecification laptopSpecification = new LaptopSpecification();
        laptopSpecification.setScreen(laptopSpecificationDTO.getScreen());
        laptopSpecification.setBattery(laptopSpecificationDTO.getBattery());
        laptopSpecification.setCpu(laptopSpecificationDTO.getCpu());
        laptopSpecification.setGpu(laptopSpecificationDTO.getGpu());
        laptopSpecification.setRam(laptopSpecificationDTO.getRam());
        laptopSpecification.setRom(laptopSpecificationDTO.getRom());
        laptopSpecification.setOperaSystem(laptopSpecificationDTO.getOperaSystem());
        laptopSpecification.setWeight(laptopSpecificationDTO.getWeight());
        laptopSpecification.setPorts(laptopSpecificationDTO.getPorts());
        laptopSpecification.setAdditionalFeatures(laptopSpecificationDTO.getAdditionalFeatures());
        return laptopSpecification;
    }

    @Override
    public LaptopSpecificationDTO toDTO(LaptopSpecification laptopSpecification) {
        LaptopSpecificationDTO laptopSpecificationDTO = new LaptopSpecificationDTO();
        laptopSpecificationDTO.setScreen(laptopSpecification.getScreen());
        laptopSpecificationDTO.setBattery(laptopSpecification.getBattery());
        laptopSpecificationDTO.setCpu(laptopSpecification.getCpu());
        laptopSpecificationDTO.setGpu(laptopSpecification.getGpu());
        laptopSpecificationDTO.setRam(laptopSpecification.getRam());
        laptopSpecificationDTO.setRom(laptopSpecification.getRom());
        laptopSpecificationDTO.setOperaSystem(laptopSpecification.getOperaSystem());
        laptopSpecificationDTO.setWeight(laptopSpecification.getWeight());
        laptopSpecificationDTO.setPorts(laptopSpecification.getPorts());
        laptopSpecificationDTO.setAdditionalFeatures(laptopSpecification.getAdditionalFeatures());
        return laptopSpecificationDTO;
    }
}
