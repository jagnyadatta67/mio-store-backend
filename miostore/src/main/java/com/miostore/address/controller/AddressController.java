package com.miostore.address.controller;


import com.miostore.address.dto.AddressRequest;
import com.miostore.address.dto.AddressResponse;
import com.miostore.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping()
    public AddressResponse addAddress(@RequestBody AddressRequest request) {
        return addressService.addAddress( request);
    }
    @GetMapping
    public List<AddressResponse> getAddress() {
        return addressService.getAllUserAddresses();
    }

    @GetMapping("/{userId}")
    public List<AddressResponse> getUserAddresses(@PathVariable Long userId) {
        return addressService.getUserAddresses(userId);
    }

    @GetMapping("/default/{userId}")
    public AddressResponse getDefaultAddress(@PathVariable Long userId) {
        return addressService.getDefaultAddress(userId);
    }
}
