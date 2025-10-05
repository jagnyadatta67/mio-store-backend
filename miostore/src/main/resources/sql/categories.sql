-- Super category
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (1, 'Food', 'food', 'All food items', NULL, NOW(), NOW());

-- Sub-category under Food
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (2, 'Millets', 'millets', 'Millet-based products', 1, NOW(), NOW());

-- Sub-subcategory under Millets
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (3, 'Snacks', 'snacks', 'Millet snack items', 2, NOW(), NOW());

-- Another category
INSERT INTO categories (id, name, slug, description, parent_id, created_at, updated_at)
VALUES (4, 'Healthy Drinks', 'healthy-drinks', 'Natural millet-based drinks', 1, NOW(), NOW());
