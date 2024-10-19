package com.nhomA.mockproject.dto;

public class UpdateStatusDTO {
    private Long orderId;
    private String statusOrderName;

    public UpdateStatusDTO(Long orderId, String statusOrderName) {
        this.orderId = orderId;
        this.statusOrderName = statusOrderName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatusOrderName() {
        return statusOrderName;
    }

    public void setStatusOrderName(String statusOrderName) {
        this.statusOrderName = statusOrderName;
    }
}
