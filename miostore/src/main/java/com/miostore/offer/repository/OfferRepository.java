package com.miostore.offer.repository;

import com.miostore.offer.entity.Offer;
import com.miostore.offer.entity.OfferType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByOfferTypeAndActiveTrueAndValidTillAfter(OfferType type, LocalDate date);
}
