ALTER TABLE videos
ADD rating float not null,
ADD update_date timestamp not null;

ALTER TABLE orders
DROP COLUMN paymentStatus,
DROP COLUMN paymentDate;

ALTER TABLE order_detail
ADD COLUMN paymentStatus varchar(15) not null,
ADD COLUMN paymentDate timestamp not null;