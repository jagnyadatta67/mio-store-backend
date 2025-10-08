package com.miostore.order.entity;

import com.miostore.address.entity.Address;
import com.miostore.common.entity.BaseEntity;
import com.miostore.offer.entity.Offer;
import com.miostore.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public abstract class AbstractOrder extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "offer_snapshot_id")
    private OfferSnapshot appliedOffer;

    private String code;
    private Double subTotal;
    private Double totalAmount;
    private Double shippingCost;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
