package com.miostore.address.service;


import com.miostore.address.dto.AddressRequest;
import com.miostore.address.dto.AddressResponse;
import com.miostore.address.entity.Address;
import com.miostore.address.repository.AddressRepository;
import com.miostore.auth.SessionService;
import com.miostore.user.entity.User;
import com.miostore.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService ;

    public AddressResponse addAddress(AddressRequest request) {

        User user =sessionService.getCurrentUser();

        // If address isDefault = true, unset others
        if (request.isDefault()) {
            addressRepository.findByUser(user).forEach(addr -> {
                addr.setDefault(false);
                addressRepository.save(addr);
            });
        }

        Address address = Address.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .postalCode(request.getPostalCode())
                .country(request.getCountry())
                .isDefault(request.isDefault())
                .user(user)
                .build();

        Address saved = addressRepository.save(address);

        return mapToResponse(saved);
    }

    public List<AddressResponse> getAllUserAddresses() {
          User user=sessionService.getCurrentUser();

        return addressRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public List<AddressResponse> getUserAddresses(Long userId) {
        User user=sessionService.getCurrentUser();

        return addressRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AddressResponse getDefaultAddress(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressRepository.findByUserAndIsDefaultTrue(user)
                .orElseThrow(() -> new RuntimeException("Default address not found"));
        return mapToResponse(address);
    }

    private AddressResponse mapToResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .fullName(address.getFullName())
                .phoneNumber(address.getPhoneNumber())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .isDefault(address.isDefault())
                .build();
    }
}
