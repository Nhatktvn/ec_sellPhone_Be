package com.nhomA.mockproject.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhomA.mockproject.dto.GHNRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

@RestController
@RequestMapping("/api")
public class GHNController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create-order-ghn")
    public ResponseEntity<?> createOrder (@RequestBody GHNRequestDTO ghnRequestDTO){
        String url = "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("ShopId", "192022");
        headers.set("token", "b02b4d89-018f-11ef-a6e6-e60958111f48");
        Gson gson = new Gson();
        String jsonItem = gson.toJson(ghnRequestDTO.getItems());
        String stringData = "{\n" +
                "                                    \"payment_type_id\": 1,\n" +
                "                                    \"note\": \"Tintest 123\",\n" +
                "                                    \"required_note\": \"CHOXEMHANGKHONGTHU\",\n" +
                "                                    \"from_name\": \"MinhNhatPhone\",\n" +
                "                                    \"from_phone\": \"0378025713\",\n" +
                "                                    \"from_address\": \"Vinhome Grand Park Quận 9, Tp.Hồ Chí Minh, Việt Nam\",\n" +
                "                                    \"from_ward_name\": \"Phường Long Thạnh Mỹ\",\n" +
                "                                    \"from_district_name\": \"Thành phố Thủ Đức\",\n" +
                "                                    \"from_province_name\": \"HCM\",\n" +
                "                                    \"return_phone\": \"0378025713\",\n" +
                "                                    \"return_address\": \"Vinhome Grand Park Quận 9, Tp.Hồ Chí Minh, Việt Nam\",\n" +
                "                                    \"return_district_id\": null,\n" +
                "                                    \"return_ward_code\": \"\",\n" +
                "                                    \"client_order_code\": \"\",\n" +
                "                                    \"to_name\": \""+ghnRequestDTO.getToName()+"\",\n" +
                "                                    \"to_phone\": \""+ghnRequestDTO.getToPhone()+"\",\n" +
                "                                    \"to_address\": \""+ghnRequestDTO.getToAddress()+"\",\n" +
                "                                    \"to_ward_code\": \""+ghnRequestDTO.getToWardCode()+"\",\n" +
                "                                    \"to_district_id\": "+ghnRequestDTO.getToDistrictId()+",\n" +
                "                                    \"cod_amount\": 0,\n" +
                "                                    \"content\": \"Theo New York Times\",\n" +
                "                                    \"weight\": 1000,\n" +
                "                                    \"length\": 20,\n" +
                "                                    \"width\": 20,\n" +
                "                                    \"height\": 50,\n" +
                "                                    \"pick_station_id\": "+ghnRequestDTO.getToDistrictId()+",\n" +
                "                                    \"deliver_station_id\": null,\n" +
                "                                    \"insurance_value\": 0,\n" +
                "                                    \"service_id\": "+ghnRequestDTO.getServiceId()+",\n" +
                "                                    \"service_type_id\": "+ghnRequestDTO.getServiceTypeId()+",\n" +
                "                                    \"coupon\":null,\n" +
                "                                    \"pick_shift\":[2],\n" +
                "                                    \"items\": "+jsonItem+"\n" +
                "                                }";
        HttpEntity<String> request = new HttpEntity<>(stringData, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        Type listType = new TypeToken<Object>() {}.getType();
        Object responseJson = gson.fromJson(response.getBody(), listType) ;
        return new ResponseEntity<>(stringData, HttpStatus.OK);
    }
}
