package com.miostore.address.util;



import com.miostore.address.entity.*;
import com.miostore.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AddressCloneUtil {

    public static Address cloneForOrder(Address original, User user) {
        return clone(original, user, AddressType.ORDER_CLONE);
    }

    public static Address  cloneForCart(Address original, User user) {
        return clone(original, user, AddressType.CART_CLONE);
    }

    private static Address clone(Address original, User user, AddressType type) {
        if (original == null) return null;

        return Address.builder()
                .fullName(original.getFullName())
                .phoneNumber(original.getPhoneNumber())
                .addressLine1(original.getAddressLine1())
                .addressLine2(original.getAddressLine2())
                .city(original.getCity())
                .state(original.getState())
                .postalCode(original.getPostalCode())
                .country(original.getCountry())
                .isDefault(false)
                .isCloned(true)
                .type(type)
                .owner(null)
                .build();
    }
}
