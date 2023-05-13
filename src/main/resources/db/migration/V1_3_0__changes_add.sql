ALTER TABLE videos
ADD sold int not null;

ALTER TABLE order_detail
ADD video_review text;

