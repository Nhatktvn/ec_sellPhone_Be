package com.nhomA.mockproject.service;

import com.nhomA.mockproject.entity.StatusOrder;
import com.nhomA.mockproject.repository.StatusOrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class StatusOrderService {
    private final StatusOrderRepository statusOrderRepository;

    public StatusOrderService(StatusOrderRepository statusOrderRepository) {
        this.statusOrderRepository = statusOrderRepository;
    }
    @PostConstruct
    public void init(){
        StatusOrder statusOrder1 = new StatusOrder();
        statusOrder1.setName("Đang xác nhận");
        statusOrderRepository.save(statusOrder1);
        StatusOrder statusOrder2 = new StatusOrder();
        statusOrder1.setName("Đang tiến hàng đóng gói");
        statusOrderRepository.save(statusOrder2);
        StatusOrder statusOrder3 = new StatusOrder();
        statusOrder1.setName("Đang chờ vận chuyển");
        statusOrderRepository.save(statusOrder3);
        StatusOrder statusOrder4 = new StatusOrder();
        statusOrder1.setName("Đang vận chuyển");
        statusOrderRepository.save(statusOrder4);
        StatusOrder statusOrder5 = new StatusOrder();
        statusOrder1.setName("Đã nhận được hàng");
        statusOrderRepository.save(statusOrder5);
        StatusOrder statusOrder6 = new StatusOrder();
        statusOrder1.setName("Đã hủy");
        statusOrderRepository.save(statusOrder6);

    }
}
