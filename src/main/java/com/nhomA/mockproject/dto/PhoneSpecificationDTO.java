package com.nhomA.mockproject.dto;

public class PhoneSpecificationDTO extends SpecificationResponseDTO{
    private String urlImageSpec;
    private double sizeScreen;
    private String screenTechnology;
    private String cameraRear;
    private String cameraFront;
    private String chipset;
    private String ram;
    private String rom;
    private int battery;
    private String screenResolution;
    private String operaSystem;
    public PhoneSpecificationDTO(){}


    public PhoneSpecificationDTO(String urlImageSpec, double sizeScreen, String screenTechnology, String cameraRear, String cameraFront, String chipset, String ram, String rom, int battery, String screenResolution, String operaSystem) {
        this.urlImageSpec = urlImageSpec;
        this.sizeScreen = sizeScreen;
        this.screenTechnology = screenTechnology;
        this.cameraRear = cameraRear;
        this.cameraFront = cameraFront;
        this.chipset = chipset;
        this.ram = ram;
        this.rom = rom;
        this.battery = battery;
        this.screenResolution = screenResolution;
        this.operaSystem = operaSystem;
    }

    public String getUrlImageSpec() {
        return urlImageSpec;
    }

    public void setUrlImageSpec(String urlImageSpec) {
        this.urlImageSpec = urlImageSpec;
    }

    public double getSizeScreen() {
        return sizeScreen;
    }

    public void setSizeScreen(double sizeScreen) {
        this.sizeScreen = sizeScreen;
    }

    public String getScreenTechnology() {
        return screenTechnology;
    }

    public void setScreenTechnology(String screenTechnology) {
        this.screenTechnology = screenTechnology;
    }

    public String getCameraRear() {
        return cameraRear;
    }

    public void setCameraRear(String cameraRear) {
        this.cameraRear = cameraRear;
    }

    public String getCameraFront() {
        return cameraFront;
    }

    public void setCameraFront(String cameraFront) {
        this.cameraFront = cameraFront;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
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

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getOperaSystem() {
        return operaSystem;
    }

    public void setOperaSystem(String operaSystem) {
        this.operaSystem = operaSystem;
    }
}
