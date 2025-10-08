UPDATE products
SET thumbnail_image = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-foxtail.png'
WHERE id = 1;

UPDATE products
SET thumbnail_image = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-little.png'
WHERE id = 2;

UPDATE products
SET thumbnail_image = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-hero.png'
WHERE id = 3;

UPDATE products
SET thumbnail_image = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png'
WHERE id =11;


-- Foxtail Millet
UPDATE product_variants
SET thumb_image_url = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-foxtail.png'
WHERE id IN (1, 2, 3);

-- Little Millet
UPDATE product_variants
SET thumb_image_url = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-little.png'
WHERE id IN (4, 5, 6);

-- Finger Millet
UPDATE product_variants
SET thumb_image_url = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/pack-finger.png'
WHERE id IN (7, 8, 9);
UPDATE product_variants
SET thumb_image_url = 'https://raw.githubusercontent.com/jagnyadatta67/mio/refs/heads/main/images/jasmine.png'
WHERE id IN (11, 12, 13);