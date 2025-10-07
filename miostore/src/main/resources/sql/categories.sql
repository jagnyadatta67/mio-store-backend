USE `miostore-dev`;

-- ===============================
-- OFFERS TABLE
-- ===============================
INSERT INTO offers
(id, title, description, offer_type, discount_percent, discount_amount, valid_from, valid_till, active, category_id, product_id, min_cart_value)
VALUES
-- 🎉 Product-specific Offers
(1, 'Foxtail Festive Offer', 'Get 10% off on Foxtail Millet variants', 'PRODUCT', 10.0, NULL,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), TRUE, NULL, 1, NULL),

(2, 'Little Millet Family Pack Offer', 'Buy 1kg or more and save 12%', 'PRODUCT', 12.0, NULL,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), TRUE, NULL, 2, NULL),

(3, 'Finger Millet Discount', 'Enjoy 15% off on all Finger Millet variants', 'PRODUCT', 15.0, NULL,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), TRUE, NULL, 3, NULL),

-- 🛒 Category-level Offers
(4, 'Millets Bonanza', 'Flat 10% off on all Millet products', 'CATEGORY', 10.0, NULL,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 20 DAY), TRUE, 2, NULL, NULL),

-- 💰 Cart-level Offer
(5, 'Cart Saver Offer', 'Get ₹100 off on cart value above ₹999', 'CART', NULL, 100.0,
 CURDATE(), DATE_ADD(CURDATE(), INTERVAL 15 DAY), TRUE, NULL, NULL, 999.00);
