ALTER TABLE order_detail
DROP COLUMN video_review;

ALTER TABLE videos
ADD COLUMN reviews text;