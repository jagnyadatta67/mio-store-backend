package com.miostore.order.entity;

import com.miostore.address.entity.Address;
import com.miostore.common.entity.BaseEntity;
import com.miostore.offer.entity.Offer;
import com.miostore.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractOrder  extends BaseEntity {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdAt", column = @Column(name = "address_created_at")),
            @AttributeOverride(name = "updatedAt", column = @Column(name = "address_updated_at"))
    })
    private OrderAddress shippingAddress;
    private String code;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdAt", column = @Column(name = "offer_created_at")),
            @AttributeOverride(name = "updatedAt", column = @Column(name = "offer_updated_at"))
    })
    private OfferSnapshot appliedOffer;
    private Double subTotal;
    private Double totalAmount;;
    private Double shippingCost;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // ✅ shared user for both Cart and Order


}
