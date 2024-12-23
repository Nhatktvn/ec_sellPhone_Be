package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.config.VnPayConfig;
import com.nhomA.mockproject.dto.GHNRequestDTO;
import com.nhomA.mockproject.dto.ItemGHNDTO;
import com.nhomA.mockproject.dto.OrderPaymentVnPayDTO;
import com.nhomA.mockproject.dto.OrderRequestDTO;
import com.nhomA.mockproject.entity.Cart;
import com.nhomA.mockproject.entity.CartLineItem;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.CartLineItemNotFoundException;
import com.nhomA.mockproject.repository.CartLineItemRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
public class VnPayController {
    private final OrderService orderService;

    private final GHNController ghnController;
    private OrderRequestDTO orderRequestDTO;
    private GHNRequestDTO ghnRequestDTO;
    private final UserRepository userRepository;
    private final CartLineItemRepository cartLineItemRepository;

    public VnPayController(OrderService orderService, GHNController ghnController, UserRepository userRepository, CartLineItemRepository cartLineItemRepository) {
        this.orderService = orderService;
        this.ghnController = ghnController;
        this.userRepository = userRepository;
        this.cartLineItemRepository = cartLineItemRepository;
    }

    @PostMapping("/pay")
    public String getPay(Authentication authentication ,@RequestParam("provinceAddress") String provinceAddress,@RequestParam("districtAddress") String districtAddress,
                         @RequestParam("wardAddress") String wardAddress, @RequestParam("name") String name, @RequestParam("phone") String phone,
                         @RequestParam("streetAddress") String streetAddress, @RequestParam("totalPrice") double totalPrice,
                         @RequestParam("couponId") Long couponId,
                         @RequestParam("fee") int fee,
                         @RequestParam("toWardCode") String toWardCode, @RequestParam("toDistrictId") int toDistrictId,
                         @RequestParam("codAmount") Long codAmount, @RequestParam("pickStationId") int pickStationId,
                         @RequestParam("serviceId") int serviceId, @RequestParam("serviceTypeId") int serviceTypeId) throws UnsupportedEncodingException{
        OrderRequestDTO orderRequestDTO1 = new OrderRequestDTO();
        orderRequestDTO1.setProvinceAddress(provinceAddress);
        orderRequestDTO1.setDistrictAddress(districtAddress);
        orderRequestDTO1.setWardAddress(wardAddress);
        orderRequestDTO1.setStreetAddress(streetAddress);
//        orderRequestDTO.setAddress(address);
        orderRequestDTO1.setFee(fee);
        orderRequestDTO1.setName(name);
        orderRequestDTO1.setPhone(phone);
        orderRequestDTO1.setCouponId(couponId);
        GHNRequestDTO ghnRequestDTO1 = new GHNRequestDTO(name,phone,districtAddress,toWardCode,toDistrictId,serviceId,serviceTypeId,codAmount);
        this.orderRequestDTO = orderRequestDTO1;
        this.ghnRequestDTO = ghnRequestDTO1;
        String username = authentication.getName();
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (long) (totalPrice*100);
//        String bankCode = "ACB";

        String vnp_TxnRef = VnPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = VnPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

//        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan hoa don: " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl + "?infoUsername=" + username);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfig.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }

    @GetMapping("payment-callback")
    public void paymentCallback(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException {
    //            orderService.orderPaymentVnPay(username, orderPaymentVnPayDTO);
        try
        {
            String username = queryParams.get("infoUsername");
            String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
            String vnp_Amount = queryParams.get("vnp_Amount");
            String vnp_BankCode = queryParams.get("vnp_BankCode");
            String vnp_TransactionNo = queryParams.get("vnp_TransactionNo");
            String vnp_OrderInfo = queryParams.get("vnp_OrderInfo");
            String vnp_SecureHash = queryParams.get("vnp_SecureHash");
            String vnp_PayDate = queryParams.get("vnp_PayDate");
            String vnp_TxnRef = queryParams.get("vnp_TxnRef");
//            String infoName = queryParams.get("infoName");
//            String infoPhone = queryParams.get("infoPhone");
////            String infoAddress = queryParams.get("infoAddressId");
//            String provinceAddress = queryParams.get("infoProvince");
//            String districtAddress = queryParams.get("infoDistrict");
//            String wardAddress = queryParams.get("infoWard");
//            String streetAddress = queryParams.get("infoStreet");
            String infoAddress = orderRequestDTO.getProvinceAddress() + ", " + orderRequestDTO.getDistrictAddress() + ", " + orderRequestDTO.getWardAddress() + ", " + orderRequestDTO.getStreetAddress();
            if (queryParams.containsKey("vnp_SecureHash"))
            {
                queryParams.remove("vnp_SecureHash");
            }
            queryParams.remove("infoUsername");
            queryParams.remove("infoName");
            queryParams.remove("infoPhone");
            queryParams.remove("infoAddress");
//            queryParams.remove("infoAddress");
//

            List fieldNames = new ArrayList(queryParams.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) queryParams.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String hashCode = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, hashData.toString());
            if (hashCode.equals(vnp_SecureHash)) {
                if ("00".equals(queryParams.get("vnp_ResponseCode"))) {
                    Optional<User> emptyUser =  userRepository.findByUsername(username);
                    User user = emptyUser.get();
                    Cart cart = user.getCart();
                    List<CartLineItem> cartLineItems = cartLineItemRepository.findByCartIdAndIsDeleted(cart.getId(), false);
                    if(cartLineItems.isEmpty()){
                        throw new CartLineItemNotFoundException("Cart line item not found!");
                    }
                    List<ItemGHNDTO> itemGHNDTOS = new ArrayList<>();
                    for (CartLineItem c : cartLineItems){
                        String name = c.getProduct().getName() + " " + c.getColor() + " " + c.getStorage();
                        ItemGHNDTO itemGHNDTO = new ItemGHNDTO(name, c.getProduct().getId().toString(),c.getQuantity(), (long)c.getSellPrice());
                        itemGHNDTOS.add(itemGHNDTO);
                    }
                    ghnRequestDTO.setItems(itemGHNDTOS);
                    ResponseEntity<?> responseGHN = ghnController.createOrder(ghnRequestDTO);
                    String orderCode = responseGHN.getBody().toString();
                    System.out.println(orderCode);
                    OrderPaymentVnPayDTO orderPaymentVnPayDTO = new OrderPaymentVnPayDTO(infoAddress, orderRequestDTO.getName() , orderRequestDTO.getPhone(), orderRequestDTO.getCouponId(), orderRequestDTO.getFee(), vnp_Amount, vnp_BankCode, vnp_TransactionNo, vnp_OrderInfo, vnp_SecureHash, vnp_PayDate, vnp_TxnRef);
                    orderService.orderPaymentVnPay(username,orderCode, orderPaymentVnPayDTO);
                    response.sendRedirect("http://localhost:3000/thanh-toan/thanh-cong");
                } else {
                    response.sendRedirect("http://localhost:3000/thanh-toan/that-bai");
                }
            }
            else
            {
                response.sendRedirect("http://localhost:3000/thanh-toan/that-bai");
            }
        }
        catch(Exception e)
        {
            response.sendRedirect("http://localhost:3000/thanh-toan/that-bai");
        }
    }
}
