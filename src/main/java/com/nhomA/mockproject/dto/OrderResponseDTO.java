package com.nhomA.mockproject.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Long Id;
    private String codeOrder;
    private String address;
    private String name;
    private String phone;
    private ZonedDateTime deliveryTime;
    private double totalPrice;
    private String statusOrder;
    private VnPayResponseDTO vnPayResponseDTO;
    private List<CartLineItemResponseDTO> cartLineItemResponseDTOs;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public VnPayResponseDTO getVnPayResponseDTO() {
        return vnPayResponseDTO;
    }


    public void setVnPayResponseDTO(VnPayResponseDTO vnPayResponseDTO) {
        this.vnPayResponseDTO = vnPayResponseDTO;
    }

    public String getStatusOrder() {
        return statusOrder;
    }


    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ZonedDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(ZonedDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartLineItemResponseDTO> getCartLineItemResponseDTOs() {
        return cartLineItemResponseDTOs;
    }

    public void setCartLineItemResponseDTOs(List<CartLineItemResponseDTO> cartLineItemResponseDTOs) {
        this.cartLineItemResponseDTOs = cartLineItemResponseDTOs;
    }
}
