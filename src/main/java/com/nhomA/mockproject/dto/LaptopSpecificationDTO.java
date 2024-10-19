package com.nhomA.mockproject.dto;

import jakarta.persistence.Column;

public class LaptopSpecificationDTO extends SpecificationResponseDTO{
    private String screen;
    private String cpu;
    private String gpu;
    private String ram;
    private String rom;
    private String battery;
    private String operaSystem;
    private String weight;
    private String ports;
    private String additionalFeatures;

    public LaptopSpecificationDTO() {
    }

    public LaptopSpecificationDTO(String screen, String cpu, String gpu, String ram, String rom, String battery, String operaSystem, String weight, String ports, String additionalFeatures) {
        this.screen = screen;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.rom = rom;
        this.battery = battery;
        this.operaSystem = operaSystem;
        this.weight = weight;
        this.ports = ports;
        this.additionalFeatures = additionalFeatures;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getOperaSystem() {
        return operaSystem;
    }

    public void setOperaSystem(String operaSystem) {
        this.operaSystem = operaSystem;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public String getAdditionalFeatures() {
        return additionalFeatures;
    }

    public void setAdditionalFeatures(String additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }
}
