-- Millet Mix belongs to Millets
INSERT INTO product_categories (product_id, category_id) VALUES (101, 2);

-- Millet Energy Bar belongs to both Millets and Snacks
INSERT INTO product_categories (product_id, category_id) VALUES (102, 2);
INSERT INTO product_categories (product_id, category_id) VALUES (102, 3);

-- Ragi Malt Drink belongs to Healthy Drinks
INSERT INTO product_categories (product_id, category_id) VALUES (103, 4);
