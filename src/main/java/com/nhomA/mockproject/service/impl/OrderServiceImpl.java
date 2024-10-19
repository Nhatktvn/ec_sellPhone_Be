package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.OrderPaymentVnPayDTO;
import com.nhomA.mockproject.dto.OrderRequestDTO;
import com.nhomA.mockproject.dto.OrderResponseDTO;
import com.nhomA.mockproject.entity.*;
import com.nhomA.mockproject.exception.*;
import com.nhomA.mockproject.mapper.OrderMapper;
import com.nhomA.mockproject.mapper.VnPayMapper;
import com.nhomA.mockproject.repository.*;
import com.nhomA.mockproject.service.OrderService;
import com.nhomA.mockproject.util.PaginationAndSortingUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final CartLineItemRepository cartLineItemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final StatusOrderRepository statusOrderRepository;
    private final VnPayMapper vnPayMapper;
    private final ProductRepository productRepository;

    public OrderServiceImpl(AddressRepository addressRepository, UserRepository userRepository, CartLineItemRepository cartLineItemRepository, OrderRepository orderRepository, OrderMapper orderMapper, StatusOrderRepository statusOrderRepository, VnPayMapper vnPayMapper, ProductRepository productRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.cartLineItemRepository = cartLineItemRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.statusOrderRepository = statusOrderRepository;
        this.vnPayMapper = vnPayMapper;
        this.productRepository = productRepository;
    }
    @Transactional
    @Override
    public OrderResponseDTO order(String username, OrderRequestDTO orderRequestDTO) {
//        List<CartLineItem> cartLineItems = cartLineItemRepository.findByCartId(cartId);
//        Order order = new Order();
//        order.setAddress(orderRequestDTO.getAddress());
//        order.setName(orderRequestDTO.getName());
//        order.setPhoneNumber(orderRequestDTO.getPhone());
//        order.setDeliveryTime(ZonedDateTime.now());
//        order.setCartLineItems(cartLineItems);
//        double totalPriceOrder = 0;
//        for (CartLineItem c: cartLineItems){
//            totalPriceOrder += c.getTotalPrice();
//        }
//        order.setTotalPrice(totalPriceOrder);
//        orderRepository.save(order);
        Optional<User> emptyUser =  userRepository.findByUsername(username);
        User user = emptyUser.get();
        Cart cart = user.getCart();
        List<CartLineItem> cartLineItems = cartLineItemRepository.findByCartIdAndIsDeleted(cart.getId(), false);
        if(cartLineItems.isEmpty()){
            throw new CartLineItemNotFoundException("Cart line item not found!");
        }
//        Optional<Address> existedAddress = addressRepository.findByIdAndUserId(orderRequestDTO.getAddressId(),user.getId());
//        if(existedAddress.isEmpty()){
//            throw new AddressNotFoundException("Address not found!");
//        }
//        Address address = existedAddress.get();
        Order order = new Order();
        String infoAddress = orderRequestDTO.getProvinceAddress() + ", " + orderRequestDTO.getDistrictAddress() + ", " + orderRequestDTO.getWardAddress() + ", " + orderRequestDTO.getStreetAddress();
        order.setAddress(infoAddress);
        order.setName(orderRequestDTO.getName());
        order.setPhoneNumber(orderRequestDTO.getPhone());
        order.setDeliveryTime(ZonedDateTime.now());
        order.setCartLineItems(cartLineItems);
        double totalPriceOrder = 0;
        for (CartLineItem c: cartLineItems){
            totalPriceOrder += c.getSellPrice() * c.getQuantity();
        }
        order.setTotalPrice(totalPriceOrder);
        for (CartLineItem c: cartLineItems){
            c.setDeleted(true);
            c.setOrder(order);
        }
        order.setCartLineItems(cartLineItems);
        Optional<StatusOrder> statusOrder = statusOrderRepository.findById(1L);
        order.setStatusOrder(statusOrder.get());
        order.setUser(user);
        user.getOrders().add(order);
//        address.getOrders().add(order);
//        addressRepository.save(address);
        orderRepository.save(order);

        for(CartLineItem c: cartLineItems){
            Long idProduct = c.getProduct().getId();
            Optional<Product> existedProduct = productRepository.findById(idProduct);
            Product product = existedProduct.get();
            for(Variant v : c.getProduct().getVariants()){
                if(v.getColor().equals(c.getColor()) && v.getStorageCapacity().equals(c.getStorage())){
                    v.setAvailable(v.getAvailable() - c.getQuantity());
                }
            }
            productRepository.save(product);
        }
        return orderMapper.toResponseDTO(order);
    }
    @Transactional

    @Override
    public OrderResponseDTO orderPaymentVnPay(String username, OrderPaymentVnPayDTO paymentVnPayDTO) {
        Optional<User> emptyUser =  userRepository.findByUsername(username);
        User user = emptyUser.get();
        Cart cart = user.getCart();
        List<CartLineItem> cartLineItems = cartLineItemRepository.findByCartIdAndIsDeleted(cart.getId(), false);
        if(cartLineItems.isEmpty()){
            throw new CartLineItemNotFoundException("Cart line item not found!");
        }
//        Optional<Address> existedAddress = addressRepository.findByIdAndUserId(paymentVnPayDTO.getAddressId(),user.getId());
//        if(existedAddress.isEmpty()){
//            throw new AddressNotFoundException("Address not found!");
//        }
//        Address address = existedAddress.get();
        Order order = new Order();
        order.setAddress(paymentVnPayDTO.getAddress());
        order.setName(paymentVnPayDTO.getName());
        order.setPhoneNumber(paymentVnPayDTO.getPhone());
        order.setDeliveryTime(ZonedDateTime.now());
        order.setCartLineItems(cartLineItems);

        double totalPriceOrder = 0;
        for (CartLineItem c: cartLineItems){
            totalPriceOrder += c.getSellPrice() * c.getQuantity();
        }
        order.setTotalPrice(totalPriceOrder);
        for (CartLineItem c: cartLineItems){
            c.setDeleted(true);
            c.setOrder(order);
        }
        order.setCartLineItems(cartLineItems);
        Optional<StatusOrder> statusOrder = statusOrderRepository.findById(1L);
        order.setStatusOrder(statusOrder.get());
        order.setUser(user);
        user.getOrders().add(order);

//        VnPayInfo vnPayInfo = vnPayMapper.toEntity(paymentVnPayDTO);
//        order.setVnPayInfo(vnPayInfo);
        VnPayInfo vnPayInfo = new VnPayInfo();
        vnPayInfo.setVnpAmount(paymentVnPayDTO.getVnpAmount());
        vnPayInfo.setVnpBankCode(paymentVnPayDTO.getVnpBankCode());
        vnPayInfo.setVnpTransactionNo(paymentVnPayDTO.getVnpTransactionNo());
        vnPayInfo.setVnpOrderInfo(paymentVnPayDTO.getVnpOrderInfo());
        vnPayInfo.setVnpSecureHash(paymentVnPayDTO.getVnpSecureHash());
        vnPayInfo.setVnpPayDate(paymentVnPayDTO.getVnpPayDate());
        vnPayInfo.setVnpTxnRef(paymentVnPayDTO.getVnpTxnRef());
        order.setVnPayInfo(vnPayInfo);
        orderRepository.save(order);

        for(CartLineItem c: cartLineItems){
            Long idProduct = c.getProduct().getId();
            Optional<Product> existedProduct = productRepository.findById(idProduct);
            Product product = existedProduct.get();
            for(Variant v : c.getProduct().getVariants()){
                if(v.getColor().equals(c.getColor()) && v.getStorageCapacity().equals(c.getStorage())){
                    v.setAvailable(v.getAvailable() - c.getQuantity());
                }
            }
            productRepository.save(product);
        }

        return orderMapper.toResponseDTO(order);
    }

    @Transactional
    @Override
    public List<OrderResponseDTO> getAllOrder(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable= PaginationAndSortingUtils.getPageable(pageNo,pageSize,sortBy,sortDir);
        Page<Order> orders= orderRepository.findAll(pageable);
        List<Order> orderContent = orders.getContent();
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.toResponseDTOs(orderList);
    }

    @Transactional
    @Override
    public List<OrderResponseDTO> getOrderByUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        List<Order> orders = orderRepository.findByUserId(user.get().getId());
        return orderMapper.toResponseDTOs(orders);
    }
    @Transactional
    @Override
    public OrderResponseDTO setStatusOrder(Long orderId, String statusOrderName) {
        Optional<Order> emptyOrder = orderRepository.findById(orderId);
        if(emptyOrder.isEmpty()){
            throw new OrderNotFoundException("Order not found!");
        }
        Optional<StatusOrder> emptyStatusOrder = statusOrderRepository.findByName(statusOrderName);
        if(emptyStatusOrder.isEmpty()){
            throw new StatusOrderNotFoundException("Cart line item not found!");
        }
        Order order = emptyOrder.get();
        if (order.getStatusOrder().getId() == 6){
            throw new StatusOrderOnChangeException("Not Change, Status current is Da Huy");
        }
        order.setStatusOrder(emptyStatusOrder.get());
        Order saveOrder = orderRepository.save(order);
        return orderMapper.toResponseDTO(saveOrder);
    }

    @Override
    public OrderResponseDTO getDetailOrder(String username, Long orderId) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        Optional<Order> existedOrder = orderRepository.findByIdAndUserId(orderId, existedUser.get().getId());
        if(existedOrder.isEmpty()){
            throw new OrderNotFoundException("Order not found!");
        }
        Order order = existedOrder.get();
        return orderMapper.toResponseDTO(order);
    }

    @Transactional
    @Override
    public boolean cancelOrder(Long idOrder, String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        Optional<Order> existedOrder = orderRepository.findByIdAndUserId(idOrder, existedUser.get().getId());
        if(existedOrder.isEmpty()){
            throw new OrderNotFoundException("Order not found!");
        }
        Order order = existedOrder.get();
        Long idStatusCurrent = order.getStatusOrder().getId();
        if(idStatusCurrent == 1){
            Optional<StatusOrder> emptyStatusOrder = statusOrderRepository.findById(6L);
            order.setStatusOrder(emptyStatusOrder.get());
            Order saveOrder = orderRepository.save(order);
            return true;
        }
        throw new StatusOrderOnChangeException("Not Cancel, Status current is " + order.getStatusOrder().getName());
    }

    @Override
    public boolean receivedProduct(Long idOrder, String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        Optional<Order> existedOrder = orderRepository.findByIdAndUserId(idOrder, existedUser.get().getId());
        if(existedOrder.isEmpty()){
            throw new OrderNotFoundException("Order not found!");
        }
        Order order = existedOrder.get();
        Long idStatusCurrent = order.getStatusOrder().getId();
        if(idStatusCurrent == 4) {
            Optional<StatusOrder> emptyStatusOrder = statusOrderRepository.findById(5L);
            order.setStatusOrder(emptyStatusOrder.get());
            Order saveOrder = orderRepository.save(order);
            return true;
        }
        throw new StatusOrderOnChangeException("Not Change, Status current is " + order.getStatusOrder().getName());
    }
}
