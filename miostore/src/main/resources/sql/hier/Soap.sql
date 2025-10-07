INSERT INTO products
(id, description, brand, rating, currency, sku, ean, created_at, updated_at)
VALUES
(
  11,
  'Gentle Jasmine Soap infused with natural jasmine essence — refreshing, aromatic, and perfect for soft, healthy skin.',
  'MiO Organics',
  4.8,
  'INR',
  'SOAP-JASMINE',
  'EAN-SOAP-JASMINE-001',
  NOW(),
  NOW()
);


INSERT INTO product_variants
(id, name, sku, ean, unit_label, color, quantity, price, sale_price, thumb_image_url, product_id)
VALUES
-- Jasmine Soap 75g
(11, 'Jasmine Soap 75g', 'SOAP-JASMINE-001-75G', 'EAN-SOAP-JASMINE-001A', '75g', 'White', 300, 45.00, 40.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png', 11),

-- Jasmine Soap 100g
(12, 'Jasmine Soap 100g', 'SOAP-JASMINE-001-100G', 'EAN-SOAP-JASMINE-001B', '100g', 'White', 250, 60.00, 55.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png', 11),

-- Jasmine Soap 150g
(13, 'Jasmine Soap 150g', 'SOAP-JASMINE-001-150G', 'EAN-SOAP-JASMINE-001C', '150g', 'White', 200, 85.00, 75.00,
 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png', 11);

INSERT INTO product_variant_images (variant_sku, image_url) VALUES
('SOAP-JASMINE-001-75G',  'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png'),
('SOAP-JASMINE-001-100G', 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png'),
('SOAP-JASMINE-001-150G', 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png');


-- 🌿 Main Category
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES
(10, 'Personal Care', 'personal-care', 'Products for body, skin, and daily care', NULL, NOW(), NOW());

-- 🧼 Subcategory under Personal Care
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES
(11, 'Bath & Body', 'bath-body', 'Natural soaps, body washes, and hygiene essentials', 10, NOW(), NOW());


INSERT INTO product_categories (product_id, category_id)
VALUES
(11, 11);
