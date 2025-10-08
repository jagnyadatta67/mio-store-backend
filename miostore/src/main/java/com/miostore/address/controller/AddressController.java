package com.miostore.address.controller;


import com.miostore.address.dto.AddressRequest;
import com.miostore.address.dto.AddressResponse;
import com.miostore.address.entity.Address;
import com.miostore.address.service.AddressService;
import com.miostore.auth.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private SessionService sessionService;

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

    /**
     * 🗑 Delete an address (only if it belongs to the current user)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(
            @PathVariable Long id) {
        addressService.deleteAddress(id,sessionService.getCurrentUser());
        return ResponseEntity.ok("Address deleted successfully");
    }
}
