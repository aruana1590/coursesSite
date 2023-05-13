create table videos (

id serial primary key,
courseName varchar(1000) not null,
section varchar(500) not null,
author varchar(200) not null,
courseInfo varchar(2000) not null,
price float not null
);

create table users (
id serial primary key,
username varchar(100) not null unique,
password varchar(100) not null
);

create table orders (
id serial primary key,
user_id int not null,
paymentStatus varchar(15) not null,
paymentDate timestamp not null,
foreign key (user_id) references users(id)
);
create table order_detail (
id serial primary key,
order_id int not null,
video_id int not null,
price float not null,
foreign key (order_id) references orders(id),
foreign key (video_id) references videos(id)
);