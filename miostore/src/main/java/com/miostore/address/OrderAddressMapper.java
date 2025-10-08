package com.miostore.address;

import com.miostore.address.entity.Address;
import com.miostore.order.entity.OrderAddress;
import com.miostore.address.dto.AddressRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderAddressMapper {

    /**
     * ✅ Converts embedded OrderAddress → AddressRequest (for DTO)
     */
    public static AddressRequest toDTO(Address address) {
        if (address == null) return null;

        return AddressRequest.builder()
                .fullName(address.getFullName())
                .phoneNumber(address.getPhoneNumber())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

    /**
     * ✅ Converts AddressRequest → OrderAddress (for entity snapshot)
     */
    public static OrderAddress fromDTO(AddressRequest dto) {
        if (dto == null) return null;

        return OrderAddress.builder()
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .build();
    }

    /**
     * ✅ Converts AddressRequest → OrderAddress (for entity snapshot)
     */
    public static Address fromCustomerAddress(Address dto) {
        if (dto == null) return null;

        return Address.builder()
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .build();
    }




}