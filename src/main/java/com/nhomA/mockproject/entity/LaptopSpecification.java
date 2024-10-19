package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "laptop_specification")
public class LaptopSpecification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "screen")
    private String screen;
    @Column(name = "cpu")
    private String cpu;
    @Column(name = "gpu")
    private String gpu;
    @Column(name = "ram")
    private String ram;
    @Column(name = "rom")
    private String rom;
    @Column(name = "battery")
    private String battery;
    @Column(name = "opera_system")
    private String operaSystem;
    @Column(name = "weight")
    private String weight;
    @Column(name = "ports")
    private String ports;
    @Column(name = "additional_features")
    private String additionalFeatures;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public LaptopSpecification() {
    }

    public LaptopSpecification(Long id, String screen, String cpu, String gpu, String ram, String rom, String battery, String operaSystem, String weight, String ports, String additionalFeatures, Product product) {
        this.id = id;
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
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
