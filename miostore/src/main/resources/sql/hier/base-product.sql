USE `mio_local`;


INSERT INTO products (id, description, brand, rating, currency, sku, ean, created_at, updated_at,thumbnail_image)
VALUES
(1, 'Organic and fiber-rich Foxtail Millet, perfect for a healthy lifestyle.', 'MiO Organics', 4.7, 'INR', 'MIL-FT', 'EAN-FT-001', NOW(), NOW(),'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-foxtail.png'),
(2, 'High in iron and calcium, Little Millet is great for kids and adults alike.', 'MiO Organics', 4.6, 'INR', 'MIL-LT', 'EAN-LT-002', NOW(), NOW(),'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-little.png'),
(3, 'Rich in calcium and minerals, Finger Millet (Ragi) supports strong bones.', 'MiO Organics', 4.8, 'INR', 'MIL-FN', 'EAN-FN-003', NOW(), NOW(),'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-little.png');


-- ===============================
-- PRODUCT VARIANTS
-- ===============================
INSERT INTO product_variants
(id, name, sku, ean, unit_label, color, quantity, price, sale_price, thumb_image_url, product_id)
VALUES
-- Foxtail Millet
(1, 'Foxtail Millet 0.5kg', 'MIL-FT-001-0.5KG', 'EAN-FT-001A', '0.5kg', 'Natural', 200, 70.00, 65.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-foxtail.png', 1),

(2, 'Foxtail Millet 1kg', 'MIL-FT-001-1KG', 'EAN-FT-001B', '1kg', 'Natural', 150, 120.00, 99.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-foxtail.png', 1),

(3, 'Foxtail Millet 1.5kg', 'MIL-FT-001-1.5KG', 'EAN-FT-001C', '1.5kg', 'Natural', 100, 150.00, 130.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-foxtail.png', 1),


-- Little Millet
(4, 'Little Millet 0.5kg', 'MIL-LT-002-0.5KG', 'EAN-LT-002A', '0.5kg', 'Natural', 180, 65.00, 60.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-little.png', 2),

(5, 'Little Millet 1kg', 'MIL-LT-002-1KG', 'EAN-LT-002B', '1kg', 'Natural', 160, 110.00, 95.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-little.png', 2),

(6, 'Little Millet 1.5kg', 'MIL-LT-002-1.5KG', 'EAN-LT-002C', '1.5kg', 'Natural', 100, 145.00, 125.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-little.png', 2),

-- Finger Millet
(7, 'Finger Millet 0.5kg', 'MIL-FN-003-0.5KG', 'EAN-FN-003A', '0.5kg', 'Natural', 190, 55.00, 49.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-hero.png', 3),

(8, 'Finger Millet 1kg', 'MIL-FN-003-1KG', 'EAN-FN-003B', '1kg', 'Natural', 150, 95.00, 85.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-hero.png', 3),

(9, 'Finger Millet 1.5kg', 'MIL-FN-003-1.5KG', 'EAN-FN-003C', '1.5kg', 'Natural', 100, 130.00, 115.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-hero.png', 3);



 -- ==========================================
-- Foxtail Millet Images
-- ==========================================
INSERT INTO product_variant_images (variant_sku, image_url) VALUES
('MIL-FT-001-0.5KG', 'https://www.themiostore.com/images/foxtail-0.5kg-1.png'),
('MIL-FT-001-0.5KG', 'https://www.themiostore.com/images/foxtail-0.5kg-2.png'),
('MIL-FT-001-0.5KG', 'https://www.themiostore.com/images/foxtail-0.5kg-3.png'),

('MIL-FT-001-1KG', 'https://www.themiostore.com/images/foxtail-1kg-1.png'),
('MIL-FT-001-1KG', 'https://www.themiostore.com/images/foxtail-1kg-2.png'),
('MIL-FT-001-1KG', 'https://www.themiostore.com/images/foxtail-1kg-3.png'),

('MIL-FT-001-1.5KG', 'https://www.themiostore.com/images/foxtail-1.5kg-1.png'),
('MIL-FT-001-1.5KG', 'https://www.themiostore.com/images/foxtail-1.5kg-2.png'),
('MIL-FT-001-1.5KG', 'https://www.themiostore.com/images/foxtail-1.5kg-3.png');

-- ==========================================
-- Little Millet Images
-- ==========================================
INSERT INTO product_variant_images (variant_sku, image_url) VALUES
('MIL-LT-002-0.5KG', 'https://www.themiostore.com/images/little-0.5kg-1.png'),
('MIL-LT-002-0.5KG', 'https://www.themiostore.com/images/little-0.5kg-2.png'),
('MIL-LT-002-0.5KG', 'https://www.themiostore.com/images/little-0.5kg-3.png'),

('MIL-LT-002-1KG', 'https://www.themiostore.com/images/little-1kg-1.png'),
('MIL-LT-002-1KG', 'https://www.themiostore.com/images/little-1kg-2.png'),
('MIL-LT-002-1KG', 'https://www.themiostore.com/images/little-1kg-3.png'),

('MIL-LT-002-1.5KG', 'https://www.themiostore.com/images/little-1.5kg-1.png'),
('MIL-LT-002-1.5KG', 'https://www.themiostore.com/images/little-1.5kg-2.png'),
('MIL-LT-002-1.5KG', 'https://www.themiostore.com/images/little-1.5kg-3.png');

-- ==========================================
-- Finger Millet Images
-- ==========================================
INSERT INTO product_variant_images (variant_sku, image_url) VALUES
('MIL-FN-003-0.5KG', 'https://www.themiostore.com/images/finger-0.5kg-1.png'),
('MIL-FN-003-0.5KG', 'https://www.themiostore.com/images/finger-0.5kg-2.png'),
('MIL-FN-003-0.5KG', 'https://www.themiostore.com/images/finger-0.5kg-3.png'),

('MIL-FN-003-1KG', 'https://www.themiostore.com/images/finger-1kg-1.png'),
('MIL-FN-003-1KG', 'https://www.themiostore.com/images/finger-1kg-2.png'),
('MIL-FN-003-1KG', 'https://www.themiostore.com/images/finger-1kg-3.png'),

('MIL-FN-003-1.5KG', 'https://www.themiostore.com/images/finger-1.5kg-1.png'),
('MIL-FN-003-1.5KG', 'https://www.themiostore.com/images/finger-1.5kg-2.png'),
('MIL-FN-003-1.5KG', 'https://www.themiostore.com/images/finger-1.5kg-3.png');


-- Super category (Food)
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (1, 'Food', 'food', 'All food items', NULL, NOW(), NOW());

-- Sub-category under Food
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (2, 'Millets', 'millets', 'Millet-based products', 1, NOW(), NOW());

-- Sub-subcategory under Millets
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (3, 'Snacks', 'snacks', 'Millet snack items', 2, NOW(), NOW());

-- Another category under Food
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (4, 'Healthy Drinks', 'healthy-drinks', 'Natural millet-based drinks', 1, NOW(), NOW());



INSERT INTO product_categories (product_id, category_id)
VALUES
(1, 1),
(2, 1),
(3, 1);
