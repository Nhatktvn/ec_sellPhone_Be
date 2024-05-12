package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "specification")
public class Specification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "size_screen")
    private double sizeScreen;
    @Column(name = "screen_technology")
    private String screenTechnology;
    @Column(name = "camera_rear")
    private String cameraRear;
    @Column(name = "camera_front")
    private String cameraFront;
    @Column(name = "chipset")
    private String chipset;
    @Column(name = "ram")
    private String ram;
    @Column(name = "rom")
    private String rom;
    @Column(name = "battery")
    private int battery;
    @Column(name = "screen_resolution")
    private String screenResolution;
    @Column(name = "opera_system")
    private String operaSystem;

    @OneToOne(mappedBy = "specification", cascade = CascadeType.ALL)
    private Product product;

    public Specification(Long id, double sizeScreen, String screenTechnology, String cameraRear, String cameraFront, String chipset, String ram, String rom, String operaSystem) {
        this.id = id;
        this.sizeScreen = sizeScreen;
        this.screenTechnology = screenTechnology;
        this.cameraRear = cameraRear;
        this.cameraFront = cameraFront;
        this.chipset = chipset;
        this.ram = ram;
        this.rom = rom;
        this.operaSystem = operaSystem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Specification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOperaSystem() {
        return operaSystem;
    }

    public void setOperaSystem(String operaSystem) {
        this.operaSystem = operaSystem;
    }

}
